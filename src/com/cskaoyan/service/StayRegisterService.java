package com.cskaoyan.service;

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
}
