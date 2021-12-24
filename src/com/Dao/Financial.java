package com.Dao;

public class Financial {

    //数据定义
    //与数据库中t_financial中定义的名称一致
    //财务信息编号
    public int financialid;
    //营业额
    public double income;
    //总支出
    public double expend;
    //利润
    public double profict;
    //月份
    public String month;
    //所属店长编号
    public int storeid;


    //方法定义
    public int getFinancialid() {
        return financialid;
    }

    public void setFinancialid(int financialid) {
        this.financialid = financialid;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpend() {
        return expend;
    }

    public void setExpend(double expend) {
        this.expend = expend;
    }

    public double getProfict() {
        return profict;
    }

    public void setProfict(double profict) {
        this.profict = profict;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }
}
