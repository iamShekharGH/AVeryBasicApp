package com.iamshekhargh.averybasicapp.ui;

import android.os.Bundle;

import com.iamshekhargh.averybasicapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

public class ResultActivity extends AppCompatActivity {
    AppCompatTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView = findViewById(R.id.resultactivity_text);
    }
}