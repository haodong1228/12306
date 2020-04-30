package com.example.nicha.a12306;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    EditText _edit1;
    EditText _edit2;
    Button _button1;
    Button _button2;
    private Handler handler = null;
    public String responseData = null;
    int flag1 = 0;
    int flag2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ExitApplication.getInstance().addActivity(this);
        _edit1 = (EditText)findViewById(R.id.edit1);
        _edit2 = (EditText)findViewById(R.id.edit2);
        _button1 = (Button)findViewById(R.id.button1);
        _button2 = (Button)findViewById(R.id.button2);
        handler = new Handler();
        _button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        _button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url("http://101.132.127.131:8080/User.jsp")
                                    .build();
                            Response response = client.newCall(request).execute();
                            responseData = response.body().string();
                            handler.post(runnableUi2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

    }
    Runnable runnableUi2 = new Runnable() {
        @Override
        public void run() {
            //更新界面
            Gson gson = new Gson();
            List<UserMessageFromWeb> appList = gson.fromJson(responseData, new TypeToken<List<UserMessageFromWeb>>() {
            }.getType());
            for (UserMessageFromWeb app : appList) {
                SharedPreferences.Editor editor = getSharedPreferences("login_data", MODE_PRIVATE).edit();
                if(app.getUsername().trim().equals(_edit1.getText().toString()) && app.getPassword().trim().equals(_edit2.getText().toString())){
                    editor.putBoolean("是否登录", true);
                    editor.apply();
                    Intent intent2 = new Intent(LoginActivity.this, JudgeActivity.class);
                    startActivity(intent2);
                    finish();
                    break;
                }else{
                    editor.putBoolean("是否登录", false);
                    editor.apply();
                    Intent intent3 = new Intent(LoginActivity.this, JudgeActivity.class);
                    startActivity(intent3);
                    finish();
                   // Toast.makeText(LoginActivity.this, "用户名或密码错误！",Toast.LENGTH_SHORT).show();
                }

            }
        }
    };

}
