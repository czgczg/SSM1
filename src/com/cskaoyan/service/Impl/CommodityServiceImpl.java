package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Commodity;
import com.cskaoyan.bean.Commoditymeasurement;
import com.cskaoyan.bean.Commoditytype;
import com.cskaoyan.dao.CommodityMapper;
import com.cskaoyan.dao.CommoditymeasurementMapper;
import com.cskaoyan.dao.CommoditytypeMapper;
import com.cskaoyan.service.CommodityService;
import com.cskaoyan.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    CommoditytypeMapper commoditytypeMapper;

    @Autowired
    CommoditymeasurementMapper commoditymeasurementMapper;

    @Autowired
    CommodityMapper commodityMapper;


    @Override
    public List<Commoditytype> findAllCommoditytype() {
        return commoditytypeMapper.findAllCommoditytype();
    }

    @Override
    public List<Commoditymeasurement> findAllCommoditymeasurement() {
        return commoditymeasurementMapper.findAllCommoditymeasurement();
    }

    @Override
    public boolean insertCommodity(Commodity commodity) {
        commodity.setCommodityTypeName(commoditytypeMapper.findCommoditytypeById(commodity.getCommodityTypeId()));
        commodity.setCommodityMeasurementName(commoditymeasurementMapper.findCommoditymeasurementById(commodity.getCommodityMeasurementId()));
        Integer i = commodityMapper.insertCommodity(commodity);
        return i==1;
    }

    @Override
    public List<Commodity> findAllCommodity() {
        return commodityMapper.findAllCommodity();
    }

    @Override
    public boolean deleteCommodityById(int id) {
        Integer i = commodityMapper.deleteCommodityById(id);
        return i==1;
    }

    @Override
    public Commodity findCommodityById(int id) {
        return commodityMapper.findCommodityById(id);
    }

    @Override
    public boolean updateCommodityById(Commodity commodity) {
        commodity.setCommodityTypeName(commoditytypeMapper.findCommoditytypeById(commodity.getCommodityTypeId()));
        commodity.setCommodityMeasurementName(commoditymeasurementMapper.findCommoditymeasurementById(commodity.getCommodityMeasurementId()));
        Integer i = commodityMapper.updateCommodityById(commodity);
        return i==1;
    }

    @Override
    public Page<Commodity> findPage(Integer currentPage, String txtname, Integer commodityTypeID) {
        if("".equals(txtname)||txtname==null){
            txtname = "%";
        } else {
            txtname = "%" + txtname + "%";
        }
        Page<Commodity> page = new Page<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        Integer sumCount = commodityMapper.findAllCommodityCountBycommodityTypeID(commodityTypeID);
        if(sumCount != null) {
            final int PAGE_NUMBER = 5;
            page.setCurrentPage(currentPage);
            int i = sumCount / PAGE_NUMBER;
            page.setTotalPage(sumCount % PAGE_NUMBER == 0 ? i : i + 1);
            hashMap.put("txtname", txtname);
            hashMap.put("offset", PAGE_NUMBER * (currentPage - 1));
            hashMap.put("limit", PAGE_NUMBER);
            hashMap.put("commodityTypeID",commodityTypeID);
            List<Commodity> list = commodityMapper.findPartCommodity(hashMap);
            page.setResult(list);
        }
        return page;
    }

    @Override
    public boolean insertCommoditytype(String txtname) {
        Integer i = commoditytypeMapper.insertCommoditytype(txtname);
        return i == 1;
    }

    @Override
    public boolean deleteCommoditytypeById(int id) {
        Integer i = commoditytypeMapper.deleteCommoditytypeById(id);
        return i==1;
    }
}
