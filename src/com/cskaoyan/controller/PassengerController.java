package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class PassengerController {
    @RequestMapping("Passenger/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/passenger/list.jsp";
    }


    //

    @RequestMapping("Passenger/toadd")
    public String passengerToAdd(HttpServletRequest request){
        HashMap<String,String> male = new HashMap<>();
        male.put("far_id","1");
        male.put("attributeDetailsName","男");
        HashMap<String,String> female = new HashMap<>();
        female.put("far_id","2");
        female.put("attributeDetailsName","女");
        ArrayList<HashMap> listGender = new ArrayList<>();
        listGender.add(male);
        listGender.add(female);
        request.setAttribute("listGender",listGender);


        return "/WEB-INF/jsp/passenger/add.jsp";
    }
}
