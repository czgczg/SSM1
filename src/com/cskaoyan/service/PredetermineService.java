package com.cskaoyan.service;

import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Orderpayment;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Roomset;

import java.util.ArrayList;
import java.util.List;

public interface PredetermineService {


    /**
     * 讲订单数据加入到数据库中,
     * 需要的入参有：房间数组
     */
    Ordermain inputOrder(Ordermain ordermain,String[] rooms);

    ArrayList<Orderpayment> getAllOrderPayment();

    List<Roomset> getAllRoomset();

    List<Passenger> getAllPassenger();

    ArrayList<Ordermain> getAllOrderIsOnBooking();



}
