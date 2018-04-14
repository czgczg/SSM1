package com.cskaoyan.bean;

public class Changeroom {

    private int changingRoomNumber;
    private String ordId;
    private String oldRoomset;
    private String newRoomset;
    private float affterPay;
    private String changRoomTime;
    private int delFlag;


    @Override
    public String toString() {
        return "Changeroom{" +
                "changingRoomNumber=" + changingRoomNumber +
                ", ordId='" + ordId + '\'' +
                ", oldRoomset='" + oldRoomset + '\'' +
                ", newRoomset='" + newRoomset + '\'' +
                ", affterPay=" + affterPay +
                ", changRoomTime='" + changRoomTime + '\'' +
                ", delFlag=" + delFlag +
                '}';
    }

    public int getChangingRoomNumber() {
        return changingRoomNumber;
    }

    public void setChangingRoomNumber(int changingRoomNumber) {
        this.changingRoomNumber = changingRoomNumber;
    }

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getOldRoomset() {
        return oldRoomset;
    }

    public void setOldRoomset(String oldRoomset) {
        this.oldRoomset = oldRoomset;
    }

    public String getNewRoomset() {
        return newRoomset;
    }

    public void setNewRoomset(String newRoomset) {
        this.newRoomset = newRoomset;
    }

    public float getAffterPay() {
        return affterPay;
    }

    public void setAffterPay(float affterPay) {
        this.affterPay = affterPay;
    }

    public String getChangRoomTime() {
        return changRoomTime;
    }

    public void setChangRoomTime(String changRoomTime) {
        this.changRoomTime = changRoomTime;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }


}