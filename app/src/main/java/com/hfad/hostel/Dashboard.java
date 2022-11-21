package com.hfad.hostel;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    ImageView Profile;
    CardView SOAC,gym;
    CardView gatepass,cv_announcement,cv_ComplainBox,cv_messmenu;
    TextView txttoolbar;
    ImageView ic_back;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Profile=findViewById(R.id.Profile);
        SOAC=findViewById(R.id.SOAC);
        gym=findViewById(R.id.gym);





        cv_announcement=findViewById(R.id.cv_announcement);
        cv_ComplainBox=findViewById(R.id.cv_ComplainBox);
        cv_messmenu=findViewById(R.id.cv_messmenu);
        mAuth=FirebaseAuth.getInstance();

        txttoolbar = findViewById(R.id.txt_toolbar);
        txttoolbar.setText(R.string.my_dashboard);

        ic_back = findViewById(R.id.ic_back);
        ic_back.setVisibility(View.GONE);

        Intent i=getIntent();
        String username=i.getStringExtra("username");





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
                i.putExtra("username",username);
                startActivity(i);

            }
        });
        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Dashboard.this,GYM.class);
                i.putExtra("username",username);
                startActivity(i);

            }
        });

       cv_announcement.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i =new Intent(Dashboard.this,announcement.class);
               i.putExtra("username",username);
               startActivity(i);
           }
       });

        cv_ComplainBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Dashboard.this,Complain_Box.class);
                i.putExtra("username",username);
                startActivity(i);
            }
        });

        cv_messmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Dashboard.this,MessMenu.class);
                i.putExtra("username",username);
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
        TextView bts_email;

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottomsheet_dialog);

        Intent i=getIntent();
        String username=i.getStringExtra("username");

        bts_email=bottomSheetDialog.findViewById(R.id.bts_tv_email);
        bts_email.setText(username);
        LinearLayout logout = bottomSheetDialog.findViewById(R.id.ll_logout);
        LinearLayout aboutus=bottomSheetDialog.findViewById(R.id.ll_aboutus);
        LinearLayout rules=bottomSheetDialog.findViewById(R.id.ll_rules);

        bottomSheetDialog.show();



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent i =new Intent(Dashboard.this,login.class);
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
//    public void onBackPressed(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
//        builder.setTitle("Are you sure you want to exit from app ? ");
//
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                Dashboard.super.onBackPressed();
//                dialog.dismiss();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.cancel();
//            }
//        });
//
//        AlertDialog dialog = builder.create();
//    }
}