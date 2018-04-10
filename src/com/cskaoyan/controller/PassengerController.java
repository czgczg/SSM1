package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PassengerController {
    @RequestMapping("Passenger/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/passenger/list.jsp";
    }
}
