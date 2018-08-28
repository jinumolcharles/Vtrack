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
import android.view.MenuItem;
import android.webkit.WebView;

import com.codeleven.vtrack.MainActivity;
import com.codeleven.vtrack.R;

import java.util.concurrent.Semaphore;

/**
 * Created by CodelevenPC on 28-Jul-18.
 */

public class Virutal_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    WebView myWebView;
    DrawerLayout drawer;

    String mWebGLTestUrl;
    String turl = "https://ownshopz.com/about-us/";
    BottomNavigationView bottomNavigationView;
    StringBuilder mMessage = new StringBuilder("\n");
    boolean mPassed = true;
    Semaphore mFinished = new Semaphore(0, false);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.virtualactivity);
        myWebView = (WebView) findViewById(R.id.degree);

        myWebView.loadUrl("https://www.ownshopz.com/about-us/");

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            Intent i = new Intent(Virutal_Activity.this, MainActivity.class);
            startActivity(i);

        }  else if (id == R.id.navigation_profile) {
            String urls = "https://ownshopz.com/about-us/";
            Intent is = new Intent(Virutal_Activity.this, Virutal_Activity.class);
            is.setData(Uri.parse(urls));
            startActivity(is);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


