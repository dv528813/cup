package com.example.student.cup;

import java.io.Serializable;

/**
 * Created by Felix on 2017/10/4.
 */

public class Goods implements Serializable {

    private String store;           // 店家
    private String goods;          // 商品名
    private float price;            // 單價

    public Goods(String store, String goods, float price) {
        this.store = store;
        this.goods = goods;
        this.price = price;
    }

}
