package com.hfad.hostel;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SOAC_Club_Info extends AppCompatActivity {
    TextView tv_oName,tv_oType,tv_oObjective,tv_oMember,tv_oFAdviser,tv_oFees,tv_oRLink,tv_cname;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soac_club_info);

        tv_oFAdviser=findViewById(R.id.tv_oFAdviser);
        tv_oFees=findViewById(R.id.tv_oFees);
        tv_oMember=findViewById(R.id.tv_oMember);
        tv_oName=findViewById(R.id.tv_oName);
        tv_oType=findViewById(R.id.tv_oType);
        tv_oObjective=findViewById(R.id.tv_oObjective);
        tv_oRLink=findViewById(R.id.tv_oRLink);
        tv_cname=findViewById(R.id.tv_cname);

        reference=FirebaseDatabase.getInstance().getReference("SOAC");
        reference.child("RKU RANGERS FOOTBALL").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot dataSnapshot=task.getResult();
                        String oname=String.valueOf(dataSnapshot.child("oname").getValue());
                        String otype=String.valueOf(dataSnapshot.child("otype").getValue());
                        String oobj=String.valueOf(dataSnapshot.child("oobj").getValue());
                        String fadvisor=String.valueOf(dataSnapshot.child("fadvisor").getValue());
                        String fees=String.valueOf(dataSnapshot.child("fees").getValue());
                        String link=String.valueOf(dataSnapshot.child("link").getValue());
                        String omember=String.valueOf(dataSnapshot.child("omember").getValue());
                        String cname=String.valueOf(dataSnapshot.child("cname").getValue());
                        tv_oName.setText(oname);
                        tv_oType.setText(otype);
                        tv_oFAdviser.setText(fadvisor);
                        tv_oFees.setText(fees);
                        tv_oMember.setText(omember);
                        tv_oObjective.setText(oobj);
                        tv_oRLink.setText(link);
                        tv_cname.setText(cname);
                        
                    }else {
                        Toast.makeText(SOAC_Club_Info.this, "Club doesn't exit!", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(SOAC_Club_Info.this, "Failed to read!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}