package com.example.student.cup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {


    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        cancel=(Button)findViewById(R.id.cancel);
    }


    public void cancel(View v)
    {

        Intent in = new Intent();
        in.setClass(Main3Activity.this,Main2Activity.class);
        startActivity(in);

    }



}
