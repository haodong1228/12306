package com.example.nicha.a12306;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutappActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutapp);
        ExitApplication.getInstance().addActivity(this);
    }
}
