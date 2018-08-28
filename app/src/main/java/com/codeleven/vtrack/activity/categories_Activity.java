package com.codeleven.vtrack.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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
import android.widget.Toast;

import com.codeleven.vtrack.Helpers.OnItemClickHelperInterface;
import com.codeleven.vtrack.MainActivity;
import com.codeleven.vtrack.R;
import com.codeleven.vtrack.catadapter;
import com.codeleven.vtrack.model.BanquetActivities;
import com.codeleven.vtrack.model.Datum;
import com.codeleven.vtrack.model.ResponseImage;
import com.codeleven.vtrack.networkhandlers.OkHttpNetworkInterface;
import com.codeleven.vtrack.networkhandlers.OkHttpNetworkListener;
import com.codeleven.vtrack.networkhandlers.OkkHttpRequestType;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.codeleven.vtrack.R.id.drawer_layout;

/**
 * Created by CodelevenPC on 28-Jul-18.
 */

public class categories_Activity extends AppCompatActivity implements OkHttpNetworkInterface, OnItemClickHelperInterface, NavigationView.OnNavigationItemSelectedListener {

    //private ActivityMainBinding mainBinding;
    RecyclerView mRecyclerView;
    private static int pageCount = 0;
    private final String main_URL = "https://ownshopz.com/ownapi/index.php/welcome/getvalues/";
//    private final String main_URL = "https://ownshopz.com/ownapi/index.php/welcome/getvalues/";
//    192.168.1.115/ownapi/index.php/welcome/getvalues/megadeal/1
    String brand,url,discoutvalue,Content;
    //intialise network handler
    private OkHttpNetworkListener networkListener;
    BottomNavigationView bottomNavigationView;
    private ImageView loadNext, loadprevious, showmenu;
    DrawerLayout drawer;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        loadNext = (ImageView) findViewById(R.id.view_all_activity_loadnext);
        loadprevious = (ImageView) findViewById(R.id.view_all_activity_loadPrevious);
        showmenu = (ImageView) findViewById(R.id.show);
        brand = getIntent().getStringExtra("Cat");
//        url= getIntent().getStringExtra("img");
        pageCount = 0;
        drawer = (DrawerLayout) findViewById(drawer_layout);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageCount = pageCount + 1;
                networkListener.startServerRequest(OkkHttpRequestType.GET, main_URL + brand+"/"+ String.valueOf(pageCount), null);
            }
        });
        loadprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageCount = pageCount - 1;
                if (pageCount != -1){
                    networkListener.startServerRequest(OkkHttpRequestType.GET, main_URL + brand+"/"+ String.valueOf(pageCount), null);}
                else
                    Toast.makeText(categories_Activity.this, "Reached minimum", Toast.LENGTH_SHORT).show();
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
        networkListener.startServerRequest(OkkHttpRequestType.GET, main_URL+ brand+"/"+ 0,null);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }
    List<Datum> cat;
    catadapter adapter;
    @Override
    public void onResponseReceived(final String data, final String requestedURL) throws JSONException {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BanquetActivities  a = new Gson().fromJson(data,BanquetActivities.class);
                //cat  =  a.getData();
                if(a.getStatus().equals("error")){

                    AlertDialog alertDialog = new AlertDialog.Builder(categories_Activity.this).create();
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
                    adapter = new catadapter(categories_Activity.this, (ArrayList<Datum>) cat, categories_Activity.this);
                    mRecyclerView.setAdapter(adapter);
                }
//                Log.i("response", cat.toString());
            }
        });


    }

    @Override
    public void onNetworkError(final String errorDetail, String requestedURL) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BanquetActivities  a = new Gson().fromJson(errorDetail,BanquetActivities.class);
                if(a.getStatus().equals("error")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(categories_Activity.this).create();
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
                }
//                    Toast.makeText(categories_Activity.this,"No Categories ", Toast.LENGTH_SHORT).show();
               // }else {
                //    cat  =  a.getData();
                  //  adapter = new catadapter(categories_Activity.this, (ArrayList<Datum>) cat, categories_Activity.this);
                  //  mRecyclerView.setAdapter(adapter);
              //  }
//                Log.i("response", cat.toString());
            }
        });


    }

    @Override
    public void onCommentsReceived(String comment, String URL) {

    }


    @Override
    public void onItemSelcted(Object data, String brandName) {
        ResponseImage responseImage = (ResponseImage) data;
        // Toast.makeText(this, "data receigved " + responseImage.toString(), Toast.LENGTH_SHORT).show();

//        Intent c = new Intent(this, View_deal_activity.class);
//        c.putExtra("brandfactory", brandName);
//        c.putExtra("img", responseImage.getResult());
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
            Intent i = new Intent(categories_Activity.this, MainActivity.class);
            startActivity(i);

        }  else if (id == R.id.navigation_profile) {
            String urls = "https://ownshopz.com/about-us/";
            Intent is = new Intent(categories_Activity.this, Virutal_Activity.class);
            is.setData(Uri.parse(urls));
            startActivity(is);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
