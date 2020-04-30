package com.example.nicha.a12306;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.DatePicker;

import java.util.Calendar;


public class DataActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int mYear, mMonth, mDay;
        super.onCreate(savedInstanceState);
        ExitApplication.getInstance().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏活动标题
        setContentView(R.layout.activity_data);
        DatePicker dp = new DatePicker(this);
        dp = (DatePicker) findViewById(R.id.date);

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH) - 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        dp.init(mYear, mMonth + 1, mDay,
                new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker View, int year, int month, int day) {
                        month++;
                        Intent intent = new Intent();
                        intent.putExtra("data_return_data", year + "-" + month  + "-" + day);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
    }
}

