package com.cskaoyan.service;

import com.cskaoyan.bean.Passenger;

import java.util.List;

public interface PassengerService {

    List<Passenger> findPassengerByName(String passenName);


     int findAllPassengerCount();
}
