package com.cskaoyan.controller;

import com.cskaoyan.bean.*;
import com.cskaoyan.dao.ListoneMapper;
import com.cskaoyan.dao.OrdermainMapper;
import com.cskaoyan.dao.PassengerMapper;
import com.cskaoyan.dao.RoomsetMapper;
import com.cskaoyan.service.PassengerService;
import com.cskaoyan.service.RoomsetService;
import com.cskaoyan.service.StayRegisterService;
import com.cskaoyan.service.optionService;
import com.cskaoyan.utils.Page;
import com.cskaoyan.utils.PassengerTransfer;
import com.cskaoyan.vo.Listone;
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
    @Autowired
    optionService optionService;
    /**
     * 显示页面和分页
     */
    @RequestMapping("/tolist.do")
    public String roomsetToList(HttpServletRequest request, Integer currentPage, Model model, HttpSession session) {
        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");//55旅客，56团队
        String isBillID = request.getParameter("isBillID");//68未结账，69已结账
        System.out.println("isBillID = " + isBillID);
        //去数据库查到orderMain数据去给到前台jsp页面
        if ("56".equals(lvKeLeiXingId)) {
            if (currentPage == null || currentPage == 0) currentPage = 1;
            //拿到输入用户名
            String roomNumber = request.getParameter("txtname");
            if (roomNumber == null || "".equals(roomNumber)) {
                roomNumber = "%";
            } else {
                roomNumber = "%" + roomNumber + "%";
            }
            Page<Ordermain> page = stayRegisterService.findPage(currentPage, roomNumber);
            //将分页的记录数返回给前台
            model.addAttribute("list", page);
            //获取结账方式
            List<Listone> list = optionService.getPayMoney();
            session.setAttribute("listOne", list);
            model.addAttribute("isBillID", isBillID);
            //返回视图层
            return "/WEB-INF/jsp/stayregister/list.jsp";
        }

            if (currentPage == null || currentPage == 0) currentPage = 1;
            //拿到输入用户名
            String roomNumber = request.getParameter("txtname");
            if (roomNumber == null || "".equals(roomNumber)) {
                roomNumber = "%";
            } else {
                roomNumber = "%" + roomNumber + "%";
            }
            Page<Ordermain> page = stayRegisterService.findPage(currentPage, roomNumber);
            //将分页的记录数返回给前台
            model.addAttribute("list", page);
            //获取结账方式
            List<Listone> list = optionService.getPayMoney();
            session.setAttribute("listOne", list);
            model.addAttribute("isBillID", isBillID);
            //返回视图层
            return "/WEB-INF/jsp/stayregister/list.jsp";



    }
    /**
     * 登记   //55旅客，56团队StayRegister/register.do?LvKeLeiXingId=55
     * 1.点击登记转到register的jsp文件。并填充下拉option和房间号等数据到表单
     */
    @GetMapping("/toregister.do")
    public String toregister(HttpServletRequest request, String roomNumber, HttpSession session, HttpServletResponse response) throws IOException {
        Boolean flag=false;

        String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
        String id = request.getParameter("id");

        session.setAttribute("lvKeLeiXingId", lvKeLeiXingId);
        session.setAttribute("id", id);
        //先将旅客类别和订单号存入登记表

        //判断房间人数，如果该房间登记人数超过了房间的床位，则不让去登记，提示用户 alert 警告！下

        //去roomset表去查对应房间号的房间床位数   roomAmount
        Roomset roomset = roomsetService.queryroomAmountByRoomNumber(roomNumber);

        //查登记表的该房间登记的人数  根据什么去查 订单号
        int count = registrationMapper.queryRegisterPersonCount(id);
        if (count >= roomset.getRoomAmount()) {
            flag=true;
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("refresh", "3;url='/StayRegister/tolist.do'");
            response.getWriter().write("该房间登记人数超过床位数，请重新选择其他房间进行登记...@_@...");
        }

        if (!flag) {

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
        //这样真的可以吗？感觉return nul不合理的啊

        return null;

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
//            stayRegisterService.modifyOrderStatus();
            //更新房态为满
            //stayRegisterService.modifyRoomStatus(roomNumber);
            //更新登记表信息
            stayRegisterService.registration(id, passenger, lvKeLeiXingId, passengerID);

        }
        else {
            stayRegisterService.registration(id, passenger, lvKeLeiXingId, passengerID);
        }

        return "redirect:/StayRegister/tolist.do";

    }

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

        List<Roomset> list = roomsetService.findAllRoomset();
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
    public String arrangeroom(Ordermain ordermain, HttpServletRequest request, Integer roomID) {
        Roomset roomset = roomsetMapper.findById(roomID);
        ordermain.setRoomNumber(roomset.getRoomNumber());
        ordermain.setRoomAmount(roomset.getRoomAmount());
        String ordId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().substring(0, 5);
        ordermain.setOrdID(ordId);  //生成订单号
        ordermain.setState(68);  //修改订单状态为未结账
        int insert = ordermainMapper.insert(ordermain);
        roomset.setRoomStateName("满");
        roomset.setRoomStateID(6);
        int j = roomsetMapper.updateById(roomset);
        if (insert == 1 && j == 1) {

            return "/WEB-INF/jsp/stayregister/list.jsp";
        }
        return "/WEB-INF/jsp/stayregister/arrangeroom.jsp";
    }

    /**
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

        /**
         * http://192.168.2.100:8080/hotelms/StayRegister/confirmChangRoom.do?
         * id=398&roomId=&changRoomMoney=0.0&changRoomTime=2018-04-14%2022:34:12.0&LvKeLeiXingId=55
         * 确认换房
         */
    }
        @RequestMapping("/confirmChangRoom")
        public String confirmChangRoom (HttpServletRequest request){

            return "/StayRegister/tolist.do";

        }


        /**
         * 押金记录
         * 接待对象，为2的时候为旅客类型 散客
         *          不为2的时候为接待对象，团队名
         * todeposit
         * @param request
         */
        /**
         * 押金记录
         */
        @RequestMapping("/todeposit")
        public String todeposit (HttpServletRequest request){
            //旅客类型id，为56
            String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
            //旅客名字，需要传递。
            String lvKeName = request.getParameter("lvKeName");
            //订单表ordermain ordId ，主键id
            String id = request.getParameter("id");

            //根据订单表id查找押金记录。
            List<Deposit> depositRecordsByOrdId = stayRegisterService.findDepositRecordsByOrdId(id);

            //将押金记录放入request 域中
            request.setAttribute("list", depositRecordsByOrdId);

            return "/WEB-INF/jsp/stayregister/deposit.jsp";

        }


        /**
         * 旅客消费
         */
        @RequestMapping("/toconsumption")
        public void toconsumption (HttpServletRequest request){
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
        public void tianJiaShangPin () {

        }

        //选择商品
        @RequestMapping("/consumption")
        public void consumption (HttpServletRequest request){
            String id = request.getParameter("id");
            String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
            String number = request.getParameter("Number");
            String consumptionStayRegisterID = request.getParameter("consumptionStayRegisterID");

        }

        //删除商品
        @RequestMapping("/consumptionDelete")
        public void consumptionDelete (HttpServletRequest request){
            String id = request.getParameter("id");
            String consumptionStayRegisterID = request.getParameter("consumptionStayRegisterID");
        }

        /**
         * 转换
         */
        //转入团队房间信息
        @RequestMapping("/toshiftteam")
        public String toshiftteam (String id, String stayregisterdetailsId, HttpServletRequest request){
            List<Ordermain> ordermains = stayRegisterService.getOrderMain(id);

            List<Recepobject> recepobjects = stayRegisterService.getAllReceiveObject();
            request.setAttribute("listRT", recepobjects);
            request.setAttribute("list", ordermains.get(0));
            return "/WEB-INF/jsp/stayregister/shiftteam.jsp";

        }

        @RequestMapping("/changOver")
        public String changOver (String id, String receiveTargetID, HttpServletRequest request){
            stayRegisterService.changOverOrderMain(id, receiveTargetID);
            return "redirect:tolist.do";

        }

        //转为散客
        @RequestMapping("/toshiftpersonage")
        public String toshiftpersonage (String id, String stayregisterdetailsId, HttpServletRequest request){
            List<Ordermain> ordermains = stayRegisterService.getOrderMain(id);
            List<Passenger> passengers = stayRegisterService.getAllPassers();

            request.setAttribute("listRT", passengers);
            request.setAttribute("list", ordermains.get(0));
            return "/WEB-INF/jsp/tayregister/shiftpersonage.jsp";
        }

        @RequestMapping("/changeOver2")
        public String changeOver (HttpServletRequest request){
            String id = request.getParameter("id");
            String lvKeLeiXingId = request.getParameter("LvKeLeiXingId");
            return "redirect:tolist.do";
        }

        /**
         * 结账
         */
        @RequestMapping("/topay")
        public void topay (HttpServletRequest request){
            String id = request.getParameter("id");
        }

        @RequestMapping("pay")
        public String pay (HttpServletRequest request){
            String id = request.getParameter("id");
            String payTime = request.getParameter("payTime");
            String payWay = request.getParameter("payWay");
            String roomId = request.getParameter("roomId");
            return "redirect:tolist.do";
        }


        /**
         * author:czg
         * //?LvKeLeiXingId=56&isBillID=68 团队未结账
         * @param currentPage
         * @param request
         * @param lvKeLeiXingId
         * @param isBillID
         * @param model
         * @param session
         * @return
         */
        @RequestMapping("/toteamlist.do")
        public String toTeamList (Integer currentPage, HttpServletRequest request, String LvKeLeiXingId, String
        isBillID,
                Model model, HttpSession session){
            if (currentPage == null || currentPage == 0) currentPage = 1;

            String roomNumber = request.getParameter("txtname");
            if (roomNumber == null || "".equals(roomNumber)) {
                roomNumber = "%";
            } else {
                roomNumber = "%" + roomNumber + "%";
            }
            Page<Ordermain> page;
            //去数据库查到orderMain数据去给到前台jsp页面
            page = stayRegisterService.findPage(currentPage, roomNumber, LvKeLeiXingId, isBillID);
            request.setAttribute("LvKeLeiXingId", LvKeLeiXingId);
            model.addAttribute("list", page);
            List<Listone> list = optionService.getPayMoney();
            session.setAttribute("listOne", list);
            model.addAttribute("isBillID", isBillID);
            return "/WEB-INF/jsp/stayregister/list.jsp";

        }

    }
}
