package com.cskaoyan.controller;
import com.cskaoyan.bean.Commodity;
import com.cskaoyan.bean.Commoditymeasurement;
import com.cskaoyan.bean.Commoditytype;
import com.cskaoyan.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

//窄化请求
@RequestMapping("/Commodity")
@Controller
public class CommodityController {

    @Autowired
    CommodityService commodityService;


    @GetMapping("/tolist")
    public String commodityToList(){

        return "/WEB-INF/jsp/commodity/list.jsp";
    }


    @PostMapping("/toadd")
    public String commodityToAdd(HttpServletRequest request){

        ArrayList<Commoditytype> allCommoditytype = commodityService.getAllCommoditytype();
        ArrayList<Commoditymeasurement> allCommoditymeasurement = commodityService.getAllCommoditymeasurement();

        request.setAttribute("listTwo",allCommoditytype);
        request.setAttribute("listOne",allCommoditymeasurement);
        return "/WEB-INF/jsp/commodity/add.jsp";
    }


    @PostMapping("/add")
    public String commodityAdd(Commodity commodity, HttpServletRequest request){
        int i = commodityService.commodityAdd(commodity, request);
        return "/WEB-INF/jsp/commodity/list.jsp";
    }
}
