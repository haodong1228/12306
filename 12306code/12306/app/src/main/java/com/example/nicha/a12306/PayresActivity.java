package com.example.nicha.a12306;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PayresActivity extends AppCompatActivity {
    Button _gototicres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payres);
        ExitApplication.getInstance().addActivity(this);
        _gototicres = (Button)findViewById(R.id.gototicres);

        _gototicres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayresActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
