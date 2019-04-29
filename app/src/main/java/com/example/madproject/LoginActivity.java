package com.example.madproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login;
    private TextView tv_signup;
    private EditText lin_email;
    private EditText lin_password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Log in");

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        btn_login = findViewById(R.id.btn_login);
        tv_signup = findViewById(R.id.tv_signup);
        lin_email = findViewById(R.id.et_user_email);
        lin_password = findViewById(R.id.et_user_password);

        tv_signup.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.tv_signup:

                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);

                break;

            case R.id.btn_login:
                userLogin();

                break;

        }

    }

    private void userLogin() {

        String email = lin_email.getText().toString().trim();
        String password = lin_password.getText().toString().trim();

        //checking the validity of the email
        if(email.isEmpty()) {
            lin_email.setError("Enter an email address");
            lin_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            lin_email.setError("Enter a valid email address");
            lin_email.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty()) {
            lin_password.setError("Enter a password");
            lin_password.requestFocus();
            return;
        }
        if(password.length() < 6) {
            lin_password.setError("Minimum length is six");
            lin_password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(LoginActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                } else {

                        Toast.makeText(getApplicationContext(), "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
