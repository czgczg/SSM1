package com.cskaoyan.dao;

import com.cskaoyan.bean.Ordextconsum;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

public interface OrdextconsumMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ordextconsum
     *
     * @mbg.generated Thu Apr 12 16:36:05 CST 2018
     */
    int insert(Ordextconsum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ordextconsum
     *
     * @mbg.generated Thu Apr 12 16:36:05 CST 2018
     */
    int insertSelective(Ordextconsum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ordextconsum
     *
     * @mbg.generated Thu Apr 12 16:36:05 CST 2018
     */
    Ordextconsum selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ordextconsum
     *
     * @mbg.generated Thu Apr 12 16:36:05 CST 2018
     */
    int updateByPrimaryKeySelective(Ordextconsum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ordextconsum
     *
     * @mbg.generated Thu Apr 12 16:36:05 CST 2018
     */
    int updateByPrimaryKey(Ordextconsum record);

    /**
     * 插入订单记录
     * @param hashMap
     * @return
     */
    @Insert("INSERT INTO ordextconsum (commodity_id,num,ord_id,consumptionMoney) VALUES (#{commodity_id},#{num},#{ord_id},#{sumConst})")
    Integer insertOrdextconsum(HashMap<String, Object> hashMap);

    /**
     * 发现所有订单数
     * @return
     */
    @Select("SELECT count(*) FROM ordextconsum WHERE del_flag=0 AND ord_id=#{ord_id}")
    Integer findAllConsumCount(String ord_id);

    /**
     *
     * @param hashMap
     * @return
     */
    @Select("SELECT * FROM ordextconsum WHERE del_flag=0 AND ord_id=#{ord_id} LIMIT #{offset},#{limit}")
    List<Ordextconsum> findPartConsum(HashMap<String, Object> hashMap);

    /**
     * 通过一个id查找详细信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM ordextconsum WHERE del_flag=0 AND id=#{id}")
    Ordextconsum findOrdextConsumById(String id);

    /**
     * 通过一个ord_id查找详细信息
     * @param ord_id
     * @return
     */
    @Select("SELECT * FROM ordextconsum WHERE del_flag=0 AND ord_id=#{ord_id}")
    Ordextconsum findOrdextConsumByOrd_Id(String ord_id);

    /**
     * 发现总价通过id
     * @param id
     * @return
     */
    @Select("SELECT consumptionMoney FROM ordextconsum WHERE del_flag=0 AND id=#{id}")
    Float findSumMoney(String id);

    /**
     * 删除商品订单
     * @param id
     * @return
     */
    @Update("UPDATE ordextconsum SET del_flag = 1 WHERE id = #{id}")
    Integer deleteConsumptionById(String id);
}