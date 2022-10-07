package com.hfad.hostel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class SOAC extends AppCompatActivity {
    ImageView ic_back ;
    Toolbar toolbar;
    TextView txttoolbar;
    CardView soac_rku_killer_smashers,football_club;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soac);

        toolbar = findViewById(R.id.toolbar);
        txttoolbar = findViewById(R.id.txt_toolbar);
        soac_rku_killer_smashers=findViewById(R.id.soac_rku_killer_smashers);
        football_club=findViewById(R.id.soac_rku_rangers_football_club);


        txttoolbar.setText(R.string.soac);

        //toolbar back button
        ic_back = findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        soac_rku_killer_smashers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SOAC.this,SOAC_Club_Info.class);
                startActivity(i);
            }
        });

        football_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SOAC.this,SOAC_Club_Info.class);
                startActivity(i);
            }
        });
    }
}