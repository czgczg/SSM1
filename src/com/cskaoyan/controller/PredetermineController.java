package com.cskaoyan.controller;

import com.cskaoyan.bean.Orderpayment;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Roomset;
import com.cskaoyan.dao.OrderpaymentMapper;
import com.cskaoyan.dao.PassengerMapper;
import com.cskaoyan.dao.RoomsetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;






@RequestMapping("/Predetermine")
@Controller
public class PredetermineController {


    @Autowired
    PassengerMapper passengerMapper;
    @Autowired
    OrderpaymentMapper orderpaymentMapper;
    @Autowired
    RoomsetMapper roomsetMapper;




    @GetMapping("/tolist")
    public String roomsetToList(HttpServletRequest request){
        String state = request.getParameter("state");//66未安排，67已安排

        return "/WEB-INF/jsp/predetermine/list.jsp";
    }
    



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








    @RequestMapping("/selectTarget")
    public void selectTarget(){

    }






    //根据room num查询
    @PostMapping("/selectRoom")
    @ResponseBody
    public List<Roomset> predetermineSelectRoom(Roomset roomset){
//        System.out.println(roomset.getRoomNumber());
//        List<Roomset> roomsets = roomsetMapper.selectAllRoomset();
//        System.out.println(roomsets);
        return null;
    }

@RequestMapping("/comfirmPassenger")
    public void comfirmPassenger(){

}

}
