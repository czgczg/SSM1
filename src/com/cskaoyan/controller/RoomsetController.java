package com.cskaoyan.controller;

import com.cskaoyan.bean.Roomset;
import com.cskaoyan.bean.Roomsetstatus;
import com.cskaoyan.bean.Roomsettype;
import com.cskaoyan.service.RoomsetService;
import com.cskaoyan.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/Roomset")
@Controller
public class RoomsetController {

    @Autowired
    RoomsetService roomsetService;

    /**
     * 发送页面去客房设置管理页面
     * @return
     */
    @GetMapping("/tolist")
    public String roomsetToList(Model model){
        Page<Roomset> pageDivide = new Page<>();
        List<Roomset> listRoomset = roomsetService.findAllRoomset();
//        pageDivide.setResult(listRoomset);
        model.addAttribute("list", pageDivide);
        return "/WEB-INF/jsp/roomset/roomset.jsp";
    }

    /**
     * 发送所有的房间状态和房间类型去房间新建页面
     * @param model
     * @return
     */
    @GetMapping("/toadd")
    public String roomsetToAdd(Model model){

        List<Roomsetstatus> listStatus;
        List<Roomsettype> listType;

        listStatus = roomsetService.findAllRoomsetstatus();
        listType = roomsetService.findAllRoomsettype();

        model.addAttribute("listTwo", listStatus);
        model.addAttribute("listOne", listType);

        return "/WEB-INF/jsp/roomset/add.jsp";
    }

    /**
     * 客房设置(新增客房)
     * @return
     */
    @PostMapping("/add")
    public String add(Roomset roomset, Model model){
        roomsetService.insertRoomset(roomset);
        return roomsetToList(model);
    }

    @RequestMapping("/toupdate")
    public String toupdate(int id,Model model){
        Roomset roomset = roomsetService.findRoomsetById(id);
        model.addAttribute("listPo", roomset);
        return "/WEB-INF/jsp/roomset/update.jsp";
    }


    /**
     * 删除房间
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/delete")
    public String deleteRoom(int id, Model model){
        roomsetService.deleteRoom(id);
        return roomsetToList(model);
    }

}
