package com.example.mehed.sqlemailvalidation.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mehed.sqlemailvalidation.R;
import com.example.mehed.sqlemailvalidation.databases.DataBaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameEt, passwordEt;
    private Button signInBtn, createAccountBtn;

    private DataBaseHelper dataBaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEt = findViewById(R.id.etUserName);
        passwordEt = findViewById(R.id.etPassword);
        signInBtn = findViewById(R.id.btSignIn);
        createAccountBtn = findViewById(R.id.btCreateAccount);

        signInBtn.setOnClickListener(this);
        createAccountBtn.setOnClickListener(this);

        dataBaseHelper = new DataBaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();

        if (v.getId() == R.id.btSignIn) {
            boolean result = dataBaseHelper.checkEmailPassword(username, password);

            if (result) {
                Intent intent = new Intent(this, WellcomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Username or password is not correct", Toast.LENGTH_SHORT).show();
            }

        } else if (v.getId() == R.id.btCreateAccount) {
            Intent intent = new Intent(MainActivity.this, EmailSignUpActivity.class);
            startActivity(intent);
        }
    }
}
