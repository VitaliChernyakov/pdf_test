package main;

import java.sql.Time;
import java.util.Date;

public class DataBean {
    private String phone;
    private String location;
    private String date;
    private String time;
    private String tel_b;
    private int prod;
    private Float summa;

    public DataBean(String phone, String location, String date, String time, String tel_b, int prod, Float summa) {
        this.phone = phone;
        this.location = location;
        this.date = date;
        this.time = time;
        this.tel_b = tel_b;
        this.prod = prod;
        this.summa = summa;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTel_b() {
        return tel_b;
    }

    public void setTel_b(String tel_b) {
        this.tel_b = tel_b;
    }

    public int getProd() {
        return prod;
    }

    public void setProd(int prod) {
        this.prod = prod;
    }

    public Float getSumma() {
        return summa;
    }

    public void setSumma(Float summa) {
        this.summa = summa;
    }
}
