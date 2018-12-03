package com.example.mehed.sqlemailvalidation.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mehed.sqlemailvalidation.R;
import com.example.mehed.sqlemailvalidation.databases.DataBaseHelper;
import com.example.mehed.sqlemailvalidation.models.PersonDetail;

public class EmailSignUpActivity extends AppCompatActivity {

    private EditText username, email, password, name;
    //private Button signUp;

    private DataBaseHelper dataBaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_sgn_up);

        username = findViewById(R.id.etUserNameForSignUp);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPasswordForSignUp);
        name = findViewById(R.id.etName);
        //signUp = findViewById(R.id.btnSignUp); // no need to find this cz here we use xml onClick method

        dataBaseHelper = new DataBaseHelper(this);
    }

    public void Save(View view) {
        String user = username.getText().toString();
        String emailAdd = email.getText().toString();
        String pass = password.getText().toString();
        String myName = name.getText().toString();

        PersonDetail personDetails = new PersonDetail(myName, emailAdd, user, pass);
        long rowId = dataBaseHelper.insertData(personDetails);

        if (rowId > 0) {
            Toast.makeText(this, "DATA is Inserted", Toast.LENGTH_SHORT).show();

            //Clear all activity flag then jump to the MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "DATA is Not Inserted", Toast.LENGTH_SHORT).show();
        }
    }
}
