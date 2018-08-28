package com.codeleven.vtrack;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codeleven.vtrack.Helpers.OnItemClickHelperInterface;
import com.codeleven.vtrack.activity.Virutal_Activity;
import com.codeleven.vtrack.model.locationdata;
import com.codeleven.vtrack.model.locationmodel;
import com.codeleven.vtrack.model.virtualdata;
import com.codeleven.vtrack.model.virtualmodel;
import com.codeleven.vtrack.networkhandlers.OkHttpNetworkInterface;
import com.codeleven.vtrack.networkhandlers.OkHttpNetworkListener;
import com.codeleven.vtrack.networkhandlers.OkkHttpRequestType;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.codeleven.vtrack.R.id.codeimage;
import static com.codeleven.vtrack.R.id.couponcode;
import static com.codeleven.vtrack.R.id.expandedImage;
import static com.codeleven.vtrack.R.id.map;
import static com.codeleven.vtrack.R.id.tit;
import static com.codeleven.vtrack.R.id.txtclose;

/**
 * Created by CodelevenPC on 04-Jul-18.
 */

public class View_deal_activity extends FragmentActivity implements Html.ImageGetter,OkHttpNetworkInterface, OnItemClickHelperInterface, NavigationView.OnNavigationItemSelectedListener ,OnMapReadyCallback, GoogleMap.OnCameraChangeListener {

    ImageView image,dealimag,mainimg;
    TextView timervalue,title,des,buy,discount;
    LinearLayout deg;

    private GoogleMap mMap;
    private List<mainmodel> deallist;
    private List<mainmodel> viewalldeal;
    String a,b,c,d,titlea,hii;
    String str;
    private final String main_URL = "https://ownshopz.com/ownapi/index.php/welcome/link360/";
    String nameValue;
    String text;
    WebView content;
    String noHTMLString,cod,sss;
    String brand,url,discoutvalue,Content,viewvalue,redeemvalue,date;
    Dialog myDialog;
    ImageView showmenu;
    TextView redeem, mviews;
    List<virtualdata> datasroyal;
    locationdata loction;
    private OkHttpNetworkListener networkListener;
//    BottomNavigationView bottomNavigationView;
DrawerLayout drawer;
    static LatLng MY_LOCATION;
    SupportMapFragment mapFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_deal_ativity);
        blink(image);
        title = findViewById(R.id.title);
        content = findViewById(R.id.des);
//        loct = findViewById(R.id.location);
        timervalue = findViewById(R.id.timer);
        buy = findViewById(R.id.buynow);
        dealimag = findViewById(R.id.img);
        mainimg = findViewById(expandedImage);
        discount = findViewById(R.id.discount);
        redeem = findViewById(R.id.timesredeem);
        mviews = findViewById(R.id.mviews);
        deg = findViewById(R.id.deg);

        brand = getIntent().getStringExtra("brandfactory");
        url= getIntent().getStringExtra("img");
        discoutvalue = getIntent().getStringExtra("discoutvalue");
        myDialog = new Dialog(this);
        discount.setText(discoutvalue);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        showmenu =(ImageView) findViewById(R.id.show);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        showmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                drawer.openDrawer(navigationView);

            }
        });

         mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getView().setVisibility(View.GONE);

        mapFragment.getMapAsync(this);

//        GoogleMapView googleMapView = (GoogleMapView) findViewById(R.id.googleMapView);
//        googleMapView.setLatitude(8.518021699999998f);
//        googleMapView.setLongitude(76.94476299999997f);
//        googleMapView.setMapType(MapType.ROADMAP);
//        googleMapView.setMapScale(MapScale.HIGH);
//        googleMapView.setMapZoom(15);
//        googleMapView.setMapWidth(250);
//        googleMapView.setMapHeight(250);
//        googleMapView.setLocation(location);
//        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Hello Maps ");
//
//// adding marker
//        googleMap.addMarker(marker);

//        googleMapView.setZoomable(this);


        Picasso.with(View_deal_activity.this)
                .load(url).resize(500,500)
                .into(mainimg);

        getAllTrip();
        getcopuncode();
        getdiscountvalue();
        getviews();
        getredeem();
        getexpire();
//        getlocation();
        new getvirutal().execute();


        networkListener = new OkHttpNetworkListener(this, this);
        networkListener.startServerRequest(OkkHttpRequestType.GET, main_URL+ brand,null);


        buy.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView texttitle;
                TextView textcode;
                TextView close;
                ImageView imge;
                ImageView qrcode;
                myDialog.setContentView(R.layout.popup);
                close =(TextView) myDialog.findViewById(txtclose);
                texttitle =(TextView) myDialog.findViewById(tit);
                textcode =(TextView) myDialog.findViewById(couponcode);
                qrcode =(ImageView) myDialog.findViewById(R.id.qrcode);
                imge = myDialog.findViewById(codeimage);
                close.setText("X");
                texttitle.setText(a);
                Picasso.with(View_deal_activity.this)
                        .load(url).resize(500,500)
                        .into(imge);
                if (nameValue==null){
                    textcode.setText("No voucher code");

                }else{
                    String replaced = nameValue.replaceAll("[\\[\\]\"]", "");
                    textcode.setText(replaced);

                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try {
                        BitMatrix bitMatrix = multiFormatWriter.encode(replaced, BarcodeFormat.QR_CODE,500,500);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        qrcode.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }


                }

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();


            }
        });
    }

    public void blink(View view) {
        image = (ImageView) findViewById(R.id.blink);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        image.startAnimation(animation1);
    }
    public void  getAllTrip() {
        deallist = new ArrayList<>();
        Ion.with(View_deal_activity.this)
                .load("https://ownshopz.com/api/get_post")
                .setTimeout(6000)
                .setMultipartParameter("deal", brand)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onCompleted(Exception error, JsonObject result) {
                        if (error == null) {
                            try {
                                JSONObject resObj = new JSONObject(result.toString());
                                if (resObj.getString("status").equals("ok")) {
                                    JSONObject tripArray = new JSONObject(resObj.getString("post"));
                                    for (int t = 0; t < tripArray.length(); t++) {
                                        mainmodel allTripFeed = new mainmodel();
                                        allTripFeed.setId(tripArray.getString("id"));
//                                        allTripFeed.setType(tripArray.getString("type"));
                                        allTripFeed.setSlugname(tripArray.getString("slug"));
                                        allTripFeed.setTitile(tripArray.getString("title"));
                                        allTripFeed.setDescription(tripArray.getString("content"));
//                                        allTripFeed.setDate(tripArray.getString("date"));
//                                        allTripFeed.setCoupon(tripArray.getString("custom_fields"));
                                        a = allTripFeed.getTitile();
                                        b = allTripFeed.getDescription();
//                                        c = allTripFeed.getDate();
//                                        d =allTripFeed.getId();


                                        noHTMLString = b.replaceAll("\\<.*?\\>\"\\\\+\"", "");
                                        cod = noHTMLString.replace("&nbsp;","");
                                        titlea = a.replaceAll("&#8217;S","");
                                        hii= titlea.replaceAll("&#8217;s","");
//                                        hii= titlea.replaceAll("+","");
//                                         str = hii.replaceAll("[^\\p{ASCII}]", "");

//                                        str = URLEncoder.encode(hii,"UTF-8");
                                        text = hii.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");





                                        deallist.add(allTripFeed);

                                    }
                                    if (b != null) {
                                        content.loadDataWithBaseURL(null, "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + b, "text/html", "UTF-8", null);
                                    }else{
                                        content.setVisibility(View.GONE);
                                    }
                                    title.setText(Html.fromHtml(text , Html.FROM_HTML_MODE_LEGACY));

//                                    Date currentDate = new Date();
//                                    SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
//                                    Date d1 = null;
//                                    try {
//                                        d1 = format.parse(c);
//                                        long diff = currentDate.getTime() - d1.getTime();
//
//                                        new CountDownTimer(diff, 1000) {
//                                            public void onTick(long diff) {
//                                                int second = (int) (diff / 1000% 60);
//                                                int minutes = (int) ((diff / (1000 * 60)) % 60);
//                                                int hours = (int) ((diff / (1000 * 60 * 60)) % 24);
//                                                int days = (int) (diff / (1000 * 60 * 60 * 24));
//                                                timervalue.setText(String.format("%02d", days) + "days "
//                                                        + String.format("%02d", hours)
//                                                        + ":" + String.format("%02d", minutes)
//                                                        + ":" + String.format("%02d", second));
//                                            }
//                                            @Override
//                                            public void onFinish() {
//
//                                            }
//                                        }.start();
//
//
//                                    }
//                                    catch (ParseException e) {
//                                        e.printStackTrace();
//                                    }



                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {

                        }
                    }
                });

    }



    @Override
    public Drawable getDrawable(String s) {


        return null;
    }





    public void  getcopuncode() {

        deallist = new ArrayList<>();

        Ion.with(View_deal_activity.this)
                .load("https://ownshopz.com/api/get_post")
                .setTimeout(6000)
                .setMultipartParameter("deal", brand)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception error, JsonObject result) {
                        if (error == null) {
                            try {
                                JSONObject resObj = new JSONObject(result.toString());
                                if (resObj.getString("status").equals("ok")) {
                                    JSONObject tripArray = new JSONObject(resObj.getString("post"));
                                        mainmodel allTripFeed = new mainmodel();

                                        JSONObject code = new JSONObject(tripArray.getString("custom_fields"));
                                        JSONArray codeArray = new JSONArray(code.getString("coupon_code"));
//                                         c= code.getString("expiring_date");

                                         nameValue = code.getString("coupon_code");
//                                         str = URLEncoder.encode(nameValue,"UTF-8");
//                                         discoutvalue = code.getString("discount_value");
                                         deallist.add(allTripFeed);
//                                         discount.setText(discoutvalue);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {

                        }
                    }
                });

    }

    public void  getviews() {

        deallist = new ArrayList<>();

        Ion.with(View_deal_activity.this)
                .load("https://ownshopz.com/api/get_post")
                .setTimeout(6000)
                .setMultipartParameter("deal", brand)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception error, JsonObject result) {
                        if (error == null) {
                            try {
                                JSONObject resObj = new JSONObject(result.toString());
                                if (resObj.getString("status").equals("ok")) {
                                    JSONObject tripArray = new JSONObject(resObj.getString("post"));
                                    mainmodel allTripFeed = new mainmodel();

                                    JSONObject code = new JSONObject(tripArray.getString("custom_fields"));
                                    JSONArray codeArray = new JSONArray(code.getString("ssd_post_views_count"));
//                                         c= code.getString("expiring_date");
//                                    nameValue = code.getString("coupon_code");
                                    viewvalue = code.getString("ssd_post_views_count");
                                    deallist.add(allTripFeed);
                                    String replaced = viewvalue.replaceAll("[\\[\\]\"]", "");
                                    mviews.setText(replaced+"  VIEWS");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {

                        }
                    }
                });
    }


    public void  getredeem() {

        deallist = new ArrayList<>();

        Ion.with(View_deal_activity.this)
                .load("https://ownshopz.com/api/get_post")
                .setTimeout(6000)
                .setMultipartParameter("deal", brand)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception error, JsonObject result) {
                        if (error == null) {
                            try {
                                JSONObject resObj = new JSONObject(result.toString());
                                if (resObj.getString("status").equals("ok")) {
                                    JSONObject tripArray = new JSONObject(resObj.getString("post"));
                                    mainmodel allTripFeed = new mainmodel();

                                    JSONObject code = new JSONObject(tripArray.getString("custom_fields"));
                                    JSONArray codeArray = new JSONArray(code.getString("ssd_post_views_count"));
//                                         c= code.getString("expiring_date");
//                                    nameValue = code.getString("coupon_code");
                                    redeemvalue = code.getString("ssd_post_button_clicks_count");
                                    deallist.add(allTripFeed);
                                    String replaced = redeemvalue.replaceAll("[\\[\\]\"]", "");
                                    redeem.setText(replaced+"  TIMES REDEEMED");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {

                        }
                    }
                });
    }


    public void  getexpire() {

        deallist = new ArrayList<>();

        Ion.with(View_deal_activity.this)
                .load("https://ownshopz.com/api/get_post")
                .setTimeout(6000)
                .setMultipartParameter("deal", brand)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception error, JsonObject result) {
                        if (error == null) {
                            try {
                                JSONObject resObj = new JSONObject(result.toString());
                                if (resObj.getString("status").equals("ok")) {
                                    JSONObject tripArray = new JSONObject(resObj.getString("post"));
                                    mainmodel allTripFeed = new mainmodel();

                                    JSONObject code = new JSONObject(tripArray.getString("custom_fields"));
                                    JSONArray codeArray = new JSONArray(code.getString("expiring_date"));
//                                         c= code.getString("expiring_date");
//                                    nameValue = code.getString("coupon_code");
                                    date = code.getString("expiring_date");
                                    deallist.add(allTripFeed);
                                    String replaced = date.replaceAll("[\\[\\]\"]", "");
//                                    mviews.setText(replaced+"  VIEWS");

                                    Date currentDate = new Date();
//                                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                                    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//                                    Date d1 = null;
                                    try {
//                                        d1 = formatter.parse(replaced);
                                        Date d1 = (Date) formatter.parse(replaced);
                                        long diff =d1.getTime()- currentDate.getTime();

                                        new CountDownTimer(diff, 1000) {
                                            public void onTick(long diff) {
                                                int second = (int) (diff / 1000% 60);
                                                int minutes = (int) ((diff / (1000 * 60)) % 60);
                                                int hours = (int) ((diff / (1000 * 60 * 60)) % 24);
                                                int days = (int) (diff / (1000 * 60 * 60 * 24));
                                                timervalue.setText(String.format("%02d", days) + "days "
                                                        + String.format("%02d", hours)
                                                        + ":" + String.format("%02d", minutes)
                                                        + ":" + String.format("%02d", second));
                                            }
                                            @Override
                                            public void onFinish() {

                                            }
                                        }.start();


                                    }
                                    catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {

                        }
                    }
                });
    }

    public void  getdiscountvalue() {

        deallist = new ArrayList<>();

        Ion.with(View_deal_activity.this)
                .load("https://ownshopz.com/api/get_post")
                .setTimeout(6000)
                .setMultipartParameter("deal", brand)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception error, JsonObject result) {
                        if (error == null) {
                            try {
                                JSONObject resObj = new JSONObject(result.toString());
                                if (resObj.getString("status").equals("ok")) {
                                    JSONObject tripArray = new JSONObject(resObj.getString("post"));
                                    mainmodel allTripFeed = new mainmodel();

                                    JSONObject code = new JSONObject(tripArray.getString("custom_fields"));
                                    JSONArray codeArray = new JSONArray(code.getString("discount_value"));
//                                         c= code.getString("expiring_date");
//                                    nameValue = code.getString("coupon_code");
                                    discoutvalue = code.getString("discount_value");
                                    deallist.add(allTripFeed);
                                    String replaced = discoutvalue.replaceAll("[\\[\\]\"]", "");
                                    discount.setText(replaced);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {

                        }
                    }
                });
    }

    @Override
    public void onItemSelcted(Object data, String brandName) {

    }

    /**
     * get post response data
     *
     * @param data
     * @param requestedURL
     */
    @Override
    public void onResponseReceived(final String data, String requestedURL) throws JSONException {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final virtualmodel a = new Gson().fromJson(data, virtualmodel.class);

                if(a.getStatus().equals("error")){

                    deg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String test = a.getStatus();

                            switch (test) {
                                case "error":
                                    AlertDialog alertDialog = new AlertDialog.Builder(View_deal_activity.this).create();
                                    alertDialog.setTitle("Alert");
                                    alertDialog.setMessage("No 360 degree available");
                                    alertDialog.show();
                                    break;
                                case "":
                                    AlertDialog alertDialogs = new AlertDialog.Builder(View_deal_activity.this).create();
                                    alertDialogs.setTitle("Alert");
                                    alertDialogs.setMessage("No 360 degree available");
                                    alertDialogs.show();
                                    break;
                                default:
                                    String urla = datasroyal.get(0).getLink();
                                    Intent is = new Intent(Intent.ACTION_VIEW);
                                    is.setData(Uri.parse(urla));
                                    startActivity(is);
                            }

                        }
                    });
                }else {
                    datasroyal = a.getData();
                    deg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String test = datasroyal.get(0).getLink();
                            switch (test) {
                                case "null":
                                    AlertDialog alertDialog = new AlertDialog.Builder(View_deal_activity.this).create();
                                    alertDialog.setTitle("Alert");
                                    alertDialog.setMessage("No 360 degree available");
                                    alertDialog.show();
                                    break;
                                case "":
                                    AlertDialog alertDialogs = new AlertDialog.Builder(View_deal_activity.this).create();
                                    alertDialogs.setTitle("Alert");
                                    alertDialogs.setMessage("No 360 degree available");
                                    alertDialogs.show();
                                    break;
                                default:
                                    String urla = datasroyal.get(0).getLink();
                                    Intent is = new Intent(Intent.ACTION_VIEW);
                                    is.setData(Uri.parse(urla));
                                    startActivity(is);
                            }

                        }
                    });

                }
//                Log.i("response", cat.toString());
            }
        });

    }

    /**
     * send error details
     *
     * @param errorDetail
     * @param requestedURL
     */
    @Override
    public void onNetworkError(String errorDetail, String requestedURL) {

    }

    /**
     * pass comments through the interface
     *
     * @param comment
     * @param URL
     */
    @Override
    public void onCommentsReceived(String comment, String URL) {

    }

    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navigation_shop) {
            Intent i = new Intent(View_deal_activity.this, MainActivity.class);
            startActivity(i);

        }  else if (id == R.id.navigation_profile) {
            String urls = "https://ownshopz.com/about-us/";
            Intent is = new Intent(View_deal_activity.this, Virutal_Activity.class);
            is.setData(Uri.parse(urls));
            startActivity(is);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        LatLng sydney = new LatLng(-8.518021699999998, 76.94476299999997);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(8.5241, 76.9366), 8);
        mMap.animateCamera(cameraUpdate);
        mMap.getMaxZoomLevel();
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(10);
        mMap.moveCamera(cameraUpdate);
        mMap.animateCamera(zoom);
        LatLng center = mMap.getCameraPosition().target;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().isZoomGesturesEnabled();
        mMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        mMap.setIndoorEnabled(true);
        mMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }


    public void  getlocation() {

        Ion.with(View_deal_activity.this)
                .load("https://ownshopz.com/ownapi/index.php/welcome/getlatlon?slug=")
                .setTimeout(6000)
                .setMultipartParameter("deal", brand)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception error, JsonObject result) {
//                        if (error == null) {
                        locationmodel a = new Gson().fromJson(result, locationmodel.class);
                        if (a.getStatus().equals("error")){
                            errsss = a.getStatus();
                        }else{
                            loction = a.getData();
                            double latitude = Double.parseDouble(loction.getLat());
                            double longitude = Double.parseDouble(loction.getLng());
                            String lat =loction.getLat();
                            String longt =loction.getLng();
                            String adder =loction.getAddress();
                            MY_LOCATION = new LatLng(latitude,longitude);
                            mMap.addMarker(new MarkerOptions().position(MY_LOCATION).title("MY LOCATION").snippet(adder)
                                    .draggable(true));
                        }

//                        } else {
//
//                        }
                    }
                });
    }
    String errsss;
    private class getvirutal extends AsyncTask<Void, Void, Void> {


        protected void onProgressUpdate(Integer... progress) {
        }

        @Override
        protected Void doInBackground(Void... params) {
            Htteprequest sh = new Htteprequest();
            String url=  "https://ownshopz.com/ownapi/index.php/welcome/getlatlon?slug="+brand;
//            String url = "http://192.168.1.115/ownapi/index.php/welcome/link360/"+brand;
            final String jsonStr = sh.makeServiceCall(url);
            if (jsonStr != null) {
                locationmodel a = new Gson().fromJson(jsonStr, locationmodel.class);

                if (a.getStatus().equals("error")){

                    errsss = a.getStatus();
//                    Log.i("response", loction.toString());
                }else{
                    loction = a.getData();

                }

//                Log.i("response", loction.toString());

            } else {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!"+jsonStr,
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (loction==(null)){
                mapFragment.getView().setVisibility(View.GONE);
                }else {
                mapFragment.getView().setVisibility(View.VISIBLE);
                double latitude = Double.parseDouble(loction.getLat());
                double longitude = Double.parseDouble(loction.getLng());
                String lat =loction.getLat();
                String longt =loction.getLng();
                String adder =loction.getAddress();
                MY_LOCATION = new LatLng(latitude,longitude);
                mMap.addMarker(new MarkerOptions().position(MY_LOCATION).title("MY LOCATION").snippet(adder)
                        .draggable(true));
                }


        }
    }









}