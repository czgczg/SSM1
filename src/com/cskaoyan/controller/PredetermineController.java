package com.cskaoyan.controller;

import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Orderpayment;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Roomset;
import com.cskaoyan.dao.OrdermainMapper;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        String id = request.getParameter("id");  //乘客id
        String name = request.getParameter("name");   //
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
        //System.out.println(roomsets);
        return roomsets;
    }

    //selectTarget
    @PostMapping("/selectPassenger")
    @ResponseBody
    public List<Passenger> predetermineSelectTarget(Roomset roomset){

        List<Passenger> allPassenger = passengerMapper.findAllPassenger();
        System.out.println(allPassenger);
        return allPassenger;
    }

    @Autowired
    OrdermainMapper ordermainMapper;

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

    @ResponseBody
    public Ordermain addOrder(Ordermain ordermain, HttpServletRequest request,Date arriveTime ){
        String id = request.getParameter("id");
        ordermain.setPassengerOrReceiveID(Integer.valueOf(id));

        String type = request.getParameter("type");//1是团队2是散客
        ordermain.setReceiveTargetID(Integer.valueOf(type));
        //订单号201804120001
        String ordId = UUID.randomUUID().toString();
        ordermain.setOrdID(ordId);
        if(type.equals("56")){
            //取团队对象
            ordermain.setTeamname(ordermain.getCommodityName());
        }else if(type.equals("55")){
            //取散客对象
            ordermain.setName(ordermain.getCommodityName());
        }


        //状态设置为未安排66
        ordermain.setState(66);

        //改房间真实状态
        //roomsetMapper.


        if(null == ordermain.getRentOutTypeName()){
            ordermain.setRentOutTypeName("1");
        }


        ordermain.setPayWayName(orderpaymentMapper.selectByPrimaryKey(ordermain.getPayWayID()).getAttributeDetailsName());

        String[] split = request.getParameter("roomIdShuZu").split(",");
        for (String s : split) {
//            System.out.println(s);//1234
            int i = Integer.parseInt(s);
            System.out.println(i);
            //Roomset roomset = roomsetMapper.selectByPrimaryKey(i);
            //ordermain.setRoomNumber(roomset.getRoomNumber());
            //ordermain.setGuestRoomLevelName(roomset.getGuestRoomLevelName());

        }

        System.out.println(ordermain);
        ordermainMapper.insert(ordermain);

        return ordermain;
    }



}
