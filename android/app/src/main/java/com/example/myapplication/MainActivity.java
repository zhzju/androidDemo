package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.SnipperView.SnipperActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private Button login;
    private Button ListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CalculateActivity.class);
                startActivity(intent);
                finish();
            }}
        );

        ListView = findViewById(R.id.List_btn);
        ListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TencentCloudActivity.class);
                startActivity(intent);
            }
        });

    }
}
