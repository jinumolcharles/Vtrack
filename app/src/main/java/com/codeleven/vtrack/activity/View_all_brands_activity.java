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
import com.codeleven.vtrack.adapter.viewallbrandadapter;
import com.codeleven.vtrack.model.brandsdatamodel;
import com.codeleven.vtrack.model.brandsmodel;
import com.codeleven.vtrack.networkhandlers.OkHttpNetworkInterface;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodelevenPC on 16-Jul-18.
 */

public class View_all_brands_activity extends AppCompatActivity implements OkHttpNetworkInterface, OnItemClickHelperInterface, NavigationView.OnNavigationItemSelectedListener {

    RecyclerView mRecyclerView;
    ArrayList<String> deallist;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Integer> logolist;
    RecyclerView.Adapter mAdaptertwo;
    BottomNavigationView bottomNavigationView;
    TextView tittle;
    Toolbar toolbar;
    DrawerLayout drawer;
    private ImageView loadNext, loadprevious,showmenu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

//        logolist = new ArrayList<>(Arrays.asList(R.drawable.pumma,R.drawable.levis, R.drawable.adidas,
//                R.drawable.images, R.drawable.download, R.drawable.pizza, R.drawable.nikepng,
//                R.drawable.skybag,R.drawable.loreal));

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        mRecyclerView.setHasFixedSize(true);
//        tittle = findViewById(R.id.textone);
//        tittle.setText("BRANDS");
        loadNext = (ImageView) findViewById(R.id.view_all_activity_loadnext);
        loadprevious = (ImageView) findViewById(R.id.view_all_activity_loadPrevious);
        toolbar = (Toolbar) findViewById(R.id.toolbars);
        showmenu = (ImageView) findViewById(R.id.show);

        loadNext.setVisibility(View.GONE);
        loadprevious.setVisibility(View.GONE);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        showmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer.openDrawer(navigationView);

            }
        });
        new getbrands().execute();


//        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(gridLayoutManager);

//        mAdaptertwo = new viewallbrandadapter(View_all_brands_activity.this,logolist);
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
            Intent i = new Intent(View_all_brands_activity.this, MainActivity.class);
            startActivity(i);

        } else if (id == R.id.navigation_profile) {
            String urls = "https://ownshopz.com/about-us/";
            Intent is = new Intent(View_all_brands_activity.this, Virutal_Activity.class);
            is.setData(Uri.parse(urls));
            startActivity(is);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private class getbrands extends AsyncTask<Void, Void, Void> {
        List<brandsdatamodel> brands;
        protected void onProgressUpdate(Integer... progress) {
        }
        @Override
        protected Void doInBackground(Void... params) {
            Htteprequest sh = new Htteprequest();
            String url = "https://ownshopz.com/ownapi/index.php/welcome/get_brand";
            final String jsonStr = sh.makeServiceCall(url);
            if (jsonStr != null) {
                brandsmodel a = new Gson().fromJson(jsonStr, brandsmodel.class);
                brands = a.getData();
                Log.i("response", brands.toString());
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
            viewallbrandadapter adapterdeal;
            adapterdeal = new viewallbrandadapter(View_all_brands_activity.this, (ArrayList<brandsdatamodel>) brands, View_all_brands_activity.this);
            mRecyclerView.setAdapter(adapterdeal);

//            brandadapter adapterdeal;
//            adapterdeal = new brandadapter(View_all_brands_activity.this, (ArrayList<brandsdatamodel>) brands, View_all_brands_activity.this);
//            mRecyclerView.setAdapter(adapterdeal);
        }
    }


}

