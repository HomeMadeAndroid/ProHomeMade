package com.example.manel.prohomemade;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ConnectedArtisant extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtName, txtEmail;
    private ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected_artisant);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        txtName = (TextView) headerView.findViewById(R.id.txtartisantname);
        txtEmail = (TextView) headerView.findViewById(R.id.txtartisantmail);
        profilePic = (ImageView) headerView.findViewById(R.id.profilpic);
        navigationView.setNavigationItemSelectedListener(this);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            String name = (String) b.get("nom");
            String prename = (String) b.get("prenom");
            int tel = b.getInt("tel");
            String email = (String) b.get("email");
            String password = (String) b.get("password");
            String matfisc = (String) b.get("matfisc");
            String adr = (String) b.get("adr");
            Log.d("nameartisant", name);
            Log.d("prenomartisant", prename);
            Log.d("emailartisant", email);
            Log.d("telartisant", String.valueOf(tel));
            Log.d("pswartisant", password);
            Log.d("matfiscartisant", matfisc);
            Log.d("adrrtisant", adr);
            txtName.setText(name.toString());
            txtEmail.setText(email);
            //account = (String) b.get("account");
            // btnVisivility(account);
            if (b.get("imgUrl") != null) {
                String imgUrl = (String) b.get("imgUrl");
                Glide.with(this).load(imgUrl).into(profilePic);
            } else {
                Glide.with(this).load(R.drawable.homemade101).into(profilePic);
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.connected_artisant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
