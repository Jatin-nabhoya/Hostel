package com.hfad.hostel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MessMenu extends AppCompatActivity {
    ImageView image_menu;
    Spinner spinner_menu;
    ImageView ic_back , Profile ;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Toolbar toolbar;
    TextView txttoolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_menu);


        image_menu=findViewById(R.id.image_menu);
        spinner_menu=findViewById(R.id.spinner_menu);

        Profile = findViewById(R.id.Profile);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        //toolbar
        toolbar = findViewById(R.id.toolbar);
        txttoolbar = findViewById(R.id.txt_toolbar);
        txttoolbar.setText(R.string.MessMenu);
        //toolbar back button
        ic_back = findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




        spinner_menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("MessMenu");
                String menu=spinner_menu.getSelectedItem().toString();

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.child(spinner_menu.getSelectedItem().toString()).getValue(String.class);

                        Picasso.get().load(value).into(image_menu);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Toast.makeText(MessMenu.this, "Fail to found image!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                Intent i = new Intent(MessMenu.this, login.class);
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