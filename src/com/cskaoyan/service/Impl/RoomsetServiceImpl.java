package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Roomset;
import com.cskaoyan.bean.Roomsetstatus;
import com.cskaoyan.bean.Roomsettype;
import com.cskaoyan.dao.RoomsetMapper;
import com.cskaoyan.dao.RoomsetstatusMapper;
import com.cskaoyan.dao.RoomsettypeMapper;
import com.cskaoyan.service.RoomsetService;
import com.cskaoyan.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
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
        Roomsetstatus roomsetstatus = roomsetstatusMapper.findStatusByPrimaryKey(roomStateID);
        roomset.setRoomStateName(roomsetstatus.getAttributeDetailsName());
        Integer guestRoomLevelID = roomset.getGuestRoomLevelID();
        Roomsettype roomsettype = roomsettypeMapper.findByPrimaryKey(guestRoomLevelID);
        roomset.setGuestRoomLevelName(roomsettype.getAttributeDetailsName());
        Integer i = roomsetMapper.insertRoomset(roomset);
        return i==1;
    }

    @Override
    public List<Roomset> findAllRoomset() {
        return roomsetMapper.findAllRoomset();
    }

    @Override
    public boolean deleteRoom(int id) {
        Integer i = roomsetMapper.deleteRoomsetByRoomNumber(id);
        return i==1;
    }

    @Override
    public Roomset findRoomsetById(int id) {
        Roomset roomset = roomsetMapper.findById(id);
        return roomset;
    }

    @Override
    public boolean updateRoomsetById(Roomset roomset) {
        Integer roomStateID = roomset.getRoomStateID();
        Roomsetstatus roomsetstatus = roomsetstatusMapper.findStatusByPrimaryKey(roomStateID);
        roomset.setRoomStateName(roomsetstatus.getAttributeDetailsName());
        Integer guestRoomLevelID = roomset.getGuestRoomLevelID();
        Roomsettype roomsettype = roomsettypeMapper.findByPrimaryKey(guestRoomLevelID);
        roomset.setGuestRoomLevelName(roomsettype.getAttributeDetailsName());
        roomset.setDel_flag(0);
        int i = roomsetMapper.updateById(roomset);
        return i==1;
    }

    @Override
    public Page<Roomset> findPage(int currentPage, String txtname) {
        if("".equals(txtname)||txtname==null){
            txtname = "%";
        } else {
            txtname = "%" + txtname + "%";
        }
        Page<Roomset> page = new Page<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        Integer sumCount = roomsetMapper.findAllCountRoomset();
        if(sumCount != null) {
            final int PAGE_NUMBER = 5;
            page.setCurrentPage(currentPage);
            int i = sumCount / PAGE_NUMBER;
            page.setTotalPage(sumCount % PAGE_NUMBER == 0 ? i : i + 1);
            hashMap.put("txtname", txtname);
            hashMap.put("offset", PAGE_NUMBER * (currentPage - 1));
            hashMap.put("limit", PAGE_NUMBER);
            List<Roomset> list = roomsetMapper.findPartRoomset(hashMap);
            page.setResult(list);
        }
        return page;
    }

    @Override
    public Roomset queryroomAmountByRoomNumber(String roomNumber) {
        return roomsetMapper.queryroomAmountByRoomNumber(roomNumber);
    }

    @Override
    public List<Roomset> findSpecial(String txtname) {
        if("".equals(txtname)||txtname==null){
            txtname = "%";
        } else {
            txtname = "%" + txtname + "%";
        }
        List<Roomset> roomsetList = roomsetMapper.findSpecial(txtname);
        return roomsetList;
    }

}
