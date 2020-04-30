package com.example.nicha.a12306;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class TimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        ExitApplication.getInstance().addActivity(this);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.radio_group);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                Intent intent = new Intent();
                intent.putExtra("data_return_time", radioButton.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}