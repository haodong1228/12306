package com.example.nicha.a12306;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class My12306Activity extends AppCompatActivity {
    Button _chepiaoyuding;
    Button _shanglvfuwu;
    Button _dingdanchaxun;
    Button _aboutapp;
    Button _zhanghu;
    Button _shouji;
    Button _baoxian;
    Button _chuxing;
    Button _wenxin;
    Button _xinxi;
    Button _dingcan;
    Button _lvxing;
    TextView _heyan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my12306);
        ExitApplication.getInstance().addActivity(this);
        _chepiaoyuding = (Button)findViewById(R.id.chepiaoyuding);
        _shanglvfuwu = (Button)findViewById(R.id.shanglvfuwu);
        _dingdanchaxun = (Button)findViewById(R.id.dingdanchaxun);
        _zhanghu = (Button)findViewById(R.id.zhanghu);
        _shouji = (Button)findViewById(R.id.shouji);
        _baoxian = (Button)findViewById(R.id.baoxian);
        _chuxing = (Button)findViewById(R.id.chuxing);
        _wenxin = (Button)findViewById(R.id.wenxin);
        _xinxi = (Button)findViewById(R.id.xinxi);
        _lvxing = (Button)findViewById(R.id.lvxing);
        _aboutapp = (Button)findViewById(R.id.aboutapp);
        _dingcan = (Button)findViewById(R.id.dingcan);
        _heyan = (TextView)findViewById(R.id.heyan);
        _chepiaoyuding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(My12306Activity.this, MainActivity.class));
            }
        });
        _shanglvfuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(My12306Activity.this, ShanglvActivity.class);
                startActivity(intent);
            }
        });
        _dingdanchaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(My12306Activity.this, TicketresActivity.class));
            }
        });

        _chuxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PackageManager pm = getPackageManager();
                Intent intent = pm.getLaunchIntentForPackage("com.baidu.BaiduMap");
                startActivity(intent);
            }
        });
        _wenxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PackageManager pm = getPackageManager();
                Intent intent = pm.getLaunchIntentForPackage("com.taobao.taobao");
                startActivity(intent);
            }
        });
        _lvxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PackageManager pm = getPackageManager();
                Intent intent = pm.getLaunchIntentForPackage("ctrip.android.view");
                startActivity(intent);
            }
        });
        _dingcan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PackageManager pm = getPackageManager();
                Intent intent = pm.getLaunchIntentForPackage("me.ele");
                startActivity(intent);
            }
        });
        _baoxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.pingan.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        _xinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://baidu.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        _aboutapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(My12306Activity.this, AboutappActivity.class));
            }
        });
        _shouji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(My12306Activity.this, DuanXinYanZhengActivity.class);
                startActivityForResult(intent,1);
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

                            finish();//
                            ExitApplication.getInstance().exit(My12306Activity.this);
                        }

                    }).show();

            return true;
        }
        return super.dispatchKeyEvent(event);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1 :
                if(resultCode == RESULT_OK){
                    String returnedData1 = data.getStringExtra("data_return_yanzhengma");
                    _heyan.setVisibility(View.VISIBLE);
                }
                break;
            default:
        }
    }
}
