package com.example.student.cup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    ImageButton settinglink;
    ImageButton messagelink;


//    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_welcome);    // 設定歡迎 Layout

        // ==========計時 n 秒後更換 Layout==========
        Runnable rab = new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_main);     //設定 Layout
            }
        };
        new Handler().postDelayed(rab,5000);    // 5 sec 後轉跳主頁


        settinglink=(ImageButton)findViewById(R.id.settinglink);
        messagelink=(ImageButton)findViewById(R.id.messagelink);


//        next=(Button)findViewById(R.id.next);
    }



//    public void next(View v)
//    {
//
//        Intent in = new Intent();
//        in.setClass(MainActivity.this,Main2Activity.class);
//        startActivity(in);
//
//    }





    public void settinglink(View v)
    {

        Intent in = new Intent();
        in.setClass(MainActivity.this,SetupActivity.class);
        startActivity(in);

    }
    public void messagelink(View v)
    {

        Intent in = new Intent();
        in.setClass(MainActivity.this,MessageActivity.class);
        startActivity(in);

    }


    public void start(View v)
    {

        Intent in = new Intent();
        in.setClass(MainActivity.this,OpenActivity.class);
        startActivity(in);

    }


}
