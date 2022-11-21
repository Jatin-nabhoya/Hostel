package com.hfad.hostel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SOAC extends AppCompatActivity {
    ImageView ic_back , Profile ;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Toolbar toolbar;
    TextView txttoolbar,tv_football,tv_smashers,tv_zero_violation,tv_badminton,tv_music,tv_king84,tv_cricket,tv_bumblebeez;
    CardView soac_rku_killer_smashers,football_club,zero_violation,king_84,badminton,cricket,bumblebeez,music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soac);

        Profile = findViewById(R.id.Profile);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        toolbar = findViewById(R.id.toolbar);
        txttoolbar = findViewById(R.id.txt_toolbar);
        soac_rku_killer_smashers=findViewById(R.id.soac_rku_killer_smashers);
        football_club=findViewById(R.id.soac_rku_rangers_football_club);
        zero_violation=findViewById(R.id.soac_zero_violation);
        king_84=findViewById(R.id.soac_king_84);
        badminton=findViewById(R.id.soac_badminton);
        cricket=findViewById(R.id.soac_cricket);
        bumblebeez=findViewById(R.id.soac_bumblebeez);
        music=findViewById(R.id.soac_music_club);


        tv_football=findViewById(R.id.tv_football_name);
        tv_smashers=findViewById(R.id.tv_smashers_name);
        tv_zero_violation=findViewById(R.id.tv_zero_violation);
        tv_badminton=findViewById(R.id.tv_badminton);
        tv_music=findViewById(R.id.tv_soul_of_music_club);
        tv_king84=findViewById(R.id.tv_king_of_84);
        tv_cricket=findViewById(R.id.tv_cricket);
        tv_bumblebeez=findViewById(R.id.tv_bumblebeez);

        Intent i = getIntent();
        String username = i.getStringExtra("username");



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
                String clubName=tv_smashers.getText().toString();
                i.putExtra("clubName",clubName);
                i.putExtra("username",username);
                startActivity(i);
            }
        });

        football_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SOAC.this,SOAC_Club_Info.class);
                String clubName=tv_football.getText().toString();
                i.putExtra("clubName",clubName);
                i.putExtra("username",username);
                startActivity(i);
            }
        });

        zero_violation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SOAC.this,SOAC_Club_Info.class);
                String clubName=tv_zero_violation.getText().toString();
                i.putExtra("clubName",clubName);
                i.putExtra("username",username);
                startActivity(i);
            }
        });

        king_84.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SOAC.this,SOAC_Club_Info.class);
                String clubName=tv_king84.getText().toString();
                i.putExtra("clubName",clubName);
                i.putExtra("username",username);
                startActivity(i);
            }
        });

        badminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SOAC.this,SOAC_Club_Info.class);
                String clubName=tv_badminton.getText().toString();
                i.putExtra("clubName",clubName);
                i.putExtra("username",username);
                startActivity(i);
            }
        });

        cricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SOAC.this,SOAC_Club_Info.class);
                String clubName=tv_cricket.getText().toString();
                i.putExtra("clubName",clubName);
                i.putExtra("username",username);
                startActivity(i);
            }
        });

        bumblebeez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SOAC.this,SOAC_Club_Info.class);
                String clubName=tv_bumblebeez.getText().toString();
                i.putExtra("clubName",clubName);
                i.putExtra("username",username);
                startActivity(i);
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SOAC.this,SOAC_Club_Info.class);
                String clubName=tv_music.getText().toString();
                i.putExtra("clubName",clubName);
                i.putExtra("username",username);
                startActivity(i);
            }
        });

        //bottomshitdialog
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });
    }
    private void showBottomSheetDialog () {
        TextView bts_email;

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottomsheet_dialog);

        Intent i = getIntent();
        String username = i.getStringExtra("username");




        bts_email = bottomSheetDialog.findViewById(R.id.bts_tv_email);
        bts_email.setText(username);
        LinearLayout logout = bottomSheetDialog.findViewById(R.id.ll_logout);
        LinearLayout aboutus = bottomSheetDialog.findViewById(R.id.ll_aboutus);
        LinearLayout rules = bottomSheetDialog.findViewById(R.id.ll_rules);

        bottomSheetDialog.show();


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent i = new Intent(SOAC.this, login.class);
                startActivity(i);
                bottomSheetDialog.dismiss();
                finish();
            }
        });
//        aboutus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(Dashboard.this,AboutUs.class);
//                startActivity(i);
//                bottomSheetDialog.dismiss();
//
//            }
//        });
//        rules.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(Dashboard.this,Rules_and_Regulation.class);
//                startActivity(i);
//            }
//        });
    }
}