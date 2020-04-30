package com.example.nicha.a12306;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText _edit1;
    EditText _edit2;
    Button _button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ExitApplication.getInstance().addActivity(this);
        _edit1 = (EditText)findViewById(R.id.edit1);
        _edit2 = (EditText)findViewById(R.id.edit2);
        _button1 = (Button)findViewById(R.id.button1);
        _button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = _edit1.getText().toString();
                String password = _edit2.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection connection = null;
                        BufferedReader reader = null;
                        try{
                            URL url = new URL("http://101.132.127.131/addtestusers.aspx");
                            connection = (HttpURLConnection)url.openConnection();
                            connection.setRequestMethod("POST");
                            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                            out.writeBytes("username="+_edit1.getText().toString()+"&password="+_edit2.getText().toString());
                            connection.setConnectTimeout(8000);
                            connection.setReadTimeout(8000);
                            InputStream in = connection.getInputStream();
                            //下面对获取到的输入流进行读取
                            reader = new BufferedReader(new InputStreamReader(in));
                            StringBuilder response = new StringBuilder();
                            String line;
                            while((line = reader.readLine()) != null){
                                response.append(line);
                            }
                            showResponse(response.toString());
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            if (reader != null){
                                try{
                                    reader.close();
                                }catch (IOException e){
                                    e.printStackTrace();
                                }
                            }
                            if(connection != null){
                                connection.disconnect();
                            }
                        }
                    }
                }).start();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, "注册成功！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
