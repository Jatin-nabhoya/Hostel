package com.hfad.hostel;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Complain_Box extends AppCompatActivity {
    FloatingActionButton btn_add_complain;
    Button btn_send,btn_cancel;
    EditText edt_roomNo,edt_name,edt_complain;
    FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
    DatabaseReference reference=rootNode.getReference("Complain");
    RecyclerView recyclerView;

    Com_Adapter com_adapter;
    ArrayList<Com_Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_box);

        btn_add_complain=findViewById(R.id.btn_add_complain);
        recyclerView=findViewById(R.id.rv_ComplainBox);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        com_adapter =new Com_Adapter(list,this);
        recyclerView.setAdapter(com_adapter);
        recyclerView.scrollToPosition(list.size()-1);

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
}