package com.hfad.hostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    public Button login;
    TextView forgot_pass;
    public TextInputLayout username, password;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressBar progressBar;
    android.app.ProgressDialog ProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgot_pass=findViewById(R.id.forgot_pass);
        login = findViewById(R.id.btn_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        progressBar =findViewById(R.id.progressBar);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        ProgressDialog=new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                performLogin();
                validateusername();
                validatepassword();
            }
        });

        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(login.this,forgotPassword_1.class);
                startActivity(i);
            }
        });
    }

    private Boolean validateusername(){
        String val=username.getEditText().getText().toString();
        if(val==null){
            username.setError("Field can't be empty");
            return false;
        }
        else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatepassword(){
        String val=password.getEditText().getText().toString();
        if(val==null){
            username.setError("Field can't be empty");
            return false;
        }
        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

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
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    Toast.makeText(login.this,"Login Successfully.",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
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
        // Check if user is signed in (non-null) and update UI accordingly.
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


    //Sign out user
//FirebaseAuth.getInstance().signOut();