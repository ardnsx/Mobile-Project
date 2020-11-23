package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class SignIn extends AppCompatActivity {

    EditText text;
    Button btn;
    ListView list;
    ArrayList <String> arrayList;
    ArrayAdapter<String> adapter;

    DatabaseHelper mDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mDatabaseHelper = new DatabaseHelper(this);

        text = (EditText) findViewById(R.id.list_item);
        btn = (Button) findViewById(R.id.button4);
        list = (ListView) findViewById(R.id.list_view);

        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<>(SignIn.this, android.R.layout.simple_list_item_1, arrayList);

        list.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = text.getText().toString();

                if (text.length() != 0) {
                    arrayList.add(note);
                    adapter.notifyDataSetChanged();
                }
                else{
                    toastMessage("Enter note.");
                }
            }
        });
    }


    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}