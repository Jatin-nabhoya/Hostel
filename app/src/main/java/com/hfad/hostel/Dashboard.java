package com.hfad.hostel;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    ImageView Profile;
    CardView SOAC,gym;
    CardView gatepass,cv_announcement,cv_ComplainBox;
    Toolbar toolbar;
    TextView txttoolbar,signout;
    ImageView ic_back;
    FirebaseAuth mAuth;
    Button out;

    BottomSheetDialog sheetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Profile=findViewById(R.id.Profile);
        SOAC=findViewById(R.id.SOAC);
        gym=findViewById(R.id.gym);

        signout=findViewById(R.id.tv_signout);

        gatepass=findViewById(R.id.gatepass);
        cv_announcement=findViewById(R.id.cv_announcement);
        cv_ComplainBox=findViewById(R.id.cv_ComplainBox);
        mAuth=FirebaseAuth.getInstance();

        txttoolbar = findViewById(R.id.txt_toolbar);
        txttoolbar.setText(R.string.my_dashboard);

        ic_back = findViewById(R.id.ic_back);
        ic_back.setVisibility(View.GONE);


        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });

        SOAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Dashboard.this,SOAC.class);
                startActivity(i);
            }
        });
        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Dashboard.this,GYM.class);
                startActivity(i);
            }
        });

       cv_announcement.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i =new Intent(Dashboard.this,announcement.class);
               startActivity(i);
           }
       });

        cv_ComplainBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Dashboard.this,Complain_Box.class);
                startActivity(i);
            }
        });







    }
//If user click back button then they can't redirect to login page
    int counter=0;

    public void onBackPressed(){
        counter++;
        if(counter ==2){
            super.onBackPressed();
        }else{
            Toast.makeText(this,"Press back again to exit",Toast.LENGTH_SHORT).show();
        }
    }

    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottomsheet_dialog);

        LinearLayout logout = bottomSheetDialog.findViewById(R.id.ll_logout);
        LinearLayout gatepass = bottomSheetDialog.findViewById(R.id.bts_ll_gatepass);
        LinearLayout gallery= bottomSheetDialog.findViewById(R.id.bts_ll_gallery);
        LinearLayout announcement = bottomSheetDialog.findViewById(R.id.bts_ll_announcement);
        LinearLayout cb = bottomSheetDialog.findViewById(R.id.bts_ll_cb);
        LinearLayout messmenu = bottomSheetDialog.findViewById(R.id.bts_ll_messmenu);

        bottomSheetDialog.show();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent i =new Intent(Dashboard.this,login.class);
                startActivity(i);
                bottomSheetDialog.dismiss();
            }
        });
        gatepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Dashboard.this,Gatepass.class);
                startActivity(i);
                bottomSheetDialog.dismiss();
            }
        });
        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Dashboard.this,announcement.class);
                startActivity(i);
                bottomSheetDialog.dismiss();
            }
        });
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Dashboard.this,Complain_Box.class);
                startActivity(i);
                bottomSheetDialog.dismiss();
            }
        });
//        messmenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i =new Intent(Dashboard.this,Mess_Menu.class);
//                startActivity(i);
//                bottomSheetDialog.dismiss();
//            }
//        });
    }
}