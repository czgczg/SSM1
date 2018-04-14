package com.cskaoyan.dao;

import com.cskaoyan.bean.Ordermain;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public interface OrdermainMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ordermain
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    int insert(Ordermain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ordermain
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    int insertSelective(Ordermain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ordermain
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    Ordermain selectByPrimaryKey(String ordID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ordermain
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    int updateByPrimaryKeySelective(Ordermain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ordermain
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */

    //安排房间
    Boolean pushRoomset(String ordId);

    //查询所有未安排的订单
    ArrayList<Ordermain> getAllOrderIsBookingOn();

    //查询所有已安排安排的订单
    ArrayList<Ordermain> getAllOrderAlreadyBooking();

    //根据状态查询总数
    @Select("select count(1) from ordermain where receiveTargetID in (#{receiveTargetID}) and del_flag=0")
    Integer getPageOfOrdermains(String receiveTargetID);

    //查询部分订单
    @Select("SELECT * FROM ordermain WHERE del_flag=0 AND receiveTargetID=#{receiveTargetID} AND ( NAME LIKE #{name} OR teamName LIKE  #{teamname} )  LIMIT #{offset},#{limit}")
    List<Ordermain> findPartOrdermains(HashMap<String, Object> hashMap);
    List<Ordermain> selectAllOrderIsBeBooking();


    /**
    *修改订单状态为未结账
     */
    @Update("update ordermain set state=68")
    int modifyOrderStatus();

    @Select("select count(*) from ordermain WHERE del_flag=0")
    int findAllOrderCount();

    List<Ordermain> findPartOrder(HashMap<String, Object> map) ;

    @Select("select * from ordermain WHERE del_flag=0 and ordID=#{ordID}")
    List<Ordermain>findOrderById(String ordID);

}