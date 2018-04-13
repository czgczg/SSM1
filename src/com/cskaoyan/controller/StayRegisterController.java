package com.cskaoyan.controller;


import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Roomset;
import com.cskaoyan.dao.ListoneMapper;
import com.cskaoyan.dao.OrdermainMapper;
import com.cskaoyan.dao.PassengerMapper;
import com.cskaoyan.dao.RoomsetMapper;
import com.cskaoyan.vo.Listone;
import org.apache.ibatis.annotations.Param;
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
    PassengerMapper passengerMapper;
    @Autowired
    RoomsetMapper roomsetMapper;
    @Autowired
    ListoneMapper listoneMapper;

    @Autowired
    OrdermainMapper ordermainMapper;

    /*
    * 显示页面
    * */
    @GetMapping("/tolist")
    public String roomsetToList(Integer LvKeLeiXingId,Model model){
//        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");//55旅客，56团队
//       request.getParameter("isBillID");//68未结账，69已结账
//

        LvKeLeiXingId=  LvKeLeiXingId==null?55:LvKeLeiXingId;
        model.addAttribute("LvKeLeiXingId",LvKeLeiXingId);

        return "/WEB-INF/jsp/stayregister/list.jsp";
    }
/*
* 登记
* */
    @RequestMapping("/toregister")
    public void toregister(HttpServletRequest request,@RequestParam Integer LvKeLeiXingId){
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");//55旅客，56团队
        String id = request.getParameter("id");
        String roomNumber = request.getParameter("roomNumber");

    }



//选择旅客

    @PostMapping("/selectPassenger")
    public void selectPassenger(String txtname){
        List<Passenger> passengers = passengerMapper.findPassengerByName(txtname);
        System.out.println(passengers);

    }


//确认旅客

    @RequestMapping("/comfirmPassenger")
    public String comfirmPassenger(){
        return "redirect:tolist.do";
    }
/*
*安排房间
 *  */
    @RequestMapping("/toarrangeroom")
    public String toanrrangeroom(Model model,@RequestParam Integer LvKeLeiXingId){

        List<Listone> rendWay = listoneMapper.getRendWay(5);
        List<Listone> passengerType = listoneMapper.getPassengerType(7);
        List<Listone> measureUnit = listoneMapper.getMeasureUnit(6);
        List<Listone> payWay = listoneMapper.getPayWay(4);
       ArrayList<HashMap>listRentOutType=new ArrayList<>();
        ArrayList<HashMap>listPassengerType=new ArrayList<>();
        ArrayList<HashMap>listBillUnit=new ArrayList<>();
        ArrayList<HashMap>listPayWay=new ArrayList<>();
        listRentOutType=getHashMaps(rendWay,listRentOutType);
        listPassengerType=getHashMaps(passengerType,listPassengerType);
        listBillUnit=getHashMaps(measureUnit,listBillUnit);
        listPayWay=getHashMaps(payWay,listPayWay);
        if(LvKeLeiXingId==55){
            listBillUnit.remove(1);
        }
        if(LvKeLeiXingId==56){
            listBillUnit.remove(0);
        }
           model.addAttribute("listRentOutType",listRentOutType);//出租方式
            model.addAttribute("listPassengerType",listPassengerType);//旅客类别
            model.addAttribute("listBillUnit",listBillUnit);//结账单位
            model.addAttribute("listPayWay",listPayWay);//支付方式
        List<Roomset> result=roomsetMapper.findAllRoomset();

          model.addAttribute("list",result);
          model.addAttribute("LvKeLeiXingId",LvKeLeiXingId);
        return "/WEB-INF/jsp/stayregister/arrangeroom.jsp";

    }

    private ArrayList<HashMap> getHashMaps(List<Listone> payWay,ArrayList<HashMap>listRentOutType) {


        for (Listone i:payWay) {
            HashMap<String, String> j = new HashMap<>();
            j.put("far_id", i.getId().toString());
            j.put("attributeDetailsName", i.getAttributeDetailsName());
            listRentOutType.add(j);
        }
        return listRentOutType;
    }

    //选择房间类型，根据房间类型查询。
    @RequestMapping("/guestRoomLevelSelectRoom")
    @ResponseBody
        public List<Roomset>guestRoomLevelSelectRoom(@RequestParam Integer guestRoomLevelID) {
            if(guestRoomLevelID==0){
                List<Roomset> result=roomsetMapper.findAllRoomset();
                return result;
            }

        List<Roomset> result = roomsetMapper.findRoomsetByLevelID(guestRoomLevelID);

           return result;

    }



    //保存到数据库并转发
    @RequestMapping( path = "/arrangeroom" ,method = {RequestMethod.POST,RequestMethod.GET})
    public String arrangeroom(Model model,  Ordermain ordermain,HttpServletRequest request,Integer roomID ) {
        Roomset roomset = roomsetMapper.findById(roomID);
        ordermain.setRoomNumber(roomset.getRoomNumber());
        ordermain.setRoomAmount(roomset.getRoomAmount());
        String ordId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().substring(0,5);
        ordermain.setOrdID(ordId);
        int insert = ordermainMapper.insert(ordermain);
        roomset.setRoomStateName("满");
        roomset.setRoomStateID(65);
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
public void tochangeroom(HttpServletRequest request){
    String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");//55旅客，56团队
    String id = request.getParameter("id");
    String lvKeName = request.getParameter("lvKeName");

}










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
