package com.example.nicha.a12306;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class JudgeActivity extends AppCompatActivity {
    boolean login = false;
    static int flag1 = 0;
    static int flag2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge);
        ExitApplication.getInstance().addActivity(this);
//        Toast.makeText(JudgeActivity.this, "正在为您查询登录信息，请稍后...", Toast.LENGTH_SHORT).show();
        SharedPreferences pref_login = getSharedPreferences("login_data", MODE_PRIVATE);
        login = pref_login.getBoolean("是否登录", false);
        if(login){
            if ( flag1 == 0){
                Toast.makeText(JudgeActivity.this, "正在为您查询登录信息，请稍后...", Toast.LENGTH_SHORT).show();
                Toast.makeText(JudgeActivity.this, "用户登录成功！", Toast.LENGTH_SHORT).show();
                flag1 = 3;
            }
            //通过一个时间控制函数Timer，在实现一个活动与另一个活动的跳转。
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    startActivity(new Intent(JudgeActivity.this, SelectActivity.class));
                    finish();
                }
            }, 2000);//这里停留时间为1000=1s。
        }else{
                if (  flag2 == 0){
                    Toast.makeText(JudgeActivity.this, "正在为您查询登录信息，请稍后...", Toast.LENGTH_SHORT).show();
                    Toast.makeText(JudgeActivity.this, "用户名或密码错误，或用户不存在!请重新登录或先注册用户！", Toast.LENGTH_SHORT).show();
                    flag2 = 3;
                }
              //  Toast.makeText(JudgeActivity.this, "用户名或密码错误，或用户不存在!请重新登录或先注册用户！", Toast.LENGTH_SHORT).show();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(new Intent(JudgeActivity.this, LoginActivity.class));
                        finish();
                    }
                }, 2000);//这里停留时间为1000=1s。

        }
      finish();
    }
}
