package com.example.student.cup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class Main2Activity extends AppCompatActivity {


    ImageButton settinglink;
    ImageButton messagelink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        settinglink=(ImageButton)findViewById(R.id.settinglink);
        messagelink=(ImageButton)findViewById(R.id.messagelink);

    }



    public void settinglink(View v)
    {

        Intent in = new Intent();
        in.setClass(Main2Activity.this,Main4Activity.class);
        startActivity(in);

    }
    public void messagelink(View v)
    {

        Intent in = new Intent();
        in.setClass(Main2Activity.this,message.class);
        startActivity(in);

    }


    public void start(View v)
    {

        Intent in = new Intent();
        in.setClass(Main2Activity.this,Main3Activity.class);
        startActivity(in);

    }

}
