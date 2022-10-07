package com.hfad.hostel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SOAC_Club_Info extends AppCompatActivity {
    ImageView ic_back ;
    Toolbar toolbar;
    TextView txttoolbar;
    TextView tv_oName, tv_oType, tv_oObjective, tv_oMember, tv_oFAdviser, tv_oFees, tv_oRLink, tv_cname;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soac_club_info);

        Intent i = getIntent();
        String clubName = i.getStringExtra("clubName");
//        String clubName1 = i.getStringExtra("RKU SMASHERS KILLER");


        toolbar = findViewById(R.id.toolbar);
        txttoolbar = findViewById(R.id.txt_toolbar);
        tv_oFAdviser = findViewById(R.id.tv_oFAdviser);
        tv_oFees = findViewById(R.id.tv_oFees);
        tv_oMember = findViewById(R.id.tv_oMember);
        tv_oName = findViewById(R.id.tv_oName);
        tv_oType = findViewById(R.id.tv_oType);
        tv_oObjective = findViewById(R.id.tv_oObjective);
        tv_oRLink = findViewById(R.id.tv_oRLink);
        tv_cname = findViewById(R.id.tv_cname);

        tv_cname.setText(clubName);
        txttoolbar.setText(R.string.soac);
        ic_back = findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        reference = FirebaseDatabase.getInstance().getReference("SOAC");

        reference.child(clubName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        String oname = String.valueOf(dataSnapshot.child("oname").getValue());
                        String otype = String.valueOf(dataSnapshot.child("otype").getValue());
                        String oobj = String.valueOf(dataSnapshot.child("oobj").getValue());
                        String fadvisor = String.valueOf(dataSnapshot.child("fadvisor").getValue());
                        String fees = String.valueOf(dataSnapshot.child("fees").getValue());
                        String link = String.valueOf(dataSnapshot.child("link").getValue());
                        String omember = String.valueOf(dataSnapshot.child("omember").getValue());

                        tv_oName.setText(oname);
                        tv_oType.setText(otype);
                        tv_oFAdviser.setText(fadvisor);
                        tv_oFees.setText(fees);
                        tv_oMember.setText(omember);
                        tv_oObjective.setText(oobj);
                        tv_oRLink.setText(link);


                    } else {
                        Toast.makeText(SOAC_Club_Info.this, "Club doesn't exit!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(SOAC_Club_Info.this, "Failed to read!", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}