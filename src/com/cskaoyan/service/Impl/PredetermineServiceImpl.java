package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.*;
import com.cskaoyan.dao.*;
import com.cskaoyan.service.PredetermineService;
import com.cskaoyan.utils.Page;
import com.cskaoyan.vo.Listone;
import com.sun.javafx.collections.MappingChange;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PredetermineServiceImpl implements PredetermineService{

    @Autowired
    OrderpaymentMapper orderpaymentMapper;

    @Autowired
    PassengerMapper passengerMapper;
    @Autowired
    OrdermainMapper ordermainMapper;

    @Autowired
    RoomsetMapper roomsetMapper;
    @Autowired
    RoomsetstatusMapper roomsetstatusMapper;

    @Autowired
    ListoneMapper listoneMapper;

    @Autowired
    RecepobjectMapper recepobjectMapper;


    @Override
    public Ordermain inputOrder(Ordermain ordermain,String[] roomsets) {
        //状态设置为未安排66
        ordermain.setState(66);

        //插入各个默认值
        if(56==ordermain.getReceiveTargetID()){
            ordermain.setBillUnitID(2);
            ordermain.setBillUnitName("团队付款");
            ordermain.setCommodityPhone(recepobjectMapper.findReceptById(ordermain.getPassengerOrReceiveID()).getContactPhoneNUmber());
        }else if(55==ordermain.getReceiveTargetID()){
            ordermain.setBillUnitID(1);
            ordermain.setBillUnitName("旅客自付");
            ordermain.setCommodityPhone(passengerMapper.findPassengerById(ordermain.getPassengerOrReceiveID()).getContactPhoneNumber());
        }
        ordermain.setPassengerTypeID(1);
        ordermain.setPassengerTypeName("国内旅客");
        //希望可以显示到秒
        ordermain.setOrderTime(new Date());
        ordermain.setLoginFlag(0);
        ordermain.setDel_flag(0);

        if(null == ordermain.getRentOutTypeName()){
            ordermain.setRentOutTypeId(1);
        }


        ordermain.setPayWayName(orderpaymentMapper.selectByPrimaryKey(ordermain.getPayWayID()).getAttributeDetailsName());

        for (String s : roomsets) {
            //订单号201804120001
            String ordId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().substring(0,5);
            ordermain.setOrdID(ordId);
            Roomset roomset = roomsetMapper.findById(Integer.parseInt(s));
            //改房间真实状态
            Roomsetstatus roomsetstatus = roomsetstatusMapper.findStatusByPrimaryKey(3);
            HashMap<String, Object> map = new HashMap<>();
            map.put("roomStateID",roomsetstatus.getFar_id());;
            map.put("roomStateName",roomsetstatus.getAttributeDetailsName());
            map.put("id",Integer.parseInt(s));
            roomsetMapper.updateRoomStateById(map);
            //改变订单房间状态
            ordermain.setRoomId(roomset.getId());
            ordermain.setRoomNumber(roomset.getRoomNumber());
            ordermain.setGuestRoomLevelName(roomset.getGuestRoomLevelName());
            ordermain.setSumConst(roomset.getStandardPriceDay()*Integer.valueOf(ordermain.getPredetermineDay()));
            ordermainMapper.insert(ordermain);
        }

        return ordermain;
    }

    @Override
    public ArrayList<Orderpayment> getAllOrderPayment() {
        ArrayList<Orderpayment> allOrderPayment = orderpaymentMapper.getAllOrderPayment();

        return allOrderPayment;
    }

    @Override
    public List<Roomset> getAllRoomset() {
        List<Roomset> roomsets = roomsetMapper.findAllRoomset();

        return roomsets;
    }

    @Override
    public List<Passenger> getAllPassenger() {
        List<Passenger> allPassenger = passengerMapper.findAllPassenger();
        return allPassenger;
    }

    @Override
    public Boolean pushRoomset(String ordId) {
        return ordermainMapper.pushRoomset(ordId);
    }

    @Override
    public ArrayList<Ordermain> getAllOrderIsBookingOn() {
        ArrayList<Ordermain> ordermains = ordermainMapper.getAllOrderIsBookingOn();

        return ordermains;
    }

    @Override
    public ArrayList<Ordermain> getAllOrderAlreadyBooking() {
        return ordermainMapper.getAllOrderAlreadyBooking();
    }

    @Override
    public ArrayList<Listone> getAllStats() {
        return listoneMapper.getListOne(666);
    }

    @Override
    public Passenger findPassengerById(Integer integer) {
        return passengerMapper.findPassengerById(integer);
    }

    @Override
    public List<Recepobject> getAllRecepobject() {
        List<Recepobject> allRecepobject = recepobjectMapper.findAllObj();
        return allRecepobject;
    }

    @Override
    public Recepobject findRecepobjectById(Integer id) {
        return recepobjectMapper.findReceptById(id);
    }

    @Override
    public Page<Ordermain> getPageOfOrdermains(Integer currentPage, String txtname, String state) {
        //当前页码处理
        if(null == currentPage || 0 == currentPage){
            currentPage=1;
        }
        //搜索文本处理
        if("".equals(txtname)||null==txtname){
            txtname = "%";
        } else {
            txtname = "%" + txtname + "%";
        }


        Page<Ordermain> ordermainPage = new Page<>();


        HashMap<String, Object> hashMap = new HashMap<>();

        //获取当前类别总数
        Integer sumCount =  ordermainMapper.getPageOfOrdermains(state);

        if(null != sumCount){
            ordermainPage.initOrder(sumCount,currentPage);
            hashMap.put("teamname", txtname);
            hashMap.put("name",txtname);
            hashMap.put("offset", ordermainPage.ORDERMAIN__NUM_PER_PAGE * (currentPage - 1));
            hashMap.put("limit", ordermainPage.ORDERMAIN__NUM_PER_PAGE);
            hashMap.put("state",state);
            List<Ordermain> list = ordermainMapper.findPartOrdermains(hashMap);
            ordermainPage.setResult(list);
        }


        return ordermainPage;
    }

    @Override
    public void arrangeRoom(String id) {
        String[] ids = id.split(",");
        for (String s : ids) {
            ordermainMapper.updateOrderFormByOrdId(s);
            ordermainMapper.updateStateByOrdId(s);

            //改房间真实状态
            Roomsetstatus roomsetstatus = roomsetstatusMapper.findStatusByPrimaryKey(6);
            HashMap<String, Object> map = new HashMap<>();
            map.put("roomStateID",roomsetstatus.getFar_id());;
            map.put("roomStateName",roomsetstatus.getAttributeDetailsName());
            map.put("id",ordermainMapper.findOrderById(s).get(0).getRoomId());
            roomsetMapper.updateRoomStateById(map);

        }

    }


}
