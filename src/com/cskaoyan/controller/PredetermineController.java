package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Predetermine")
@Controller
public class PredetermineController {

    @GetMapping("/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/predetermine/list.jsp";
    }

    @PostMapping("/selectPassenger")
    public void selectPassenger(){

    }

}
