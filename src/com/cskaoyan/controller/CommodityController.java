package com.cskaoyan.controller;


import com.cskaoyan.bean.Commodity;
import com.cskaoyan.bean.Commoditymeasurement;
import com.cskaoyan.bean.Commoditytype;
import com.cskaoyan.dao.CommodityMapper;
import com.cskaoyan.dao.CommoditymeasurementMapper;
import com.cskaoyan.dao.CommoditytypeMapper;
import com.cskaoyan.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    @GetMapping("Commodity/tolist")
    public String commodityToList(){

        return "/WEB-INF/jsp/commodity/list.jsp";
    }


    @PostMapping("Commodity/toadd")
    public String commodityToAdd(HttpServletRequest request){

        ArrayList<Commoditytype> allCommoditytype = commodityService.getAllCommoditytype();
        ArrayList<Commoditymeasurement> allCommoditymeasurement = commodityService.getAllCommoditymeasurement();

        request.setAttribute("listTwo",allCommoditytype);
        request.setAttribute("listOne",allCommoditymeasurement);
        return "/WEB-INF/jsp/commodity/add.jsp";
    }



    @PostMapping("Commodity/add")
    public String commodityAdd(Commodity commodity, HttpServletRequest request){
        int i = commodityService.commodityAdd(commodity, request);
        return "/WEB-INF/jsp/commodity/list.jsp";
    }
}
