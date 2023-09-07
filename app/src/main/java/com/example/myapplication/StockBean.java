package com.example.myapplication;

/**
 * create time : 2023/9/6 14:39
 * create by : xupengpeng
 */
public class StockBean {

    private String name;

    private String prisePercent;

    private String speed;

    private String current;

    private String total;

    private String time;

    private boolean showTip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrisePercent() {
        return prisePercent;
    }

    public void setPrisePercent(String prisePercent) {
        this.prisePercent = prisePercent;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setShowTip(boolean showTip) {
        this.showTip = showTip;
    }

    public boolean isShowTip(){
        return showTip;
    }

}
