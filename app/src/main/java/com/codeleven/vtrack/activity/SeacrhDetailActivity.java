package com.codeleven.vtrack.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.codeleven.vtrack.Helpers.OnItemClickHelperInterface;
import com.codeleven.vtrack.MainActivity;
import com.codeleven.vtrack.R;
import com.codeleven.vtrack.adapter.Searchadapter;
import com.codeleven.vtrack.model.slugdatamodel;
import com.codeleven.vtrack.model.slugmodel;
import com.codeleven.vtrack.networkhandlers.OkHttpNetworkInterface;
import com.codeleven.vtrack.networkhandlers.OkHttpNetworkListener;
import com.codeleven.vtrack.networkhandlers.OkkHttpRequestType;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodelevenPC on 10-Aug-18.
 */


public class SeacrhDetailActivity extends AppCompatActivity  implements OkHttpNetworkInterface, OnItemClickHelperInterface, NavigationView.OnNavigationItemSelectedListener {

    private ImageView loadNext, loadprevious,showmenu;
    RecyclerView mRecyclerView;
    String brand;
    private OkHttpNetworkListener networkListener;
    private final String main_URL = "https://ownshopz.com/ownapi/index.php/welcome/search?slug=";
//    private final String main_URL = "http://192.168.1.104/ownapi/index.php/welcome/search?slug=";

    DrawerLayout drawer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        loadNext = (ImageView) findViewById(R.id.view_all_activity_loadnext);
        loadprevious = (ImageView) findViewById(R.id.view_all_activity_loadPrevious);
        showmenu = (ImageView) findViewById(R.id.show);
        brand = getIntent().getStringExtra("Cat");

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

        networkListener = new OkHttpNetworkListener(this, this);
        networkListener.setProgressBarMessage("Loading");
        networkListener.startServerRequest(OkkHttpRequestType.GET, main_URL+ brand,null);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(gridLayoutManager);
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
    List<slugdatamodel> cat;
    Searchadapter adapter;
    @Override
    public void onResponseReceived(final String data, String requestedURL) throws JSONException {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                slugmodel a = new Gson().fromJson(data,slugmodel.class);
                //cat  =  a.getData();
                if(a.getStatus().equals("error")){

                    AlertDialog alertDialog = new AlertDialog.Builder(SeacrhDetailActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Currently there are no deals in category");
//                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Intent c = new Intent(categories_Activity.this, MainActivity.class);
//                                    startActivity(c);
//                                }
//                            });
                    alertDialog.show();
//                    Toast.makeText(categories_Activity.this,"No Categories ", Toast.LENGTH_SHORT).show();
                }else {
                    cat  =  a.getData();
                    adapter = new Searchadapter(SeacrhDetailActivity.this, (ArrayList<slugdatamodel>) cat, SeacrhDetailActivity.this);
                    mRecyclerView.setAdapter(adapter);
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
            Intent i = new Intent(SeacrhDetailActivity.this, MainActivity.class);
            startActivity(i);

        }  else if (id == R.id.navigation_profile) {
            String urls = "https://ownshopz.com/about-us/";
            Intent is = new Intent(SeacrhDetailActivity.this, Virutal_Activity.class);
            is.setData(Uri.parse(urls));
            startActivity(is);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
