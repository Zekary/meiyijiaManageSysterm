package com.Dao;

public class User {
    //定义变量
    //与数据库中t_user中定义的名称一致
    //编号
    public int id;
    //用户名
    public String userName;
    //用户密码
    public String userPsd;
    //分店名
    public String storeName;
    //等级
    public int grade;

    //get和set的基本方法
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsd() {
        return userPsd;
    }

    public void setUserPsd(String userPsd) {
        this.userPsd = userPsd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setGrade(int grade){ this.grade=grade;}

    public int getGrade(){return grade;}

}
