package com.hfad.hostel;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Gatepass extends AppCompatActivity {
    ImageView ic_back ;
    Toolbar toolbar;
    TextView txttoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatepass);

        toolbar = findViewById(R.id.toolbar);
        txttoolbar = findViewById(R.id.txt_toolbar);

        txttoolbar.setText(R.string.GatePass);

        //toolbar back button
        ic_back = findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}