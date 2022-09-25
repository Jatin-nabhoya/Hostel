package com.hfad.hostel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class forgotPassword_1 extends AppCompatActivity {
    ImageView ic_back;
    EditText email;
    FirebaseAuth mAuth;
    MaterialButton submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password1);

        ic_back =findViewById(R.id.ic_back);
        email=findViewById(R.id.email);
        submit=findViewById(R.id.btn_submit);
        mAuth = FirebaseAuth.getInstance();


        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=email.getText().toString().trim();
                if(mail.equals("")){
                    email.setError("Field can't be Empty!");
                }else{
                    email.setError(null);


                    forgotPassword();}

            }
        });


    }

    private void forgotPassword(){
        String mail=email.getText().toString().trim();

        mAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Intent i=new Intent(forgotPassword_1.this,login.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(forgotPassword_1.this, "Email Sent.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i=new Intent(forgotPassword_1.this,forgotPassword_1.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(forgotPassword_1.this, "Error Occurred.", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(forgotPassword_1.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}