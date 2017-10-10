package com.example.student.cup;

/**
 * Created by Felix on 2017/10/10.
 */

public final class TOOLS {

    public final static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
