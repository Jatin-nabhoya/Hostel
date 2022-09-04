package com.hfad.hostel;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Dashboard extends AppCompatActivity {
    ImageView Profile;
    CardView gatepass;
    Toolbar toolbar;
    TextView txttoolbar;
    ImageView ic_back;

    BottomSheetDialog sheetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Profile=findViewById(R.id.Profile);
        gatepass=findViewById(R.id.gatepass);

        txttoolbar = findViewById(R.id.txt_toolbar);
        txttoolbar.setText(R.string.my_dashboard);

        ic_back = findViewById(R.id.ic_back);
        ic_back.setVisibility(View.GONE);

        Profile.setOnClickListener(view -> {
            sheetDialog=new BottomSheetDialog(Dashboard.this,R.style.BottomsheetStyle);
            View view1 = LayoutInflater.from(Dashboard.this).inflate(R.layout.bottomsheet_dialog,(LinearLayout)findViewById(R.id.sheet));
            sheetDialog.setContentView(view1);
            sheetDialog.show();
        });

       gatepass.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i =new Intent(Dashboard.this,Gatepass.class);
               startActivity(i);
           }
       });
    }
}