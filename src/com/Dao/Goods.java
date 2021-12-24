package com.Dao;

public class Goods {
    //定义变量
    //与数据库中t_user中定义的名称一致
    //商品编号
    public int goodsid;
    //商品名
    public String gname;
    //商品数量
    public int num;
    //单价
    public double price;
    //生产商
    public String factory;
    //保质期
    public int storageday;
    //生产日期
    public String productdate;
    //所属店长编号
    public int storeid;


    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public void setStorageday(int storageday) {
        this.storageday = storageday;
    }

    public void setProductdate(String productdate) {
        this.productdate = productdate;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public int getGoodsid() {
        return goodsid;
    }

    public String getGname() {
        return gname;
    }

    public int getNum() {
        return num;
    }

    public double getPrice() {
        return price;
    }

    public String getFactory() {
        return factory;
    }

    public int getStorageday() {
        return storageday;
    }

    public String getProductdate() {
        return productdate;
    }

    public int getStoreid() {
        return storeid;
    }
}
