package com.hfad.hostel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GYM extends AppCompatActivity {
    ImageView ic_back ;
    Toolbar toolbar;
    TextView txttoolbar;
    ImageView call,whatsapp,mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);

        //toolbar
        toolbar = findViewById(R.id.toolbar);
        txttoolbar = findViewById(R.id.txt_toolbar);
        txttoolbar.setText(R.string.gym);
        //toolbar back button
        ic_back = findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //contect us Button
        call = findViewById(R.id.call);
        whatsapp = findViewById(R.id.whatsapp);
        mail = findViewById(R.id.mail);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:+919909956173"));
                startActivity(i);
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = " https://api.whatsapp.com/send?phone=+916352687538&text=hello+sir";
                Intent i = new Intent();
                i.setAction(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.setPackage("com.whatsapp");
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "";
                Intent e = new Intent(Intent.ACTION_SEND);
                e.setData(Uri.parse("mailto:jnabhoya474@rku.ac.in"));
                e.setType("plain/text");
                startActivity(e);
            }
        });

    }
}