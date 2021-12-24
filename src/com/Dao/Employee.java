package com.Dao;

public class Employee {
    //定义变量
    //与数据库中t_user中定义的名称一致
    //编号
    public int empid;
    //姓名
    public String empName;
    //年龄
    public String age;
    //性别
    public String sex;
    //薪资
    public double salary;
    //出生日期
    public String birthday;
    //入职日期
    public String edate;
    //所属店长编号
    public int storeid;

    //get和set的基本方法
    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }
}
