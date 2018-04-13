package com.cskaoyan.service;

import com.cskaoyan.bean.Passenger;
import com.cskaoyan.utils.Page;

import java.util.List;

public interface PassengerService {
    //分页
    public Page<Passenger> findPage(int currentPage, String passengerName);


    List<Passenger> findPassengerByName(String passenName);

    int findAllPassengerCount();

    List<Passenger> findAllPassenger();

    int  addPassenger(Passenger passenger);

    Boolean updatePassenger(Passenger id);


    void deletePassenger(Passenger passenger);

    Passenger findPassengerById(Integer id);


}
