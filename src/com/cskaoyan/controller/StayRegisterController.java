package com.cskaoyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/StayRegister")
@Controller
public class StayRegisterController {


    /*
    * 显示页面
    * */
    @GetMapping("/tolist")
    public String roomsetToList(HttpServletRequest request){
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");//55旅客，56团队
        String isBillID = request.getParameter("isBillID");//68未结账，69已结账
        return "/WEB-INF/jsp/stayregister/list.jsp";
    }
/*
* 登记
* */
    @RequestMapping("/toregister")
    public void toregister(HttpServletRequest request){
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");//55旅客，56团队
        String id = request.getParameter("id");
        String roomNumber = request.getParameter("roomNumber");

    }



//选择旅客

    @PostMapping("/selectPassenger")
    public void selectPassenger(){

    }


//确认旅客

    @RequestMapping("/comfirmPassenger")
    public String comfirmPassenger(){
        return "redirect:tolist.do";
    }
/*
*安排房间
 *  */
    @RequestMapping("/toanrrangeroom")
    public void toanrrangeroom(){

    }
/*
* 换房
* */
@RequestMapping("/tochangeroom")
public void tochangeroom(HttpServletRequest request){
    String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");//55旅客，56团队
    String id = request.getParameter("id");
    String lvKeName = request.getParameter("lvKeName");

}
//选择房间
    @RequestMapping("/changeRoomSelectPassenger")
    public void changeRoomSelectPassenger(){



    }



    /*
    * 押金记录
    * */
    @RequestMapping("/todeposit")
    public void todeposit(HttpServletRequest request){
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
        String lvKeName = request.getParameter("lvKeName");
        String id = request.getParameter("id");

    }


    /*
    * 旅客消费
    * */
    @RequestMapping("/toconsumption")
    public void toconsumption(HttpServletRequest request){
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
    public void tianJiaShangPin(){

}
//选择商品
@RequestMapping("/consumption")
    public void consumption(HttpServletRequest request){
    String id = request.getParameter("id");
    String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
    String number = request.getParameter("Number");
    String consumptionStayRegisterID = request.getParameter("consumptionStayRegisterID");

}
//删除商品
@RequestMapping("/consumptionDelete")
    public void consumptionDelete(HttpServletRequest request){
    String id = request.getParameter("id");
    String consumptionStayRegisterID = request.getParameter("consumptionStayRegisterID");
}

/*
* 转换
* */
//转入团队房间信息
        @RequestMapping("/toshiftteam")
        public void toshiftteam(HttpServletRequest request){

        }

//转为散客
    @RequestMapping("/toshiftpersonage")
        public void toshiftpersonage(HttpServletRequest request){
        String id = request.getParameter("id");
        String stayregisterdetailsId = request.getParameter("stayregisterdetailsId");
    }
    //转入
    @RequestMapping("/changeOver")
    public String changeOver(HttpServletRequest request){
        String id = request.getParameter("id");
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
        return "redirect:tolist.do";
    }



/*
* 结账
* */
        @RequestMapping("/topay")
    public void topay(HttpServletRequest request){
            String id = request.getParameter("id");
        }

        @RequestMapping("pay")
public String pay(HttpServletRequest request){
            String id = request.getParameter("id");
            String payTime = request.getParameter("payTime");
            String payWay = request.getParameter("payWay");
            String roomId = request.getParameter("roomId");
            return "redirect:tolist.do";
        }


}
