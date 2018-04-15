package com.cskaoyan.service;

import com.cskaoyan.bean.Deposit;
import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Recepobject;
import com.cskaoyan.bean.Roomset;
import com.cskaoyan.utils.Page;
import com.cskaoyan.vo.Listone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface StayRegisterService {

//    void modifyOrderStatus();

//    void modifyRoomStatus(String roomNumber);

    Page<Ordermain> findPage(Integer currentPage, String roomNumber,String lvKeLeiXingId,String isBillID);

     List<Roomset> guestRoomLevelSelectRoom(Integer guestRoomLevelID);

    ArrayList<HashMap> getHashMaps(List<Listone> payWay, ArrayList<HashMap> listRentOutType);

    List<Deposit> findDepositRecordsByOrdId(String id);
    boolean addDeposit(Deposit deposit);

    List<Roomset> findRoomsetAsEmpty(String roomNumber);

    Ordermain findOrderById(String id);

    List<Ordermain> findOrderByRoomNum(String roomNumber);

    int registration(String id, Passenger passenger, String lvKeLeiXingId, int passengerID);

    //根据id获取订单详情
    List<Ordermain> getOrderMain(String oid);
    int changeOrderRoomNumber(Ordermain ordermain);
    boolean changeRoom(String oldRoomNum, String newRoomNum);


    //团队与散客互相转换
    void changOverOrderMain(String oid,String PassengerOrReceiveID);

    //获取全部对象
    List<Recepobject> getAllReceiveObject();

    //获取全部旅客
    List<Passenger> getAllPassers();


}
