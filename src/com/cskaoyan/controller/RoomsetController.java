package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/RoomSet")
@Controller
public class RoomsetController {

    @GetMapping("/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/roomset/roomset.jsp";
    }
}
