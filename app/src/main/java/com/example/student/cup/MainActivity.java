package com.example.student.cup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.student.cup.Account.Info;
import com.example.student.cup.Account.Sign;
import com.example.student.cup.Network.Net;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.student.cup.Account.Info.gEmail;
import static com.example.student.cup.Account.Info.gLoginOk;

public class MainActivity extends AppCompatActivity {

    ImageButton settinglink;
    ImageButton messagelink;

    Sign gSign;

//    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_welcome);    // 設定歡迎 Layout

        // ==========檢查Network==========
        if (Net.NetCheck(this) == false) {
            openWifi();                            // 提示開啟WIFI
        }

        // ==========計時 n 秒後更換 Layout==========
        Runnable rab = new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_main);     //設定 Layout
                runLoging();                               //執行登入程序
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


    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        new AlertDialog.Builder(this)
                .setMessage("您要離開應用程式？")
                .setPositiveButton("繼續", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ;
                    }
                })
                .setNegativeButton("離開", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .show();

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
//        AccountManager am = (AccountManager) getSystemService(ACCOUNT_SERVICE);
//        Account[] aGoogle = am.getAccountsByType("com.google");
//        return aGoogle[0].toString();     // 第一筆帳戶

        return gEmail;

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

            // ====== 取得帳戶資訊並放置於 Info class ======
            gSign.Result(data);

        }

    }


    public void openWifi() {

        //若wifi狀態為關閉則將它開啟
        WifiManager wfm = ((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE));
        if (!wfm.isWifiEnabled()) {

            new AlertDialog.Builder(this)
                    .setMessage("應用程式要求開啟 Wi-Fi 功能。")
                    .setPositiveButton("允許", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE)).setWifiEnabled(true);
                        }
                    })
                    .setNegativeButton("拒絕", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ;
                        }
                    })
                    .show();

        }

    }


    public final void runOnUiTextView(final int id, final CharSequence cs) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView) findViewById(id)).setText(cs);
            }
        });

    }

    public final void runOnUiImageView(final int id, final Bitmap bm) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((ImageView) findViewById(id)).setImageBitmap(bm);
            }
        });

    }

    public final void runLoging() {

        new Thread(new Runnable() {
            @Override
            public void run() {

//                // ====== 等待歡迎頁面結束 ======
//                while ((findViewById(R.id.TextViewName) == null)
//                        || (findViewById(R.id.ImageViewIcon) == null)
//                        ) {
//                    ;
//                }

                runOnUiTextView(R.id.TextViewName, "＜尚未連接網路＞");
                while (Net.NetCheck(getApplicationContext()) == false) {
                    ;
                }

                runOnUiTextView(R.id.TextViewName, "＜正在登入帳戶＞");
                // ==========取得 Account 資訊==========
                gSign = new Sign(MainActivity.this);   // 取得 Account 資訊
                while (gLoginOk == false) {
                    ;
                }

                gSign.dispData();   // 準備暱稱及圖
                runOnUiTextView(R.id.TextViewName, Info.gDisplayNameNick);
                while (Info.gPhotoUrlBitmap == null) {
                    ;
                }
                runOnUiImageView(R.id.ImageViewIcon, Info.gPhotoUrlBitmap);

            }
        }).start();

    }


}
