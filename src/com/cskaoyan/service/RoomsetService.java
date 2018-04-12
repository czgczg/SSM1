package com.cskaoyan.service;

import com.cskaoyan.bean.Roomset;
import com.cskaoyan.bean.Roomsetstatus;
import com.cskaoyan.bean.Roomsettype;

import java.util.List;

public interface RoomsetService {

    /**
     * 发现所有的房间状态并返回
     * @return
     */
    List<Roomsetstatus> findAllRoomsetstatus();

    /**
     * 发现所有的房间类型并返回
     * @return
     */
    List<Roomsettype> findAllRoomsettype();

    /**
     * 插入成功返回true，插入失败返回false
     * @param roomset
     * @return
     */
    boolean insertRoomset(Roomset roomset);

    /**
     * 查找所有的roomset并返回
     * @return
     */
    List<Roomset> findAllRoomset();


    /**
     * 删除roomset
     * @param id
     * @return true表示删除成功，false表示删除失败
     */
    boolean deleteRoom(int id);

    /**
     * 根据id查找相印的Roomset
     * @param id
     * @return
     */
    Roomset findRoomsetById(int id);
}
