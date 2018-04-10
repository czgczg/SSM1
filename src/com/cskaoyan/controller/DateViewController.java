package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DateViewController {
    @RequestMapping("DateView/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/dateview/shili.jsp";
    }

}
