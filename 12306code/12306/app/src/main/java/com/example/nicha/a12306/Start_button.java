package com.example.nicha.a12306;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Start_button extends AppCompatActivity {
    Button _shanghai;
    Button _beijing;
    Button _hangzhou;
    Button _yiwu;
    EditText _startbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_button);
        ExitApplication.getInstance().addActivity(this);
        _shanghai = (Button)findViewById(R.id.button_shanghai);
        _beijing = (Button)findViewById(R.id.button_beijing);
        _hangzhou = (Button)findViewById(R.id.button_hangzhou);
        _yiwu = (Button)findViewById(R.id.button_yiwu);
        _startbtn = (EditText)findViewById(R.id.startbtn);
        _shanghai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data1 = _shanghai.getText().toString();
                Intent intent = new Intent(Start_button.this, MainActivity.class);
                intent.putExtra("data_start", data1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        _beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data1 = _beijing.getText().toString();
                Intent intent = new Intent(Start_button.this, MainActivity.class);
                intent.putExtra("data_start", data1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        _hangzhou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data1 = _hangzhou.getText().toString();
                Intent intent = new Intent(Start_button.this, MainActivity.class);
                intent.putExtra("data_start", data1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        _yiwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data1 = _yiwu.getText().toString();
                Intent intent = new Intent(Start_button.this, MainActivity.class);
                intent.putExtra("data_start", data1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}