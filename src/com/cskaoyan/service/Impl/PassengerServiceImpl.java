package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Passenger;
import com.cskaoyan.dao.PassengerMapper;
import com.cskaoyan.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerMapper passengerMapper;

    @Override
    public List<Passenger> findPassengerByName(String passenName) {

        List<Passenger> passenger = passengerMapper.findPassengerByName(passenName);

        return passenger;

    }


    @Override
    public int findAllPassengerCount() {
        return    passengerMapper.findAllPassengerCount();
    }
}
