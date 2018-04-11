package com.cskaoyan.controller;

import com.cskaoyan.bean.Passenger;
import com.cskaoyan.utils.PageDivide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/passenger")
@Controller
public class PassengerController {

//    @Autowired
//    PassengerService passengerService;

    @RequestMapping("/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/passenger/list.jsp";
    }


    //

    @RequestMapping("/toadd")
    public String passengerToAdd(HttpServletRequest request){
        HashMap<String,String> male = new HashMap<>(10);
        male.put("far_id","1");
        male.put("attributeDetailsName","男");
        HashMap<String,String> female = new HashMap<>(10);
        female.put("far_id","2");
        female.put("attributeDetailsName","女");

        ArrayList<HashMap> listGender = new ArrayList<>();
        listGender.add(male);
        listGender.add(female);
        request.setAttribute("listGender",listGender);

        //-------------------------------------------
        //民族
        HashMap<String,String> nation1 = new HashMap<>(10);
        nation1.put("far_id", "1");
        nation1.put("attributeDetailsName", "汉族");

        HashMap<String,String> nation2 = new HashMap<>(10);
        nation2.put("far_id", "2");
        nation2.put("attributeDetailsName", "其他");
        ArrayList<HashMap> listNation = new ArrayList<>();

        listNation.add(nation1);
        listNation.add(nation2);

        request.setAttribute("listNation",listNation);

//-----------------------------------------------------
        //文化程度  去数据库获取





        return "/WEB-INF/jsp/passenger/add.jsp";
    }



//    @PostMapping("/tolist.do")
//    public String findPassengerByName(HttpServletRequest request) {
//
//        String passenName = request.getParameter("txtname");
//
//        List<Passenger> passenger = passengerService.findPassengerByName(passenName);
//
//        PageDivide<Passenger> pageDivide = new PageDivide<>();
//
//        //获取所有旅客数量
////       int  totalCount= passengerService.findAllPassengerCount();
//
//       pageDivide.setTotalCount(totalCount);
//        pageDivide.init();
//
//        return null;
//
//    }
}
