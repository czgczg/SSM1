package com.cskaoyan.controller;

import com.cskaoyan.bean.Admin;
import com.cskaoyan.dao.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RequestMapping("/Login")
@Controller
public class AdminController {


    @Autowired
    AdminMapper adminMapper;

    @GetMapping("/tologin.do")
    public String tologin(){
        return "/WEB-INF/jsp/login/login.jsp";
    }

    @PostMapping("/tomain.do")
    public String tomain(HttpServletRequest request){
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        if(null != userName && !"".equals(userName)&& null !=password && !"".equals(password)){
            HashMap<String,String> loginInfo = new HashMap<>();
            loginInfo.put("userName",userName);
            loginInfo.put("password",password);
            Admin admin = adminMapper.getAdminByUserNameAndPassword(loginInfo);
            HttpSession session = request.getSession();
            session.setAttribute("admin",admin);
            return "/WEB-INF/jsp/main/main.jsp";
        }

        return "/WEB-INF/jsp/login/login.jsp";
    }

}
