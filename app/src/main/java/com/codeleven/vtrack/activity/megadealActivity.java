package com.codeleven.vtrack.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.Toast;

import com.codeleven.vtrack.Helpers.OnItemClickHelperInterface;
import com.codeleven.vtrack.MainActivity;
import com.codeleven.vtrack.R;
import com.codeleven.vtrack.megadealadapter;
import com.codeleven.vtrack.model.GalleryResponse;
import com.codeleven.vtrack.model.ResponseImage;
import com.codeleven.vtrack.networkhandlers.OkHttpNetworkInterface;
import com.codeleven.vtrack.networkhandlers.OkHttpNetworkListener;
import com.codeleven.vtrack.networkhandlers.OkkHttpRequestType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by CodelevenPC on 27-Jul-18.
 */

public class megadealActivity extends AppCompatActivity implements OkHttpNetworkInterface,OnItemClickHelperInterface, NavigationView.OnNavigationItemSelectedListener {

    //    private ActivityMainBinding mainBinding;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout drawer;

    private static int pageCount = 1;

    private final String main_URL = "https://ownshopz.com/wp-json/wp/v2/deal?per_page=8&page=";


    //intialise network handler
    private OkHttpNetworkListener networkListener;
    private ImageView loadNext, loadprevious,showmenu;
    BottomNavigationView bottomNavigationView;
    String discountvalue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        loadNext = (ImageView) findViewById(R.id.view_all_activity_loadnext);
        loadprevious = (ImageView) findViewById(R.id.view_all_activity_loadPrevious);

        discountvalue = getIntent().getStringExtra("discoutvalue");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        loadNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageCount = pageCount + 1;

                networkListener.startServerRequest(OkkHttpRequestType.GET, main_URL + String.valueOf(pageCount), null);

            }
        });

        loadprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageCount = pageCount - 1;

                if (pageCount != -1){
                    networkListener.startServerRequest(OkkHttpRequestType.GET, main_URL + String.valueOf(pageCount), null);}
                else
                    Toast.makeText(megadealActivity.this, "Reached minimum", Toast.LENGTH_SHORT).show();

            }
        });
        showmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer.openDrawer(navigationView);

            }
        });



        networkListener = new OkHttpNetworkListener(this, this);
        networkListener.setProgressBarMessage("Loading");
        networkListener.startServerRequest(OkkHttpRequestType.GET, main_URL + String.valueOf(pageCount), null);


        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);


    }

    ArrayList<GalleryResponse> galleryResponse;
    megadealadapter adapter;
    GalleryResponse type;

    @Override
    public void onResponseReceived(final String data, final String requestedURL) throws JSONException {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                galleryResponse = new Gson().fromJson(data.trim(), new TypeToken<ArrayList<GalleryResponse>>() {
                }.getType());

                    adapter = new megadealadapter(megadealActivity.this, galleryResponse, megadealActivity.this);
                    mRecyclerView.setAdapter(adapter);
                    Log.i("response", galleryResponse.toString());

            }
        });


    }


    @Override
    public void onNetworkError(String errorDetail, String requestedURL) {

    }

    @Override
    public void onCommentsReceived(String comment, String URL) {

    }



    public void onItemSelcted(Object data, String brandName ) {
        ResponseImage responseImage = (ResponseImage) data;
        // Toast.makeText(this, "data receigved " + responseImage.toString(), Toast.LENGTH_SHORT).show();

//        Intent c = new Intent(this, View_deal_activity.class);
//        c.putExtra("brandfactory", brandName);
//        c.putExtra("img", responseImage.getResult());
//        c.putExtra("discoutvalue", discountvalue);
//        startActivity(c);

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
            Intent i = new Intent(megadealActivity.this, MainActivity.class);
            startActivity(i);

        } else if (id == R.id.navigation_profile) {
            String urls = "https://ownshopz.com/about-us/";
            Intent is = new Intent(megadealActivity.this, Virutal_Activity.class);
            is.setData(Uri.parse(urls));
            startActivity(is);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

