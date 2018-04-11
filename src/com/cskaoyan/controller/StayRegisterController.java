package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/StayRegister")
@Controller
public class StayRegisterController {

    @GetMapping("/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/stayregister/list.jsp";
    }
}
