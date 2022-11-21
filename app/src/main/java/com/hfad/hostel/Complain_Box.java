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

public class Complain_Box extends AppCompatActivity {
    ImageView ic_back , Profile ;
    Toolbar toolbar;
    TextView txttoolbar;
    FloatingActionButton btn_add_complain;
    Button btn_send,btn_cancel;
    EditText edt_roomNo,edt_name,edt_complain;
    FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
    DatabaseReference reference=rootNode.getReference("Complain");
    RecyclerView recyclerView;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    Com_Adapter com_adapter;
    ArrayList<Com_Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_box);

        txttoolbar = findViewById(R.id.txt_toolbar);
        Profile = findViewById(R.id.Profile);
        btn_add_complain=findViewById(R.id.btn_add_complain);
        recyclerView=findViewById(R.id.rv_ComplainBox);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        com_adapter =new Com_Adapter(list,this);
        recyclerView.setAdapter(com_adapter);
        recyclerView.scrollToPosition(list.size()-1);

        //toolbar
        toolbar = findViewById(R.id.toolbar);
        txttoolbar = findViewById(R.id.txt_toolbar);
        txttoolbar.setText(R.string.Room_Maintence_box);
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
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Com_Model com_model=dataSnapshot.getValue(Com_Model.class);
                    list.add(com_model);

                }
                com_adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
 
        btn_add_complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(Complain_Box.this);
                dialog.setContentView(R.layout.add_complain_lay);
                dialog.setCanceledOnTouchOutside(false);

                btn_send=dialog.findViewById(R.id.btn_send);
                btn_cancel=dialog.findViewById(R.id.btn_cancel);
                edt_roomNo=dialog.findViewById(R.id.edt_roomNo);
                edt_name=dialog.findViewById(R.id.edt_fname);
                edt_complain=dialog.findViewById(R.id.edt_complain);

                btn_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String roomNo=edt_roomNo.getText().toString().trim();
                        String name=edt_name.getText().toString().trim();
                        String complain=edt_complain.getText().toString().trim();
                        list.clear();

                        if(!roomNo.equals("") && !name.equals("") && !complain.equals("")){
                            HelperClass helperClass=new HelperClass(roomNo,name,complain);
                            reference.child(name).setValue(helperClass);
                            dialog.dismiss();

                        }else{
                            edt_complain.setError("Field can't be empty!!");
                            edt_roomNo.setError("Field can't be empty!!");
                            edt_name.setError("Field can't be empty!!");
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
                Intent i = new Intent(Complain_Box.this, login.class);
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