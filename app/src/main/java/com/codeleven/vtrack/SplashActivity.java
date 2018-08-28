package com.codeleven.vtrack;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by CodelevenPC on 04-Jul-18.
 */

public class SplashActivity extends AppCompatActivity {

    String currentVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashactivity);
//        Fabric.with(this, new Crashlytics());

        try {
            currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        new GetVersionCode().execute();
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {
            ex.printStackTrace();
        }



        Log.d("gps_enabled", "gps_enabled = " + gps_enabled);
//        if(!gps_enabled) {
//            AlertDialog.Builder dialog = new AlertDialog.Builder(SplashActivity.this);
//            dialog.setTitle("Improve location accurancy?");
//            dialog.setMessage("This app wants to change your device setting:");
//            dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                    // TODO Auto-generated method stub
//                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                    startActivityForResult(myIntent, 1);
//                    //get gps
//                }
//            });
//            dialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                    finish();
//                }
//            });
//            dialog.show();
//        }else{
//            String id = Common.getPreferenceString(SplashActivity.this,"id");
//            if(!id.equals("")){
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (isNetworkAvailable(SplashActivity.this)) {
//                            Intent i = new Intent(SplashActivity.this,HomeActivity.class);
//                            startActivity(i);
//                            SplashActivity.this.finish();
//                        } else {
//                            showInternetInfo(SplashActivity.this, "");
//                            new Handler().postDelayed(this, 3000);
//                        }
//                    }
//                }, 3000);
//
//            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      //  if (isNetworkAvailable(SplashActivity.this)) {
                            //if (!Common.getPreferenceString(SplashActivity.this, "termsAndCondition").equals("true")) {
//                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                                finish();
                           // } else {
                            //    startActivity(new Intent(SplashActivity.this, LoginOptionActivity.class));
                              //  SplashActivity.this.finish();
                           // }
                        //} else {
                        //    showInternetInfo(SplashActivity.this, "");
                           // new Handler().postDelayed(this, 3000);
                        //}
                    }
                }, 6000);
//            }
        }

    public static boolean isNetworkAvailable(Activity act){

        ConnectivityManager connMgr = (ConnectivityManager)act.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            return true;
        } else {
            // display error
            return false;
        }

    }

    class GetVersionCode extends AsyncTask<Void, String, String> {

        @Override

        protected String doInBackground(Void... voids) {

            String newVersion = null;

            try {
                Document document = Jsoup.connect("https://play.google.com/store/apps/details?id=" + SplashActivity.this.getPackageName()  + "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get();
                if (document != null) {
                    Elements element = document.getElementsContainingOwnText("Current Version");
                    for (Element ele : element) {
                        if (ele.siblingElements() != null) {
                            Elements sibElemets = ele.siblingElements();
                            for (Element sibElemet : sibElemets) {
                                newVersion = sibElemet.text();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newVersion;

        }


        @Override

        protected void onPostExecute(String onlineVersion) {

//            onlineVersion="19.0";

            super.onPostExecute(onlineVersion);

            if (onlineVersion != null && !onlineVersion.isEmpty()) {

                if (Float.valueOf(currentVersion) < Float.valueOf(onlineVersion)) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SplashActivity.this);
                    alertDialogBuilder.setTitle("Do you want to update your application?");
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                                            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.codeleven.vtrack"));
                                            startActivity(intent);
                                        }
                                    })

                            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                    finish();
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }

            }
//            Toast.makeText(getApplicationContext(),  "Current version " + currentVersion + "playstore version " + onlineVersion, Toast.LENGTH_LONG).show();
            Log.d("update", "Current version " + currentVersion + "playstore version " + onlineVersion);

        }
    }

    }


