package com.cskaoyan.service;

import com.cskaoyan.bean.Passengerdegree;

import java.util.List;

public interface PassengerdegreeService {
    public List<Passengerdegree> findall();


    public String findPassengerDegreeNameById(Integer far_id);
}
