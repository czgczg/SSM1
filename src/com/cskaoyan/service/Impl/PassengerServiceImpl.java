package com.cskaoyan.service.Impl;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.dao.PassengerMapper;
import com.cskaoyan.service.PassengerService;
import com.cskaoyan.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ObjectOutput;
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
        //拿到id对应的下拉form的具体属性名
        String genderID = passenger.getGenderID();
        String genderName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(genderID));
        String nationID = passenger.getNationID();
        String nationName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(nationID));
        String educationDegreeID = passenger.getEducationDegreeID();
        String educationDegreeName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(educationDegreeID));
        String passengerLevelID = passenger.getPassengerLevelID();
        String passengerLevelName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(passengerLevelID));
        String papersID = passenger.getPapersID();
        String papersName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(papersID));
        String thingReasonID = passenger.getThingReasonID();
        String thingsReasonName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(thingReasonID));

        passenger.setGenderName(genderName);
        passenger.setNationName(nationName);
        passenger.setEducationDegreeName(educationDegreeName);
        passenger.setPassengerLevelName(passengerLevelName);
        passenger.setPapersName(papersName);
        passenger.setThingReasonName(thingsReasonName);

        return   passengerMapper.addPassenger(passenger);
    }

    /**
     * 删除旅客
     * @param passenger
     */
    @Override
    public void deletePassenger(Passenger passenger) {

        //这个可以做一个工具类，每次中间的service曾都要转一下 很是麻烦
        //拿到id对应的下拉form的具体属性名
//        Object  genderID = passenger.getGenderID();
//        String genderName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(genderID));
//        String nationID = passenger.getNationID();
//        String nationName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(nationID));
//        String educationDegreeID = passenger.getEducationDegreeID();
//        String educationDegreeName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(educationDegreeID));
//        String passengerLevelID = passenger.getPassengerLevelID();
//        String passengerLevelName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(passengerLevelID));
//        String papersID = passenger.getPapersID();
//        String papersName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(papersID));
//        String thingReasonID = passenger.getThingReasonID();
//        String thingsReasonName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(thingReasonID));
//
////        passenger.setGenderName(genderName);
//        passenger.setNationName(nationName);
//        passenger.setEducationDegreeName(educationDegreeName);
//        passenger.setPassengerLevelName(passengerLevelName);
//        passenger.setPapersName(papersName);
//        passenger.setThingReasonName(thingsReasonName);

        passengerMapper.deletePassenger(passenger);

    }

    //更新旅客信息
    @Override
    public Boolean updatePassenger(Passenger passenger) {

        //拿到id对应的下拉form的具体属性名
        String genderID = passenger.getGenderID();
        String genderName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(genderID));
        String nationID = passenger.getNationID();
        String nationName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(nationID));
        String educationDegreeID = passenger.getEducationDegreeID();
        String educationDegreeName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(educationDegreeID));
        String passengerLevelID = passenger.getPassengerLevelID();
        String passengerLevelName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(passengerLevelID));
        String papersID = passenger.getPapersID();
        String papersName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(papersID));
        String thingReasonID = passenger.getThingReasonID();
        String thingsReasonName = passengerMapper.findPassengerAttributeByID(Integer.parseInt(thingReasonID));

        passenger.setGenderName(genderName);
        passenger.setNationName(nationName);
        passenger.setEducationDegreeName(educationDegreeName);
        passenger.setPassengerLevelName(passengerLevelName);
        passenger.setPapersName(papersName);
        passenger.setThingReasonName(thingsReasonName);

        int i = passengerMapper.updatePassenger(passenger);
        return i==1;
    }


    /**
     * 分页处理
     * @param num
     * @return
     */
    @Override
    public Page<Passenger> findPage(int  num,String passengerName) {
        Page<Passenger> page = new Page<>();

        int totalNumber = findAllPassengerCount();
        page.setTotalCount(totalNumber);
        page.init(num);
        page.setCurrentPage(num);

        HashMap<String, Object> map = new HashMap<>();
        map.put("limit",Page.PASSENGER__NUM_PER_PAGE);
        map.put ("offset",(num - 1) * Page.PASSENGER__NUM_PER_PAGE);
        map.put("name", passengerName);

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