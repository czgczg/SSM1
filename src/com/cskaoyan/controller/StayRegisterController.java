package com.cskaoyan.controller;

import com.cskaoyan.bean.*;
import com.cskaoyan.dao.PassengerMapper;
import com.cskaoyan.service.CommodityService;
import com.cskaoyan.service.OrdermainService;
import com.cskaoyan.service.PassengerService;
import com.cskaoyan.service.StayRegisterService;
import com.cskaoyan.utils.Page;
import com.cskaoyan.utils.PassengerTransfer;
import com.cskaoyan.vo.ConsumptionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RequestMapping("/StayRegister")
@Controller
public class StayRegisterController {

    @Autowired
    CommodityService commodityService;

    @Autowired
    StayRegisterService stayRegisterService;

    @Autowired
    PassengerService passengerService;

    @Autowired
    PassengerMapper passengerMapper;

    @Autowired
    OrdermainService ordermainService;

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

    /*
    * 换房
    * */
    @RequestMapping("/tochangeroom")
    public void tochangeroom(HttpServletRequest request) {
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");//55旅客，56团队
        String id = request.getParameter("id");
        String lvKeName = request.getParameter("lvKeName");

    }

    //选择房间
    @RequestMapping("/changeRoomSelectPassenger")
    public void changeRoomSelectPassenger() {


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




    /**
     * 旅客消费
     * @param LvKeLeiXingId 旅客类型
     * @param lvKeName 旅客姓名
     * @param id 订单id
     * @param roomNumber 房间名
     * @param isBillID
     * @param Number
     * @param consumptionStayRegisterID
     * @param currentPage
     * @param model
     * @return
     */
    @RequestMapping("/toconsumption")
    public String toconsumption(
            String LvKeLeiXingId,String lvKeName,
            String id,String roomNumber,
            String isBillID,String Number,
            String consumptionStayRegisterID, Integer currentPage,
            Model model){
        List<Commoditytype> commoditytype = commodityService.findAllCommoditytype();
        Page<ConsumptionVo> page = ordermainService.findPage(currentPage, id);

        model.addAttribute("list", page);
        model.addAttribute("listOne", commoditytype);
        model.addAttribute("LvKeLeiXingId", LvKeLeiXingId);
        model.addAttribute("lvKeName",lvKeName);
        System.out.println(id);
        model.addAttribute("id", id);
        model.addAttribute("roomNumber", roomNumber);
        model.addAttribute("isBillID", isBillID);
        model.addAttribute("Number", Number);
        return "/WEB-INF/jsp/stayregister/consumption.jsp";

    }

    /**
     * 发现所有商品
     * @param name
     * @param cboid
     * @return
     */
    @RequestMapping("/findAllCommodity")
    @ResponseBody
    public List<Commodity> findAllCommodity(String name, String cboid){
        return commodityService.findPartCommodity(name, cboid);
    }

    /**
     * 插入消费订单信息，更新总消费，并且回显
     * @param id 商品id
     * @param LvKeLeiXingId 旅客类型
     * @param Number 商品数量
     * @param consumptionStayRegisterID  未知
     * @param ord_id 订单号
     */
    @RequestMapping("/consumption")
    public String consumption(String id, String LvKeLeiXingId,
                              String Number, String consumptionStayRegisterID,
                              String ord_id, Model model, HttpServletRequest request) {

        ordermainService.insertOrdextconsum(id, Number, ord_id);
        Ordermain orderByOrdId = ordermainService.findOrderByOrdId(ord_id);

        return toconsumption(
                orderByOrdId.getPassengerTypeID()+"",orderByOrdId.getName(),ord_id,orderByOrdId.getRoomNumber(),
        orderByOrdId.getBillUnitID()+"","6","7",1,model);

        // 写入数据库并且回显

    }

    /**
     * 删除商品
     * @param id 需要删除的商品id(主键)
     * @param consumptionStayRegisterID
     */
    @RequestMapping("/consumptionDelete")
    public String consumptionDelete(String[] id, String consumptionStayRegisterID,Model model) {

        Ordextconsum ordextconsum = ordermainService.findOrdextConsumById(id[0]);
        Ordermain orderByOrdId = ordermainService.findOrderByOrdId(ordextconsum.getOrd_id());
        for(String i : id) {
            ordermainService.deleteConsumptionByCommodityOrderId(i);
        }

        return toconsumption(
                orderByOrdId.getPassengerTypeID()+"",orderByOrdId.getName(),orderByOrdId.getOrdID(),orderByOrdId.getRoomNumber(),
                orderByOrdId.getBillUnitID()+"","6","7",1,model);


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
