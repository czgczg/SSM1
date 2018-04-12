package com.cskaoyan.dao;

import com.cskaoyan.bean.Passengerdegree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface PassengerdegreeMapper {
          public List<Passengerdegree> find();

          public String findPassengerDegreeNameById(Integer far_id);
}