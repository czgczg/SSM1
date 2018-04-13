package com.cskaoyan.service.Impl;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.dao.PassengerMapper;
import com.cskaoyan.service.PassengerService;
import com.cskaoyan.utils.Page;
import com.cskaoyan.utils.PassengerTransfer;
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
        //封装的工具类处理id和name一起存入数据库的问题
        PassengerTransfer.transfer(passenger, passengerMapper);
        return   passengerMapper.addPassenger(passenger);
    }

    @Override
    public Passenger findPassengerById(Integer id) {
        Passenger passenger = passengerMapper.findPassengerById(id);
        return passenger;
    }
    /**
     * 删除旅客
     * @param passenger
     */
    @Override
    public void deletePassenger(Passenger passenger) {

        passengerMapper.deletePassenger(passenger);
    }

    //更新旅客信息
    @Override
    public Boolean updatePassenger(Passenger passenger) {
        //拿到id对应的下拉form的具体属性名
        PassengerTransfer.transfer(passenger, passengerMapper);
        return 1== passengerMapper.updatePassenger(passenger);
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