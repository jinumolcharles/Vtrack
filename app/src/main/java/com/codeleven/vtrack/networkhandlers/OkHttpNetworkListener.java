package com.codeleven.vtrack.networkhandlers;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import dmax.dialog.SpotsDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

//import org.jetbrains.annotations.NotNull;

/**
 * Created by sunil.js on 11/17/2017.
 * <p>
 * dependancies in gradle
 * <p>
 * compile 'com.squareup.okhttp3:okhttp:3.9.0'
 * compile 'com.google.code.gson:gson:2.8.1'
 */

public class OkHttpNetworkListener {

    private Context context;
    private OkHttpNetworkInterface okHttpNetworkInterface;
    private String requestType = null;
    private String url = null;
    private String requestData = null;

    private OkHttpClient okHttpClient;
    private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private String USER_TOKEN = "";


    private HttpUrl.Builder urlBuilder = null;
    private Logger logger;

    private AlertDialog alertDialog = null;

    private boolean isBasicAuthEnabled = false;
    private boolean isProgressBarNeeded = false;


    /**
     * if basic authentication needed enable set flag as true
     */
    public void setBasicAuthEnabled(boolean basicAuthEnabled) {
        isBasicAuthEnabled = basicAuthEnabled;
    }

    /**
     * set message that is to be shown in progress bar
     *
     * @param progressBarMessage message to be shown
     */
    public void setProgressBarMessage(String progressBarMessage) {
        alertDialog = new SpotsDialog(context, progressBarMessage);
        isProgressBarNeeded = true;

    }


    public OkHttpNetworkListener(Context context, OkHttpNetworkInterface okHttpNetworkInterface) {
        this.context = context;
        this.okHttpNetworkInterface = okHttpNetworkInterface;


        //time out also set for the requests
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).build();

        logger = LoggerFactory.getLogger(OkHttpNetworkListener.class);

    }


    /**
     * initialise OkHttp connection
     * progress is also started
     *
     * @param requestType type of the request whether GET, POST
     * @param URL         API url.
     * @param dataObject  Object of the model class
     */
    public void startServerRequest(com.codeleven.vtrack.networkhandlers.OkkHttpRequestType requestType, String URL, Object dataObject) {

        this.requestType = requestType.name();
        url = URL;

       // logger.info("received {}", this.requestType);
      //  logger.info("received URL {}", this.url);
        if (checkAllData()) {

            if (isProgressBarNeeded)
                //start progress
                alertDialog.show();

            if (requestType.equals(com.codeleven.vtrack.networkhandlers.OkkHttpRequestType.GET)) {

                initialiseGETrequest();

            } else if (requestType.equals(com.codeleven.vtrack.networkhandlers.OkkHttpRequestType.POST)) {
                this.requestData = dataObject instanceof JSONObject ? ((JSONObject) dataObject).toString() : new Gson().toJson(dataObject).toString();
                okHttpNetworkInterface.onCommentsReceived("received data " + requestData, url);
                initialisePostRequest();
            }

        } else {
            okHttpNetworkInterface.onNetworkError("Parameters not valid", url);
        }
    }


    /**
     * HANDLE GET REQUEST TO SERVER
     */
    private void initialiseGETrequest() {

        if (urlBuilder != null) {
            url = urlBuilder.build().toString();
        } else {
            okHttpNetworkInterface.onCommentsReceived("uri builder is null", url);
        }


        Builder builder = new Builder();
        builder.url(url);
        builder.addHeader("Content-Type", "application/json");
        builder.addHeader("cache-control", "no-cache");

        if (!USER_TOKEN.equals("")) {
            builder.addHeader("Authorization", USER_TOKEN);
          //  logger.info("user token set {}", USER_TOKEN);

        } else {
            logger.info("user token not set");
        }
        Request request = null;

        //if basic auth neeeded then only enabled
        if (isBasicAuthEnabled) {
//            //set up basic authentication along with the request
//            final String login = API.USERNAME;
//            final String password = API.PASSWORD;
//            String credential = Credentials.basic(login, password);
//            request = builder.header("Authorization", credential).build();
        } else {
            request = builder.build();
        }


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (isProgressBarNeeded)
                    alertDialog.dismiss();
               try {
                   String mMessage = e.getMessage().toString();
                   logger.error(mMessage);
                   okHttpNetworkInterface.onNetworkError(getErrorCodeResponse(404), url);
               }catch (Exception newError){
                   newError.printStackTrace();
               }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (isProgressBarNeeded)
                    alertDialog.dismiss();

                if (!response.isSuccessful()) {
                    okHttpNetworkInterface.onNetworkError(getErrorCodeResponse(response.code()), url);
                    logger.error("{} Unexpected code " + response, url);
                } else {
                    Headers responseHeaders = response.headers();
                    for (int i = 0; i < responseHeaders.size(); i++) {
                     //   logger.info(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    String responseMessage = response.body().string();
                  //  logger.info(url + ": " + responseMessage);

                    try {


                        okHttpNetworkInterface.onResponseReceived(responseMessage, url);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        logger.error("json exception", e);
                        okHttpNetworkInterface.onNetworkError(e.getMessage(), url);
                    }
                }
            }
        });


    }

    /**
     * handle post request to server
     */
    private void initialisePostRequest() {
        RequestBody body = RequestBody.create(MEDIA_TYPE,
                requestData);

        Builder builder = new Builder()

                .url(url);
        builder.addHeader("Content-Type", "application/json");

        if (!requestData.equals("") && !requestData.equals(null)) {
            builder.post(body);
        } else {
            okHttpNetworkInterface.onCommentsReceived("no data available in request body", url);
        }

        if (!USER_TOKEN.equals("")) {
            builder.addHeader("Authorization", USER_TOKEN);
           // logger.info("user token set {}", USER_TOKEN);

        } else {
            logger.info("user token not set ");
        }

        builder.addHeader("cache-control", "no-cache");


        Request request = null;
        //if basic auth neeeded then only enabled
        if (isBasicAuthEnabled) {
//            //set up basic authentication along with the request
//            final String login = API.USERNAME;
//            final String password = API.PASSWORD;
//            String credential = Credentials.basic(login, password);
//            request = builder.header("Authorization", credential).build();
        } else {
            request = builder.build();
        }

        Log.d("request", request.toString());
        Headers requestHeaders = request.headers();
        for (int i = 0; i < requestHeaders.size(); i++) {
          //  logger.info("request header  {}", requestHeaders.name(i) + ": " + requestHeaders.value(i));
        }

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (isProgressBarNeeded)
                    alertDialog.dismiss();

                String mMessage = e.getMessage().toString();
                okHttpNetworkInterface.onNetworkError(getErrorCodeResponse(404), url);
                logger.error("response error {}", e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (isProgressBarNeeded)
                    alertDialog.dismiss();

                if (!response.isSuccessful()) {
                    okHttpNetworkInterface.onNetworkError(getErrorCodeResponse(response.code()), url);
                } else {

                    Headers responseHeaders = response.headers();
                    for (int i = 0; i < responseHeaders.size(); i++) {
                        Log.d("DEBUG", responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    String responseMessage = null;
                    try {
                        responseMessage = response.body().string();
                       // logger.info("response {}", responseMessage);

                        okHttpNetworkInterface.onResponseReceived(responseMessage, url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        logger.error("response error", e);
                        okHttpNetworkInterface.onCommentsReceived(e.toString(), url);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        logger.error("json exception in response", e);
                        okHttpNetworkInterface.onCommentsReceived(e.toString(), url);

                    }
                }


            }
        });
    }


    /**
     * send mutipart request with multiple data
     *
     * @param formField fields to to send
     * @param filePart  file
     * @param url       URL of the API
     */
    public void startMulipartRequest(final String url,
                                     @Nullable ArrayMap<String, String> formField,
                                     @Nullable ArrayMap<String, File> filePart)
            throws Exception {

        if (isProgressBarNeeded)
            //start progress
            alertDialog.show();


        okhttp3.Request.Builder requestBuilder = new okhttp3.Request.Builder().url(url);
        if (formField != null || filePart != null) {
            okhttp3.MultipartBody.Builder multipartBodyBuilder = new okhttp3.MultipartBody.Builder();
            multipartBodyBuilder.setType(okhttp3.MultipartBody.FORM);
            if (formField != null) {
                for (Map.Entry<String, String> entry : formField.entrySet()) {
                    multipartBodyBuilder.addFormDataPart(entry.getKey(), entry.getValue());
                }
            }

            if (filePart != null) {
                for (Map.Entry<String, File> entry : filePart.entrySet()) {
                    File file = entry.getValue();
                    multipartBodyBuilder.addFormDataPart(
                            entry.getKey(),
                            file.getName(),
                            okhttp3.RequestBody.create(getMediaType(file.toURI()), file)
                    );
                }
            }
            requestBuilder.post(multipartBodyBuilder.build());
        }
        Request request = requestBuilder.build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                alertDialog.dismiss();
                String mMessage = e.getMessage().toString();
                okHttpNetworkInterface.onNetworkError("Unable to connect to server", url);
                logger.error("response error {}", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                alertDialog.dismiss();

                if (!response.isSuccessful()) {
                    okHttpNetworkInterface.onNetworkError("Internal server error", url);
                    throw new IOException(response.message());
                } else {

                    String responseMessage = null;
                    try {
                        responseMessage = response.body().string();
                  //      logger.info("response {}", responseMessage);

                        okHttpNetworkInterface.onResponseReceived(responseMessage, url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        logger.error("response error", e);
                        okHttpNetworkInterface.onNetworkError(e.toString(), url);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        logger.error("json exception in response", e);
                        okHttpNetworkInterface.onNetworkError(e.toString(), url);

                    }
                }

            }
        });


    }

    /**
     * send mutipart request with single data
     *
     * @param data     fields to to send
     * @param filePart file
     * @param url      URL of the API
     */
    public void startMulipartRequest( final String url,
                                     String data,
                                     @Nullable File filePart)
            throws Exception {

        if (isProgressBarNeeded)
            //start progress
            alertDialog.show();

        okhttp3.Request.Builder requestBuilder = new okhttp3.Request.Builder().url(url);
        okhttp3.MultipartBody.Builder multipartBodyBuilder = new okhttp3.MultipartBody.Builder();
        okHttpNetworkInterface.onCommentsReceived("received data " + data, url);
        if (data != null) {
            multipartBodyBuilder.setType(okhttp3.MultipartBody.FORM);

            multipartBodyBuilder.addFormDataPart("data", data);

        } else {
            okHttpNetworkInterface.onCommentsReceived("no data send", url);

        }

        if (filePart != null) {
            multipartBodyBuilder.addFormDataPart(
                    "file",
                    filePart.getName(),
                    RequestBody.create(MediaType.parse("multipart/form-data"), filePart)
            );

        }

        requestBuilder.post(multipartBodyBuilder.build());

        Request request = requestBuilder.build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                alertDialog.dismiss();
                String mMessage = e.getMessage().toString();
                okHttpNetworkInterface.onNetworkError("Unable to connect to server", url);
                logger.error("response error {}", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    okHttpNetworkInterface.onNetworkError("I/O exception from server", url);
                    throw new IOException(response.message());
                } else {

                    String responseMessage = null;
                    try {
                        responseMessage = response.body().string();
                      //  logger.info("response {}", responseMessage);

                        okHttpNetworkInterface.onResponseReceived(responseMessage, url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        logger.error("response error", e);
                        okHttpNetworkInterface.onNetworkError(e.toString(), url);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        logger.error("json exception in response", e);
                        okHttpNetworkInterface.onNetworkError(e.toString(), url);

                    }
                }

            }
        });


    }

    /**
     * get the media type for request
     */
    private okhttp3.MediaType getMediaType(URI uri1) {
        Uri uri = Uri.parse(uri1.toString());
        String mimeType;
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            ContentResolver cr = context.getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        return okhttp3.MediaType.parse(mimeType);
    }


    /**
     * check whether all data received as parameters are vaild
     */
    private boolean checkAllData() {
        boolean isValid = false;
        if (okHttpNetworkInterface.equals(null)) {
            okHttpNetworkInterface.onNetworkError("interface referance cannot be null", url);
        } else if (!requestType.equals("GET") && !requestType.equals("POST")) {
            okHttpNetworkInterface.onNetworkError("Invalid request type ", url);
        } else if (url.equals("") || url.equals(null)) {
            okHttpNetworkInterface.onNetworkError("URL cannot be empty", url);
        } else {
            isValid = true;
        }


        return isValid;
    }

    /**
     * set user token if available
     */
    public void setUserToken(String testToken) {

        this.USER_TOKEN = testToken;
    }

    /**
     * url builder for get request to pass query parameters
     * referance : https://www.journaldev.com/13629/okhttp-android-example-tutorial
     *
     * @param urlBuilder object of a url builder
     */
    public void setUrlBuilder(HttpUrl.Builder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }


    /**
     * Handle network errors with codes
     *
     * @param code error code received
     */
    private String getErrorCodeResponse(int code) {
        String errorName = null;
        switch (code) {
            case 404:
                errorName = "Connection failed";
                break;
            case 400:
                errorName = "Bad request. Invalid username/password";
                break;
            default:
                errorName = "Unknown error code received " + code;
                break;
        }

        return errorName;

    }

    /**
     * stop all network requests
     */
    public void stopNetwork() {

        okHttpClient.dispatcher().cancelAll();

    }

}
