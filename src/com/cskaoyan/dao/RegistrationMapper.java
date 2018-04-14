package com.cskaoyan.dao;


import java.util.HashMap;

/**
 * author:czg
 * 对登记表的操作
 */
public interface RegistrationMapper {

    int  insertToRistration(HashMap<String, Object> map);
}