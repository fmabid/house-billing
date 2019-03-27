package com.example.madproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn_login;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = findViewById(R.id.btn_login);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString();
                String pass = password.getText().toString();

                Log.d("Clicked -->   ", uname + " " + pass);
                if (logedIn(uname, pass)) {
                    Intent startNextActivity = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(startNextActivity);
                }
            }
        });
    }

    /*  Validation  */
    public boolean logedIn(String uname, String pass) {
        return uname.equals("abid") && pass.equals("123");
    }
}
