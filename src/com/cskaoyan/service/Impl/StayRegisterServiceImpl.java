package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Deposit;
import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Recepobject;
import com.cskaoyan.bean.Roomset;
import com.cskaoyan.dao.*;
import com.cskaoyan.service.StayRegisterService;
import com.cskaoyan.utils.Page;
import com.cskaoyan.vo.Listone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class StayRegisterServiceImpl implements StayRegisterService {

    @Autowired
    OrdermainMapper ordermainMapper;

    @Autowired
    RoomsetMapper roomsetMapper;


    @Autowired
    RegistrationMapper registrationMapper;

    @Autowired
    RecepobjectMapper recepobjectMapper;

    @Autowired
    PassengerMapper passengerMapper;


    /**
     * author:czg
     *登记旅客信息到登记表，因为一个房间可能登记多人，限制，房间床位数
     * @param id
     * @param passenger
     * @param lvKeLeiXingId
     * @param passengerID
     * @return
     */
    @Override
    public int registration(String id, Passenger passenger, String lvKeLeiXingId, int passengerID) {

        Date date = new Date();
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("passenger", passenger);
        map.put("passengerType", lvKeLeiXingId);
        map.put("registerTime", date);
        map.put("passengerID", passengerID);
        registrationMapper.insertToRistration(map);

        return 0;
    }

    @Override
    public List<Ordermain> getOrderMain(String oid) {
        return ordermainMapper.findOrderById(oid);
    }

    @Override
    public void changOverOrderMain(String oid,String passengerOrReceiveID) {
        //改变旅客状态55-》56，删除（？）预定人名字，加入团队信息在里面,结账方式变更1.旅客自付 2.团队付款
        Ordermain ordermain = ordermainMapper.findOrderById(oid).get(0);
        ordermain.setPassengerOrReceiveID(Integer.valueOf(passengerOrReceiveID));
        Integer type = ordermain.getReceiveTargetID();
        if(type == 56){
            ordermain.setReceiveTargetID(55);
            ordermain.setBillUnitID(1);
            ordermain.setBillUnitName("旅客自付");
            ordermain.setTeamname("");
            Passenger passengerById = passengerMapper.findPassengerById(Integer.valueOf(passengerOrReceiveID));
            ordermain.setName(passengerById.getName());
            ordermain.setCommodityPhone(passengerById.getContactPhoneNumber());
        }else if(type == 55){
            ordermain.setReceiveTargetID(56);
            ordermain.setBillUnitID(2);
            ordermain.setBillUnitName("团队付款");
            ordermain.setName("");
            Recepobject receptById = recepobjectMapper.findReceptById(Integer.parseInt(passengerOrReceiveID));
            ordermain.setTeamname(receptById.getTeamName());
            ordermain.setCommodityPhone(receptById.getContactPhoneNUmber());
        }
        ordermainMapper.updateByPrimaryKeySelective(ordermain);


    }

    @Override
    public List<Recepobject> getAllReceiveObject() {
        return recepobjectMapper.findAllObj();
    }

    @Override
    public List<Passenger> getAllPassers() {
        return passengerMapper.findAllPassenger();
    }

    /**
     * ordermain的分页处理和显示
     * @param currentPage
     * @param roomNumber
     * @return
     */
    @Override
    public Page<Ordermain> findPage(Integer currentPage, String roomNumber) {
        Page<Ordermain> page = new Page<>();
        int totalNumber = findAllOrderMainCount();
        page.setTotalCount(totalNumber);
        int num = currentPage;
        page.initOrder(totalNumber,num);
        page.setCurrentPage(num);

        HashMap<String, Object> map = new HashMap<>();
        map.put("limit", Page. ORDERMAIN__NUM_PER_PAGE);
        map.put ("offset",(num - 1) * Page. ORDERMAIN__NUM_PER_PAGE);
        map.put("name", roomNumber);
        List<Ordermain> order = ordermainMapper.findPartOrder(map);
        page.setResult(order);




        return page;
    }

    @Override
    public List<Roomset> guestRoomLevelSelectRoom(Integer guestRoomLevelID) {
        if(guestRoomLevelID==0){
            List<Roomset> result=roomsetMapper.findAllRoomset();
            return result;
        }

        List<Roomset> result = roomsetMapper.findRoomsetByLevelID(guestRoomLevelID);
        return result;
    }

    @Override
    public ArrayList<HashMap> getHashMaps(List<Listone> payWay, ArrayList<HashMap> listRentOutType) {
        for (Listone i:payWay) {
            HashMap<String, String> j = new HashMap<>();
            j.put("far_id", i.getId().toString());
            j.put("attributeDetailsName", i.getAttributeDetailsName());
            listRentOutType.add(j);
        }
        return listRentOutType;
    }

    @Override
    public List<Deposit> findDepositRecordsByOrdId(String id) {
        return null;
    }

    @Override
    public List<Roomset> findRoomsetAsEmpty(String roomNumber) {
        return null;
    }

    @Override
    public Ordermain findOrderById(String id) {
        return null;
    }

    @Override
    public List<Ordermain> findOrderByRoomNum(String roomNumber) {
        return null;
    }

    private int findAllOrderMainCount() {

       return ordermainMapper.findAllOrderCount();

    }

    @Override
    public void modifyRoomStatus(String roomNumber) {
        roomsetMapper.modifyRoomStatus(roomNumber);
    }

    @Override
    public void modifyOrderStatus() {
        ordermainMapper.modifyOrderStatus();
    }


}
