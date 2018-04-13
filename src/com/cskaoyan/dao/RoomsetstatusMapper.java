package com.cskaoyan.dao;

import com.cskaoyan.bean.Roomsetstatus;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoomsetstatusMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetstatus
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    int deleteByPrimaryKey(Integer roomsetstatusid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetstatus
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    int insert(Roomsetstatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetstatus
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    int insertSelective(Roomsetstatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetstatus
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    Roomsetstatus selectByPrimaryKey(Integer roomsetstatusid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetstatus
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    int updateByPrimaryKeySelective(Roomsetstatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetstatus
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(Roomsetstatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetstatus
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    int updateByPrimaryKey(Roomsetstatus record);

    /**
     * 查找所有的房间状态并返回
     * @return
     */
    @Select("SELECT * FROM roomsetstatus")
    List<Roomsetstatus> findAllRoomsetstatus();

    /**
     * 根据主键查找相印的status
     * @param far_id
     * @return
     */
    @Select("SELECT * FROM roomsetstatus WHERE far_id=#{far_id}")
    Roomsetstatus findStatusByPrimaryKey(int far_id);


}