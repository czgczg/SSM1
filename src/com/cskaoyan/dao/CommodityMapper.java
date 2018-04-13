package com.cskaoyan.dao;

import com.cskaoyan.bean.Commodity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

public interface CommodityMapper {

    /**
     * 插入商品
     * @param commodity
     * @return 成功返回1，失败返回0
     */
    @Insert("INSERT INTO commodity (commodityName, commodityTypeId, commodityTypeName, commodityMeasurementId, commodityMeasurementName, commodityPrice) " +
            "VALUES (#{commodityName},#{commodityTypeId},#{commodityTypeName},#{commodityMeasurementId},#{commodityMeasurementName},#{commodityPrice})")
    Integer insertCommodity(Commodity commodity);

    /**
     * 发现所有商品
     * @return
     */
    @Select("SELECT * FROM commodity WHERE del_flag = 0")
    List<Commodity> findAllCommodity();

    /**
     * 根据主键，删除商品
     * @param id
     * @return
     */
    @Update("UPDATE commodity SET del_flag = 1 WHERE id = #{id}")
    Integer deleteCommodityById(int id);

    /**
     * 根据主键返回相应的商品
     * @param id
     * @return
     */
    @Select("SELECT * FROM commodity WHERE del_flag = 0 AND id=#{id}")
    Commodity findCommodityById(int id);

    /**
     * 根据主键修改商品
     * @param commodity
     * @return
     */
    @Update("UPDATE commodity SET " +
            "commodityName = #{commodityName},commodityTypeId = #{commodityTypeId}," +
            "commodityTypeName = #{commodityTypeName},commodityMeasurementId = #{commodityMeasurementId}," +
            "commodityMeasurementName = #{commodityMeasurementName},commodityPrice = #{commodityPrice} " +
            "WHERE del_flag = 0 AND id = #{id}")
    Integer updateCommodityById(Commodity commodity);

    /**
     * 发现所有商品数量
     * @return
     */
    @Select("SELECT count(*) FROM commodity WHERE del_flag = 0 AND commodityTypeID = #{commodityTypeID}")
    Integer findAllCommodityCountBycommodityTypeID(Integer commodityTypeID);

    /**
     * 发现部分商品
     * @param hashMap
     * @return
     */
    @Select("SELECT * FROM commodity WHERE del_flag=0 AND commodityTypeID=#{commodityTypeID} AND commodityName LIKE #{txtname} LIMIT #{offset},#{limit}")
    List<Commodity> findPartCommodity(HashMap<String, Object> hashMap);
}