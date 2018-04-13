package com.cskaoyan.service.Impl;

import com.cskaoyan.dao.ListoneMapper;
import com.cskaoyan.service.optionService;
import com.cskaoyan.vo.Listone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 所有下拉的属性的获取服务
 */


@Service
public class optionServiceImpl implements optionService {

    @Autowired
    ListoneMapper listoneMapper;



    @Override
    public List<Listone> getRoomSet() {


        return  listoneMapper.getRoomset(1);


    }

    @Override
    public List<Listone> getRoomLevel() {
        return listoneMapper.getRoomLevel(2);
    }

    @Override
    public List<Listone> getComodityType() {
        return listoneMapper.getComodityType(3);
    }

    @Override
    public List<Listone> getPayWay() {
        return listoneMapper.getPayWay(4);
    }

    @Override
    public List<Listone> getRendWay() {
        return listoneMapper.getRendWay(5);
    }

    @Override
    public List<Listone> getPayUnit() {
        return listoneMapper.getPayUnit(6);
    }

    @Override
    public List<Listone> getPassengerType() {
        return listoneMapper.getPassengerType(7);
    }

    @Override
    public List<Listone> getSex() {
        return listoneMapper.getSex(8);
    }

    @Override
    public List<Listone> getNation() {
        return listoneMapper.getNation(9);
    }

    @Override
    public List<Listone> getIdentifyCard() {
        return listoneMapper. getIdentifyCard(10) ;
    }

    @Override
    public List<Listone> getEducationDegree() {
        return listoneMapper.getEducationDegree(11);
    }

    @Override
    public List<Listone> getThingReason() {
        return listoneMapper. getThingReason(12);
    }

    @Override
    public List<Listone> getpassengerLevel() {
        return listoneMapper.getpassengerLevel(13);
    }

    @Override
    public List<Listone> getObjectType() {
        return listoneMapper.getObjectType(14);
    }

    @Override
    public List<Listone> getMeasureUnit() {
        return listoneMapper. getMeasureUnit(15);
    }

    @Override
    public List<Listone> getOrderStatus() {
        return listoneMapper. getOrderStatus(16);
    }

    @Override
    public List<Listone> getPayMoney() {
        return listoneMapper.getPayMoney(17);
    }
    }

