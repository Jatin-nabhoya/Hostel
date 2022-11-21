package com.hfad.hostel;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class announcement extends AppCompatActivity {
    ImageView ic_back , Profile ;
    Toolbar toolbar;
    TextView txttoolbar;
    FloatingActionButton btn_add_announcement;
    Button btn_send,btn_cancel;
    EditText edt_title, edt_disc;
    FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
    DatabaseReference reference=rootNode.getReference("Announcement");
    DatabaseReference reference1=rootNode.getReference("user");
    RecyclerView recyclerView;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    Ann_Adapter ann_adapter;
    ArrayList<Ann_Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);


        txttoolbar = findViewById(R.id.txt_toolbar);
        Profile = findViewById(R.id.Profile);
        btn_add_announcement = findViewById(R.id.btn_add_announcement);
        recyclerView = findViewById(R.id.rv_announcement);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        ann_adapter = new Ann_Adapter(list, this);
        recyclerView.setAdapter(ann_adapter);
        recyclerView.scrollToPosition(list.size() - 1);

        //toolbar
        toolbar = findViewById(R.id.toolbar);
        txttoolbar = findViewById(R.id.txt_toolbar);
        txttoolbar.setText(R.string.announcement);
        //toolbar back button
        ic_back = findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //bottomshitdialog
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Ann_Model ann_model = dataSnapshot.getValue(Ann_Model.class);
                    list.add(ann_model);

                }
                ann_adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn_add_announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(announcement.this);
                dialog.setContentView(R.layout.activity_add_anno_lay);
                dialog.setCanceledOnTouchOutside(false);

                btn_send = dialog.findViewById(R.id.btn_send);
                edt_title = dialog.findViewById(R.id.edt_title);
                edt_disc = dialog.findViewById(R.id.edt_disc);
                btn_cancel = dialog.findViewById(R.id.btn_cancel);

                btn_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String title = edt_title.getText().toString().trim();
                        String disc = edt_disc.getText().toString().trim();
                        list.clear();

                        if (!title.equals("") && !disc.equals("")) {
                            HelperClass helperClass = new HelperClass(title, disc);
                            reference.child(title).setValue(helperClass);
                            edt_title.setError(null);
                            edt_disc.setError(null);
                            dialog.dismiss();


                        } else {
                            edt_title.setError("Field can't be empty!!");
                            edt_disc.setError("Field can't be empty!!");
                        }

                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
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
                    Intent i = new Intent(announcement.this, login.class);
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