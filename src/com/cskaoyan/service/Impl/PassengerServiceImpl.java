package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Passengerdegree;
import com.cskaoyan.dao.PassengerMapper;
import com.cskaoyan.dao.PassengerdegreeMapper;
import com.cskaoyan.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerMapper passengerMapper;

    @Autowired
    PassengerdegreeMapper passengerdegreeMapper;


    @Override
    public List<Passenger> findPassengerByName(String passenName) {

        List<Passenger> passenger = passengerMapper.findPassengerByName(passenName);

        return passenger;

    }

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


        if( passenger.getEducationDegreeID().matches("^[1-9]\\d*$")) {

            int i = Integer.parseInt(passenger.getEducationDegreeID());
            String passengerDegreeNameById = passengerdegreeMapper.findPassengerDegreeNameById(i);
            passenger.setEducationDegreeID(passengerDegreeNameById);
        }

        int i=passengerMapper.insert(passenger);
        return i;
    }


    @Override
    public int findAllPassengerCount() {
        return passengerMapper.findAllPassengerCount();
    }
}
