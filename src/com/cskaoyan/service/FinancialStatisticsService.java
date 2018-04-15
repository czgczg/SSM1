package com.cskaoyan.service;

import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Ordextconsum;
import com.cskaoyan.utils.Page;

import java.util.List;

public interface FinancialStatisticsService {

    /**
     * 发现一夜page
     * @param currentPage
     * @param datemin
     * @param datemax
     * @return
     */
    Page<Ordermain> findPage(Integer currentPage, String datemin, String datemax);

    /**
     * 根据订单号发现所有消费记录
     * @param id
     * @return
     */
    List<Ordextconsum> findAllOrdextconsumByOrd_id(String id);
}
