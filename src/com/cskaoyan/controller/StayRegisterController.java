package com.cskaoyan.controller;

import com.cskaoyan.bean.*;
import com.cskaoyan.dao.*;
import com.cskaoyan.service.CommodityService;
import com.cskaoyan.service.OrdermainService;
import com.cskaoyan.service.PassengerService;
import com.cskaoyan.service.RoomsetService;
import com.cskaoyan.service.StayRegisterService;
import com.cskaoyan.service.optionService;
import com.cskaoyan.utils.Page;
import com.cskaoyan.utils.PassengerTransfer;
import com.cskaoyan.vo.ConsumptionVo;
import com.cskaoyan.vo.Listone;
import com.cskaoyan.vo.Listpay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

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
    RoomsetMapper roomsetMapper;
    @Autowired
    ChangeroomMapper changeroomMapper;

    @Autowired
    OrdermainMapper ordermainMapper;
    @Autowired
    RoomsetService roomsetService;
    @Autowired
    ListoneMapper listoneMapper;
    @Autowired
    optionService optionService;
    @Autowired
    OrdextconsumMapper ordextconsumMapper;

    @Autowired
    OrdermainService ordermainService;

    @Autowired
    OrderpaymentMapper orderpaymentMapper;
    /**
     * 显示页面和分页
     */
    @RequestMapping("/tolist.do")
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
    public String toregister(HttpServletRequest request, String roomNumber, HttpSession session) {
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
        String id = request.getParameter("id");

        session.setAttribute("lvKeLeiXingId", lvKeLeiXingId);
        session.setAttribute("id", id);
        //先将旅客类别和订单号存入登记表

        List<Listone> sex = optionService.getSex();
        List<Listone> nation = optionService.getNation();
        List<Listone> educationDegree = optionService.getEducationDegree();
        //旅客级别
        List<Listone> level = optionService.getpassengerLevel();
        List<Listone> identifyCard = optionService.getIdentifyCard();
        List<Listone> thingReason = optionService.getThingReason();
        session.setAttribute("listGender", sex);
        session.setAttribute("listNation", nation);
        session.setAttribute("listEducationDegree", educationDegree);
        session.setAttribute("listPassengerLevel", level);
        session.setAttribute("listPapers", identifyCard);
        session.setAttribute("listThingReason", thingReason);

        session.setAttribute("roomNumber", roomNumber);
        return "/WEB-INF/jsp/stayregister/register.jsp";
    }

    /**
     * 处理登记表单数据存入数据库
     * 2.后来选择旅客的时候，内嵌的表单选中后又转到这里处理，去保存数据到相应需要用的
     * 地方，成功后重新要跳转到tolist首页去显示   订单主表信息。
     */
    @RequestMapping(value = "/register.do", method = {RequestMethod.GET, RequestMethod.POST})
    public String register(HttpServletRequest request, Passenger passenger, HttpSession session) {
        //点击内嵌的选择之后，填冲数据到表单后，原来的订单号和对象类别在请求参数里都不见了，所以赛道session里面。
//        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
//        String roomNumber = request.getParameter("roomNumber");
//        String id = request.getParameter("id");//id是复选框的value我们是订单号

        String name = passenger.getName();

        List<Passenger> passengerByName = passengerService.findPassengerByName(name);


        String id = (String) session.getAttribute("id");//2018038983894828924
        String lvKeLeiXingId = (String) session.getAttribute("lvKeLeiXingId");
        String roomNumber = (String) session.getAttribute("roomNumber");
        int passengerID = (int) session.getAttribute("passengerID");
        if ("55".equals(lvKeLeiXingId)) {
//            //如果旅客存在，直接更新旅客信息，因为登记的肯定是最真实的信息
//            PassengerTransfer.transfer(passenger, passengerMapper);
//            //兰姐说这个好像不用了，吴同学处理了
//            passengerService.addPassenger(passenger);
            //更新订单的状态为未结账
            stayRegisterService.modifyOrderStatus();
            //更新房态为满
            stayRegisterService.modifyRoomStatus(roomNumber);
            //更新登记表信息
            stayRegisterService.registration(id, passenger, lvKeLeiXingId, passengerID);

        }


        return "/StayRegister/tolist.do";

    }
//选择旅客

    /**
     * 选择旅客     查出所有的旅客信息，以简略形式展现给内层的form
     */
    @PostMapping("/selectPassenger.do")
    @ResponseBody
    public List<Passenger> selectPassenger(Model model) {
        List<Passenger> allPassenger = passengerService.findAllPassenger();
        return allPassenger;
    }


    /**
     * Author：czg       StayRegister/confirmPassenger
     * 确认旅客      需要带上内弹出的选中的tr数据回显给到
     * WEB-INF/jsp/stayregister/register.jsp
     */
    @RequestMapping("/confirmPassenger")
    @ResponseBody
    public Passenger comfirmPassenger(Passenger passenger, Model model, HttpSession session) {
        Integer id = passenger.getId();
        session.setAttribute("passengerID", id);
        //拿到id去查数据库
        Passenger passenger1 = passengerService.findPassengerById(id);
        PassengerTransfer.transfer(passenger1, passengerMapper);
        return passenger1;
    }

    /**
     * 安排房间
     */
    @RequestMapping("/toarrangeroom")
    public String toanrrangeroom(Model model, @RequestParam Integer LvKeLeiXingId) {

        List<Listone> rendWay = listoneMapper.getRendWay(5);
        List<Listone> passengerType = listoneMapper.getPassengerType(7);
        List<Listone> measureUnit = listoneMapper.getMeasureUnit(6);
        List<Listone> payWay = listoneMapper.getPayWay(4);
        ArrayList<HashMap> listRentOutType = new ArrayList<>();
        ArrayList<HashMap> listPassengerType = new ArrayList<>();
        ArrayList<HashMap> listBillUnit = new ArrayList<>();
        ArrayList<HashMap> listPayWay = new ArrayList<>();
        listRentOutType = stayRegisterService.getHashMaps(rendWay, listRentOutType);
        listPassengerType = stayRegisterService.getHashMaps(passengerType, listPassengerType);
        listBillUnit = stayRegisterService.getHashMaps(measureUnit, listBillUnit);
        listPayWay = stayRegisterService.getHashMaps(payWay, listPayWay);
        if (LvKeLeiXingId == 55) {     //旅客结账单位为旅客自付
            listBillUnit.remove(1);
        }
        if (LvKeLeiXingId == 56) {   //团队结账单位为团队付款
            listBillUnit.remove(0);
        }
        model.addAttribute("listRentOutType", listRentOutType);   //出租方式
        model.addAttribute("listPassengerType", listPassengerType);  //旅客类别
        model.addAttribute("listBillUnit", listBillUnit);   //结账单位
        model.addAttribute("listPayWay", listPayWay);   //支付方式

        List<Roomset> list = roomsetMapper.findAllRoomset();
        model.addAttribute("list", list);
        model.addAttribute("LvKeLeiXingId", LvKeLeiXingId);
        return "/WEB-INF/jsp/stayregister/arrangeroom.jsp";
    }


    //选择房间类型，根据房间类型查询。
    @RequestMapping("/guestRoomLevelSelectRoom")
    @ResponseBody
    public List<Roomset> guestRoomLevelSelectRoom(@RequestParam Integer guestRoomLevelID) {

        List<Roomset> result = stayRegisterService.guestRoomLevelSelectRoom(guestRoomLevelID);
        return result;
    }

    //保存到数据库并转发
    @RequestMapping(path = "/arrangeroom", method = {RequestMethod.POST, RequestMethod.GET})
    public String arrangeroom(Ordermain ordermain, Integer roomID) {
        Roomset roomset = roomsetMapper.findById(roomID);
        ordermain.setRoomNumber(roomset.getRoomNumber());
        ordermain.setRoomAmount(roomset.getRoomAmount());
        String ordId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().substring(0, 2);
        ordermain.setOrdID(ordId);  //生成订单号
        ordermain.setOrderFrom(68);  //修改订单状态为未结账
        ordermain.setReceiveTargetID(55);
        ordermain.setPassengerOrReceiveID(0);
        ordermain.setName("现场预定");
        ordermain.setDel_flag(0);

        //钟点则显示钟点数
        Integer stayNumber=0;
        if(ordermain.getRentOutTypeId()==5) {
            stayNumber = ordermain.getStayNumber();
            //ordermain.setPredetermineDay(0);
            ordermain.setRentOutTypeName("钟点");
        }
        //全日则显示天数
        if(ordermain.getRentOutTypeId()==4){
            ordermain.setPredetermineDay(ordermain.getStayNumber());
            ordermain.setStayNumber(0);
            ordermain.setRentOutTypeName("全日");
            stayNumber = ordermain.getPredetermineDay();
        }
        ordermain.setSumConst(stayNumber*roomset.getStandardPrice());
        ordermain.setGuestRoomLevelName(roomset.getGuestRoomLevelName());
        ordermain.setRegisterTime(new Date());
        ordermain.setPayWayName(orderpaymentMapper.selectByPrimaryKey(ordermain.getPayWayID()).getAttributeDetailsName());
        ordermain.setLoginFlag(0);
        ordermain.setRoomId(roomset.getId());
        ordermain.setBillUnitName(ordermain.getBillUnitID()==6?listoneMapper.getListOne(6).get(0).getAttributeDetailsName():listoneMapper.getListOne(6).get(1).getAttributeDetailsName());
        ordermain.setPassengerTypeName(ordermain.getPassengerTypeID()==8?"国内旅客":"国外旅客");
        int insert = ordermainMapper.insert(ordermain);
        roomset.setRoomStateName("满");
        roomset.setRoomStateID(6);

        int j = roomsetMapper.updateById(roomset);
        if (insert == 1 && j == 1) {

            return "/StayRegister/tolist.do";
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
    public void changeRoomSelectPassenger() {}

    /**
     * http://192.168.2.100:8080/hotelms/StayRegister/confirmChangRoom.do?
     * id=398&roomId=&changRoomMoney=0.0&changRoomTime=2018-04-14%2022:34:12.0&LvKeLeiXingId=55
     * 确认换房
     */
    @RequestMapping("/confirmChangRoom")
    public String confirmChangRoom(HttpServletRequest request){

        return "/StayRegister/tolist.do";

    }



    /**
     * 押金记录
     * 接待对象，为2的时候为旅客类型 散客
     *          不为2的时候为接待对象，团队名
     * todeposit
     * @param request
     */

    @RequestMapping("/todeposit")
    public String todeposit(HttpServletRequest request) {
        //旅客类型id，为56
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
        //旅客名字，需要传递。
        String lvKeName = request.getParameter("lvKeName");
        //订单表ordermain ordId ，主键id
        String id = request.getParameter("id");

        //根据订单表id查找押金记录。
        List<Deposit> depositRecordsByOrdId = stayRegisterService.findDepositRecordsByOrdId(id);

        //将押金记录放入request 域中
        request.setAttribute("list",depositRecordsByOrdId);

        return "/WEB-INF/jsp/stayregister/deposit.jsp";

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
    public String toshiftteam(String id,String stayregisterdetailsId,HttpServletRequest request) {
        List<Ordermain> ordermains = stayRegisterService.getOrderMain(id);

        List<Recepobject> recepobjects = stayRegisterService.getAllReceiveObject();
        request.setAttribute("listRT",recepobjects);
        request.setAttribute("list",ordermains.get(0));
        return "/WEB-INF/jsp/stayregister/shiftteam.jsp";

    }

    @RequestMapping("/changOver")
    public String changOver(String id,String receiveTargetID,HttpServletRequest request) {
        stayRegisterService.changOverOrderMain(id,receiveTargetID);
        return "redirect:tolist.do";

    }

    //转为散客
    @RequestMapping("/toshiftpersonage")
    public String toshiftpersonage(String id,String stayregisterdetailsId,HttpServletRequest request) {
        List<Ordermain> ordermains = stayRegisterService.getOrderMain(id);
        List<Passenger> passengers = stayRegisterService.getAllPassers();

        request.setAttribute("listRT",passengers);
        request.setAttribute("list",ordermains.get(0));
        return "/WEB-INF/jsp/tayregister/shiftpersonage.jsp";
    }

    @RequestMapping("/changeOver2")
    public String changeOver(HttpServletRequest request) {
        String id = request.getParameter("id");
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
        return "redirect:tolist.do";
    }


    /**
     * 结账
     */
    @RequestMapping("/topay")
    public String topay(String id, String lvKeName, Model model) {  //订单ID

        if (lvKeName == null) {

            return "redirect: tolist.do";
        }
        List<Listpay> listpayList = new ArrayList<>(10);
        Listpay listpay = new Listpay();
        listpayList.add(listpay);
        List<Ordermain> list = ordermainMapper.findOrderById(id);
        List<Listone> payWay = listoneMapper.getPayWay(4);
        ArrayList<HashMap> listOne = new ArrayList<>();
        listOne = stayRegisterService.getHashMaps(payWay, listOne);
        model.addAttribute("listOne", listOne);

        Float yaJin = list.get(0).getDeposit(); //押金  yaJin
        model.addAttribute("yaJin", yaJin);
        //换房费
        listpayList.get(0).setChangRoomMoney(list.get(0).getChangRoomMoney());
        //换房次数
        listpayList.get(0).setChangingRoomNumber(list.get(0).getChangingRoomNumber());
        //结账金额
        listpayList.get(0).setSumConst(list.get(0).getSumConst());
        //房间号
        listpayList.get(0).setRoomNumber(list.get(0).getRoomNumber());
        //结账单位
        listpayList.get(0).setBillUnitName(list.get(0).getBillUnitName());
        //登记时间
        list.get(0).getRegisterTime();
        listpayList.get(0).setRegisterTime(list.get(0).getRegisterTime());

        //出租方式
        listpayList.get(0).setRentOutTypeName(list.get(0).getRentOutTypeName());


        //接待对象
        if (list.get(0).getReceiveTargetID() == 55) {
            listpayList.get(0).setReceiveTargeTypeName("散客");
        }
        if (list.get(0).getReceiveTargetID() == 56) {
            listpayList.get(0).setReceiveTargeTypeName("团队");
        }

        model.addAttribute("listpayList", listpayList);
        //roomId
        List<Roomset> roomsetlist = roomsetMapper.findRoomsetByRoomNumber(list.get(0).getRoomNumber());
        listpayList.get(0).setRoomID(roomsetlist.get(0).getId());
        //房价/天
        Float standardPriceDay = roomsetlist.get(0).getStandardPriceDay();
        listpayList.get(0).setRoomStandardPriceDay(standardPriceDay);
        // 房价/小时
        Float standardPrice = roomsetlist.get(0).getStandardPrice();
        listpayList.get(0).setRoomStandardPrice(standardPrice);
       // 首段价格
        listpayList.get(0).setRoomFirstPrice(roomsetlist.get(0).getFirstPrice());
        //住宿费自己算  roomset  zhuSuFei
        Integer rentOutTypeId = list.get(0).getRentOutTypeId();
        //钟点则显示钟点数
        if(rentOutTypeId==5) {
            listpayList.get(0).setStayNumber(list.get(0).getStayNumber());
            Integer stayNumber = listpayList.get(0).getStayNumber();
            float zhuSuFei=stayNumber*standardPrice;
            model.addAttribute("zhuSuFei",zhuSuFei);
        }
        //全日则显示天数
        if(rentOutTypeId==4){
            listpayList.get(0).setStayNumber(list.get(0).getPredetermineDay());
            Integer stayNumber = listpayList.get(0).getStayNumber();
            float zhuSuFei=stayNumber*standardPriceDay;
            model.addAttribute("zhuSuFei",zhuSuFei);
        }

        //商品费再说  orderconsume  shangPinXiaoFei
        //先判断有没有商品，有
        Integer shangPinXiaoFei=0;
        if(0 != ordextconsumMapper.findAllConsumCount(id)) {
            Ordextconsum ordextconsum = ordextconsumMapper.findOrdextConsumByOrd_Id(id);
            shangPinXiaoFei = ordextconsum.getConsumptionMoney();
        }
        model.addAttribute("shangPinXiaoFei",shangPinXiaoFei);
        //结账时间，当前时间 timestamp
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String timestamp = df.format(new Date());
        model.addAttribute("timestamp", timestamp);

        //结账金额，住宿费+其他消费+换房费


        //应补缴金额，结账金额减去押金
        Float sum = listpayList.get(0).getSumConst() - yaJin;
        model.addAttribute("yingBuJinE", sum);
        model.addAttribute("stayId", id);
        model.addAttribute("lvKeName", lvKeName);
        return "/WEB-INF/jsp/stayregister/pay.jsp";
    }


    //更新订单表，修改结账状态
    @RequestMapping("pay")
    public String pay(String id, String payWay, Date payTime) {
        List<Ordermain> list = ordermainMapper.findOrderById(id);
        if (list.size() == 0) {
            return "/WEB-INF/jsp/stayregister/pay.jsp";
        }
        list.get(0).setOrderFrom(69);
        list.get(0).setPayWayName(payWay);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

            list.get(0).setPayTime(payTime);

        //更新订单状态
        int i = ordermainMapper.updateByPrimaryKey(list.get(0));

        Roomset roomset = roomsetMapper.findById(list.get(0).getRoomId());
        roomset.setRoomStateID(1);
        roomset.setRoomStateName("空");
        //更新房间状态
        int update = roomsetMapper.updateById(roomset);
        if (i == 0 || update == 0) {
            return "/WEB-INF/jsp/stayregister/pay.jsp";
        }

        return "/StayRegister/tolist.do";
    }


}
