package com.example.android.farmmlai;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    EditText txtemail,txtpassword,txtconfirmPassword;
    Button btnregister;
    ProgressBar progressbar;

    private FirebaseAuth firebaseAuthObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Signup Form");

        txtemail = (EditText)findViewById(R.id.input_email);
        txtpassword = (EditText)findViewById(R.id.input_password);
        txtconfirmPassword = (EditText)findViewById(R.id.input_confirmPassword);
        btnregister = (Button) findViewById(R.id.btn_signup);
        progressbar = (ProgressBar) findViewById(R.id.progressBar);

        firebaseAuthObj = FirebaseAuth.getInstance();


        btnregister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = txtemail.getText().toString().trim();
                String password = txtpassword.getText().toString().trim();
                String confirmpassword = txtconfirmPassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Signup.this,"Please Enter Email",Toast.LENGTH_LONG);
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Signup.this,"Please Enter Password",Toast.LENGTH_LONG);
                    return;
                }
                if(TextUtils.isEmpty(confirmpassword)){
                    Toast.makeText(Signup.this,"Please Confirm Password",Toast.LENGTH_LONG);
                    return;
                }

                if(password.length()<8){
                    Toast.makeText(Signup.this,"Password too Short!",Toast.LENGTH_LONG);
                    return;
                }

                progressbar.setVisibility(View.VISIBLE);

                if(password.equals(confirmpassword)){
                    firebaseAuthObj.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressbar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(Signup.this,"Registration Sucessful",Toast.LENGTH_LONG);
                                    } else {
                                        Toast.makeText(Signup.this,"Authentication Failed",Toast.LENGTH_LONG);
                                    }
                                }
                            });
                }
            }
        });
    }
}
