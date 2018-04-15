package com.cskaoyan.controller;

import com.cskaoyan.bean.Commodity;
import com.cskaoyan.bean.Commoditytype;
import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Ordextconsum;
import com.cskaoyan.dao.OrdermainMapper;
import com.cskaoyan.service.FinancialStatisticsService;
import com.cskaoyan.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RequestMapping("/FinancialStatistics")
@Controller
public class FinancialStatisticsController {

    @Autowired
    FinancialStatisticsService financialStatisticsService;

    @Autowired
    OrdermainMapper ordermainMapper;

    @RequestMapping("/tolist")


    /**
     * 跳转财务list页面的预处理Controller
     * 需要预置的数据有
     * 1.分页数据
     * 2.Order表单list数据，包含换房信息
     *
     */
    public String roomsetToList(Integer currentPage, String datemin, String datemax, Model model){

        if(currentPage==null){
            currentPage = 1;
        }
        Page<Ordermain> page = financialStatisticsService.findPage(currentPage, datemin, datemax);
        model.addAttribute("list", page);
        return "/WEB-INF/jsp/financialstatistics/financialstatistics.jsp";
    }

    /**
     *
     * @param id 订单id
     * @param stayregisterdetailsId
     * @param min
     * @param max
     * @return
     */
    @RequestMapping("/toinformation")
    public String toinformation(String id, String stayregisterdetailsId, String min, String max, Model model){
        List<Ordextconsum> ordextconsums = financialStatisticsService.findAllOrdextconsumByOrd_id(id);
        Ordermain ordermain = ordermainMapper.findOrdermainByOrdId(id);
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(ordermain);
        model.addAttribute("listDeposit", ordextconsums);
        model.addAttribute("list", objects);
        return "/WEB-INF/jsp/financialstatistics/particulars.jsp";
    }
}
