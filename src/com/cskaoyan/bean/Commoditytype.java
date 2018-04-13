package com.cskaoyan.bean;

/**
 * 商品类别类
 */
public class Commoditytype {


    private Integer far_id;
    private String attributeDetailsName;
    private Integer del_flag;

    public Integer getFar_id() {
        return far_id;
    }

    public void setFar_id(Integer far_id) {
        this.far_id = far_id;
    }

    public String getAttributeDetailsName() {
        return attributeDetailsName;
    }

    public void setAttributeDetailsName(String attributeDetailsName) {
        this.attributeDetailsName = attributeDetailsName;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }
}