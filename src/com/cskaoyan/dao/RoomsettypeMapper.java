package com.cskaoyan.dao;

import com.cskaoyan.bean.Roomsettype;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

public interface RoomsettypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsettype
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    int insert(Roomsettype record);

   // ArrayList<>

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsettype
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    int insertSelective(Roomsettype record);

    /**
     * 发现所有房间类型，并以List返回
     * @return
     */
    @Select("SELECT * FROM roomsettype")
    List<Roomsettype> findAllRoomsettype();

    /**
     * 根据主键返回房间状态
     * @param guestRoomLevelID
     * @return
     */
    @Select("SELECT * FROM roomsettype WHERE far_id = #{guestRoomLevelID}")
    Roomsettype findByPrimaryKey(Integer guestRoomLevelID);
}