/*
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
            String ordId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().substring(0,2);
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

    @Override
    public Ordermain findOrderminByOrdID(String id) {
        return ordermainMapper.findOrderById(id).get(0);
    }

    @Override
    public List<Roomset> getRoomsetByOrdID(String id) {
        ArrayList<Roomset> roomsets = new ArrayList<>();
        List<Ordermain> orderById = ordermainMapper.findOrderById(id);
        for (Ordermain ordermain : orderById) {
            System.out.println(ordermain.getRoomId());
            Roomset roomset = roomsetMapper.findById(ordermain.getRoomId());
            roomsets.add(roomset);
        }
        return roomsets;
    }

    @Override
    public void updateOrder(Ordermain ordermain) {
        //获取原本的房间号
        int roomIdOld = ordermainMapper.getRoomIdByOrdId(ordermain.getOrdID());
        int roomIdNew = ordermain.getRoomId();
        Roomset roomsetNew = roomsetMapper.findById(roomIdNew);
        //改变订单房间状态
        ordermain.setRoomId(roomsetNew.getId());
        ordermain.setRoomNumber(roomsetNew.getRoomNumber());
        ordermain.setGuestRoomLevelName(roomsetNew.getGuestRoomLevelName());

        //因为在预定中状态，不会出现商品消费或者换房消费，所以只需要重新计算房价以及押金
        ordermain.setSumConst(roomsetNew.getStandardPriceDay()*Integer.valueOf(ordermain.getPredetermineDay()));
        ordermainMapper.updateByPrimaryKeySelective(ordermain);
        //将新房间置为预定中，将老房间置为空
        Roomsetstatus roomsetstatus = roomsetstatusMapper.findStatusByPrimaryKey(3);
        HashMap<String, Object> map = new HashMap<>();
        map.put("roomStateID",roomsetstatus.getFar_id());;
        map.put("roomStateName",roomsetstatus.getAttributeDetailsName());
        map.put("id",roomIdNew);
        roomsetMapper.updateRoomStateById(map);
        HashMap<String, Object> map2 = new HashMap<>();
        Roomsetstatus roomsetstatus2 = roomsetstatusMapper.findStatusByPrimaryKey(1);
        map2.put("roomStateID",roomsetstatus2.getFar_id());;
        map2.put("roomStateName",roomsetstatus2.getAttributeDetailsName());
        map2.put("id",roomIdOld);
        roomsetMapper.updateRoomStateById(map2);

    }

    @Override
    public void deleteOrdermaim(String oid) {
        //根据订单类型做处理
        Ordermain ordermin = findOrderminByOrdID(oid);

        ordermainMapper.removeOrderMain(oid);

        //所有订单都要改变房间状态
        Roomsetstatus roomsetstatus = roomsetstatusMapper.findStatusByPrimaryKey(1);
        HashMap<String, Object> map = new HashMap<>();
        map.put("roomStateID",roomsetstatus.getFar_id());;
        map.put("roomStateName",roomsetstatus.getAttributeDetailsName());
        map.put("id",ordermin.getRoomId());
        roomsetMapper.updateRoomStateById(map);
        //已安排的订单需要同步通知删除相关的换房以及商品,待做
    }


}
*/
