package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Recepobject;
import com.cskaoyan.dao.RecepobjectMapper;
import com.cskaoyan.page.PageVo;
import com.cskaoyan.service.RecepObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecepObjectServiceImpl implements RecepObjectService {

    @Autowired
    RecepobjectMapper dao;


    @Override
    public List<Recepobject> findAllRecepObj() {
        return dao.findAllObj();
    }

    @Override
    public boolean insertRecept(Recepobject recepobject) {
        return dao.insertRecept(recepobject) == 1;
    }

    @Override
    public boolean updateRecept(Recepobject recepobject) {
        return dao.updateRecept(recepobject) == 1;
    }

    @Override
    public Recepobject findReceptById(int id) {
        return dao.findReceptById(id);
    }

    @Override
    public boolean deleteRecept(int id) {
        return dao.deleteRecept(id) == 1;
    }

    @Override
    public int countAllRecepObjLike(String txtname) {
        return dao.countAllRecepObjLike(txtname);
    }
    @Override
    public List<Recepobject> findAllRecepObjLike(PageVo pageVo) {
        return dao.findAllRecepObjLike(pageVo);
    }
}
