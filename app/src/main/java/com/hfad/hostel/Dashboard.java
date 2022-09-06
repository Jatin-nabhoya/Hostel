package com.hfad.hostel;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    ImageView Profile;
    CardView gatepass;
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
        gatepass=findViewById(R.id.gatepass);
        signout=findViewById(R.id.tv_signout);
        mAuth=FirebaseAuth.getInstance();
        out=findViewById(R.id.btn_signout);

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

       out.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mAuth.signOut();
               Intent i =new Intent(Dashboard.this,login.class);
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
}