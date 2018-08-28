package com.codeleven.vtrack.networkhandlers;

import org.json.JSONException;

/**
 * Created by sunil.js on 11/17/2017.
 */

public interface OkHttpNetworkInterface  {

    /**
     * get post response data
    */
    void onResponseReceived(String data, String requestedURL) throws JSONException;



    /**
     * send error details
    */
    void onNetworkError(String errorDetail, String requestedURL);

    /**
     * pass comments through the interface
    */
    void onCommentsReceived(String comment, String URL);
}
