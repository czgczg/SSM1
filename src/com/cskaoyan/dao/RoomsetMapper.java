package com.cskaoyan.dao;

import com.cskaoyan.bean.Roomset;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.HashMap;
import java.util.List;

public interface RoomsetMapper {

    /**
     * 查找所有的roomset并返回
     * @return
     */
    @Select("SELECT * FROM roomset where del_flag = 0")
    List<Roomset> findAllRoomset();

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
    Integer findAllCountRoomset();

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

    /**
     * 通过id更改房间状态
     * 请提供一个HashMap，Map中包含三个键值对
     * roomStateID：xxx
     * roomStateName：xxx
     * id：xxx
     *
     * 提示：roomStateName可以使用RoomsetstatusMapper接口的selectByPrimaryKey方法来查找
     * @param hashMap
     * @return
     */
    @Update("UPDATE roomset SET roomStateID = #{roomStateID}, roomStateName = #{roomStateName} WHERE id = #{id}")
    Integer updateRoomStateById(HashMap hashMap);

    @Update("update roomset set roomStateID= 2 where roomNumber=#{roomNumber}")
    int modifyRoomStatus(String roomNumber);
    /**
     * 插入房间
     * @param roomset
     * @return
     */
    @Insert("INSERT INTO roomset (" +
            "roomNumber,guestRoomLevelID," +
            "roomStateID,roomAmount," +
            "standardPriceDay,standardPrice," +
            "maxDuration,firstDuration," +
            "firstPrice,roomStateName," +
            "guestRoomLevelName) VALUES(#{roomNumber},#{guestRoomLevelID}," +
            "#{roomStateID},#{roomAmount},#{standardPriceDay},#{standardPrice}," +
            "#{maxDuration},#{firstDuration},#{firstPrice},#{roomStateName},#{guestRoomLevelName})")
    Integer insertRoomset(Roomset roomset);

    /**
     * 根据主键查找 房间
     * @param id
     * @return
     */
    @Select("SELECT * FROM roomset WHERE del_flag=0 AND id=#{id}")
    Roomset findById(int id);

    /**
     * 更新房间信息
     * @param roomset
     * @return
     */
    @Update("UPDATE roomset SET roomNumber=#{roomNumber},guestRoomLevelID=#{guestRoomLevelID}," +
            "roomStateID=#{roomStateID},roomAmount=#{roomAmount},standardPriceDay=#{standardPriceDay}," +
            "standardPrice=#{standardPrice},maxDuration=#{maxDuration},firstDuration=#{firstDuration}," +
            "roomStateName=#{roomStateName},guestRoomLevelName=#{guestRoomLevelName} WHERE del_flag=0 AND id=#{id}")
    int updateById(Roomset roomset);

    /**
     * 发现roomset通过guestRoomLevelID
     * @param guestRoomLevelID
     * @return
     */
    @Select("SELECT * FROM roomset WHERE  del_flag=0 AND guestRoomLevelID=#{guestRoomLevelID}")
    List<Roomset> findRoomsetByLevelID(Integer guestRoomLevelID);
}