package com.codeleven.vtrack.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codeleven.vtrack.Helpers.OnItemClickHelperInterface;
import com.codeleven.vtrack.Htteprequest;
import com.codeleven.vtrack.MainActivity;
import com.codeleven.vtrack.R;
import com.codeleven.vtrack.adapter.view_all_asso;
import com.codeleven.vtrack.model.associatedatamodel;
import com.codeleven.vtrack.model.associatemodel;
import com.codeleven.vtrack.networkhandlers.OkHttpNetworkInterface;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodelevenPC on 16-Jul-18.
 */

public class View_all_asso_activity extends AppCompatActivity  implements OkHttpNetworkInterface, OnItemClickHelperInterface, NavigationView.OnNavigationItemSelectedListener {

    RecyclerView mRecyclerView;
    DrawerLayout drawer;
    ArrayList<String> deallist;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Integer> associatepartnerlist;
    RecyclerView.Adapter mAdaptertwo;

    BottomNavigationView bottomNavigationView;
    private ImageView loadNext, loadprevious,showmenu;
    TextView tittle;
    Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
//        associatepartnerlist = new ArrayList<>(Arrays.asList(
//                R.drawable.dff,
//                R.drawable.fgf, R.drawable.ayra, R.drawable.frrdg, R.drawable.gdfg, R.drawable.gdg,
//                R.drawable.hfjk,R.drawable.gfdg,R.drawable.gfg,R.drawable.gvfg,R.drawable.im,
//                R.drawable.partnerim,R.drawable.partnerimg
//                ,R.drawable.rgtg
//        ));

        new getlogos().execute();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        loadNext = (ImageView) findViewById(R.id.view_all_activity_loadnext);
        loadprevious = (ImageView) findViewById(R.id.view_all_activity_loadPrevious);
        mRecyclerView.setHasFixedSize(true);
        toolbar = (Toolbar) findViewById(R.id.toolbars);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        showmenu = (ImageView) findViewById(R.id.show);

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        tittle = findViewById(R.id.textone);
//        tittle.setText("ASSOCIATE PARTNERS");
        loadNext.setVisibility(View.GONE);
        loadprevious.setVisibility(View.GONE);
        showmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer.openDrawer(navigationView);

            }
        });

//        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(gridLayoutManager);

//        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(
//                new BottomNavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.navigation_shop:
//                                Intent i = new Intent(View_all_asso_activity.this, MainActivity.class);
//                                startActivity(i);
//                                break;
//                            case R.id.navigation_cart:
//                                break;
//                            case R.id.navigation_profile:
//                                String urls = "https://ownshopz.com/about-us/";
//                                Intent is = new Intent(View_all_asso_activity.this, Virutal_Activity.class);
//                                is.setData(Uri.parse(urls));
//                                startActivity(is);
//                                break;
//                        }
//                        return false;
//                    }
//                });
//        mAdaptertwo = new view_all_asso(View_all_asso_activity.this,associatepartnerlist);
//        mRecyclerView.setAdapter(mAdaptertwo);


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
    public void onResponseReceived(String data, String requestedURL) throws JSONException {

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
            Intent i = new Intent(View_all_asso_activity.this, MainActivity.class);
            startActivity(i);

        }  else if (id == R.id.navigation_profile) {
            String urls = "https://ownshopz.com/about-us/";
            Intent is = new Intent(View_all_asso_activity.this, Virutal_Activity.class);
            is.setData(Uri.parse(urls));
            startActivity(is);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class getlogos extends AsyncTask<Void, Void, Void> {
        List<associatedatamodel> dataasso;
        protected void onProgressUpdate(Integer... progress) {
        }
        @Override
        protected Void doInBackground(Void... params) {
            Htteprequest sh = new Htteprequest();
//            String url = "http://192.168.1.103/ownapi/index.php/welcome/get_associate";
            String url ="https://ownshopz.com/ownapi/index.php/welcome/get_associate";
            final String jsonStr = sh.makeServiceCall(url);
            if (jsonStr != null) {
                associatemodel a = new Gson().fromJson(jsonStr, associatemodel.class);
                dataasso = a.getData();
                Log.i("response", dataasso.toString());
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
            view_all_asso adapterdeal;
            adapterdeal = new view_all_asso(View_all_asso_activity.this, (ArrayList<associatedatamodel>) dataasso, View_all_asso_activity.this);
            mRecyclerView.setAdapter(adapterdeal);
        }
    }
}
