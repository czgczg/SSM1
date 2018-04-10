package com.cskaoyan.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommodityController {

    @RequestMapping("Commodity/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/commodity/list.jsp";
    }
}
