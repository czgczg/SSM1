package com.cskaoyan.dao;

import com.cskaoyan.bean.Commoditytype;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CommoditytypeMapper {

    /**
     *  发现所有的商品类型
     * @return
     */
    @Select("SELECT * FROM commoditytype WHERE del_flag = 0")
    List<Commoditytype> findAllCommoditytype();


    /**
     * 根据主键查找相应的商品类型
     * @param id
     * @return
     */
    @Select("SELECT attributeDetailsName FROM commoditytype WHERE del_flag = 0 AND far_id=#{id}")
    String findCommoditytypeById(Integer id);

    /**
     * 插入新商品类别
     * @param txtname
     * @return
     */
    @Insert("INSERT INTO commoditytype (attributeDetailsName) VALUES (#{txtname})")
    Integer insertCommoditytype(String txtname);

    /**
     * 删除商品类别
     * @param id
     * @return
     */
    @Update("UPDATE commoditytype SET del_flag = 1 WHERE far_id = #{id} ")
    Integer deleteCommoditytypeById(int id);

}