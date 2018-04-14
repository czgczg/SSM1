package com.cskaoyan.utils;

import com.cskaoyan.bean.Passenger;
import com.cskaoyan.dao.PassengerMapper;

public class PassengerTransfer {

    public static void transfer(Passenger passenger, PassengerMapper passengerMapper) {

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
    }

}
