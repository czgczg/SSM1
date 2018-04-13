package com.cskaoyan.dao;

import com.cskaoyan.bean.Commoditymeasurement;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

public interface CommoditymeasurementMapper {


    /**
     * 发现所有的计量单位
     * @return
     */
    @Select("SELECT * FROM commoditymeasurement WHERE del_flag = 0")
    List<Commoditymeasurement> findAllCommoditymeasurement();

    /**
     * 根据主键查找相应的计量单位
     * @param id
     * @return
     */
    @Select("SELECT attributeDetailsName FROM commoditymeasurement WHERE del_flag = 0 AND far_id=#{id}")
    String findCommoditymeasurementById(Integer id);
}