package com.hfad.hostel;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GYM extends AppCompatActivity {
    ImageView ic_back , Profile ;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Toolbar toolbar;
    TextView txttoolbar;
    ImageView call,whatsapp,mail;
    Button gym_registration;
    Dialog dialog;
    LinearLayout linearLayout;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);

        Profile = findViewById(R.id.Profile);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        gym_registration=findViewById(R.id.btn_gym_register1);
        linearLayout=findViewById(R.id.linearlayout);
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

        //contact us Button
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

        //bottomshitdialog
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });

        View view=getLayoutInflater().inflate(R.layout.gym_registration_full_dialog,null);
        dialog=new Dialog(this, androidx.appcompat.R.style.Theme_AppCompat_DayNight_NoActionBar);
        dialog.setContentView(view);



        gym_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GYM.this, "Clicked", Toast.LENGTH_SHORT).show();

            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
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
                Intent i = new Intent(GYM.this, login.class);
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