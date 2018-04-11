package com.cskaoyan.service.Impl;

import com.cskaoyan.bean.Passengerdegree;
import com.cskaoyan.dao.PassengerdegreeMapper;
import com.cskaoyan.service.PassengerdegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PassengerdegreeServiceImpl implements PassengerdegreeService {

    @Autowired
    PassengerdegreeMapper passengerdegreeMapper;


    @Override
    public Passengerdegree findall() {
        Passengerdegree allPassengerdegree = passengerdegreeMapper.find();
        return allPassengerdegree;
    }
}
