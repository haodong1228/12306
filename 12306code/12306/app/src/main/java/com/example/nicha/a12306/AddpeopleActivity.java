package com.example.nicha.a12306;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddpeopleActivity extends AppCompatActivity {
    Button _button_addp;
    EditText _edit1;
    EditText _edit2;
    EditText _edit3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpeople);
        ExitApplication.getInstance().addActivity(this);
        _button_addp = (Button)findViewById(R.id.button_addp);
        _edit1 = (EditText)findViewById(R.id.edit1);
        _edit2 = (EditText)findViewById(R.id.edit2);


        _button_addp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("people_data", MODE_PRIVATE).edit();
                //editor.putBoolean("是否登录", login);
                editor.putString("姓名", _edit1.getText().toString());
                editor.putString("身份证号", _edit2.getText().toString());
                editor.apply();
                Intent intent = new Intent();
                intent.putExtra("data_addpeople", _edit1.getText().toString()+"   "+_edit2.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
