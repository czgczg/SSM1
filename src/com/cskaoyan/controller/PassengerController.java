package com.cskaoyan.controller;

import com.cskaoyan.bean.Passenger;
import com.cskaoyan.service.PassengerService;
import com.cskaoyan.service.optionService;
import com.cskaoyan.utils.Page;
import com.cskaoyan.vo.Listone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/Passenger")
@Controller
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @Autowired
    optionService optionService;

    /**
     * 点击旅客信息的时候，页面的初始化处理
     * 需要查到所有的旅客信息，并作分页处理
     * <p>
     * 同时处理 前台的搜索，根据用户名搜索结果，并进行分页
     *
     * @return
     */
    @RequestMapping("/tolist.do")
    public String passengerToList(Integer currentPage, Model model, HttpServletRequest request) {
        if (currentPage == null || currentPage == 0) {
            currentPage = 1;
        }

        //拿到输入用户名
        String passengerName = request.getParameter("txtname");
        if (passengerName == null || "".equals(passengerName)) {
            passengerName = "%";
        } else {
            passengerName = "%" + passengerName + "%";
        }
        //根据分页，查出限定的记录条数
        Page<Passenger> page = passengerService.findPage(currentPage, passengerName);

        //将分页的记录数返回给前台
        model.addAttribute("list", page);
        //返回视图层
        return "/WEB-INF/jsp/passenger/list.jsp";
    }

    @RequestMapping("/toadd.do")
    public String passengerToAdd(Model model, HttpSession session) {

        //查出所有的下拉form的值给前台jsp展示
        //查出所有的下拉form的值给前台jsp展示
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


        return "/WEB-INF/jsp/passenger/add.jsp";
    }


    /**
     * 增加旅客信息到数据库
     * @param passenger
     * @return
     */
    @PostMapping("/add.do")
    public String addPassenger(Passenger passenger, Model model) {
       passengerService.addPassenger(passenger);
        return "/Passenger/tolist.do";
    }


    /**
     * 转发到更新
     * @return
     */
    @GetMapping("/toupdate.do")
    public String toUpdatePassenger(Passenger passenger, Model model) {

        //查出所有的下拉form的值给前台jsp展示
        //查出所有的下拉form的值给前台jsp展示
        List<Listone> sex = optionService.getSex();
        List<Listone> nation = optionService.getNation();
        List<Listone> educationDegree = optionService.getEducationDegree();
        //旅客级别
        List<Listone> level = optionService.getpassengerLevel();
        List<Listone> identifyCard = optionService.getIdentifyCard();
        List<Listone> thingReason = optionService.getThingReason();
        model.addAttribute("listGender", sex);
        model.addAttribute("listNation", nation);
        model.addAttribute("listEducationDegree", educationDegree);
        model.addAttribute("listPassengerLevel", level);
        model.addAttribute("listPapers", identifyCard);
        model.addAttribute("listThingReason", thingReason);
        Integer id = passenger.getId();
        Passenger passen = passengerService.findPassengerById(id);
        ArrayList<Passenger> list = new ArrayList<>();
        list.add(passenger);
        model.addAttribute("list", passen);


        return "/WEB-INF/jsp/passenger/update.jsp";
    }

    /**
     * 更新旅客信息
     * @return
     */
    @RequestMapping("/update.do")
    public String updatePassenger(Model model, Passenger passenger, String id, HttpServletRequest request) {

        Boolean ret = passengerService.updatePassenger(passenger);
        if (!ret) {
            model.addAttribute("message", "更新出错，请重新输入。。。");
        }
        return "/Passenger/tolist.do";
    }


    @RequestMapping("/delete.do")
    public String deletePassenger(Passenger passenger) {
        passengerService.deletePassenger(passenger);
        return "/Passenger/tolist.do";
    }
}




