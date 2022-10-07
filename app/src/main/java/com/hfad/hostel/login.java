package com.hfad.hostel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    Button login;
    TextView forgot_pass;
    TextInputLayout username, password;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressBar progressBar;
    android.app.ProgressDialog ProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgot_pass = findViewById(R.id.forgot_pass);
        login = findViewById(R.id.btn_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        progressBar =findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        ProgressDialog = new ProgressDialog(this);





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val=username.getEditText().getText().toString().trim();
                String val1=password.getEditText().getText().toString().trim();

                

                if(val.equals("") && val1.equals("")){
                    username.setError("Field can't be empty");
                    password.setError("Field can't be empty");
                }
                else if(val.equals("")){
                    username.setError("Field can't be empty");
                }
                else if(val1.equals("")){
                    password.setError("Field can't be empty");
                }

                else{
                    username.setError(null);
                    username.setErrorEnabled(false);
                    password.setError(null);
                    password.setErrorEnabled(false);
                    performLogin();
                }

            }
        });

        forgot_pass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i=new Intent(login.this,forgotPassword_1.class);
                startActivity(i);
                finish();

            }


        });
    }
//    private void forgotPassword(){
//         String user=username.getEditText().getText().toString().trim();
//
//                mAuth.sendPasswordResetEmail(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(login.this, "Done Sent.", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(login.this, "Error Occurred.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                        Toast.makeText(login.this, e.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }


    private void performLogin() {
        String user=username.getEditText().getText().toString();
        String pass=password.getEditText().getText().toString();
//        ProgressDialog.setMessage("Please wait while login...");
//        ProgressDialog.setTitle("Login");
//        ProgressDialog.setCanceledOnTouchOutside(false);
//        ProgressDialog.show();

        mAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i=new Intent(login.this,Dashboard.class);
                    i.putExtra("username",user);
                    startActivity(i);
                    finish();
                    Toast.makeText(login.this,"Login Successfully.",Toast.LENGTH_SHORT).show();
                    ProgressDialog.hide();
                }
                else{
                    Toast.makeText(login.this,""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null)
        if(mUser != null) {
            // User is signed in
            Intent i = new Intent(login.this, Dashboard.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        else{

            Intent i = new Intent(login.this, login.class);
        }

    }
}

