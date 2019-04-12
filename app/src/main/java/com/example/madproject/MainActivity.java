package com.example.madproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements UserFormFragment.FormFragmentListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /*  change the sign in/out activity if validation done    */
    @Override
    public void changeActivity(String uname, String pass) {
        if (logedIn(uname, pass)) {
            Intent startNextActivity = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(startNextActivity);
        }
    }

    /*  Validation  */
    public boolean logedIn(String uname, String pass) {
        return uname.equals("abid") && pass.equals("123");
    }
}
