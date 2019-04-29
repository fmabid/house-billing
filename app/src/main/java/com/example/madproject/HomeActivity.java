package com.example.madproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    private TextView newHouse;
    private EditText tot_floor,tot_unit,house_address;
    private Button addHouse;

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.setTitle("Dashboard");
        databaseReference = FirebaseDatabase.getInstance().getReference("house");

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
             uid = user.getUid();
        }

        newHouse = findViewById(R.id.tv_addNewHome);
        tot_floor= findViewById(R.id.et_nmFloor);
        tot_unit= findViewById(R.id.et_nmUnit);
        house_address= findViewById(R.id.et_houseAddress);
        addHouse = findViewById(R.id.btn_addHouse);

        newHouse.setOnClickListener(this);
        addHouse.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.tv_addNewHome:

                tot_floor.setVisibility(View.VISIBLE);
                tot_unit.setVisibility(View.VISIBLE);
                house_address.setVisibility(View.VISIBLE);
                addHouse.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_addHouse:

                saveHouse();
                break;

        }

    }

    private void saveHouse() {

        String floors = tot_floor.getText().toString().trim();
        String units = tot_unit.getText().toString().trim();
        String address = house_address.getText().toString().trim();

        String key = databaseReference.push().getKey();

        House house = new House(floors,units,address,uid);

        databaseReference.child(key).setValue(house);

        Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();




    }
}
