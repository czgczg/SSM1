package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Passenger;
import com.cskaoyan.dao.PassengerMapper;
import com.cskaoyan.dao.PassengerdegreeMapper;
import com.cskaoyan.service.PassengerService;
import com.cskaoyan.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerMapper passengerMapper;

    @Autowired
    PassengerdegreeMapper passengerdegreeMapper;


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
     * 添加用户到数据库
     */
    @Override
    public int passengerAdd(Passenger passenger) {
        switch (passenger.getGenderID()){
            case "1":
                passenger.setGenderID("男");
                break;
            case "2":
                passenger.setGenderID("女");
                break;
        }
        switch (passenger.getNationID()){
            case "1":
                passenger.setNationID("汉族");
                break;
            case "2":
                passenger.setNationID("其他");
                break;
        }

        switch (passenger.getPassengerLevelID()){
            case "1":
                passenger.setPassengerLevelID("首次");
                break;
            case "2":
                passenger.setPassengerLevelID("熟客");
                break;
            case "3":
                passenger.setPassengerLevelID("VIP");
                break;
        }

        switch (passenger.getPapersID()){
            case "1":
                passenger.setPapersID("二代身份证");
                break;
            case "2":
                passenger.setPapersID("护照");
                break;
            case "3":
                passenger.setPapersID("其他");
        }


        switch (passenger.getThingReasonID()){
            case "1":
                passenger.setThingReasonID("个人旅行");
                break;
            case "2":
                passenger.setThingReasonID("公务出差");
                break;
            case "3":
                passenger.setThingReasonID("其他");
        }


        if( passenger.getEducationDegreeID().matches("^[0-9]*[1-9][0-9]*$")){

            int i = Integer.parseInt(passenger.getEducationDegreeID());
            String passengerDegreeNameById = passengerdegreeMapper.findPassengerDegreeNameById(i);
            passenger.setEducationDegreeID(passengerDegreeNameById);
        }

        int i=passengerMapper.insert(passenger);
        return i;
    }

    /**
     * 查找所有的旅客数量
     * @return
     */
    @Override
    public int findAllPassengerCount() {
        return   0;
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
