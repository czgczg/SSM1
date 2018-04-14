package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Ordermain;
import com.cskaoyan.bean.Passenger;
import com.cskaoyan.bean.Roomset;
import com.cskaoyan.dao.OrdermainMapper;
import com.cskaoyan.dao.RoomsetMapper;
import com.cskaoyan.service.RoomsetService;
import com.cskaoyan.service.StayRegisterService;
import com.cskaoyan.utils.Page;
import com.cskaoyan.vo.Listone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StayRegisterServiceImpl implements StayRegisterService {

    @Autowired
    OrdermainMapper ordermainMapper;

    @Autowired
    RoomsetMapper roomsetMapper;


    /**
     * ordermain的分页处理和显示
     * @param currentPage
     * @param roomNumber
     * @return
     */
    @Override
    public Page<Ordermain> findPage(Integer currentPage, String roomNumber) {
        Page<Ordermain> page = new Page<>();
        int totalNumber = findAllOrderMainCount();
        page.setTotalCount(totalNumber);
        int num = currentPage;

        page.init(totalNumber);
        page.setCurrentPage(num);

        HashMap<String, Object> map = new HashMap<>();
        map.put("limit",Page. ORDERMAIN__NUM_PER_PAGE);
        map.put ("offset",(num - 1) * Page. ORDERMAIN__NUM_PER_PAGE);
        map.put("name", roomNumber);
        List<Ordermain> order = ordermainMapper.findPartOrder(map);
        page.setResult(order);
        return page;
    }

    @Override
    public List<Roomset> guestRoomLevelSelectRoom(Integer guestRoomLevelID) {
        if(guestRoomLevelID==0){
            List<Roomset> result=roomsetMapper.findAllRoomset();
            return result;
        }

        List<Roomset> result = roomsetMapper.findRoomsetByLevelID(guestRoomLevelID);
        return result;
    }

    @Override
    public ArrayList<HashMap> getHashMaps(List<Listone> payWay, ArrayList<HashMap> listRentOutType) {
        for (Listone i:payWay) {
            HashMap<String, String> j = new HashMap<>();
            j.put("far_id", i.getId().toString());
            j.put("attributeDetailsName", i.getAttributeDetailsName());
            listRentOutType.add(j);
        }
        return listRentOutType;
    }

    private int findAllOrderMainCount() {

       return ordermainMapper.findAllOrderCount();

    }

    @Override
    public void modifyRoomStatus(String roomNumber) {
        roomsetMapper.modifyRoomStatus(roomNumber);
    }

    @Override
    public void modifyOrderStatus() {
        ordermainMapper.modifyOrderStatus();
    }


}
