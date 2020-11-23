package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private Button confirmButton, cancelButton;
    public EditText mEmail, fName, lName, password;
    DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmail = (EditText)findViewById(R.id.email1);
        String regEmail = mEmail.getText().toString();

        fName = (EditText)findViewById(R.id.firstName1);
        String regFirstName = fName.getText().toString();

        lName = (EditText)findViewById(R.id.lastName1);
        String regLastName = lName.getText().toString();

        password = (EditText)findViewById(R.id.password3);
        String regPass = password.getText().toString();

        confirmButton = (Button) findViewById(R.id.confirmBtn);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (regFirstName.equals(null) && regLastName.equals(null)&&regEmail.equals(null)&&regPass.equals(null)) {
                    toastMessage("Fill all entries");
                } else {
                    UserData currentUser = new UserData(fName.getText().toString(),
                            lName.getText().toString(),
                            mEmail.getText().toString(),
                            password.getText().toString());
                    boolean status = dbHelper.addUser(currentUser);
                    if (status) {
                        toastMessage("Successful Registration");
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    } else {
                        toastMessage("Unable to Register");
                    }
                }
            }
        });

        cancelButton = (Button) findViewById(R.id.cancelBtn);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (SignUp.this, MainActivity.class));
            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}