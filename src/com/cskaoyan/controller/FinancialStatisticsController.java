package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FinancialStatisticsController {


    @RequestMapping("FinancialStatistics/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/financialstatistics/financialstatistics.jsp";
    }
}
