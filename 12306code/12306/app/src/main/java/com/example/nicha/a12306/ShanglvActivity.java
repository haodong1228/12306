package com.example.nicha.a12306;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class ShanglvActivity extends AppCompatActivity {

    Button _chepiaoyuding;
    Button _dingdanchaxun;
    Button _wode12306;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shanglv);
        ExitApplication.getInstance().addActivity(this);
        _chepiaoyuding = (Button)findViewById(R.id.chepiaoyuding);
        _dingdanchaxun = (Button)findViewById(R.id.dingdanchaxun);
        _wode12306 = (Button)findViewById(R.id.wode12306);

        _chepiaoyuding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShanglvActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        _dingdanchaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShanglvActivity.this, TicketresActivity.class);
                startActivity(intent);
            }
        });
        _wode12306.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShanglvActivity.this, My12306Activity.class);
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
                            ExitApplication.getInstance().exit(ShanglvActivity.this);
                            finish();//
                        }

                    }).show();

            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
