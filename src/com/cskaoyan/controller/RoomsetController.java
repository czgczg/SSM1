package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoomsetController {

    @RequestMapping("RoomSet/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/roomset/roomset.jsp";
    }
}
