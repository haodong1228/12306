package com.example.nicha.a12306;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.EOFException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ListActivity extends AppCompatActivity{
    private Handler handler = null;
    public List<Train> trainList = new ArrayList<>();
  //  public List<Car> carList = new ArrayList<>();
    public String responseData = null;
    TextView _show_place;
    TextView _show_date;
    Button _xiala;
    String start;
    String arrive;
    String date;
    Boolean login;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ExitApplication.getInstance().addActivity(this);
        //接收
        SharedPreferences pref = getSharedPreferences("all_data", MODE_PRIVATE);
        start = pref.getString("出发站", "");
        arrive = pref.getString("到达站", "");
        date = pref.getString("出发日期", "");
        login = pref.getBoolean("是否登录", false);
        editor = getSharedPreferences("just_date_data", MODE_PRIVATE).edit();
        editor.putString("当前日期", date);
        editor.apply();
        _show_place = (TextView)findViewById(R.id.show_place);
        _show_date = (TextView)findViewById(R.id.show_date);
        _show_place.setText(start + "---" + arrive);
        _show_date.setText(date);

        //登录

        //没有登录
        //创建属于主线程的handler
        handler = new Handler();
        Toast.makeText(ListActivity.this, "正在加载......", Toast.LENGTH_SHORT).show();
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
                                if((app.getDate().trim()).equals(_show_date.getText().toString()) && (app.getStart_web().trim()).equals(temp1) && (app.getArrive_web().trim()).equals(temp2)){
                                    trainList.add(new Train(app.getCheci_web(),R.drawable.start,app.getStart_web(),app.getStart_time_web(),
                                            R.drawable.card,app.getTake_time_web(),R.drawable.arrive,app.getArrive_web(),
                                            app.getArrive_time_web(),app.getShangwu_ticket_web(),app.getYideng_ticket_web(),app.getErdeng_ticket_web(),app.getWuzuo_ticket_web()));
                                }
                            }
                            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(ListActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            TrainAdapter adapter = new TrainAdapter(trainList);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Button before = (Button) findViewById(R.id.before);
        Button after = (Button) findViewById(R.id.after);
        before.setOnClickListener(new sendBeforeRequestOnClickListener());
        after.setOnClickListener(new sendAfterRequestOnClickListener());

    }
    class sendBeforeRequestOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //trainList.remove(0);
            trainList.clear();
            SharedPreferences pref_date = getSharedPreferences("just_date_data", MODE_PRIVATE);
            date = pref_date.getString("当前日期", "");
            String afterday = getSpecifiedDayBefore(date);
            editor = getSharedPreferences("just_date_data", MODE_PRIVATE).edit();
            editor.putString("当前日期", afterday);
            editor.apply();
            _show_date.setText(afterday);
            Toast.makeText(ListActivity.this, "正在加载......", Toast.LENGTH_SHORT).show();
            new Thread() {
                public void run() {
                    try {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://101.132.127.131:8080/Test.jsp")
                                .build();
                        Response response = client.newCall(request).execute();
                        responseData = response.body().string();
                        handler.post(runnableUi);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        // 构建Runnable对象，在runnable中更新界面
        Runnable runnableUi = new Runnable() {
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
                    if((app.getDate().trim()).equals(_show_date.getText().toString()) && (app.getStart_web().trim()).equals(temp1) && (app.getArrive_web().trim()).equals(temp2)){
                        trainList.add(new Train(app.getCheci_web(),R.drawable.start,app.getStart_web(),app.getStart_time_web(),
                                R.drawable.card,app.getTake_time_web(),R.drawable.arrive,app.getArrive_web(),
                                app.getArrive_time_web(),app.getShangwu_ticket_web(),app.getYideng_ticket_web(),app.getErdeng_ticket_web(),app.getWuzuo_ticket_web()));
                    }
                }
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                LinearLayoutManager layoutManager = new LinearLayoutManager(ListActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                TrainAdapter adapter = new TrainAdapter(trainList);
                recyclerView.setAdapter(adapter);
            }
        };
    }
    class sendAfterRequestOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
          //  trainList.remove(0);
            trainList.clear();
            SharedPreferences pref_date = getSharedPreferences("just_date_data", MODE_PRIVATE);
            date = pref_date.getString("当前日期", "");
            String afterday = getSpecifiedDayAfter(date);
            editor = getSharedPreferences("just_date_data", MODE_PRIVATE).edit();
            editor.putString("当前日期", afterday);
            editor.apply();
            _show_date.setText(afterday);
            Toast.makeText(ListActivity.this, "正在加载......", Toast.LENGTH_SHORT).show();
            new Thread() {
                public void run() {
                    try {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://101.132.127.131:8080/Test.jsp")
                                .build();
                        Response response = client.newCall(request).execute();
                        responseData = response.body().string();
                        handler.post(runnableUi);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        // 构建Runnable对象，在runnable中更新界面
        Runnable runnableUi = new Runnable() {
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
                    if((app.getDate().trim()).equals(_show_date.getText().toString()) && (app.getStart_web().trim()).equals(temp1) && (app.getArrive_web().trim()).equals(temp2)){
                        trainList.add(new Train(app.getCheci_web(),R.drawable.start,app.getStart_web(),app.getStart_time_web(),
                                R.drawable.card,app.getTake_time_web(),R.drawable.arrive,app.getArrive_web(),
                                app.getArrive_time_web(),app.getShangwu_ticket_web(),app.getYideng_ticket_web(),app.getErdeng_ticket_web(),app.getWuzuo_ticket_web()));
                    }
                }
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                LinearLayoutManager layoutManager = new LinearLayoutManager(ListActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                TrainAdapter adapter = new TrainAdapter(trainList);
                recyclerView.setAdapter(adapter);
            }
        };
    }
    public static String getSpecifiedDayBefore(String specifiedDay){
//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-1);

        String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }
    /**
     * 获得指定日期的后一天
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+1);

        String dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
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
    public int changeInt(String s){
        int result = 0;
        if(s.equals("R.drawable.start")){
            result = R.drawable.start;
        }
        if(s.equals("R.drawable.arrive")){
            result = R.drawable.arrive;
        }
        if(s.equals("R.drawable.mid")){
            result = R.drawable.mid;
        }
        if(s.equals("R.drawable.card")){
            result = R.drawable.card;
        }
        return result;
    }
}
