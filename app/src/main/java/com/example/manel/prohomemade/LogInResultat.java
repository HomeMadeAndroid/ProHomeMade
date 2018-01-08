package com.example.manel.prohomemade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class LogInResultat extends AppCompatActivity {

    private TextView txtName;
    //private TextView txtEmail;
    private ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_resultat);
        txtName = (TextView) findViewById(R.id.txtName);
        //txtEmail = (TextView) findViewById(R.id.txtEmail);
        profilePic = (ImageView) findViewById(R.id.profilePic);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            String name = (String) b.get("name");
            //String email =(String) b.get("email");
            String imgUrl = (String) b.get("imgUrl");
            txtName.setText(name);
            txtName.setText(name);
            //txtEmail.setText(email);
            Glide.with(this).load(imgUrl).into(profilePic);
        }
    }
}
