package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/ReceiveTarget")
@Controller
public class ReceiveTargetController {

    @GetMapping("/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/receivetarget/list.jsp";
    }
}
