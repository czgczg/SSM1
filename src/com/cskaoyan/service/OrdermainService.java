package com.cskaoyan.service;

import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Ordextconsum;
import com.cskaoyan.utils.Page;
import com.cskaoyan.vo.ConsumptionVo;

public interface OrdermainService {
    /**
     *
     * @param commodity_id 商品id
     * @param num 商品数量
     * @param ord_id 订单号
     * @return
     */
    boolean insertOrdextconsum(String commodity_id, String num, String ord_id);


    /**
     * 发现一个旅客消费的页面
     * @param currentPage
     * @return
     */
    Page<ConsumptionVo> findPage(Integer currentPage, String id);

    /**
     * 发现一个订单
     * @param ord_id
     * @return
     */
    Ordermain findOrderByOrdId(String ord_id);

    /**
     * 删除商品订单
     * @param id
     * @return
     */
    boolean deleteConsumptionByCommodityOrderId(String id);

    /**
     * 查询商品订单详细信息
     * @param s
     * @return
     */
    Ordextconsum findOrdextConsumById(String s);
}
