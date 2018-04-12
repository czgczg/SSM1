package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Passenger;
import com.cskaoyan.dao.PassengerMapper;
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

    /**
     *添加旅客信息
     * @param passenger
     * @return
     */
    @Override
    public int addPassenger(Passenger passenger) {

        return   passengerMapper.addPassenger(passenger);

    }



    /**
     * 分页处理
     * @param num
     * @return
     */
    @Override
    public Page<Passenger> findPage(int  num) {
        Page<Passenger> page = new Page<>();

        int totalNumber = findAllPassengerCount();
        page.setTotalCount(totalNumber);

        page.init(num);

        page.setCurrentPage(num);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("limit",Page.PASSENGER__NUM_PER_PAGE);
        map.put ("offset",(num - 1) * Page.PASSENGER__NUM_PER_PAGE);

        List<Passenger> passengers = passengerMapper.findPartPassenger(map);

        for (Passenger passenger : passengers) {
            String genderID = passenger.getGenderID();//性别
            String nationID = passenger.getNationID();//民族
            String educationDegreeID = passenger.getEducationDegreeID();//教育程度

            String papersID = passenger.getPapersID();//证件类型
            String passengerLevelID = passenger.getPassengerLevelID();//旅客级别
            String thingReasonID = passenger.getThingReasonID();//是由


            String genderName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(genderID));
            String nationName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(nationID));
            String passengerLevelName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(passengerLevelID));
            String papersName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(papersID));
            passenger.setGenderID(genderID);
            passenger.setNationID(nationName);
            passenger.setPassengerLevelID(passengerLevelID);
            passenger.setPapersID(papersName);
        }

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

        return   passengerMapper.findAllPassengerCount();
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
