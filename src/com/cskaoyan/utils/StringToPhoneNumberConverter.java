package com.cskaoyan.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StringToPhoneNumberConverter implements Converter<String, Integer> {
    Pattern pattern = Pattern.compile("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\\\d{8}$");
    @Override
    public Integer convert(String source) {
        if(!StringUtils.hasLength(source)) {
            //①如果source为空 返回null
            return null;
        }
        Matcher matcher = pattern.matcher(source);
        if(matcher.matches()) {
            //②如果匹配 进行转换
            Integer phoneNumber=0;
            phoneNumber=Integer.parseInt(source);
            return phoneNumber;
        } else {
            //③如果不匹配 转换失败
            throw new IllegalArgumentException(String.format("类型转换失败，格式是[%s]", source));
        }
    }
}