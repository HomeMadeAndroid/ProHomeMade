package com.example.manel.prohomemade;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ConnectedClient extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtName, txtEmail;
    String account, accnt;
    String name, prename;
    int tel;
    String email, password;
    TextView txtt;
    private ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectedclient);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = new Bundle();
        bundle.putString("nom", name);
        bundle.putString("prenom", prename);
        bundle.putInt("tel", tel);
        bundle.putString("email", email);
        bundle.putString("password", password);
        bundle.putString("account", accnt);
        FirstPageConnectedClient firstPageConnectedClient = new FirstPageConnectedClient();
        firstPageConnectedClient.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, firstPageConnectedClient)
                .commit();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(),CmderProduit.class);
                startActivity(intent);
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        txtName = (TextView) headerView.findViewById(R.id.txtclientname);
        txtEmail = (TextView) headerView.findViewById(R.id.txtclientmail);
        profilePic = (ImageView) headerView.findViewById(R.id.profilpic);
        navigationView.setNavigationItemSelectedListener(this);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            name = (String) b.get("nom");
            prename = (String) b.get("prenom");
            tel = b.getInt("tel");
            email = (String) b.get("email");
            password = (String) b.get("password");
            Log.d("nameclient", name);
            Log.d("prenomclient", prename);
            Log.d("emailclient", email);
            Log.d("telclient", String.valueOf(tel));
            Log.d("pswclient", password);
            txtName.setText(name.toString() + " " + prename.toString());
            txtEmail.setText(email);
            account = (String) b.get("account");
            if (account.matches("Google")) {
                accnt = "google";
            } else if (account.matches("Facebook")) {
                accnt = "facebook";
            } else {
                accnt = "btn";
            }
            //btnVisivility(account);
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
        getMenuInflater().inflate(R.menu.connectedclient, menu);
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
            Intent intent = new Intent(getApplicationContext(), ParametreC.class);
            intent.putExtra("account", accnt);
            startActivity(intent);
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_actualite) {
            // Handle the camera action
            // actualitee
            // commandee produit
            Intent intent = new Intent(getApplicationContext(), ListeProduit.class);
            intent.putExtra("nom", name);
            intent.putExtra("prenom", prename);
            intent.putExtra("tel", tel);
            intent.putExtra("email", email);
            intent.putExtra("password", password);
            intent.putExtra("account", accnt);
            startActivity(intent);
           /* Bundle bundle = new Bundle();
            bundle.putString("nom", name);
            bundle.putString("prenom", prename);
            bundle.putInt("tel", tel);
            bundle.putString("email", email);
            bundle.putString("password", password);
            bundle.putString("account", accnt);
            FirstPageConnectedClient firstPageConnectedClient = new FirstPageConnectedClient();
            firstPageConnectedClient.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, firstPageConnectedClient)
                    .commit();*/
        } else if (id == R.id.nav_manage) {
            // modifier profil
            Intent intent = new Intent(getApplicationContext(), ModifierC.class);
            intent.putExtra("nom", name);
            intent.putExtra("prenom", prename);
            intent.putExtra("tel", tel);
            intent.putExtra("email", email);
            intent.putExtra("password", password);
            intent.putExtra("account", accnt);
            startActivity(intent);

        } else if (id == R.id.nav_camera) {
            // panier

            //****************************************************************************

        } else if (id == R.id.nav_slideshow) {
            // consulter video

            Toast.makeText(getApplicationContext(), "prespective ...", Toast.LENGTH_LONG);
            //} else if (id == R.id.nav_share) {

            //} else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
