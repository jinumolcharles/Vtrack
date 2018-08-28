package com.codeleven.vtrack;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.codeleven.vtrack.adapter.viewalladapter;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by CodelevenPC on 13-Jul-18.
 */

public class View_all_activity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> deallist;
    ArrayList<String> imagelist;
    ArrayList<Integer> dealistimage;
    RecyclerView.Adapter mAdaptertwo;
    TextView tittle;
    private List<mainmodel>images;
    mainmodel allTripFeed;
    private ArrayList<dealmodel> viewalldeal;
    String a,b,c,d;
    String im;
    int g;
    dealmodel all;
    String approverEmpId, bs;
    private static Shareactivity session;
    private Intent intent;

    private dealmodel values;
    dealmodel model =new dealmodel();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_activity);

//        getAllTrip();

//        getslugname();
         new getAllTrip().execute();

//         a=viewalldeal.get(0).getId();

//        session = new Shareactivity(View_all_activity.this);
//        HashMap<String, String> model = new Shareactivity(View_all_activity.this).getEmployeeDetails();
//        approverEmpId = model.get(Shareactivity.IMAGEID);
//        bs = model.get(Shareactivity.SLUGNAME);
//        Intent c = new Intent();
//        c.putExtra("imagename",approverEmpId.toString());
//        c.putExtra("slugnma",bs.toString());
//        imagelist = new ArrayList<>(Arrays.asList("1615","1592","1652","1584","1516","1502","1495","1490","1513","1487"
//        ));
//        viewalldeal = new ArrayList<>();
//        listing();
//        dealistimage = new ArrayList<>(Arrays.asList(R.drawable.brandfactorynew, R.drawable.concepttyres,R.drawable.carpalaceedit,R.drawable.rittunew,
//                R.drawable.img,R.drawable.variety,  R.drawable.mrabakery,
//                R.drawable.archiesgallery,
//                R.drawable.opt,
//                R.drawable.kadosh,
//                R.drawable.varietymall,
//                R.drawable.gearedit,
//                R.drawable.dreslyn,R.drawable.miss,
//                R.drawable.shoplittle,R.drawable.goldsgym,R.drawable.babas,R.drawable.ayraa
//        ));

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        tittle = (TextView) findViewById(R.id.textone);
        tittle.setText("PERMIUM PARTNERS");
        mRecyclerView.setHasFixedSize(true);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(gridLayoutManager);


//        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, );
//        mRecyclerView.setLayoutManager(mLayoutManager);

    }
    private void setAdapter() {
        mAdaptertwo = new viewalladapter(View_all_activity.this, images);
        mRecyclerView.setAdapter(mAdaptertwo);
    }




    private class getAllTrip extends AsyncTask<Void, Void, Void> {


        protected void onProgressUpdate(Integer... progress) {
        }

        @Override
        protected Void doInBackground(Void... params) {
            Htteprequest sh = new Htteprequest();
            String url = "https://ownshopz.com/wp-json/wp/v2/deal?per_page=50";
            String jsonStr = sh.makeServiceCall(url);


            viewalldeal = new ArrayList<dealmodel>();

            if (jsonStr != null) {

                try {
                    JSONArray tripArray = new JSONArray(jsonStr);
                    for (int t = 0; t < tripArray.length(); t++) {
                        JSONObject trpObj = tripArray.getJSONObject(t);

                        dealmodel model = new dealmodel();
                        model.setId(trpObj.getString("image"));
                        model.setSlugname(trpObj.getString("slug"));
//                        allTripFeed.setId(trpObj.getString("id"));
                        viewalldeal.add(model);
                        g = viewalldeal.size();

                    }

                } catch (final JSONException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

           listing();

        }
    }

    public void listing() {



        viewalldeal = new ArrayList<dealmodel>();

        String[] months = {"1615","1592","1652","1584","1495","1490","1513","1487","1516","1502","1615","1592","1652","1584","1495","1490","1513","1487","1516","1502"};


        ArrayList<String> listMonth = new ArrayList<>();
        Collections.addAll(listMonth,months);


//        dealmodel model = new dealmodel();
        for (int i = 0; i < listMonth.size(); i++) {
             im=listMonth.get(i);
            getslugname();


        }


    }


    public void getslugname() {

        images = new ArrayList<>();
        Ion.with(View_all_activity.this)
                .load("https://ownshopz.com/api/get_img/")
                .setTimeout(6000)
                .setMultipartParameter("id",im)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception error, JsonObject result) {
                        if (error == null) {
                            try {
                                JSONObject resObj = new JSONObject(result.toString());
//                                for (int t = 0; t < resObj.length(); t=t+2) {
                                    mainmodel allTripFeed = new mainmodel();
                                    allTripFeed.setImage(resObj.getString("result"));
                                    allTripFeed.setImagename(resObj.getString("id"));
                                    images.add(allTripFeed);

                                    setAdapter();

//                                }
                            }

                            catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {

//                            Common.ShowHttpErrorMessage(Banquet_Activities.this, error.getMessage());
                        }
                    }
                });



    }




}
