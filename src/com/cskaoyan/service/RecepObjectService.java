package com.cskaoyan.service;

import com.cskaoyan.bean.Recepobject;
import com.cskaoyan.page.PageVo;

import java.util.List;

public interface RecepObjectService {
    List<Recepobject> findAllRecepObj();
    boolean insertRecept(Recepobject recepobject);
    boolean updateRecept(Recepobject recepobject);
    Recepobject findReceptById(int id);
    boolean deleteRecept(int id);
    List<Recepobject> findAllRecepObjLike(String txtname);
    List<Recepobject> findAllRecepObjLike(PageVo pageVo);
}
