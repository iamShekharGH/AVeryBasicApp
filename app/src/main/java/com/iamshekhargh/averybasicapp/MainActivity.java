package com.iamshekhargh.averybasicapp;

import android.content.Intent;
import android.os.Bundle;

import com.iamshekhargh.averybasicapp.ui.HomeActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, HomeActivity.class));
        finish();
        //          "package_name": "com.iamshekhargh.fcmessage",

    }
}