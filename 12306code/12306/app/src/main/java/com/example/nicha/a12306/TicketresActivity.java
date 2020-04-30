package com.example.nicha.a12306;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TicketresActivity extends AppCompatActivity {
    TextView _pasmes;
    TextView _trimes;
    Button _chepiaoyuding;
    Button _shanglvfuwu;
    Button _wode12306;
    String _start;
    String _arrive;
    String _start_time;
    String _arrive_time;
    String _take_time;
    String _xibie;
    String _checi;
    String _date;
    String _name;
    String _id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketres);
        ExitApplication.getInstance().addActivity(this);
        _pasmes = (TextView)findViewById(R.id.pasmes);
        _trimes = (TextView)findViewById(R.id.trimes);
        SharedPreferences pref = getSharedPreferences("dingdan_data", MODE_PRIVATE);
        _start = pref.getString("出发站", "");
        _arrive = pref.getString("到达站", "");
        _start_time = pref.getString("出发时间","");
        _checi = pref.getString("车次","");
        _xibie = pref.getString("席别","");
        _take_time = pref.getString("花费时间","");
        _arrive_time = pref.getString("到达时间","");
        _date = pref.getString("出发日期","");
        SharedPreferences pref2 = getSharedPreferences("people_data", MODE_PRIVATE);
        _name = pref2.getString("姓名", "");
        _id = pref2.getString("身份证号", "");
        _pasmes.setText("姓名："+_name+"\n身份证号："+_id);
        _trimes.setText("出发日期："+_date+"\n车次："+_checi+"\n席别："+_xibie+"\n出发站："+_start
                +"\n到达站："+_arrive+"\n出发时间："+_start_time+"\n花费时间："+_take_time+"\n到达时间："+_arrive_time);
        _chepiaoyuding = (Button)findViewById(R.id.chepiaoyuding);
        _shanglvfuwu = (Button)findViewById(R.id.shanglvfuwu);
        _wode12306 = (Button)findViewById(R.id.wode12306);

        _chepiaoyuding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TicketresActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        _shanglvfuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TicketresActivity.this, ShanglvActivity.class);
                startActivity(intent);
            }
        });
        _wode12306.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TicketresActivity.this, My12306Activity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && event.getRepeatCount() == 0) {
            //详细的操作代码
            new AlertDialog.Builder(this)


                    .setTitle("确定退出程序么")



                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        @Override

                        public void onClick(DialogInterface dialog, int which) {
                        }

                    })

                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            ExitApplication.getInstance().exit(TicketresActivity.this);
                            finish();//
                        }

                    }).show();

            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
