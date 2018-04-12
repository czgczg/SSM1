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
     *
     * 同时处理 前台的搜索，根据用户名搜索结果，并进行分页
     * @return
     */
    @RequestMapping("/tolist.do")
    public String passengerToList(Integer  currentPage,Model model ,HttpServletRequest request ) {
        if (currentPage == null || currentPage==0) {

            currentPage =1;

        }
        //拿到输入用户名
        String passengerName = request.getParameter("txtname");
        //根据用户名查出旅客
        List<Passenger> passengerByName = passengerService.findPassengerByName(passengerName);

        //查出所有旅客
        passengerService.findAllPassenger();

        //根据分页，查出限定的记录条数
        Page<Passenger> page = passengerService.findPage(currentPage);

        //将分页的记录数返回给前台
        model.addAttribute("list", page);
        //返回视图层
        return "/WEB-INF/jsp/passenger/list.jsp";
    }


    @RequestMapping("/toadd.do")
    public String passengerToAdd(HttpServletRequest request,Model model) {


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
        model.addAttribute("listPassengerLevel",level );
        model.addAttribute("listPapers", identifyCard);
        model.addAttribute("listThingReason", thingReason);

        return "/WEB-INF/jsp/passenger/add.jsp";
    }


    /**
     * Author:czg
     * 增加旅客信息到数据库
     * @param passenger
     * @return
     */
    @PostMapping("/add.do")
    public String addPassenger(Passenger passenger) {

        int i = passengerService.addPassenger(passenger);

        System.out.println(i);


        return "/WEB-INF/jsp/passenger/list.jsp";
    }

}




