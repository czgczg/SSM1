package com.cskaoyan.service;

import com.cskaoyan.bean.Commodity;
import com.cskaoyan.bean.Commoditymeasurement;
import com.cskaoyan.bean.Commoditytype;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public interface CommodityService {

    public ArrayList<Commoditytype> getAllCommoditytype();

    public  ArrayList<Commoditymeasurement> getAllCommoditymeasurement();

    public int commodityAdd(Commodity commodity, HttpServletRequest request);
}
