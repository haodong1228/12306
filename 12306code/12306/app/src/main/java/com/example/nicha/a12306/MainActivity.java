package com.example.nicha.a12306;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.provider.Settings;

public class MainActivity extends AppCompatActivity {
    Button _jiaohuan;
    Button _button1;
    Button _button2;
    Button _button3;
    Button _button4;
    Button _button5;
    Button _button6;
    Button _button7;
    Button _button8;
    Button _button9;
    Button _button10;
    Button _button11;
    Button _button12;
    Button _button_tijiao;
    Button _zhengwandian;
    Button _wenxinfuwu;
    Button _dingcanfuwu;
    Button _yueche;
    Button _shanglvfuwu;
    Button _dingdanchaxun;
    Button _wode12306;
    CheckBox _student;
    int judge = 0;
    int gdc = 0;
    int z = 0;
    int t = 0;
    int k = 0;
    int e = 0;
    List<String> list;
    TextView Text1;
    TextView _mes_p;
    public static boolean login = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExitApplication.getInstance().addActivity(this);
        JudgeConnectInternet();
        list = new ArrayList<String>( );
        for(int j = 0; j < 5; j++){
            list.add(" \n");
        }
        Text1 = (TextView)findViewById(R.id.text1);
        _button1 = (Button)findViewById(R.id.button1);
        _button2 = (Button)findViewById(R.id.button2);
        _button3 = (Button)findViewById(R.id.button3);
        _button4 = (Button)findViewById(R.id.button4);
        _button5 = (Button)findViewById(R.id.button5);
        _button6 = (Button)findViewById(R.id.button6);
        _button7 = (Button)findViewById(R.id.button7);
        _button8 = (Button)findViewById(R.id.button8);
        _button9 = (Button)findViewById(R.id.button9);
        _button10 = (Button)findViewById(R.id.button10);
        _button11 = (Button)findViewById(R.id.button11);
        _button12 = (Button)findViewById(R.id.button12);
        _button_tijiao = (Button)findViewById(R.id.button_tijiao);
        _jiaohuan = (Button)findViewById(R.id.jiaohuan);
        _zhengwandian = (Button)findViewById(R.id.zhengwandian);
        _wenxinfuwu = (Button)findViewById(R.id.wenxinfuwu);
        _dingcanfuwu = (Button)findViewById(R.id.dingcanfuwu);
        _yueche = (Button)findViewById(R.id.yueche);
        _shanglvfuwu = (Button)findViewById(R.id.shanglvfuwu);
        _dingdanchaxun = (Button)findViewById(R.id.dingdanchaxun);
        _wode12306 = (Button)findViewById(R.id.wode12306);
        _student = (CheckBox)findViewById(R.id.student);
        _mes_p = (TextView)findViewById(R.id.mes_p);
        _zhengwandian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        _wenxinfuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        _dingcanfuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        _yueche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        _shanglvfuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShanglvActivity.class);
                startActivity(intent);
            }
        });
        _dingdanchaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TicketresActivity.class);
                startActivity(intent);
            }
        });
        _wode12306.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, My12306Activity.class);
                startActivity(intent);
            }
        });

        _jiaohuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data1 = _button1.getText().toString();
                String data2 = _button2.getText().toString();
                _button1.setText(data2);
                _button2.setText(data1);
            }
        });
        _button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Start_button.class);
                startActivityForResult(intent,1);
            }
        });
        _button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Arrive_button.class);
                startActivityForResult(intent,2);
            }
        });
        _button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
                startActivityForResult(intent,3);
            }
        });
        _button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TimeActivity.class);
                startActivityForResult(intent,4);
            }
        });
        _button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LevelActivity.class);
                startActivityForResult(intent,5);
            }
        });
        _button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gdc == 1 || z == 1 || t == 1 || k == 1 || e == 1 )
                {
                    judge = 0;
                }
                if(judge == 0){
                    _button7.setBackgroundColor(Color.rgb(255,255,255));
                    _button8.setBackgroundColor(Color.rgb(255,255,255));
                    _button9.setBackgroundColor(Color.rgb(255,255,255));
                    _button10.setBackgroundColor(Color.rgb(255,255,255));
                    _button11.setBackgroundColor(Color.rgb(255,255,255));
                    _button6.setBackgroundColor(Color.rgb(90,170,220));
                    gdc = 0;
                    z = 0;
                    t = 0;
                    k = 0;
                    e = 0;
                    judge = 1;
                }

            }
        });
        _button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gdc == 0){
                    _button6.setBackgroundColor(Color.rgb(255,255,255));
                    _button7.setBackgroundColor(Color.rgb(90,170,220));
                    gdc = 1;
                }
                else{
                    _button7.setBackgroundColor(Color.rgb(255,255,255));
                    gdc = 0;
                }

            }
        });
        _button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(z == 0){
                    _button6.setBackgroundColor(Color.rgb(255,255,255));
                    _button8.setBackgroundColor(Color.rgb(90,170,220));
                    z = 1;
                }else {
                    _button8.setBackgroundColor(Color.rgb(255,255,255));
                    z = 0;
                }
            }
        });
        _button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t == 0){
                    _button6.setBackgroundColor(Color.rgb(255,255,255));
                    _button9.setBackgroundColor(Color.rgb(90,170,220));
                    t = 1;
                }else {
                    _button9.setBackgroundColor(Color.rgb(255,255,255));
                    t = 0;
                }

            }
        });
        _button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k == 0){
                    _button6.setBackgroundColor(Color.rgb(255,255,255));
                    _button10.setBackgroundColor(Color.rgb(90,170,220));
                    k = 1;
                }else {
                    _button10.setBackgroundColor(Color.rgb(255,255,255));
                    k = 0;
                }

            }
        });
        _button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e == 0){
                    _button6.setBackgroundColor(Color.rgb(255,255,255));
                    _button11.setBackgroundColor(Color.rgb(90,170,220));
                    e = 1;
                }else {
                    _button11.setBackgroundColor(Color.rgb(255,255,255));
                    e = 0;
                }
            }
        });
        _button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddpeopleActivity.class);
                startActivityForResult(intent, 6);
            }
        });
//        _button12.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        _button_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                read();
                SharedPreferences.Editor editor = getSharedPreferences("all_data", MODE_PRIVATE).edit();
                //editor.putBoolean("是否登录", login);
                editor.putString("出发站", _button1.getText().toString());
                editor.putString("到达站", _button2.getText().toString());
                editor.putString("出发日期", _button3.getText().toString());
                editor.putString("出发时间", _button4.getText().toString());
                editor.putString("席别", _button5.getText().toString());
                editor.putBoolean("是否学生票", _student.isChecked());
                editor.apply();
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
        read();
    }

    private void JudgeConnectInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo == null || !networkInfo.isAvailable())
        {
            //当前无可用网络
            new AlertDialog.Builder(MainActivity.this).setTitle("系统提示")//设置对话框标题

                    .setMessage("没有互联网连接，请先联网再打开此应用，点击进入设置页面进行联网设置。")//设置显示的内容

                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮



                        @Override

                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                            // TODO Auto-generated method stub

                            finish();

                        }

                    }).setNegativeButton("打开设置",new DialogInterface.OnClickListener() {//添加打开设置按钮



                @Override

                public void onClick(DialogInterface dialog, int which) {//响应事件

                    // TODO Auto-generated method stub
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);startActivity(intent); // 打开系统设置界面
                    startActivity(intent);

                }

            }).show();//在按键响应事件中显示此对话框
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1 :
                if(resultCode == RESULT_OK){
                    String returnedData1 = data.getStringExtra("data_start");
                    _button1.setText(returnedData1);
                }
                break;
            case 2 :
                if(resultCode == RESULT_OK){
                    String returnedData2 = data.getStringExtra("data_arrive");
                    _button2.setText(returnedData2);
                }
                break;
            case 3 :
                if(resultCode == RESULT_OK){
                    String returnedData3 = data.getStringExtra("data_return_data");
                    _button3.setText(returnedData3);
                }
                break;
            case 4 :
                if(resultCode == RESULT_OK){
                    String returnedData4 = data.getStringExtra("data_return_time");
                    _button4.setText(returnedData4);
                }
                break;
            case 5 :
                if(resultCode == RESULT_OK){
                    String returnedData5 = data.getStringExtra("data_return_level");
                    _button5.setText(returnedData5);
                }
                break;
            case 6 :
                if(resultCode == RESULT_OK){
                    String returnedData1 = data.getStringExtra("data_addpeople");
                    _mes_p.setText(returnedData1);
                    _mes_p.setVisibility(View.VISIBLE);
                }
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    private void save() {
        String content = "  "+_button1.getText().toString() + "-----" + _button2.getText().toString() + "\n";
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext())
        {
            if (iterator.next().equals(content))
            {
                iterator.remove();
                break;
            }
        }
        list.add(0,content);
        for (int i = 0; i < list.size(); i++) {
            if (i == 5) {
                list.remove(5);
            }
        }
        try {
            FileOutputStream outputStream = openFileOutput("travel.txt",
                    Activity.MODE_PRIVATE);
            for (int i = 0; i < list.size(); i++) {
                outputStream.write(list.get(i).getBytes());
        }
            outputStream.flush();
            outputStream.close();
         //   Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void read() {
        try {
            FileInputStream inputStream = this.openFileInput("travel.txt");
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            while (inputStream.read(bytes) != -1) {
                arrayOutputStream.write(bytes, 0, bytes.length);
            }
            inputStream.close();
            arrayOutputStream.close();
            String content = new String(arrayOutputStream.toByteArray());
            Text1.setText(content);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                            finish();//
                            ExitApplication.getInstance().exit(MainActivity.this);
                        }

                    }).show();

            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
