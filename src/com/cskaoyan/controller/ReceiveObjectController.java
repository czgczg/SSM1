package com.cskaoyan.controller;

import com.cskaoyan.bean.Recepobject;
import com.cskaoyan.page.PageVo;
import com.cskaoyan.service.RecepObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReceiveObjectController {

    @Autowired
    RecepObjectService service;

    //分页查询写两个controller，一个接收currentPageNum（main页面），一个不接受（list页面）
/*
    @RequestMapping("ReceiveTarget/tolist")
    public String roomsetToList(HttpSession session){
        //main方法进来，查询总数
        List<Recepobject> allRecepObj = service.findAllRecepObj();
        int totalNum = allRecepObj.size();
        // get方法得到的page currentPageNum和totalPageNum已经确定,txtname 为 "";
        PageVo page = PageVo.getPage(totalNum, 1);

        //将page作为参数进行查询.
        List<Recepobject> allRecepObjLike = service.findAllRecepObjLike(page);
        // 将得到的list放入page中。
        page.setRecords(allRecepObjLike);
        //将page作为list放入session
        session.setAttribute("list",page);
        //跳转页面到list，此时有currentPageNum和totalPageNum
        return "/WEB-INF/jsp/receivetarget/list.jsp";
    }
*/

    @RequestMapping("ReceiveTarget/tolist")
    public String roomsetToList(HttpSession session,int currentPageNum,String txtname){
        //查询总数
        List<Recepobject> allRecepObj = service.findAllRecepObj();
        int totalNum = allRecepObj.size();
        // get方法得到的page currentPageNum和totalPageNum已经确定,txtname 为 "";
        PageVo page = PageVo.getPage(totalNum, currentPageNum);
        //如果txtname不为null，则填充到page中
        if(txtname != null){
            page.setTxtname(txtname);
        }
        //此时page三个参数currentPageNum和totalPageNum已经确定，txtname 为 txtname
        //将page作为参数进行查询.
        List<Recepobject> allRecepObjLike = service.findAllRecepObjLike(page);
        //分页新的页数
        int totalPageNum = page.getTotalPageNum(allRecepObjLike.size());

        page.setTotalPageNum(totalPageNum);

        // 将得到的list放入page中。
        page.setRecords(allRecepObjLike);
        //将page作为list放入session
        session.setAttribute("list",page);
        //跳转页面到list，此时有currentPageNum和totalPageNum
        return "/WEB-INF/jsp/receivetarget/list.jsp";
    }


/*
    //list传了currentPageNum参数
    @RequestMapping("ReceiveTarget/tolist")
    public String roomsetToList(HttpSession session,int currentPageNum,String txtname){
        //防止txtname为null
        if(txtname == null){
            txtname = "";
        }
        //查询接待对象数据
        List<Recepobject> list;
        //模糊查询
        list = service.findAllRecepObjLike("%"+txtname+"%");
        //新建一个page实例
        PageVo page = new PageVo();
        //获取搜索到的数据总数
        int size = list.size();
        //给jsp传递两个参数，当前页和总页数，默认为1.根据查询的size来计算总页数
        //调用静态方法获得page，默认currentPageNum为1,totalPage为1.page中有limit和offset
        //以page作为参数来实现分页查询
        PageVo.getPage(size,currentPageNum);

        page.setCurrentPageNum(currentPageNum);
        //  page.setTotalRecordsNum(size);
        page.setRecords(list);
        session.setAttribute("list",page);


        System.out.println("list" + list);
        return "/WEB-INF/jsp/receivetarget/list.jsp";
    }
*/

    @RequestMapping("/ReceiveTarget/toadd")
    public String toadd(HttpSession session){

        //填充团队类别list
        List<String> list = new ArrayList<>();
        list.add(0,"团队");
        list.add(1,"散客");
        session.setAttribute("listOne",list);

        return "/WEB-INF/jsp/receivetarget/add.jsp";
    }

    @RequestMapping(value = "/ReceiveTarget/add", method = RequestMethod.POST)
    public String addReceptorObject(Recepobject recepobject){

        //插入数据成功ret为true

        boolean ret = service.insertRecept(recepobject);

        System.out.println(" ret = " + ret);

//        return "/WEB-INF/jsp/receivetarget/list.jsp";
        return "/ReceiveTarget/tolist.do?currentPageNum=1";

    }

    @RequestMapping("/ReceiveTarget/toupdate")
    public String toupdate(int id, HttpSession session, Model model){

        System.out.println("toupdate-----------");
        //搜索id得到对象，填充
        Recepobject receptById = service.findReceptById(id);

        System.out.println(receptById);
        System.out.println("toupdate--------------------------");
        //填充团队类别list
        List<String> list = new ArrayList<>();
        list.add(0,"团队");
        list.add(1,"散客");
        session.setAttribute("listOne",list);

        session.setAttribute("list",receptById);

        return "/WEB-INF/jsp/receivetarget/update.jsp";
    }

    @RequestMapping("/ReceiveTarget/update")
    public String update(Recepobject recepobject){

        boolean ret = service.updateRecept(recepobject);
        System.out.println("ret = " + ret);
        return "/ReceiveTarget/tolist.do?currentPageNum=1";
    }

    @RequestMapping("/ReceiveTarget/delete")
    public String delete(int id){
        //将del_flag改为1，表示删除
        boolean ret = service.deleteRecept(id);
        System.out.println("ret = " + ret );
        return "/ReceiveTarget/tolist.do?currentPageNum=1";
    }
}
