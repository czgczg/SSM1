package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReceiveTargetController {

    @RequestMapping("ReceiveTarget/tolist")
    public String roomsetToList(){


        return "/WEB-INF/jsp/receivetarget/list.jsp";
    }
}
