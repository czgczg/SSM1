package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/DateView")
@Controller
public class DateViewController {
    @RequestMapping("/tolist")
    public String roomsetToList(Model model){

        for (int i = 12; i >0; i--) {
            model.addAttribute("year"+i,"2018-"+i);
            model.addAttribute("sZongFeiYong1"+i,100-i);
            model.addAttribute("tZongFeiYong"+i,i<<2);
        }


        return "/WEB-INF/jsp/dateview/shili.jsp";
    }

}
