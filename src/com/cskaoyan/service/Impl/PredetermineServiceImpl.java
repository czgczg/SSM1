package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Orderpayment;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Roomset;
import com.cskaoyan.dao.OrdermainMapper;
import com.cskaoyan.dao.OrderpaymentMapper;
import com.cskaoyan.dao.PassengerMapper;
import com.cskaoyan.dao.RoomsetMapper;
import com.cskaoyan.service.PredetermineService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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


    @Override
    public Ordermain inputOrder(Ordermain ordermain,String[] roomsets) {
        //状态设置为未安排66
        ordermain.setState(66);

        //改房间真实状态
        //roomsetMapper.


        if(null == ordermain.getRentOutTypeName()){
            ordermain.setRentOutTypeId(1);
        }


        //希望可以显示到秒
        ordermain.setOrderTime(new Date());

        ordermain.setDel_flag(0);
        ordermain.setPayWayName(orderpaymentMapper.selectByPrimaryKey(ordermain.getPayWayID()).getAttributeDetailsName());

        for (String s : roomsets) {
            //订单号201804120001
            String ordId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().substring(0,5);
            ordermain.setOrdID(ordId);
            Roomset roomset = roomsetMapper.findById(Integer.parseInt(s));
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
    public ArrayList<Ordermain> getAllOrderIsOnBooking() {
        ArrayList<Ordermain> ordermains = ordermainMapper.getAllOrderIsOnBooking();

        return ordermains;
    }
}
