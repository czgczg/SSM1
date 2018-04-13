package com.cskaoyan.bean;

import org.omg.PortableInterceptor.INACTIVE;

public class Commodity {

    private Integer id;
    private String commodityName;
    private Integer commodityTypeId;
    private String commodityTypeName;
    private Integer commodityMeasurementId;
    private String commodityMeasurementName;
    private Float commodityPrice;
    private Integer del_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommodityTypeName() {
        return commodityTypeName;
    }

    public void setCommodityTypeName(String commodityTypeName) {
        this.commodityTypeName = commodityTypeName;
    }

    public String getCommodityMeasurementName() {
        return commodityMeasurementName;
    }

    public void setCommodityMeasurementName(String commodityMeasurementName) {
        this.commodityMeasurementName = commodityMeasurementName;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getCommodityTypeId() {
        return commodityTypeId;
    }

    public void setCommodityTypeId(Integer commodityTypeId) {
        this.commodityTypeId = commodityTypeId;
    }

    public Integer getCommodityMeasurementId() {
        return commodityMeasurementId;
    }

    public void setCommodityMeasurementId(Integer commodityMeasurementId) {
        this.commodityMeasurementId = commodityMeasurementId;
    }

    public Float getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Float commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }
}