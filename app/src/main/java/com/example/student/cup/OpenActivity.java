package com.example.student.cup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class OpenActivity extends AppCompatActivity {


    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);


        cancel=(Button)findViewById(R.id.cancel);
    }


    public void cancel(View v)
    {

        Intent in = new Intent();
        in.setClass(OpenActivity.this,MainActivity.class);
//        startActivity(in);
        finish();

    }



}
