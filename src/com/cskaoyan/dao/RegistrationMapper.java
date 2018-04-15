package com.cskaoyan.dao;


import org.apache.ibatis.annotations.Select;

import java.util.HashMap;

/**
 * author:czg
 * 对登记表的操作
 */
public interface RegistrationMapper {

    int  insertToRistration(HashMap<String, Object> map);


    @Select("select count(*) from registration where orderId=#{orderId}")
    int queryRegisterPersonCount(String orderId);
}