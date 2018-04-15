package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Commodity;
import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Ordextconsum;
import com.cskaoyan.dao.OrdermainMapper;
import com.cskaoyan.dao.OrdextconsumMapper;
import com.cskaoyan.service.FinancialStatisticsService;
import com.cskaoyan.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class FinancialStatisticsServiceImpl implements FinancialStatisticsService{
    @Autowired
    OrdermainMapper ordermainMapper;

    @Autowired
    OrdextconsumMapper ordextconsumMapper;


    @Override
    public Page<Ordermain> findPage(Integer currentPage, String datemin, String datemax) {

        Page<Ordermain> page = new Page<>();
        if ("".equals(datemax) && "".equals(datemin)) {
            HashMap<String, Object> hashMap = new HashMap<>();
            Integer sumCount = ordermainMapper.findAllOrderCount();
            if (sumCount != null) {
                page.setCurrentPage(currentPage);
                page.setTotalCount(sumCount);
                page.initOrder(sumCount,currentPage);
                hashMap.put("offset", Page.ORDERMAIN__NUM_PER_PAGE * (currentPage - 1));
                hashMap.put("limit", Page.ORDERMAIN__NUM_PER_PAGE);
                hashMap.put("name", "%");
                hashMap.put("datemin", datemin);
                hashMap.put("datemax", datemax);
                List<Ordermain> list = ordermainMapper.findPartOrder2(hashMap);
                page.setResult(list);
            }
        } else if (datemax == null||datemin ==null) {
            HashMap<String, Object> hashMap = new HashMap<>();
            Integer sumCount = ordermainMapper.findAllOrderCount();
            if (sumCount != null) {
                page.setCurrentPage(currentPage);
                page.setTotalCount(sumCount);
                page.initOrder(sumCount,currentPage);
                hashMap.put("offset", Page.ORDERMAIN__NUM_PER_PAGE * (currentPage - 1));
                hashMap.put("limit", Page.ORDERMAIN__NUM_PER_PAGE);
                hashMap.put("name", "%");
                hashMap.put("datemin", datemin);
                hashMap.put("datemax", datemax);
                List<Ordermain> list = ordermainMapper.findPartOrder2(hashMap);
                page.setResult(list);
            }
        }else{
            if (datemax == null || "".equals(datemax)) {
                datemax = "9999-12-12";
            }
            if (datemin == null || "".equals(datemin)) {
                datemin = "1970-1-1";
            }
            HashMap<String, Object> hashMap = new HashMap<>();
            Integer sumCount = ordermainMapper.findAllOrderCount();
            if (sumCount != null) {
                page.setCurrentPage(currentPage);
                page.setTotalCount(sumCount);
                page.initOrder(sumCount,currentPage);
                hashMap.put("offset", Page.ORDERMAIN__NUM_PER_PAGE * (currentPage - 1));
                hashMap.put("limit", Page.ORDERMAIN__NUM_PER_PAGE);
                hashMap.put("name", "%");
                hashMap.put("datemin", datemin);
                hashMap.put("datemax", datemax);
                List<Ordermain> list = ordermainMapper.findPartOrderByDate(hashMap);
                page.setResult(list);
            }
        }
        return page;
    }

    @Override
    public List<Ordextconsum> findAllOrdextconsumByOrd_id(String id) {
        return ordextconsumMapper.findAllOrdextconsumByOrd_id(id);
    }
}
