package com.cskaoyan.vo;

import com.cskaoyan.bean.Ordermain;

public class Listpay extends Ordermain {

    private String rentOutTypeName;
   private String receiveTargeTypeName;
   private float roomStandardPriceDay;
   private float roomStandardPrice;

    public float getRoomFirstPrice() {
        return roomFirstPrice;
    }

    public void setRoomFirstPrice(float roomFirstPrice) {
        this.roomFirstPrice = roomFirstPrice;
    }

    private float roomFirstPrice;

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    private Integer roomID;
    @Override
    public String getRentOutTypeName() {
        return rentOutTypeName;
    }

    @Override
    public void setRentOutTypeName(String rentOutTypeName) {
        this.rentOutTypeName = rentOutTypeName;
    }

    public String getReceiveTargeTypeName() {
        return receiveTargeTypeName;
    }

    public void setReceiveTargeTypeName(String receiveTargeTypeName) {
        this.receiveTargeTypeName = receiveTargeTypeName;
    }

    public float getRoomStandardPriceDay() {
        return roomStandardPriceDay;
    }

    public void setRoomStandardPriceDay(float roomStandardPriceDay) {
        this.roomStandardPriceDay = roomStandardPriceDay;
    }

    public float getRoomStandardPrice() {
        return roomStandardPrice;
    }

    public void setRoomStandardPrice(float roomStandardPrice) {
        this.roomStandardPrice = roomStandardPrice;
    }


}
