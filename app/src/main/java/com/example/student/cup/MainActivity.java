package com.example.student.cup;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.student.cup.Account.Info;
import com.example.student.cup.Account.Sign;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton settinglink;
    ImageButton messagelink;

    Sign gSign;

//    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_welcome);    // 設定歡迎 Layout

        // ==========取得 Account 資訊==========
        gSign = new Sign(MainActivity.this);               // 取得 Account 資訊

        // ==========計時 n 秒後更換 Layout==========
        Runnable rab = new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_main);     //設定 Layout
            }
        };
        new Handler().postDelayed(rab, 5000);    // 5 sec 後轉跳主頁


        settinglink = (ImageButton) findViewById(R.id.settinglink);
        messagelink = (ImageButton) findViewById(R.id.messagelink);


//        next=(Button)findViewById(R.id.next);


        // ====== 新增商品 ======
        List<Goods> lg = new ArrayList<>();
        lg.add(new Goods("50嵐", "珍珠奶茶", 50.0f));    // 商品0
        lg.add(new Goods("50嵐", "茉莉綠茶", 51.0f));    // 商品1
        lg.add(new Goods("50嵐", "四季春茶", 52.0f));    // 商品2
        lg.add(new Goods("50嵐", "阿薩姆紅茶", 53.0f));   // 商品3
        // ====== 新增計畫 ======
        List<Plan> lp = new ArrayList<>();
        lp.add(new Plan(getGoogleId(), "老地方拿飲料", setDeadline("2017/12/31 23:59:59"), "50嵐湊兩百外送", lg));     // 計畫0

        // ====== 新增商品 ======
        lg = new ArrayList<>();
        lg.add(new Goods("COMEBUY", "珍珠奶茶", 54.0f));    // 商品0
        lg.add(new Goods("COMEBUY", "四季春茶", 55.0f));    // 商品1
        // ====== 新增計畫 ======
        lp.add(new Plan(getGoogleId(), "我的座位", setDeadline(1, 30), "拎杯請喝COMEBUY", lg));     // 計畫1

        // ====== 新增商品 ======
        lg = new ArrayList<>();
        lg.add(new Goods("茶湯會", "阿薩姆紅茶", 56.0f));   // 商品0
        lg.add(new Goods("清心福全", "茉莉綠茶", 57.0f));   // 商品1
        // ====== 新增計畫 ======
        lp.add(new Plan(getGoogleId(), "1F大廳櫃台", setDeadline(3, 0), "清心&茶湯會預購從速", lg)); // 計畫2

        // ====== 下訂單 ======
        List<Order> lo = new ArrayList<>();
        lo.add(new Order(lp.get(0), getGoogleId(), lp.get(0).getGoods(0), null));     // 訂購 計畫0 的 商品0
        lo.add(new Order(lp.get(0), getGoogleId(), lp.get(0).getGoods(1), "少冰"));     // 訂購 計畫0 的 商品1
        lo.add(new Order(lp.get(0), getGoogleId(), lp.get(0).getGoods(2), null));     // 訂購 計畫0 的 商品0
        lo.add(new Order(lp.get(0), getGoogleId(), lp.get(0).getGoods(3), "少糖"));     // 訂購 計畫0 的 商品1

        lo.add(new Order(lp.get(1), getGoogleId(), lp.get(1).getGoods(0), "去冰無糖"));     // 訂購 計畫1 的 商品0

        lo.add(new Order(lp.get(2), getGoogleId(), lp.get(2).getGoods(1), null));     // 訂購 計畫2 的 商品1


        // ====== 追加計畫內商品 ======
        Plan p = lp.get(1);
        p.addGoods(new Goods("COMEBUY", "多多綠", 60.0f));
        lp.set(1, p);   // 追加計畫1 的商品2


        // ====== 刪除計畫內商品 ======
        p = lp.get(1);
        p.delGoods(0);
        lp.set(1, p);   // 刪除計畫1 的商品0


    }


//    public void next(View v)
//    {
//
//        Intent in = new Intent();
//        in.setClass(MainActivity.this,Main2Activity.class);
//        startActivity(in);
//
//    }


    public void settinglink(View v) {

        Intent in = new Intent();
        in.setClass(MainActivity.this, SetupActivity.class);
        startActivity(in);

    }

    public void messagelink(View v) {

        Intent in = new Intent();
        in.setClass(MainActivity.this, MessageActivity.class);
        startActivity(in);

    }


    public void start(View v) {

        Intent in = new Intent();
        in.setClass(MainActivity.this, OpenActivity.class);
        startActivity(in);

    }

    // ====== 取得帳戶資訊 ======
    public String getGoogleId() {
        AccountManager am = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        Account[] aGoogle = am.getAccountsByType("com.google");
        return aGoogle[0].toString();     // 第一筆帳戶
    }

    // ====== 截止時間換算 ======
    public Calendar setDeadline(String t) {
        Calendar cal = Calendar.getInstance(); // 取得目前時間
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  //定義好時間字串的格式
            Date dt = sdf.parse(t);                              //將字串轉成Date型
            cal.setTime(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    public Calendar setDeadline(int hh, int mm) {
        Calendar cal = Calendar.getInstance(); // 取得目前時間
        cal.add(Calendar.HOUR, hh);        //小時+hh
        cal.add(Calendar.MINUTE, mm);      //分+mm
        return cal;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // ====== Get Account Information ======
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == Sign.RC_SIGN_IN) {

            // ====== 取得資訊放置於 Info class ======
            gSign.Result(data);

            // ====== 建立執行緒等待處理顯示 ======
            new Thread(new Runnable() {
                @Override
                public void run() {

                    // ====== 等待歡迎頁面結束 ======
                    while ((findViewById(R.id.TextViewName) == null)
                            || (findViewById(R.id.ImageViewIcon) == null)
                            || (Info.gDisplayNameNick == null)
                            || (Info.gPhotoUrlBitmap == null)
                            ) {
                        ;
                    }

                    // ====== 顯示資訊於UI ======
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            ((TextView) findViewById(R.id.TextViewName)).setText(Info.gDisplayNameNick);
                            ((ImageView) findViewById(R.id.ImageViewIcon)).setImageBitmap(Info.gPhotoUrlBitmap);

                        }
                    });

                }
            }).start();


        }


    }


}
