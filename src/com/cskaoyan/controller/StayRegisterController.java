package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StayRegisterController {

    @RequestMapping("StayRegister/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/stayregister/list.jsp";
    }
}
