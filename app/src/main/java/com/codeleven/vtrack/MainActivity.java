package com.codeleven.vtrack;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codeleven.vtrack.Helpers.OnItemClickHelperInterface;
import com.codeleven.vtrack.activity.SeacrhDetailActivity;
import com.codeleven.vtrack.activity.View_all_asso_activity;
import com.codeleven.vtrack.activity.View_all_brands_activity;
import com.codeleven.vtrack.activity.View_all_new_activity;
import com.codeleven.vtrack.activity.Virutal_Activity;
import com.codeleven.vtrack.activity.categories_Activity;
import com.codeleven.vtrack.adapter.associ_adapter;
import com.codeleven.vtrack.adapter.brandadapter;
import com.codeleven.vtrack.adapter.rablogoadpter;
import com.codeleven.vtrack.model.Catmodel;
import com.codeleven.vtrack.model.GalleryResponse;
import com.codeleven.vtrack.model.PreData;
import com.codeleven.vtrack.model.Premiummodel;
import com.codeleven.vtrack.model.associatedatamodel;
import com.codeleven.vtrack.model.associatemodel;
import com.codeleven.vtrack.model.brandsdatamodel;
import com.codeleven.vtrack.model.brandsmodel;
import com.codeleven.vtrack.model.datacat;
import com.codeleven.vtrack.model.imagedata;
import com.codeleven.vtrack.model.imagemodel;
import com.codeleven.vtrack.model.royaldata;
import com.codeleven.vtrack.model.royalmodel;
import com.codeleven.vtrack.networkhandlers.OkHttpNetworkInterface;
import com.codeleven.vtrack.networkhandlers.OkHttpNetworkListener;
import com.codeleven.vtrack.networkhandlers.OkkHttpRequestType;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements OkHttpNetworkInterface, OnItemClickHelperInterface, NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "fh";
    private Menu menu;

    RecyclerView mRecyclerView,mRecyclerViewtwo,mRecyclerViewthree,mRecyclerViewfour,mRecyclerViewfive,mRecyclerViewlogo;
    RecyclerView.LayoutManager mLayoutManager,mLayoutManagertwo,mLayoutManagerthree,mLayoutManagerfour,mLayoutManagerfive,mLayoutManagerlogo;
    RecyclerView.Adapter mAdapter;
    RecyclerView.Adapter mAdaptertwo;
    RecyclerView.Adapter mAdapterthree;
//    private adabterclass mAdapterthree;
    RecyclerView.Adapter mAdapterfour;
    RecyclerView.Adapter mAdapterfive,mAdapterlogo;
    ArrayList<String> alName;
    ArrayList<String> deallist;
    ArrayList<Integer> alImage;
    ArrayList<Integer> deallargeimg;
    ArrayList<Integer> dealistimage;
    ArrayList<Integer> logolist;
    ArrayList<Integer> associatepartnerlist;
    ArrayList<Integer> rablist;
    HorizontalScrollView s,scroll,logoscrll;


    private boolean scrollingLeft = false;
    private boolean scrollingpa = false;
    private boolean scrollinglogol = false;
    TextView text,btnview,btnviewper,btnviewallcat,btnbrands,btnasso;
    LinearLayout dinebtns;
    ImageView megadeal,weekend,virtualtour,dine,onam,imagecover,showmenu;
    private ArrayList<dealmodel> viewalldeal;

    EditText medit_deal;
    ImageView msearch;
    DrawerLayout drawer;



    List<royaldata> da;
    List<royaldata> wee;
    royalmodel abj = new royalmodel();

    private List<mainmodel> categorylist;

    private final String main_URL = "https://ownshopz.com/ownapi/index.php/welcome/getcategories";

    private OkHttpNetworkListener networkListener;


    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_activity);
        scrollingassiocate();
        scrollingbrand();
        scrollingrab();
        zoom(text);

        deallargeimg = new ArrayList<>(Arrays.asList( R. drawable.locationbanner));
        logolist = new ArrayList<>(Arrays.asList(R.drawable.pumma,R.drawable.levis, R.drawable.adidas,
                R.drawable.images, R.drawable.download, R.drawable.pizza, R.drawable.nikepng,
                R.drawable.skybag,R.drawable.loreal));

        associatepartnerlist = new ArrayList<>(Arrays.asList(
                R.drawable.dff,
                R.drawable.fgf, R.drawable.ayra, R.drawable.frrdg, R.drawable.gdfg, R.drawable.gdg,
                R.drawable.hfjk,R.drawable.gfdg,R.drawable.gfg,R.drawable.gvfg,R.drawable.im,
                R.drawable.partnerim,R.drawable.partnerimg
                ,R.drawable.rgtg ,R.drawable.chellammart ,R.drawable.interiorexpress
                ,R.drawable.kadoshlogo,R.drawable.missworldlogo
                ,R.drawable.mralogo,R.drawable.nexgen
        ));
        rablist = new ArrayList<>(Arrays.asList(
                R.drawable.caferes,
                R.drawable.carnival, R.drawable.chickblast, R.drawable.cocobongo, R.drawable.fresco, R.drawable.frankztreat
//                R.drawable.fresco, R.drawable.garammasala, R.drawable.hidine, R.drawable.honeyavailablein, R.drawable.houselogore
//              R.drawable.kingraba, R.drawable.kl, R.drawable.logorab, R.drawable.mychickenlogopage, R.drawable.nbres, R.drawable.olive
//                R.drawable.pinroll, R.drawable.res, R.drawable.resrab, R.drawable.resrabb, R.drawable.ressrab, R.drawable.salwyadine
        ));

        new gatallpremium().execute();
        new getallroyality().execute();
        new getallimages().execute();
        new getbrands().execute();
        new getlogos().execute();



        networkListener = new OkHttpNetworkListener(this,this);
        networkListener.setProgressBarMessage("Loading");
        networkListener.startServerRequest(OkkHttpRequestType.GET, main_URL , null);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        showmenu = (ImageView) findViewById(R.id.show);


        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        showmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                drawer.openDrawer(navigationView);

            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerViewtwo = (RecyclerView) findViewById(R.id.recycler_view1);
        mRecyclerViewthree = (RecyclerView) findViewById(R.id.recycler_view2);
        mRecyclerViewfour = (RecyclerView) findViewById(R.id.recycler_view3);
        mRecyclerViewfive = (RecyclerView) findViewById(R.id.recycler_view4);
        mRecyclerViewlogo = (RecyclerView) findViewById(R.id.logos);
        s = (HorizontalScrollView) findViewById(R.id.scroll);
        scroll = (HorizontalScrollView) findViewById(R.id.scrolls);
        logoscrll = (HorizontalScrollView) findViewById(R.id.scrolling);
        btnviewper = (TextView) findViewById(R.id.periumview);
//      btnviewallcat = findViewById(R.id.viewallcat);
        btnbrands = (TextView) findViewById(R.id.btnviewbrands);
        btnasso = (TextView) findViewById(R.id.viewassociate);
        dinebtns = (LinearLayout) findViewById(R.id.dine_in);
//      btnview  = findViewById(R.id.btn_view);
        megadeal = (ImageView) findViewById(R.id.megabtn);
        weekend = (ImageView) findViewById(R.id.weekendbtn);
        virtualtour = (ImageView) findViewById(R.id.virtualtour);
        dine = (ImageView) findViewById(R.id.rest);
        onam = (ImageView) findViewById(R.id.onam);
        virtualtour = (ImageView) findViewById(R.id.virtualtour);
        imagecover = (ImageView) findViewById(R.id.coverimg);
        medit_deal = (EditText) findViewById(R.id.edit_deal);
        msearch = (ImageView) findViewById(R.id.search);
        medit_deal.getText();


        mRecyclerView.setHasFixedSize(true);
        mRecyclerViewtwo.setHasFixedSize(true);
        mRecyclerViewthree.setHasFixedSize(true);
        mRecyclerViewfour.setHasFixedSize(true);
        mRecyclerViewfive.setHasFixedSize(true);
        mRecyclerViewlogo.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerViewtwo.setLayoutManager(gridLayoutManager);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mLayoutManagerthree = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerfour = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerfive = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerlogo = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);


        mRecyclerViewthree.setLayoutManager(mLayoutManagerthree);
        mRecyclerViewfour.setLayoutManager(mLayoutManagerfour);
        mRecyclerViewfive.setLayoutManager(mLayoutManagerfive);
        mRecyclerViewlogo.setLayoutManager(mLayoutManagerlogo);
        mRecyclerViewfive.setItemAnimator(new DefaultItemAnimator());


        mAdapterlogo = new rablogoadpter(MainActivity.this,rablist);
        mRecyclerViewlogo.setAdapter(mAdapterlogo);
        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                } else if (isShow) {
                    isShow = false;
                }
            }
        });

        virtualtour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent c = new Intent(MainActivity.this, Virutal_Activity.class);
//                startActivity(c);
                String url = "https://ownshopz.com/mobile-360-degree/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        btnviewper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(MainActivity.this, View_all_new_activity.class);
                startActivity(c);
            }
        });


        btnbrands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(MainActivity.this, View_all_brands_activity.class);
                startActivity(c);
            }
        });

        btnasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(MainActivity.this, View_all_asso_activity.class);
                startActivity(c);
            }
        });

        msearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String slug = String.valueOf(medit_deal.getText());
                if (medit_deal.length()==0)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Please enter the Location");
                    alertDialog.show();

                }
                else if(medit_deal.length() < 2){
                    Toast.makeText(MainActivity.this,"Sorry, you need to enter at least 2 characters",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent c = new Intent(MainActivity.this, SeacrhDetailActivity.class);
                    c.putExtra("Cat",slug);
                    startActivity(c);
                }

            }
        });
    }
    private void scrollingrab() {
        Timer timer = new Timer("horizontalScrollViewTimera");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (scrollinglogol) {
                            if (logoscrll.getScrollX() == 0) {
                                logoscrll.smoothScrollBy(5, 0);
                                scrollinglogol = false;
                            } else {
                                logoscrll.smoothScrollBy(-5, 0);
                            }
                        } else {
                            if (logoscrll.canScrollHorizontally(View.FOCUS_RIGHT)) {
                                logoscrll.smoothScrollBy(5, 0);
                            } else {
                                logoscrll.smoothScrollBy(-5, 0);
                                scrollinglogol = true;
                            }
                        }
                    }
                });
            }
        }, 3000, 40);

    }

    private void scrollingbrand() {
        Timer timer = new Timer("horizontalScrollViewTimer");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (scrollingLeft) {
                            if (s.getScrollX() == 0) {
                                s.smoothScrollBy(5, 0);
                                scrollingLeft = false;
                            } else {
                                s.smoothScrollBy(-5, 0);
                            }
                        } else {
                            if (s.canScrollHorizontally(View.FOCUS_RIGHT)) {
                                s.smoothScrollBy(5, 0);
                            } else {
                                s.smoothScrollBy(-5, 0);
                                scrollingLeft = true;
                            }
                        }
                    }
                });
            }
        }, 3000, 40);

    }
    private void scrollingassiocate() {

        Timer timer1 = new Timer("horizontalScrollViewTimers");
        timer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (scrollingpa) {
                            if (scroll.getScrollX() == 0) {
                                scroll.smoothScrollBy(5, 0);
                                scrollingpa = false;
                            } else {
                                scroll.smoothScrollBy(-5, 0);
                            }
                        } else {
                            if (scroll.canScrollHorizontally(View.FOCUS_RIGHT)) {
                                scroll.smoothScrollBy(5, 0);
                            } else {
                                scroll.smoothScrollBy(-5, 0);
                                scrollingpa = true;
                            }
                        }
                    }
                });
            }
        }, 3000, 40);
    }


    public void zoom(View view){
        text = (TextView) findViewById(R.id.zoom);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom);
        text.startAnimation(animation1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }

    public void onStart() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onStart();
    }

    public void onResume() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onResume();
    }
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Do you want to exit application?");
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    @Override
    public void onItemSelcted(Object data, String brandName) {

//        ResponseImage responseImage = (ResponseImage) data;
        // Toast.makeText(this, "data receigved " + responseImage.toString(), Toast.LENGTH_SHORT).show();

            Intent c = new Intent(MainActivity.this, categories_Activity.class);
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
    MainAdapter adapter;
    GalleryResponse type;
    @Override
    public void onResponseReceived(final String data, String requestedURL) throws JSONException {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                Catmodel a = new Gson().fromJson(data,Catmodel.class);

                category  =  a.getData();


                adapter = new MainAdapter(MainActivity.this, (ArrayList<datacat>) category, MainActivity.this);
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
//            Intent i = new Intent(MainActivity.this, MainActivity.class);
//            startActivity(i);

        } else if (id == R.id.navigation_profile) {
            String urls = "https://ownshopz.com/about-us/";
            Intent is = new Intent(MainActivity.this, Virutal_Activity.class);
            is.setData(Uri.parse(urls));
            startActivity(is);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }


    private class gatallpremium extends AsyncTask<Void, Void, Void> {
        List<PreData> dataspre;

        protected void onProgressUpdate(Integer... progress) {
        }

        @Override
        protected Void doInBackground(Void... params) {
            Htteprequest sh = new Htteprequest();
            String url = "https://ownshopz.com/ownapi/index.php/welcome/show_frontcat";
            final String jsonStr = sh.makeServiceCall(url);
            if (jsonStr != null) {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        Premiummodel a = new Gson().fromJson(jsonStr, Premiummodel.class);
                        dataspre = a.getData();


                        Log.i("response", dataspre.toString());

                    }
                });


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
            dealAdapter adapterdeal;
            adapterdeal = new dealAdapter(MainActivity.this, (ArrayList<PreData>) dataspre, MainActivity.this);
            mRecyclerViewtwo.setAdapter(adapterdeal);
        }
    }

    private class getallroyality extends AsyncTask<Void, Void, Void> {
        List<royaldata> datasroyal;
        protected void onProgressUpdate(Integer... progress) {
        }
        @Override
        protected Void doInBackground(Void... params) {
            Htteprequest sh = new Htteprequest();
            String url = "https://ownshopz.com/ownapi/index.php/welcome/get_royalty";
            final String jsonStr = sh.makeServiceCall(url);
            if (jsonStr != null) {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        royalmodel a = new Gson().fromJson(jsonStr, royalmodel.class);
                        datasroyal = a.getData();
                        Log.i("response", datasroyal.toString());

                    }
                });

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
            adabterclass adapterdeal;
            adapterdeal = new adabterclass(MainActivity.this, (ArrayList<royaldata>) datasroyal, MainActivity.this);
            mRecyclerViewthree.setAdapter(adapterdeal);
        }
    }

    private class getallimages extends AsyncTask<Void, Void, Void> {

        //https://ownshopz.com/ownapi/images
        List<imagedata> imagedat;
        protected void onProgressUpdate(Integer... progress) {
        }
        @Override
        protected Void doInBackground(Void... params) {
            Htteprequest sh = new Htteprequest();
            String url = "https://ownshopz.com/ownapi/index.php/welcome/get_frontimage";
            final String jsonStr = sh.makeServiceCall(url);
            if (jsonStr != null) {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        imagemodel a = new Gson().fromJson(jsonStr, imagemodel.class);
                        imagedat = a.getData();
                        Log.i("response", imagedat.toString());
                    }
                });

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
            String imageurl = "https://ownshopz.com/ownapi/images/";
//            megadeal.setImageResource(imageurl+imagedat.get(1).getImageurl());

            final String url1 = imagedat.get(1).getImageurl();
            Picasso.with(MainActivity.this)
                    .load("https://ownshopz.com/ownapi/images/"+url1)
                    .into(megadeal);
            final String url2 = imagedat.get(0).getImageurl();
            Picasso.with(MainActivity.this)
                    .load("https://ownshopz.com/ownapi/images/"+url2)
                    .into(weekend);
            final String url3 = imagedat.get(2).getImageurl();
            Picasso.with(MainActivity.this)
                    .load("https://ownshopz.com/ownapi/images/"+url3)
                    .into(dine);
            final String url4 = imagedat.get(4).getImageurl();
            Picasso.with(MainActivity.this)
                    .load("https://ownshopz.com/ownapi/images/"+url4)
                    .into(onam);
            final String url5 = imagedat.get(4).getImageurl();
            Picasso.with(MainActivity.this)
                    .load("https://ownshopz.com/ownapi/images/"+url5)
                    .into(onam);
            final String url6 = imagedat.get(3).getImageurl();
            Picasso.with(MainActivity.this)
                    .load("https://ownshopz.com/ownapi/images/"+url6)
                    .into(imagecover);

            megadeal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String a = imagedat.get(1).getCategory();
                    Intent c = new Intent(MainActivity.this, categories_Activity.class);
                    c.putExtra("Cat", a);
                    startActivity(c);
                }
            });

            dine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String b = imagedat.get(2).getCategory();
                    Intent c = new Intent(MainActivity.this, categories_Activity.class);
                    c.putExtra("Cat", b);
                    startActivity(c);

                }
            });

            onam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String d = imagedat.get(4).getCategory();
                    Intent c = new Intent(MainActivity.this, categories_Activity.class);
                    c.putExtra("Cat", d);
                    startActivity(c);
                }
            });

            weekend.setOnClickListener(new View.OnClickListener() {    
                @Override
                public void onClick(View view) {
//                Intent c = new Intent(MainActivity.this, megaweekactivity.class);
                    final String e = imagedat.get(0).getCategory();
                    Intent c = new Intent(MainActivity.this, categories_Activity.class) ;
                    c.putExtra("Cat", e);
                    startActivity(c);
                }
            });



        }
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

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        brandsmodel a = new Gson().fromJson(jsonStr, brandsmodel.class);
                        brands = a.getData();
                        Log.i("response", brands.toString());
                    }
                });

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
            brandadapter adapterdeal;
            adapterdeal = new brandadapter(MainActivity.this, (ArrayList<brandsdatamodel>) brands, MainActivity.this);
            mRecyclerViewfour.setAdapter(adapterdeal);
        }
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

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        associatemodel a = new Gson().fromJson(jsonStr, associatemodel.class);
                        dataasso = a.getData();
                        Log.i("response", dataasso.toString());

                    }
                });

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
            associ_adapter adapterdeal;
            adapterdeal = new associ_adapter(MainActivity.this, (ArrayList<associatedatamodel>) dataasso, MainActivity.this);
            mRecyclerViewfive.setAdapter(adapterdeal);
        }
    }


}
