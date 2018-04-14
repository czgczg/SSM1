package com.cskaoyan.controller;

import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Roomset;
import com.cskaoyan.dao.PassengerMapper;
import com.cskaoyan.service.PassengerService;
import com.cskaoyan.service.StayRegisterService;
import com.cskaoyan.utils.Page;
import com.cskaoyan.utils.PassengerTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/StayRegister")
@Controller
public class StayRegisterController {

    @Autowired
    StayRegisterService stayRegisterService;
    @Autowired
    PassengerService passengerService;
    @Autowired
    PassengerMapper passengerMapper;

    /**
     * 显示页面和分页
     */
    @GetMapping("/tolist.do")
    public String roomsetToList(HttpServletRequest request,Integer currentPage,Model model) {
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");//55旅客，56团队
        String isBillID = request.getParameter("isBillID");//68未结账，69已结账
        //去数据库查到orderMain数据去给到前台jsp页面

        if (currentPage == null || currentPage == 0) {
            currentPage = 1;
        }

        //拿到输入用户名
        String roomNumber = request.getParameter("txtname");
        if (roomNumber == null || "".equals(roomNumber)) {
            roomNumber = "%";
        } else {
            roomNumber = "%" + roomNumber + "%";
        }
        //根据分页，查出限定的记录条数
        Page<Ordermain> page = stayRegisterService.findPage(currentPage, roomNumber);

        //将分页的记录数返回给前台
        model.addAttribute("list", page);
//        //返回视图层
//        stayRegisterService.queryOrderMain();
        return "/WEB-INF/jsp/stayregister/list.jsp";
    }

    /**
     * 登记   //55旅客，56团队StayRegister/register.do?LvKeLeiXingId=55
     */
    @GetMapping("/toregister.do")
    public String toregister(HttpServletRequest request) {
        return "/WEB-INF/jsp/stayregister/register.jsp";
    }

    //处理登记表单数据存入数据库
    @PostMapping("/register.do")
    public String register(HttpServletRequest request, Passenger passenger) {

        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
        String roomNumber = request.getParameter("roomNumber");
        String id = request.getParameter("id");//id是复选框的value
        if ("55".equals(lvKeLeiXingId)) {

            //如果旅客存在，直接更新旅客信息，因为登记的肯定是最真实的信息
            PassengerTransfer.transfer(passenger, passengerMapper);
            passengerService.addPassenger(passenger);

            //更新订单的状态为未结账
            stayRegisterService.modifyOrderStatus();
            //更新房态为满
            stayRegisterService.modifyRoomStatus(roomNumber);

        }


        return "/StayRegister/tolist.do";

    }
//选择旅客

    @PostMapping("/selectPassenger")
    public void selectPassenger() {

    }


//确认旅客

    @RequestMapping("/comfirmPassenger")
    public String comfirmPassenger() {
        return "redirect:tolist.do";
    }

    /**
     * 安排房间
     */
    @RequestMapping("/toanrrangeroom")
    public void toanrrangeroom() {

    }

    /**
     * 换房
     * jsp 中返回的是list集合，单个换房用list[0]表示。
     * @param request
     * @param ordermain
     */
    @RequestMapping("/tochangeroom")
    public String tochangeroom(HttpServletRequest request, Ordermain ordermain, HttpSession session){
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");//55旅客，56团队
        String id = request.getParameter("id"); //订单id
        String lvKeName = request.getParameter("lvKeName"); //旅客名字


        //根据订单id查询数据库得到该订单实例
        Ordermain orderById = stayRegisterService.findOrderById(id);
        //新建一个list，将order放入list中，再将list作为元素放入request
        List<Ordermain> list = new ArrayList<>();
        list.add(orderById);
        request.setAttribute("list",list);



        return "/WEB-INF/jsp/stayregister/changroom.jsp";
    }

    //选择房间
    @RequestMapping("/changeRoomSelectPassenger")
    @ResponseBody
    public  List<Roomset> changeRoomSelectPassenger(){

        //搜索room状态为空的房间
        List<Roomset> roomsetAsEmpty = stayRegisterService.findRoomsetAsEmpty();
        //返回数据
        return roomsetAsEmpty;


    }


    /*
    * 押金记录
    * */
    @RequestMapping("/todeposit")
    public void todeposit(HttpServletRequest request) {
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
        String lvKeName = request.getParameter("lvKeName");
        String id = request.getParameter("id");

    }


    /*
    * 旅客消费
    * */
    @RequestMapping("/toconsumption")
    public void toconsumption(HttpServletRequest request) {
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
        String lvKeName = request.getParameter("lvKeName");
        String id = request.getParameter("id");
        String roomNumber = request.getParameter("roomNumber");
        String isBillID = request.getParameter("isBillID");//68未结账，69已结账
        String number = request.getParameter("Number");
        request.getParameter("consumptionStayRegisterID");


    }

    //添加商品
    @RequestMapping("/tianJiaShangPin")
    public void tianJiaShangPin() {

    }

    //选择商品
    @RequestMapping("/consumption")
    public void consumption(HttpServletRequest request) {
        String id = request.getParameter("id");
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
        String number = request.getParameter("Number");
        String consumptionStayRegisterID = request.getParameter("consumptionStayRegisterID");

    }

    //删除商品
    @RequestMapping("/consumptionDelete")
    public void consumptionDelete(HttpServletRequest request) {
        String id = request.getParameter("id");
        String consumptionStayRegisterID = request.getParameter("consumptionStayRegisterID");
    }

    /*
    * 转换
    * */
//转入团队房间信息
    @RequestMapping("/toshiftteam")
    public void toshiftteam(HttpServletRequest request) {

    }

    //转为散客
    @RequestMapping("/toshiftpersonage")
    public void toshiftpersonage(HttpServletRequest request) {
        String id = request.getParameter("id");
        String stayregisterdetailsId = request.getParameter("stayregisterdetailsId");
    }

    //转入
    @RequestMapping("/changeOver")
    public String changeOver(HttpServletRequest request) {
        String id = request.getParameter("id");
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
        return "redirect:tolist.do";
    }


    /*
    * 结账
    * */
    @RequestMapping("/topay")
    public void topay(HttpServletRequest request) {
        String id = request.getParameter("id");
    }

    @RequestMapping("pay")
    public String pay(HttpServletRequest request) {
        String id = request.getParameter("id");
        String payTime = request.getParameter("payTime");
        String payWay = request.getParameter("payWay");
        String roomId = request.getParameter("roomId");
        return "redirect:tolist.do";
    }


}
