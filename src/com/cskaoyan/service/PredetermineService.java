package com.cskaoyan.service;

import com.cskaoyan.bean.*;
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

    List<Roomset> getAllRoomset(int roomStateID);

    //获取所有旅客
    List<Passenger> getAllPassenger();


    //根据id获取旅客信息
    Passenger findPassengerById(Integer integer);

    //获取所有对象
    List<Recepobject> getAllRecepobject();

    //根据id获取对象信息
    Recepobject findRecepobjectById(Integer id);

    //安排房间
    Boolean pushRoomset(String ordId);

    //查询所有未安排的订单
    ArrayList<Ordermain> getAllOrderIsBookingOn();

    //查询所有已安排安排的订单
    ArrayList<Ordermain> getAllOrderAlreadyBooking();

    //获取下拉框已安排未安排
    ArrayList<Listone> getAllStats();



    Page<Ordermain> getPageOfOrdermains(Integer currentPage, String txtname, String state);

    //安排房间
    void arrangeRoom(String id);


    Ordermain findOrderminByOrdID(String id);

    //根据oid获取房间列表，由于订单拆分，事实上每次只有一个房间
    List<Roomset> getRoomsetByOrdID(String id);

    //修改订单
    void updateOrder(Ordermain ordermain);

    void deleteOrdermaim(String Oid);
}
