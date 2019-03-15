package com.example.android.farmmlai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        public void loginAct(View v)
        {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }


        public void signupAct(View v)
        {
            Intent intent = new Intent(MainActivity.this, Signup.class);
            startActivity(intent);
        }
    }
}
