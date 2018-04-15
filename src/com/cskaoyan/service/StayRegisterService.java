package com.cskaoyan.service;

import com.cskaoyan.bean.Deposit;
import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Roomset;
import com.cskaoyan.utils.Page;
import com.cskaoyan.vo.Listone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface StayRegisterService {

    void modifyOrderStatus();

    void modifyRoomStatus(String roomNumber);

    Page<Ordermain> findPage(Integer currentPage, String roomNumber);

     List<Roomset> guestRoomLevelSelectRoom(Integer guestRoomLevelID);

    ArrayList<HashMap> getHashMaps(List<Listone> payWay, ArrayList<HashMap> listRentOutType);

    List<Roomset> findRoomsetAsEmpty(String roomNumber);

    Ordermain findOrderById(String id);
    int registration(String id, Passenger passenger, String lvKeLeiXingId, int passengerID);

    List<Ordermain> findOrderByRoomNum(String roomNumber);

    int changeOrderRoomNumber(String roomId);

    boolean changeRoom(String oldRoomNum, String newRoomNum);

    List<Deposit> findDepositRecordsByOrdId(String id);

    boolean addDeposit(Deposit deposit);
}
