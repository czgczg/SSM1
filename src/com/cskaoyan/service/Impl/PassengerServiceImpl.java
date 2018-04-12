package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Passenger;
import com.cskaoyan.dao.PassengerMapper;
import com.cskaoyan.service.PassengerService;
import com.cskaoyan.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerMapper passengerMapper;

    /**
     * 分页处理
     * @param num
     * @return
     */
    @Override
    public Page<Passenger> findPage(String num) {

        Page<Passenger> page = new Page<>();

        int totalNumber = findAllPassengerCount();
        page.setTotalCount(totalNumber);

        page.init(Integer.parseInt(num));

        page.setCurrentPage(Integer.parseInt(num));
        int intNum = Integer.parseInt(num);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("limit",Page.PASSENGER__NUM_PER_PAGE);
        map.put ("offset",(intNum - 1) * Page.PASSENGER__NUM_PER_PAGE);

        List<Passenger> passengers = passengerMapper.findPartPassenger(map);

        page.setResult(passengers);

        return page;
    }

    /**
     * 根据名字查找旅客
     * @param passenName
     * @return
     */
    @Override
    public List<Passenger> findPassengerByName(String passenName) {

        List<Passenger> passenger = passengerMapper.findPassengerByName(passenName);

        return passenger;

    }


    /**
     * 查找所有的旅客数量
     * @return
     */
    @Override
    public int findAllPassengerCount() {
        return   2;
    }


    /**
     * 查找所有的旅客
     * @return
     */
    @Override
    public List<Passenger> findAllPassenger() {


        return passengerMapper.findAllPassenger();
    }
}
