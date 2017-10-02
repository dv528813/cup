package com.example.student.cup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class message extends AppCompatActivity {

    Button home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        home=(Button)findViewById(R.id.home);

    }


    public void home(View v)
    {

        Intent in = new Intent();
        in.setClass(message.this,Main2Activity.class);
        startActivity(in);

    }
}
