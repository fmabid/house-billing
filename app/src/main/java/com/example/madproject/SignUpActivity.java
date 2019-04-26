package com.example.madproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    Button btn_login;
    Button btn_signup;
    EditText username;
    EditText password;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        name = findViewById(R.id.et_woner_name);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString();
                String pass = password.getText().toString();
                String fullName = name.getText().toString();

                // Log.d("Clicked -->   ", uname + " " + pass);
                if (sugnUp(uname, pass, fullName)) {
                    Intent startNextActivity = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(startNextActivity);
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNextActivity = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(startNextActivity);
            }
        });
    }


    /*  Sign up validation  */
    public boolean sugnUp(String uname, String pass, String fullName) {
        /*
        *
        *   Write here the query for signing up.
        *
        * */

        return true;
    }
}
