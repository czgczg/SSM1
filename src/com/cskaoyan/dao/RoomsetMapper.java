package com.cskaoyan.dao;

import com.cskaoyan.bean.Roomset;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.HashMap;
import java.util.List;

public interface RoomsetMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomset
     *
     * @mbg.generated Thu Apr 12 15:17:27 CST 2018
     */
    int insert(Roomset record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomset
     *
     * @mbg.generated Thu Apr 12 15:17:27 CST 2018
     */
    int insertSelective(Roomset record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomset
     *
     * @mbg.generated Thu Apr 12 15:17:27 CST 2018
     */
    Roomset selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomset
     *
     * @mbg.generated Thu Apr 12 15:17:27 CST 2018
     */
    int updateByPrimaryKeySelective(Roomset record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomset
     *
     * @mbg.generated Thu Apr 12 15:17:27 CST 2018
     */
    int updateByPrimaryKey(Roomset record);

    /**
     * 查找所有的roomset并返回
     * @return
     */
    @Select("SELECT * FROM roomset")
    List<Roomset> selectAllRoomset();

    /**
     * 根据RoomNumber将del_flag置为0
     * @param id RoomNumber
     * @return 修改的行数
     */
    @Update("UPDATE roomset SET del_flag = 1 WHERE id = #{id}")
    Integer deleteRoomsetByRoomNumber(int id);

    /**
     * 统计所有房间的数量
     * @return
     */
    @Select("SELECT count(*) FROM roomset WHERE del_flag=0")
    Integer selectAllCountRoomset();

    /**
     * 查找部分商品从offset到limit,并且模糊查询txtname
     * @param hashMap
     * @return
     */
    @Select("SELECT * FROM roomset WHERE del_flag=0 AND roomNumber LIKE #{txtname} LIMIT #{offset},#{limit}")
    List<Roomset> findPartRoomset(HashMap<String, Object> hashMap);

    /**
     * 查找特定商品
     * @param txtname
     * @return
     */
    @Select("SELECT * FROM roomset WHERE del_flag=0 AND roomNumber LIKE #{txtname}")
    List<Roomset> findSpecial(String txtname);

}