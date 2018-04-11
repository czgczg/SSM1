package com.cskaoyan.controller;


import com.cskaoyan.bean.Commodity;
import com.cskaoyan.bean.Commoditymeasurement;
import com.cskaoyan.bean.Commoditytype;
import com.cskaoyan.dao.CommodityMapper;
import com.cskaoyan.dao.CommoditymeasurementMapper;
import com.cskaoyan.dao.CommoditytypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RequestMapping("/Commodity")
@Controller
public class CommodityController {

    @RequestMapping("/tolist")
    public String commodityToList(){

        return "/WEB-INF/jsp/commodity/list.jsp";
    }
    @Autowired
    CommoditytypeMapper commoditytypeMapper;

    @Autowired
    CommoditymeasurementMapper commoditymeasurementMapper;

    @RequestMapping("/toadd")
    public String commodityToAdd(HttpServletRequest request){
        ArrayList<Commoditytype> allCommoditytype = commoditytypeMapper.getAllCommoditytype();
        ArrayList<Commoditymeasurement> allCommoditymeasurement = commoditymeasurementMapper.getAllCommoditymeasurement();
        request.setAttribute("listTwo",allCommoditytype);
        request.setAttribute("listOne",allCommoditymeasurement);
        return "/WEB-INF/jsp/commodity/add.jsp";
    }

    @Autowired
    CommodityMapper commodityMapper;

    @RequestMapping("/add")
    public String commodityAdd(Commodity commodity, HttpServletRequest request){
        int i = commodityMapper.insertSelective(commodity);
        System.out.println(i);
        return "/WEB-INF/jsp/commodity/list.jsp";
    }
}
