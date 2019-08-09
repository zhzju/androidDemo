package com.example.myapplication.SnipperView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.myapplication.R;

public class SnipperActivity extends AppCompatActivity {

    private Spinner SP_1;

    private String[] SnipperList = {"钟骅","曹贺","小钟钟","小贺贺"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snipper);

        SP_1 = findViewById(R.id.SP_1);
        SP_1.setAdapter(new MySpinnerAdaptor(SnipperActivity.this,SnipperList));
    }
}
