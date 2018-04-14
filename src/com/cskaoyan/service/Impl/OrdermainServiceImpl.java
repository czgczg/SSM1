package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Commodity;
import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Ordextconsum;
import com.cskaoyan.dao.CommodityMapper;
import com.cskaoyan.dao.OrdermainMapper;
import com.cskaoyan.dao.OrdextconsumMapper;
import com.cskaoyan.service.OrdermainService;
import com.cskaoyan.utils.Page;
import com.cskaoyan.vo.ConsumptionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrdermainServiceImpl implements OrdermainService {

    @Autowired
    OrdermainMapper ordermainMapper;

    @Autowired
    OrdextconsumMapper ordextconsumMapper;

    @Autowired
    CommodityMapper commodityMapper;

    @Override
    public boolean insertOrdextconsum(String commodity_id, String num, String ord_id) {
        HashMap<String, Object> hashMap =new HashMap<>();
        Commodity commodity = commodityMapper.findCommodityById(Integer.parseInt(commodity_id));
        Float sumConst = commodity.getCommodityPrice() * Integer.parseInt(num);
        hashMap.put("sumConst", sumConst);
        hashMap.put("commodity_id", commodity_id);
        hashMap.put("num", num);
        hashMap.put("ord_id", ord_id);
        Integer i = ordextconsumMapper.insertOrdextconsum(hashMap);
        Integer x = null;
        if(i==1) {
            // 修改总金额
            x = ordermainMapper.updateSumConstByOrdID(hashMap);
        }
        return i==1&&x==1;
    }

    @Override
    public boolean deleteConsumptionByCommodityOrderId(String id) {
        HashMap<String, Object> hashMap =new HashMap<>();
        Float sumMoney = ordextconsumMapper.findSumMoney(id);
        Ordextconsum ordextConsumById = ordextconsumMapper.findOrdextConsumById(id);
        Integer i = ordextconsumMapper.deleteConsumptionById(id);
        String ordId = ordextConsumById.getOrd_id();
        hashMap.put("ord_id", ordId);
        hashMap.put("sumConst", sumMoney);
        Integer x = null;
        if(i==1){
            x = ordermainMapper.deleteSumConstByOrdID(hashMap);
        }
        return i==1&&x==1;
    }


    @Override
    public Page<ConsumptionVo> findPage(Integer currentPage, String ord_id) {
        if(currentPage==null || currentPage<0){
            currentPage=1;
        }
        Page<ConsumptionVo> page = new Page<>();
        HashMap<String, Object> hashMap = new HashMap<>();

        Integer sumCount = ordextconsumMapper.findAllConsumCount(ord_id);
        page.setCurrentPage(currentPage);
        page.setTotalCount(sumCount);
        page.init(currentPage);
        hashMap.put("offset", Page.ORDERMAIN__NUM_PER_PAGE * (currentPage - 1));
        hashMap.put("limit", Page.ORDERMAIN__NUM_PER_PAGE);
        hashMap.put("ord_id", ord_id);
        List<Ordextconsum> list = ordextconsumMapper.findPartConsum(hashMap);
        List<ConsumptionVo> listVo = new LinkedList<>();
        for(Ordextconsum o : list){
            ConsumptionVo consumptionVo = new ConsumptionVo();
            consumptionVo.setConsumptionMoney(o.getConsumptionMoney()+"");
            consumptionVo.setConsumptionNumber(o.getNum()+"");
            consumptionVo.setConsumptionTime(o.getConsumptionTime());
            consumptionVo.setConsumptionId(o.getId()+"");
            Commodity commodityById = commodityMapper.findCommodityById(o.getCommodity_id());
            consumptionVo.setCommodityName(commodityById.getCommodityName());
            consumptionVo.setCommoditySalePrice(commodityById.getCommodityPrice()+"");
            consumptionVo.setCommodityUOMName(commodityById.getCommodityMeasurementName()+"");
            listVo.add(consumptionVo);
        }
        page.setResult(listVo);
        return page;
    }

    @Override
    public Ordermain findOrderByOrdId(String ord_id) {
        Ordermain ordermain = ordermainMapper.selectByPrimaryKey(ord_id);
        return ordermain;
    }


    @Override
    public Ordextconsum findOrdextConsumById(String id) {
        return ordextconsumMapper.findOrdextConsumById(id);
    }
}
