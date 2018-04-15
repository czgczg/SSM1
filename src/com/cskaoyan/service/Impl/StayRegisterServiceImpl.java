package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.*;
import com.cskaoyan.dao.*;
import com.cskaoyan.service.StayRegisterService;
import com.cskaoyan.utils.Page;
import com.cskaoyan.vo.Listone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

    @Autowired
    ChangeroomMapper changeroomMapper;

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
        return ordermainMapper.findOrderByRoomNum(oid);
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
    public Page<Ordermain> findPage(Integer currentPage, String roomNumber,String lvKeLeiXingId,String isBillID) {
        Page<Ordermain> page = new Page<>();
        int totalNumber = findAllOrderMainCount();
        page.setTotalCount(totalNumber);
        int num = currentPage;
        page.initOrder(totalNumber,num);
        page.setCurrentPage(num);

        HashMap<String, Object> map = new HashMap<>();
        map.put("limit", Page. ORDERMAIN__NUM_PER_PAGE);
        map.put ("offset",(num - 1) * Page. ORDERMAIN__NUM_PER_PAGE);
        map.put("receiveTargetID",lvKeLeiXingId);
        map.put("isBillID",isBillID);
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
    public List<Roomset> findRoomsetAsEmpty(String roomNumber) {
        return roomsetMapper.findRoomsetAsEmpty("%" + roomNumber+ "%");
    }

    @Override
    public Ordermain findOrderById(String id) {
        return ordermainMapper.findOrderById(id).get(0);
    }

    @Override
    public  List<Ordermain> findOrderByRoomNum(String roomNumber) {
        return ordermainMapper.findOrderByRoomNum(roomNumber);
    }

    /**
     * 修改订单表（ordermain中的roomNumber）
     * @param ordermain
     */
    @Override
    public int changeOrderRoomNumber(Ordermain ordermain) {
       return ordermainMapper.changeOrderRoomNumber(ordermain);
    }

    /**
     * 实现换房。
     * @param oldRoomNum
     * @param newRoomNum
     * @return
     */

    @Override
    //》》》》》》》》》》》》》》》》》》》》》》》》》》》》》Roomnum不是主键需要迭代
    public boolean changeRoom(String oldRoomNum, String newRoomNum) {
        //根据房间号查找对应的订单,数据问题，有多个重复房间数据，只取第一个
        List<Ordermain> orderById = ordermainMapper.findOrderByRoomNum(oldRoomNum);
        Ordermain ordermain = orderById.get(0);

        //需要将订单房间号更改,将实例房间号改变
        ordermain.setRoomNumber(newRoomNum);
        //换房次数+1，换房时间
        int changingRoomNumber = ordermain.getChangingRoomNumber() + 1;
        ordermain.setChangingRoomNumber(changingRoomNumber);
        //获取当前时间作为换房时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String format = df.format(new Date());
        ordermain.setTimestamp(new Date());
        //换房费changRoomMoney
        double changRoomMoney = changingRoomNumber * 200;
        ordermain.setChangRoomMoney(changRoomMoney);

        //》》》》》》》》》》》》》》》》》》》总金额需要迭代
        //ordermain.setSumConst( roomsetMapper.findRoomsetByRoomNumber(oldRoomNum).get(0).getStandardPriceDay() + ordermain.getSumConst()+200);

        //还需要修改数据库，传入ordId不变，房间号改命的实例，使得数据库中房间号改变。
        //玩家换房次数和换房时间
        int result = ordermainMapper.changeOrderRoomNumber(ordermain);

        //订单id，需要放入changeroom实例中
        String ordID = ordermain.getOrdID();


        //新建一个changeroom实例，填充数据，并保存进数据库
        Changeroom changeroom = new Changeroom();
        changeroom.setOrdId(ordID);
        changeroom.setOldRoomset(oldRoomNum);
        changeroom.setNewRoomset(newRoomNum);
        changeroom.setAffterPay(200);//换房费


        changeroom.setChangRoomTime(format);

        //插入数据库
        int ret = changeroomMapper.insertChangeRoom(changeroom);
        return ret == 1;
    }

    @Override
    public List<Deposit> findDepositRecordsByOrdId(String id) {
        return ordermainMapper.findDepositRecordsByOrdId(id);
    }

    @Override
    public boolean addDeposit(Deposit deposit) {
        return ordermainMapper.addDeposit(deposit) == 1;
    }

    private int findAllOrderMainCount() {

       return ordermainMapper.findAllOrderCount();

    }

//    @Override
//    public void modifyRoomStatus(String roomNumber) {
//        roomsetMapper.modifyRoomStatus(roomNumber);
//    }

//    @Override
//    public void modifyOrderStatus() {
//        ordermainMapper.modifyOrderStatus();
//    }


}
