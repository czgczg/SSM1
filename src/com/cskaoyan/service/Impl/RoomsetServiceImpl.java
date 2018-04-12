package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Roomset;
import com.cskaoyan.bean.Roomsetstatus;
import com.cskaoyan.bean.Roomsettype;
import com.cskaoyan.dao.RoomsetMapper;
import com.cskaoyan.dao.RoomsetstatusMapper;
import com.cskaoyan.dao.RoomsettypeMapper;
import com.cskaoyan.service.RoomsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoomsetServiceImpl implements RoomsetService{

    @Autowired
    RoomsetMapper roomsetMapper;

    @Autowired
    RoomsetstatusMapper roomsetstatusMapper;

    @Autowired
    RoomsettypeMapper roomsettypeMapper;

    @Override
    public List<Roomsetstatus> findAllRoomsetstatus() {
        return roomsetstatusMapper.findAllRoomsetstatus();
    }

    @Override
    public List<Roomsettype> findAllRoomsettype() {
        return roomsettypeMapper.findAllRoomsettype();
    }

    @Override
    public boolean insertRoomset(Roomset roomset) {
        Integer roomStateID = roomset.getRoomStateID();
        Roomsetstatus roomsetstatus = roomsetstatusMapper.selectByPrimaryKey(roomStateID);
        roomset.setRoomStateName(roomsetstatus.getAttributeDetailsName());
        Integer guestRoomLevelID = roomset.getGuestRoomLevelID();
        Roomsettype roomsettype = roomsettypeMapper.selectByPrimaryKey(guestRoomLevelID);
        roomset.setGuestRoomLevelName(roomsettype.getAttributeDetailsName());
        int i = roomsetMapper.insertSelective(roomset);
        return i==1;
    }

    @Override
    public List<Roomset> findAllRoomset() {
        return roomsetMapper.selectAllRoomset();
    }

    @Override
    public boolean deleteRoom(int id) {
        Integer i = roomsetMapper.deleteRoomsetByRoomNumber(id);
        return i==1;
    }

    @Override
    public Roomset findRoomsetById(int id) {
        Roomset roomset = roomsetMapper.selectByPrimaryKey(id);
        return roomset;
    }

}
