package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PredetermineController {

    @RequestMapping("Predetermine/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/predetermine/list.jsp";
    }
}
