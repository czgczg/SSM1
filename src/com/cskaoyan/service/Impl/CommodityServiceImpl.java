package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Commodity;
import com.cskaoyan.bean.Commoditymeasurement;
import com.cskaoyan.bean.Commoditytype;
import com.cskaoyan.dao.CommodityMapper;
import com.cskaoyan.dao.CommoditymeasurementMapper;
import com.cskaoyan.dao.CommoditytypeMapper;
import com.cskaoyan.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    CommoditytypeMapper commoditytypeMapper;

    @Autowired
    CommoditymeasurementMapper commoditymeasurementMapper;

    @Autowired
    CommodityMapper commodityMapper;


    @Override
    public ArrayList<Commoditytype> getAllCommoditytype() {
        ArrayList<Commoditytype> allCommoditytype = commoditytypeMapper.getAllCommoditytype();

        return allCommoditytype;

    }

    @Override
    public ArrayList<Commoditymeasurement> getAllCommoditymeasurement() {

        ArrayList<Commoditymeasurement> allCommoditymeasurement = commoditymeasurementMapper.getAllCommoditymeasurement();

        return allCommoditymeasurement;

    }

    @Override
    public int commodityAdd(Commodity commodity, HttpServletRequest request) {
        int i = commodityMapper.insertSelective(commodity);
        return i;

    }
}
