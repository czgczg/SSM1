package com.cskaoyan.service;

import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.utils.Page;

public interface FinancialStatisticsService {

    /**
     * 发现一夜page
     * @param currentPage
     * @param datemin
     * @param datemax
     * @return
     */
    Page<Ordermain> findPage(Integer currentPage, String datemin, String datemax);
}
