package com.cskaoyan.controller;

import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Passengerdegree;
import com.cskaoyan.service.PassengerService;
import com.cskaoyan.service.PassengerdegreeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/Passenger")
@Controller
public class PassengerController {
    @GetMapping("/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/passenger/list.jsp";
    }

    @Autowired
    PassengerdegreeService passengerdegreeService;
    @Autowired
    PassengerService passengerService;
    //

    @RequestMapping("/toadd")
    public String passengerToAdd(HttpServletRequest request){

        //-------------------------------------------
        //性别
        ArrayList<HashMap> listGender = getHashMaps("男", "女");
        request.setAttribute("listGender",listGender);


        //-------------------------------------------
        //民族
        ArrayList<HashMap> listNation = getHashMaps("汉族", "其他");
        request.setAttribute("listNation",listNation);

//-----------------------------------------------------
        //文化程度 数据库获取
        ArrayList<HashMap>listEducationDegree=new ArrayList<>();

        List<Passengerdegree> findall = passengerdegreeService.findall();

        for (int i = 0; i <findall.size() ; i++) {
            HashMap<String, String> j= new HashMap<>(10);
            j.put("far_id",findall.get(i).getFar_id().toString());
           j.put("attributeDetailsName",findall.get(i).getPassengerDegreeName());
           listEducationDegree.add(j);
        }

        request.setAttribute("listEducationDegree",listEducationDegree);

        //-----------------------------------------------------
        //旅客级别
        ArrayList<HashMap> listPassengerLevel = getHashMaps("首次", "熟客", "VIP");
        request.setAttribute("listPassengerLevel",listPassengerLevel);
        //-----------------------------------------------------
        //证件类型
        ArrayList<HashMap> listPapers = getHashMaps("二代身份证", "护照", "其他");

        request.setAttribute("listPapers",listPapers);
        //-----------------------------------------------------
        //事由类型

        ArrayList<HashMap> listThingReason = getHashMaps("个人旅行", "公务出差", "其他");
        request.setAttribute("listThingReason",listThingReason);
        return "/WEB-INF/jsp/passenger/add.jsp";
    }

    private ArrayList<HashMap> getHashMaps(String d, String e, String f) {
        HashMap<String, String> level1 = new HashMap<>();
        level1.put("far_id", "1");
        level1.put("attributeDetailsName", d);

        HashMap<String, String> level2 = new HashMap<>();
        level2.put("far_id", "2");
        level2.put("attributeDetailsName", e);

        HashMap<String, String> level3 = new HashMap<>();
        level3.put("far_id", "3");
        level3.put("attributeDetailsName", f);


        ArrayList<HashMap> listPassengerLevel = new ArrayList<>();
        listPassengerLevel.add(level1);
        listPassengerLevel.add(level2);
        listPassengerLevel.add(level3);
        return listPassengerLevel;
    }

    private ArrayList<HashMap> getHashMaps(String a, String b) {
        HashMap<String, String> male = new HashMap<>(10);
        male.put("far_id", "1");
        male.put("attributeDetailsName", a);
        HashMap<String, String> female = new HashMap<>(10);
        female.put("far_id", "2");
        female.put("attributeDetailsName", b);

        ArrayList<HashMap> listGender = new ArrayList<>();
        listGender.add(male);
        listGender.add(female);
        return listGender;
    }


    @PostMapping("/add")
    public String passengerAdd(HttpServletRequest request, Passenger passenger) {
        int i = passengerService.passengerAdd(passenger);
        return "/WEB-INF/jsp/passenger/list.jsp";
    }
















}
