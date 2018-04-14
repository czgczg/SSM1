package com.cskaoyan.service;

import com.cskaoyan.bean.Commodity;
import com.cskaoyan.bean.Commoditymeasurement;
import com.cskaoyan.bean.Commoditytype;
import com.cskaoyan.utils.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public interface CommodityService {

    /**
     * 发现所有的商品类型
     * @return
     */
    List<Commoditytype> findAllCommoditytype();

    /**
     *  发现所有的计量单位
     * @return
     */
    List<Commoditymeasurement> findAllCommoditymeasurement();

    /**
     * 插入商品
     * @param commodity
     * @return 成功返回true， 失败返回false
     */
    boolean insertCommodity(Commodity commodity);

    /**
     * 发现所有的商品
     * @return
     */
    List<Commodity> findAllCommodity();

    /**
     * 删除商品
     * @param id
     * @return
     */
    boolean deleteCommodityById(int id);

    /**
     * 根据主键查询相应的商品
     * @param id
     * @return
     */
    Commodity findCommodityById(int id);

    /**
     * 修改商品信息
     * @param commodity
     * @return
     */
    boolean updateCommodityById(Commodity commodity);

    /**
     * 发现页面
     * @param currentPage
     * @param txtname
     * @return
     */
    Page<Commodity> findPage(Integer currentPage, String txtname, Integer commodityTypeID);

    /**
     * 新建商品类别
     * @param txtname
     * @return
     */
    boolean insertCommoditytype(String txtname);

    /**
     * 删除商品类别
     * @param id
     * @return
     */
    boolean deleteCommoditytypeById(int id);

    /**
     * 根据条件发现部分商品
     * @param txtname
     * @param commodityTypeID
     * @return
     */
    List<Commodity> findPartCommodity(String txtname, String commodityTypeID);



}
