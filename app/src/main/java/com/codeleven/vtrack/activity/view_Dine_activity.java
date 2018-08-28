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
import android.view.MenuItem;
import android.widget.ImageView;

import com.codeleven.vtrack.MainActivity;
import com.codeleven.vtrack.R;
import com.codeleven.vtrack.adapter.dineadabter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by CodelevenPC on 17-Jul-18.
 */

public class view_Dine_activity  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView mRecyclerView;
    ArrayList<String> deallist;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Integer> deallargeimg;
    RecyclerView.Adapter mAdaptertwo;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawer;
    ImageView showmenu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        deallargeimg = new ArrayList<>(Arrays.asList( R.drawable.carnival,
                R.drawable.mra,
                R.drawable.vayal,
                R.drawable.chares
        ));

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        showmenu = (ImageView) findViewById(R.id.show);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdaptertwo = new dineadabter(view_Dine_activity.this,deallargeimg);
        mRecyclerView.setAdapter(mAdaptertwo);
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
            Intent i = new Intent(view_Dine_activity.this, MainActivity.class);
            startActivity(i);

        }  else if (id == R.id.navigation_profile) {
            String urls = "https://ownshopz.com/about-us/";
            Intent is = new Intent(view_Dine_activity.this, Virutal_Activity.class);
            is.setData(Uri.parse(urls));
            startActivity(is);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}