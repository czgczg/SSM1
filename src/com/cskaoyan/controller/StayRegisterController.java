package com.cskaoyan.controller;

import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Roomset;
import com.cskaoyan.dao.ListoneMapper;
import com.cskaoyan.dao.OrdermainMapper;
import com.cskaoyan.dao.PassengerMapper;
import com.cskaoyan.dao.RoomsetMapper;
import com.cskaoyan.service.PassengerService;
import com.cskaoyan.service.RoomsetService;
import com.cskaoyan.service.StayRegisterService;
import com.cskaoyan.utils.Page;
import com.cskaoyan.utils.PassengerTransfer;
import com.cskaoyan.vo.Listone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/StayRegister")
@Controller
public class StayRegisterController {

    @Autowired
    StayRegisterService stayRegisterService;
    @Autowired
    PassengerService passengerService;
    @Autowired
    PassengerMapper passengerMapper;
@Autowired
    RoomsetMapper roomsetMapper;
@Autowired
OrdermainMapper ordermainMapper;
@Autowired
RoomsetService roomsetService;
@Autowired
ListoneMapper listoneMapper;
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
    @RequestMapping("/toarrangeroom")
    public String toanrrangeroom(Model model,@RequestParam Integer LvKeLeiXingId) {

        List<Listone> rendWay = listoneMapper.getRendWay(5);
        List<Listone> passengerType = listoneMapper.getPassengerType(7);
        List<Listone> measureUnit = listoneMapper.getMeasureUnit(6);
        List<Listone> payWay = listoneMapper.getPayWay(4);
        ArrayList<HashMap>listRentOutType=new ArrayList<>();
        ArrayList<HashMap>listPassengerType=new ArrayList<>();
        ArrayList<HashMap>listBillUnit=new ArrayList<>();
        ArrayList<HashMap>listPayWay=new ArrayList<>();
        listRentOutType=stayRegisterService.getHashMaps(rendWay,listRentOutType);
        listPassengerType=stayRegisterService.getHashMaps(passengerType,listPassengerType);
        listBillUnit=stayRegisterService.getHashMaps(measureUnit,listBillUnit);
        listPayWay=stayRegisterService.getHashMaps(payWay,listPayWay);
        if(LvKeLeiXingId==55){     //旅客结账单位为旅客自付
            listBillUnit.remove(1);
        }
        if(LvKeLeiXingId==56){   //团队结账单位为团队付款
            listBillUnit.remove(0);
        }
        model.addAttribute("listRentOutType",listRentOutType);   //出租方式
        model.addAttribute("listPassengerType",listPassengerType);  //旅客类别
        model.addAttribute("listBillUnit",listBillUnit);   //结账单位
        model.addAttribute("listPayWay",listPayWay);   //支付方式

        List<Roomset> list=roomsetService.findAllRoomset();
        model.addAttribute("list",list);
        model.addAttribute("LvKeLeiXingId",LvKeLeiXingId);
        return "/WEB-INF/jsp/stayregister/arrangeroom.jsp";
    }


    //选择房间类型，根据房间类型查询。
    @RequestMapping("/guestRoomLevelSelectRoom")
    @ResponseBody
    public List<Roomset> guestRoomLevelSelectRoom(@RequestParam Integer guestRoomLevelID) {

        List<Roomset>result=stayRegisterService.guestRoomLevelSelectRoom(guestRoomLevelID);
        return result;
    }

    //保存到数据库并转发
    @RequestMapping( path = "/arrangeroom" ,method = {RequestMethod.POST,RequestMethod.GET})
    public String arrangeroom( Ordermain ordermain,HttpServletRequest request,Integer roomID ) {
        Roomset roomset = roomsetMapper.findById(roomID);
        ordermain.setRoomNumber(roomset.getRoomNumber());
        ordermain.setRoomAmount(roomset.getRoomAmount());
        String ordId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().substring(0,5);
        ordermain.setOrdID(ordId);  //生成订单号
        ordermain.setState(68);  //修改订单状态为未结账
        int insert = ordermainMapper.insert(ordermain);
        roomset.setRoomStateName("满");
        roomset.setRoomStateID(6);
        int j = roomsetMapper.updateById(roomset);
        if(insert==1&&j==1){

            return "/WEB-INF/jsp/stayregister/list.jsp";
        }
        return "/WEB-INF/jsp/stayregister/arrangeroom.jsp";
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
