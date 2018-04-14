package com.cskaoyan.controller;

import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Orderpayment;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Roomset;
import com.cskaoyan.dao.OrdermainMapper;
import com.cskaoyan.dao.OrderpaymentMapper;
import com.cskaoyan.dao.PassengerMapper;
import com.cskaoyan.dao.RoomsetMapper;
import com.cskaoyan.service.PredetermineService;
import com.cskaoyan.utils.Page;
import com.cskaoyan.vo.Listone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/Predetermine")
@Controller
public class PredetermineController {


    @Autowired
    PredetermineService predetermineService;


    @RequestMapping("/tolist")
    public String roomsetToList(HttpServletRequest request,Integer currentPage, String txtname,String state){

        //下拉框 已安排未安排
        ArrayList<Listone> allStats = predetermineService.getAllStats();

        if(null == state){
            state = "55,56";
        }
        //
        Page<Ordermain> page = predetermineService.getPageOfOrdermains(currentPage, txtname, state);

        System.out.println(page.getResult());

        request.setAttribute("list",page);

        request.setAttribute("listOne",allStats);
        return "/WEB-INF/jsp/predetermine/list.jsp";
    }





    @GetMapping("/toadd")
    public String predetermineToAdd(HttpServletRequest request){
        String id = request.getParameter("id");  //乘客id
        String name = request.getParameter("name");   //
        String type = request.getParameter("type");  //1为团队 2为散客


        request.setAttribute("name",name);
        request.setAttribute("id",id);
        request.setAttribute("type",type);

        ArrayList<Orderpayment> allOrderPayment = predetermineService.getAllOrderPayment();
        request.setAttribute("listOne",allOrderPayment);

        return "/WEB-INF/jsp/predetermine/add.jsp";
    }




    //根据room num查询
    @PostMapping("/selectRoom")
    @ResponseBody
    public List<Roomset> predetermineSelectRoom(Roomset roomset){
        List<Roomset> roomsets = predetermineService.getAllRoomset();
        return roomsets;
    }

    //selectTarget
    //查询所有的可下单人
    @PostMapping("/selectPassenger")
    @ResponseBody
    public List<Passenger> predetermineSelectTarget(Roomset roomset){

        List<Passenger> allPassenger = predetermineService.getAllPassenger();
        return allPassenger;
    }
    //选取某个下单人后返回
    @RequestMapping("/confirmPassenger")
    @ResponseBody
    public Passenger confirmPassenger(HttpServletRequest request){
        Passenger passenger = predetermineService.findPassengerById(Integer.valueOf(request.getParameter("id")));
        System.out.println(passenger.getContactPhoneNumber());
        return passenger;
    }


    //新增订单
    @PostMapping("/add")
    public String addOrder( Ordermain ordermain, HttpServletRequest request/*,@RequestParam Date arriveTime*/ ){
        String id = request.getParameter("id");
        ordermain.setPassengerOrReceiveID(Integer.valueOf(id));
        String type = request.getParameter("type");//1是团队2是散客
        ordermain.setReceiveTargetID(Integer.valueOf(type));
        if(type.equals("56")){
            //取团队对象
            ordermain.setTeamname(ordermain.getCommodityName());
        }else if(type.equals("55")){
            //取散客对象
            ordermain.setName(ordermain.getCommodityName());
        }

        String[] roomsets = request.getParameter("roomIdShuZu").split(",");

        predetermineService.inputOrder(ordermain,roomsets);

        return "redirect:/Predetermine/tolist.do";
    }



}
