package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/DateView")
@Controller
public class DateViewController {
    @RequestMapping("/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/dateview/shili.jsp";
    }

}
