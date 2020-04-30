package com.example.nicha.a12306;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SelectActivity extends AppCompatActivity {
    public List<TrainSomeMessage> trainList = new ArrayList<>();
    private int position;
    private String date;
    private String start;
    private String arrive;
    private Handler handler = null;
    public String responseData = null;
    TextView _start;
    TextView _start_time;
    TextView _arrive;
    TextView _arrive_time;
    TextView _message_people;
    Button _checi;
    Button _take_time;
    Button _addpeople;
    Button _button01;
    Button _button_an1;
    Button _button_an2;
    Button _button_an3;
    Button _button1;
    Button _chepiaoyuding;
    Button _shanglvfuwu;
    Button _wode12306;
    Button _dingdanchexun;
    int flag1=0;
    int flag2=0;
    int flag3=0;
    ScrollView _scrollview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ExitApplication.getInstance().addActivity(this);
        _arrive = (TextView)findViewById(R.id.arrive);
        _arrive_time = (TextView)findViewById(R.id.arrive_time);
        _start = (TextView)findViewById(R.id.start);
        _start_time = (TextView)findViewById(R.id.start_time);
        _checi = (Button)findViewById(R.id.checi);
        _take_time = (Button)findViewById(R.id.take_time);
        _addpeople = (Button)findViewById(R.id.addpeople);
        _scrollview = (ScrollView)findViewById(R.id.scrollview);
        _message_people = (TextView)findViewById(R.id.message_people);
        _button01 = (Button)findViewById(R.id.button01);
        _button1 = (Button)findViewById(R.id.button1);
        _button_an1 = (Button)findViewById(R.id.an1);
        _button_an2 = (Button)findViewById(R.id.an2);
        _button_an3 = (Button)findViewById(R.id.an3);
        _chepiaoyuding = (Button)findViewById(R.id.chepiaoyuding);
        _shanglvfuwu = (Button)findViewById(R.id.shanglvfuwu);
        _wode12306 = (Button)findViewById(R.id.wode12306);
        _dingdanchexun = (Button)findViewById(R.id.dingdanchaxun);

        SharedPreferences pref_position = getSharedPreferences("position_data", MODE_PRIVATE);
        position = pref_position.getInt("position", 0);
        SharedPreferences pref_date = getSharedPreferences("just_date_data", MODE_PRIVATE);
        date = pref_date.getString("当前日期", "");
        SharedPreferences pref = getSharedPreferences("all_data", MODE_PRIVATE);
        start = pref.getString("出发站", "");
        arrive = pref.getString("到达站", "");
        handler = new Handler();
        new Thread() {
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://101.132.127.131:8080/Test.jsp")
                            .build();
                    Response response = client.newCall(request).execute();
                    responseData = response.body().string();
                    //parseJSONWithGSON(responseData);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //更新界面
                            Gson gson = new Gson();
                            List<TrainFromWeb> appList = gson.fromJson(responseData, new TypeToken<List<TrainFromWeb>>() {
                            }.getType());
                            for (TrainFromWeb app : appList) {
                                String temp1 = null;
                                String temp2 = null;
                                temp1 = sonStringOperation(start, app.getStart_web().trim());
                                temp2 = sonStringOperation(arrive, app.getArrive_web().trim());
                                if(app.getStart_web().trim().equals(temp1) && app.getArrive_web().trim().equals(temp2) && app.getDate().trim().equals(date)){
                                    trainList.add(new TrainSomeMessage(app.getCheci_web().trim(),temp1,app.getStart_time_web().trim(),
                                            app.getTake_time_web().trim(),temp2,app.getArrive_time_web().trim()));
                                }
                            }
                            _button01.setText(date);
                            _start.setText(trainList.get(position).getStart());
                            _start_time.setText(trainList.get(position).getStart_time());
                            _arrive.setText(trainList.get(position).getArrive());
                            _arrive_time.setText(trainList.get(position).getArrive_time());
                            _checi.setText(trainList.get(position).getCheci());
                            _take_time.setText(trainList.get(position).getTake_time());
                            _scrollview.setVisibility(View.VISIBLE);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        _addpeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, AddpeopleActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        _button_an1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag2 == 1 || flag3 == 1){
                    flag1 = 0;
                }
                if(flag1 == 0){
                    _button_an2.setBackgroundColor(Color.rgb(255,255,255));
                    _button_an3.setBackgroundColor(Color.rgb(255,255,255));
                    _button_an1.setBackgroundColor(Color.rgb(90,170,220));
                    flag2 = 0;
                    flag3 = 0;
                    flag1 = 1;
                }
            }
        });
        _button_an2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag1 == 1 || flag3 == 1){
                    flag2 = 0;
                }
                if(flag2 == 0){
                    _button_an1.setBackgroundColor(Color.rgb(255,255,255));
                    _button_an3.setBackgroundColor(Color.rgb(255,255,255));
                    _button_an2.setBackgroundColor(Color.rgb(90,170,220));
                    flag1 = 0;
                    flag3 = 0;
                    flag2 = 1;
                }
            }
        });
        _button_an3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag1 == 1 || flag2 == 1){
                    flag3 = 0;
                }
                if(flag3 == 0){
                    _button_an1.setBackgroundColor(Color.rgb(255,255,255));
                    _button_an2.setBackgroundColor(Color.rgb(255,255,255));
                    _button_an3.setBackgroundColor(Color.rgb(90,170,220));
                    flag1 = 0;
                    flag2 = 0;
                    flag3 = 1;
                }
            }
        });
        _button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("dingdan_data", MODE_PRIVATE).edit();
                //editor.putBoolean("是否登录", login);
                editor.putString("出发站", _start.getText().toString());
                editor.putString("车次", _checi.getText().toString());
                editor.putString("到达站", _arrive.getText().toString());
                editor.putString("出发日期", _button01.getText().toString());
                editor.putString("出发时间", _start_time.getText().toString());
                editor.putString("花费时间", _take_time.getText().toString());
                editor.putString("到达时间", _arrive_time.getText().toString());
                String xibie = null;
                if(flag1 == 1){
                    xibie = "商务座";
                }
                if(flag2 == 1){
                    xibie = "一等座";
                }
                if(flag3 == 1){
                    xibie = "二等座";
                }
                editor.putString("席别", xibie);
                editor.apply();
                Intent intent = new Intent(SelectActivity.this, PayresActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private static String sonStringOperation(String s2, String s) {
        String temp; // 用来交换字符串，使s.length()>s2.length
        String s3;// 用于存储截取后的字符串
        if (s.length() < s2.length()) {//比较s与s2长度，将较长的字符串赋给s
            temp = s2;
            s2 = s;
            s = temp;
        }
        int count = s2.length();//将较短字符串的长度赋值给count
        int sum = (count + 1) * count / 2;//计算得出所有可能结果的最大值sum
        String[] sonArray = new String[sum];//分配sum个空间给sonArray数组
        int arrIndex = 0;//sonArray数组下标
        if (s.indexOf(s2) != -1) {//如果s2为s的子字符串
            return s;
        }else
            return null;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1 :
                if(resultCode == RESULT_OK){
                    String returnedData1 = data.getStringExtra("data_addpeople");
                    _message_people.setText(returnedData1);
                    _message_people.setVisibility(View.VISIBLE);
                }
                break;
            default:
        }
    }
}
