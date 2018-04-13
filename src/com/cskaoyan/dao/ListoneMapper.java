package com.cskaoyan.dao;




import com.cskaoyan.bean.Listone;

import java.util.List;

public interface ListoneMapper {

    List<Listone> getRoomset(int i);

    List<Listone> getRoomLevel(int i);

    List<Listone> getComodityType(int i);

    List<Listone> getPayWay(int i);

    List<Listone> getRendWay(int i);

    List<Listone> getPayUnit(int i);

    List<Listone> getPassengerType(int i);

    List<Listone> getSex(int i);

    List<Listone> getNation(int i);

    List<Listone> getIdentifyCard(int i);

    List<Listone> getEducationDegree(int i);

    List<Listone> getThingReason(int i);

    List<Listone> getpassengerLevel(int i);

    List<Listone> getObjectType(int i);

    List<Listone> getMeasureUnit(int i);

    List<Listone> getOrderStatus(int i);

    List<Listone> getPayMoney(int i);
}