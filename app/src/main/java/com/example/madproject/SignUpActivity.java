package com.example.madproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_login;
    private Button btn_signup;
    private EditText useremail;
    private EditText userpassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign up");
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        tv_login = findViewById(R.id.tv_login);
        btn_signup = findViewById(R.id.btn_signup);
        useremail = findViewById(R.id.et_useremail);
        userpassword = findViewById(R.id.et_password);

        tv_login.setOnClickListener(this);
        btn_signup.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.tv_login:

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

                break;

            case R.id.btn_signup:
                
                userRegister();

                break;

        }

    }

    private void userRegister() {

        String email = useremail.getText().toString().trim();
        String password = userpassword.getText().toString().trim();

        //checking the validity of the email
        if(email.isEmpty()) {
            useremail.setError("Enter an email address");
            useremail.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            useremail.setError("Enter a valid email address");
            useremail.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty()) {
            userpassword.setError("Enter a password");
            userpassword.requestFocus();
            return;
        }
        if(password.length() < 6) {
            userpassword.setError("Minimum length is six");
            userpassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(SignUpActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);

                } else {

                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(SignUpActivity.this, "Already have an account.", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(SignUpActivity.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });


    }
}
