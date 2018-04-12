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
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping("/tolist")
    public String roomsetToList(Integer currentPage, String txtname, Model model){
        if(currentPage==null){
            currentPage = 1;
        }
        Page<Roomset> roomsetPage = roomsetService.findPage(currentPage, txtname);
        model.addAttribute("list", roomsetPage);
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
        return roomsetToList(1, "",model);
    }

    /**
     * 转发到update.jsp页面，并提供必要的数据
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toupdate")
    public String toupdate(int id,Model model){

        List<Roomsetstatus> listStatus;
        List<Roomsettype> listType;
        Roomset roomset;
        listStatus = roomsetService.findAllRoomsetstatus();
        listType = roomsetService.findAllRoomsettype();
        roomset = roomsetService.findRoomsetById(id);
        model.addAttribute("listTwo", listStatus);
        model.addAttribute("listOne", listType);
        model.addAttribute("listPo", roomset);
        return "/WEB-INF/jsp/roomset/update.jsp";
    }

    /**
     * 修改roomset，并且转发到roomset.jsp
     * @param roomset
     * @param model
     * @return
     */
    @RequestMapping("/update")
    public String update(Roomset roomset, Model model){
        roomsetService.updateRoomsetById(roomset);
        return roomsetToList(1, "",model);
    }

    /**
     * 删除房间
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/delete")
    public String deleteRoom(int[] id, Model model){
        for(int i : id){
            roomsetService.deleteRoom(i);
        }
        return roomsetToList(1, "",model);
    }

//    /**
//     * 模糊查询（用这个的话，分页不好弄）
//     * @param txtname
//     * @return
//     */
//    @RequestMapping("/fuzzyfind")
//    @ResponseBody
//    public List<Roomset> fuzzyfind(String txtname){
//        System.out.println(txtname);
//        List<Roomset> result = roomsetService.findSpecial(txtname);
//        return result;
//    }

}
