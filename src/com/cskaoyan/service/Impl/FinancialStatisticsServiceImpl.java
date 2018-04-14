package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Commodity;
import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.dao.OrdermainMapper;
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


    @Override
    public Page<Ordermain> findPage(Integer currentPage, String datemin, String datemax) {
        Page<Ordermain> page = new Page<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        Integer sumCount = ordermainMapper.findAllOrderCount();
        if(sumCount != null){
            page.setCurrentPage(currentPage);
            page.setTotalCount(sumCount);
            page.init(currentPage);
            hashMap.put("offset", Page.ORDERMAIN__NUM_PER_PAGE * (currentPage - 1));
            hashMap.put("limit", Page.ORDERMAIN__NUM_PER_PAGE);
            hashMap.put("name", "%");
            List<Ordermain> list = ordermainMapper.findPartOrder(hashMap);
            page.setResult(list);
        }
        return page;
    }
}
