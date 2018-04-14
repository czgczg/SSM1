package com.cskaoyan.service;

import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Orderpayment;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Roomset;
import com.cskaoyan.utils.Page;
import com.cskaoyan.vo.Listone;

import java.util.ArrayList;
import java.util.HashMap;
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

    //安排房间
    Boolean pushRoomset(String ordId);

    //查询所有未安排的订单
    ArrayList<Ordermain> getAllOrderIsBookingOn();

    //查询所有已安排安排的订单
    ArrayList<Ordermain> getAllOrderAlreadyBooking();

    //获取下拉框已安排未安排
    ArrayList<Listone> getAllStats();

    //根据id获取旅客信息
    Passenger findPassengerById(Integer integer);


    Page<Ordermain> getPageOfOrdermains(Integer currentPage, String txtname, String state);
}
