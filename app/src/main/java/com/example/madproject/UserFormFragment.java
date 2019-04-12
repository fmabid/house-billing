package com.example.madproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class UserFormFragment extends Fragment {
    private Button btn_login;
    private EditText username;
    private EditText password;

    FormFragmentListener activityCommander;

    public interface FormFragmentListener{
        public void changeActivity(String username, String password);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityCommander = (FormFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_form_fragment, container, false);

        btn_login = (Button) view.findViewById(R.id.btn_login);
        username = (EditText) view.findViewById(R.id.et_username);
        password = view.findViewById(R.id.et_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked(v);
            }
        });

        return view;
    }

    public void buttonClicked(View view) {
        activityCommander.changeActivity(username.getText().toString(), password.getText().toString());
        /*if (logedIn(uname, pass)) {
            Intent startNextActivity = new Intent(UserFormFragment.this, HomeActivity.class);
            startActivity(startNextActivity);
        }*/
    }
}