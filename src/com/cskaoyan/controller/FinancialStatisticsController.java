package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/FinancialStatistics")
@Controller
public class FinancialStatisticsController {


    @RequestMapping("/tolist")


    /**
     * 跳转财务list页面的预处理Controller
     * 需要预置的数据有
     * 1.分页数据
     * 2.Order表单list数据，包含换房信息
     *
     */
    public String roomsetToList(){

        return "/WEB-INF/jsp/financialstatistics/financialstatistics.jsp";
    }
}
