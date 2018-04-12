package com.cskaoyan.controller;

import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Orderpayment;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Roomset;
import com.cskaoyan.dao.OrderpaymentMapper;
import com.cskaoyan.dao.PassengerMapper;
import com.cskaoyan.dao.RoomsetMapper;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/Predetermine")
@Controller
public class PredetermineController {

    @GetMapping("/tolist")
    public String roomsetToList(){

        return "/WEB-INF/jsp/predetermine/list.jsp";
    }
    
    @Autowired
    PassengerMapper passengerMapper;
    @Autowired
    OrderpaymentMapper orderpaymentMapper;


    @GetMapping("/toadd")
    public String predetermineToAdd(HttpServletRequest request){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String type = request.getParameter("type");  //1为团队 2为散客

        request.setAttribute("name",name);
        request.setAttribute("id",id);
        request.setAttribute("type",type);

        ArrayList<Orderpayment> allOrderPayment = orderpaymentMapper.getAllOrderPayment();

        request.setAttribute("listOne",allOrderPayment);
        Passenger passenger = passengerMapper.selectByPrimaryKey(Integer.valueOf(id));


        return "/WEB-INF/jsp/predetermine/add.jsp";
    }

    @Autowired
    RoomsetMapper roomsetMapper;


    //根据room num查询
    @PostMapping("/selectRoom")
    @ResponseBody
    public List<Roomset> predetermineSelectRoom(Roomset roomset){
        //System.out.println(roomset.getRoomNumber());
        List<Roomset> roomsets = roomsetMapper.selectAllRoomset();
        System.out.println(roomsets);
        return roomsets;
    }



    //http://localhost:8080/Predetermine/add.do?id=2&type=1&roomIdShuZu=undefined

    //新增订单
    @PostMapping("/add")




    /**
     * {
     "commodityName":	"A级旅行社",
     "predetermineDay":"5",
     "deposit"	:"23",
     "payWayID":	"1",
     "arriveTime":	"2018-04-13+14:52:13"
     }
     */

    public String addOrder(Ordermain ordermain, HttpServletRequest request){
        String id = request.getParameter("id");
        Roomset roomset = roomsetMapper.selectByPrimaryKey(Integer.valueOf(id));

        String type = request.getParameter("type");//1是团队2是散客
        if(type.equals(1)){
            //取团队对象

        }else if(type.equals(2)){
            //取散客对象
        }
        String[] split = request.getParameter("roomIdShuZu").split(",");
        for (String s : split) {

        }

        System.out.println(ordermain);

        return "";
    }



}
