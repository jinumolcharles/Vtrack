package com.codeleven.vtrack.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeleven.vtrack.Helpers.OnItemClickHelperInterface;
import com.codeleven.vtrack.MainActivity;
import com.codeleven.vtrack.R;
import com.codeleven.vtrack.adapter.ViewallCatAdpter;
import com.codeleven.vtrack.model.Catmodel;
import com.codeleven.vtrack.model.GalleryResponse;
import com.codeleven.vtrack.model.datacat;
import com.codeleven.vtrack.networkhandlers.OkHttpNetworkInterface;
import com.codeleven.vtrack.networkhandlers.OkHttpNetworkListener;
import com.codeleven.vtrack.networkhandlers.OkkHttpRequestType;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodelevenPC on 16-Jul-18.
 */

public class view_all_cat_activity extends AppCompatActivity implements OkHttpNetworkInterface,OnItemClickHelperInterface, NavigationView.OnNavigationItemSelectedListener {

    RecyclerView mRecyclerView;
    ArrayList<String> deallist;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Integer> dealistimage;
    RecyclerView.Adapter mAdaptertwo;
    TextView tittle;
    DrawerLayout drawer;
    BottomNavigationView bottomNavigationView;

    private ImageView loadNext, loadprevious,showmenu;

    private final String main_URL = "https://ownshopz.com/ownapi/index.php/welcome/getcategories";

    private OkHttpNetworkListener networkListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        loadNext = (ImageView) findViewById(R.id.view_all_activity_loadnext);
        loadprevious = (ImageView) findViewById(R.id.view_all_activity_loadPrevious);
        showmenu = (ImageView) findViewById(R.id.show);

        loadNext.setVisibility(View.GONE);
        loadprevious.setVisibility(View.GONE);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        networkListener = new OkHttpNetworkListener(this,this);
        networkListener.setProgressBarMessage("Loading");
        networkListener.startServerRequest(OkkHttpRequestType.GET, main_URL , null);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        showmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer.openDrawer(navigationView);

            }
        });
    }

    @Override
    public void onItemSelcted(Object data, String brandName) {

        Intent c = new Intent(view_all_cat_activity.this, categories_Activity.class);
        c.putExtra("Cat", brandName);
        c.putExtra("img", "https://ownshopz.com/wp-content/uploads/2018/07/5d84ef15c47229ed33758985f45411e3.jpg");
        startActivity(c);

    }

    /**
     * get post response data
     *
     * @param data
     * @param requestedURL
     */

    List<datacat> category;
    ViewallCatAdpter adapter;
    GalleryResponse type;
    @Override
    public void onResponseReceived(String data, String requestedURL) throws JSONException {
        Catmodel a = new Gson().fromJson(data,Catmodel.class);

        category  =  a.getData();
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                adapter = new ViewallCatAdpter(view_all_cat_activity.this, (ArrayList<datacat>) category, view_all_cat_activity.this);
                mRecyclerView.setAdapter(adapter);
                Log.i("response", category.toString());

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
            Intent i = new Intent(view_all_cat_activity.this, MainActivity.class);
            startActivity(i);

        }  else if (id == R.id.navigation_profile) {
            String urls = "https://ownshopz.com/about-us/";
            Intent is = new Intent(view_all_cat_activity.this, Virutal_Activity.class);
            is.setData(Uri.parse(urls));
            startActivity(is);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
