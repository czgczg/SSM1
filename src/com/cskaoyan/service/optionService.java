package com.cskaoyan.service;


import com.cskaoyan.vo.Listone;
import ff2.T;

import java.util.List;

public interface optionService {

    //房间状态
    public List<Listone> getRoomSet();

    //客房等级
    public List<Listone> getRoomLevel();


    //商品类别
    public List<Listone> getComodityType();


    //支付方式
    public List<Listone> getPayWay();

    //出租方式
    public List<Listone> getRendWay();

    //结账单位
    public List<Listone> getPayUnit();

    //旅客类别
    public List<Listone> getPassengerType();

    //性别
    public List<Listone> getSex();

    //民族
    public List<Listone> getNation();

    //证件
    public List<Listone> getIdentifyCard();

    //文化程度
    public List<Listone> getEducationDegree();


    //是由
    public List<Listone> getThingReason();

    //旅客级别
    public List<Listone> getpassengerLevel();

    //对象类别
    public List<Listone> getObjectType();

    //计量单位
    public List<Listone> getMeasureUnit();

    //预定状态
    public List<Listone> getOrderStatus();

    //结账
    public List<Listone> getPayMoney();






}
