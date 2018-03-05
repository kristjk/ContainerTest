package com.example.notandi.containertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View view){
        Button button = (Button) view;

        Intent intent;
        if (button.getId() == R.id.buttonContacts) {
            intent = new Intent(this, ContactsActivity.class);
            startActivity(intent);
        }

        if (button.getId() == R.id.buttonContactsLoad){
            intent = new Intent(this, ContactsLoaderActivity.class);
            startActivity(intent);
        }

        if (button.getId() == R.id.buttonCustomContacts){
            intent = new Intent(this, CustomContactsActivity.class);
            startActivity(intent);
        }
    }
}
