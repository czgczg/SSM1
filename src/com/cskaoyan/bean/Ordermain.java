package com.cskaoyan.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Ordermain {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.ordID
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private String ordID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.passengerOrReceiveID
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private Integer passengerOrReceiveID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.commodityName
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private String commodityName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.commodityPhone
     *
     * @mbg.generated Sat Apr 14 00:26:17 CST 2018
     */
    private String commodityPhone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.name
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.teamname
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private String teamname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.receiveTargetID
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private Integer receiveTargetID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.status
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private Integer state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.orderFrom
     *
     * @mbg.generated Sat Apr 14 00:26:17 CST 2018
     */
    private Integer orderFrom;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.rentOutTypeId
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    private Integer rentOutTypeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.rentOutTypeName
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private String rentOutTypeName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.LoginFlag
     *
     * @mbg.generated Sat Apr 14 00:26:17 CST 2018
     */
    private Integer loginFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.stayNumber
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private Integer stayNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.predetermineDay
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private Integer predetermineDay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.deposit
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private Float deposit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.payWayID
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private Integer payWayID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.payWayName
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private String payWayName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.arriveTime
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date arriveTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.roomId
     *
     * @mbg.generated Sat Apr 14 15:51:16 CST 2018
     */
    private Integer roomId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.roomNumber
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private String roomNumber;


    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.guestRoomLevelName
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private String guestRoomLevelName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.roomAmount
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    private Integer roomAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.orderTime
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.registerTime
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private Date registerTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.payTime
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private Date payTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.billUnitID
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    private Integer billUnitID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.billUnitName
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    private String billUnitName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.passengerTypeName
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    private String passengerTypeName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.passengerTypeID
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    private Integer passengerTypeID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.sumConst
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private Float sumConst;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.timestamp
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    private String timestamp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.changingRoomNumber
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    private int changingRoomNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.changRoomMoney
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    private Double changRoomMoney;

    /**
     * 一天的标准房价
     *
     */
    private double roomStandardPriceDay;

    public double getRoomStandardPriceDay() {
        return roomStandardPriceDay;
    }

    public void setRoomStandardPriceDay(double roomStandardPriceDay) {
        this.roomStandardPriceDay = roomStandardPriceDay;
    }


    /**
     * receiveTeamName 接收对象
     */

    private String receiveTeamName;



    public String getReceiveTeamName() {
        return receiveTeamName;
    }

    public void setReceiveTeamName(String receiveTeamName) {
        this.receiveTeamName = receiveTeamName;
    }

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordermain.del_flag
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    private Integer del_flag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.ordID
     *
     * @return the value of ordermain.ordID
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public String getOrdID() {
        return ordID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.ordID
     *
     * @param ordID the value for ordermain.ordID
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setOrdID(String ordID) {
        this.ordID = ordID == null ? null : ordID.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.passengerOrReceiveID
     *
     * @return the value of ordermain.passengerOrReceiveID
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public Integer getPassengerOrReceiveID() {
        return passengerOrReceiveID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.passengerOrReceiveID
     *
     * @param passengerOrReceiveID the value for ordermain.passengerOrReceiveID
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setPassengerOrReceiveID(Integer passengerOrReceiveID) {
        this.passengerOrReceiveID = passengerOrReceiveID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.commodityName
     *
     * @return the value of ordermain.commodityName
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public String getCommodityName() {
        return commodityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.commodityName
     *
     * @param commodityName the value for ordermain.commodityName
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.commodityPhone
     *
     * @return the value of ordermain.commodityPhone
     *
     * @mbg.generated Sat Apr 14 13:18:44 CST 2018
     */
    public String getCommodityPhone() {
        return commodityPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.commodityPhone
     *
     * @param commodityPhone the value for ordermain.commodityPhone
     *
     * @mbg.generated Sat Apr 14 13:18:44 CST 2018
     */
    public void setCommodityPhone(String commodityPhone) {
        this.commodityPhone = commodityPhone == null ? null : commodityPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.name
     *
     * @return the value of ordermain.name
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.name
     *
     * @param name the value for ordermain.name
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.teamname
     *
     * @return the value of ordermain.teamname
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public String getTeamname() {
        return teamname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.teamname
     *
     * @param teamname the value for ordermain.teamname
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setTeamname(String teamname) {
        this.teamname = teamname == null ? null : teamname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.receiveTargetID
     *
     * @return the value of ordermain.receiveTargetID
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public Integer getReceiveTargetID() {
        return receiveTargetID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.receiveTargetID
     *
     * @param receiveTargetID the value for ordermain.receiveTargetID
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setReceiveTargetID(Integer receiveTargetID) {
        this.receiveTargetID = receiveTargetID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.status
     *
     * @return the value of ordermain.status
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.status
     *
     * @param state the value for ordermain.state
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.orderFrom
     *
     * @return the value of ordermain.orderFrom
     *
     * @mbg.generated Sat Apr 14 00:26:17 CST 2018
     */
    public Integer getOrderFrom() {
        return orderFrom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.orderFrom
     *
     * @param orderFrom the value for ordermain.orderFrom
     *
     * @mbg.generated Sat Apr 14 00:26:17 CST 2018
     */
    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.rentOutTypeId
     *
     * @return the value of ordermain.rentOutTypeId
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public Integer getRentOutTypeId() {
        return rentOutTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.rentOutTypeId
     *
     * @param rentOutTypeId the value for ordermain.rentOutTypeId
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public void setRentOutTypeId(Integer rentOutTypeId) {
        this.rentOutTypeId = rentOutTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.rentOutTypeName
     *
     * @return the value of ordermain.rentOutTypeName
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public String getRentOutTypeName() {
        return rentOutTypeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.rentOutTypeName
     *
     * @param rentOutTypeName the value for ordermain.rentOutTypeName
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setRentOutTypeName(String rentOutTypeName) {
        this.rentOutTypeName = rentOutTypeName == null ? null : rentOutTypeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.LoginFlag
     *
     * @return the value of ordermain.LoginFlag
     *
     * @mbg.generated Sat Apr 14 00:26:17 CST 2018
     */
    public Integer getLoginFlag() {
        return loginFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.LoginFlag
     *
     * @param loginFlag the value for ordermain.LoginFlag
     *
     * @mbg.generated Sat Apr 14 00:26:17 CST 2018
     */
    public void setLoginFlag(Integer loginFlag) {
        this.loginFlag = loginFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.stayNumber
     *
     * @return the value of ordermain.stayNumber
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public Integer getStayNumber() {
        return stayNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.stayNumber
     *
     * @param stayNumber the value for ordermain.stayNumber
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setStayNumber(Integer stayNumber) {
        this.stayNumber = stayNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.predetermineDay
     *
     * @return the value of ordermain.predetermineDay
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public Integer getPredetermineDay() {
        return predetermineDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.predetermineDay
     *
     * @param predetermineDay the value for ordermain.predetermineDay
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setPredetermineDay(Integer predetermineDay) {
        this.predetermineDay = predetermineDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.deposit
     *
     * @return the value of ordermain.deposit
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public Float getDeposit() {
        return deposit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.deposit
     *
     * @param deposit the value for ordermain.deposit
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setDeposit(Float deposit) {
        this.deposit = deposit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.payWayID
     *
     * @return the value of ordermain.payWayID
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public Integer getPayWayID() {
        return payWayID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.payWayID
     *
     * @param payWayID the value for ordermain.payWayID
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setPayWayID(Integer payWayID) {
        this.payWayID = payWayID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.payWayName
     *
     * @return the value of ordermain.payWayName
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public String getPayWayName() {
        return payWayName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.payWayName
     *
     * @param payWayName the value for ordermain.payWayName
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName == null ? null : payWayName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.arriveTime
     *
     * @return the value of ordermain.arriveTime
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getArriveTime() {
        return arriveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.arriveTime
     *
     * @param arriveTime the value for ordermain.arriveTime
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.roomId
     *
     * @return the value of ordermain.roomId
     *
     * @mbg.generated Sat Apr 14 15:51:16 CST 2018
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.roomId
     *
     * @param roomId the value for ordermain.roomId
     *
     * @mbg.generated Sat Apr 14 15:51:16 CST 2018
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.roomNumber
     *
     * @return the value of ordermain.roomNumber
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.roomNumber
     *
     * @param roomNumber the value for ordermain.roomNumber
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber == null ? null : roomNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.guestRoomLevelName
     *
     * @return the value of ordermain.guestRoomLevelName
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public String getGuestRoomLevelName() {
        return guestRoomLevelName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.guestRoomLevelName
     *
     * @param guestRoomLevelName the value for ordermain.guestRoomLevelName
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setGuestRoomLevelName(String guestRoomLevelName) {
        this.guestRoomLevelName = guestRoomLevelName == null ? null : guestRoomLevelName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.roomAmount
     *
     * @return the value of ordermain.roomAmount
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public Integer getRoomAmount() {
        return roomAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.roomAmount
     *
     * @param roomAmount the value for ordermain.roomAmount
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public void setRoomAmount(Integer roomAmount) {
        this.roomAmount = roomAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.orderTime
     *
     * @return the value of ordermain.orderTime
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.orderTime
     *
     * @param orderTime the value for ordermain.orderTime
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.registerTime
     *
     * @return the value of ordermain.registerTime
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.registerTime
     *
     * @param registerTime the value for ordermain.registerTime
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.payTime
     *
     * @return the value of ordermain.payTime
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.payTime
     *
     * @param payTime the value for ordermain.payTime
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.billUnitID
     *
     * @return the value of ordermain.billUnitID
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public Integer getBillUnitID() {
        return billUnitID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.billUnitID
     *
     * @param billUnitID the value for ordermain.billUnitID
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public void setBillUnitID(Integer billUnitID) {
        this.billUnitID = billUnitID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.billUnitName
     *
     * @return the value of ordermain.billUnitName
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public String getBillUnitName() {
        return billUnitName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.billUnitName
     *
     * @param billUnitName the value for ordermain.billUnitName
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public void setBillUnitName(String billUnitName) {
        this.billUnitName = billUnitName == null ? null : billUnitName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.passengerTypeName
     *
     * @return the value of ordermain.passengerTypeName
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public String getPassengerTypeName() {
        return passengerTypeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.passengerTypeName
     *
     * @param passengerTypeName the value for ordermain.passengerTypeName
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public void setPassengerTypeName(String passengerTypeName) {
        this.passengerTypeName = passengerTypeName == null ? null : passengerTypeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.passengerTypeID
     *
     * @return the value of ordermain.passengerTypeID
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public Integer getPassengerTypeID() {
        return passengerTypeID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.passengerTypeID
     *
     * @param passengerTypeID the value for ordermain.passengerTypeID
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public void setPassengerTypeID(Integer passengerTypeID) {
        this.passengerTypeID = passengerTypeID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.sumConst
     *
     * @return the value of ordermain.sumConst
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public Float getSumConst() {
        return sumConst;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.sumConst
     *
     * @param sumConst the value for ordermain.sumConst
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setSumConst(Float sumConst) {
        this.sumConst = sumConst;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.timestamp
     *
     * @return the value of ordermain.timestamp
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.timestamp
     *
     * @param timestamp the value for ordermain.timestamp
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.changingRoomNumber
     *
     * @return the value of ordermain.changingRoomNumber
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public int getChangingRoomNumber() {
        return changingRoomNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.changingRoomNumber
     *
     * @param changingRoomNumber the value for ordermain.changingRoomNumber
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public void setChangingRoomNumber(int changingRoomNumber) {
        this.changingRoomNumber = changingRoomNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.changRoomMoney
     *
     * @return the value of ordermain.changRoomMoney
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public Double getChangRoomMoney() {
        return changRoomMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.changRoomMoney
     *
     * @param changRoomMoney the value for ordermain.changRoomMoney
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public void setChangRoomMoney(Double changRoomMoney) {
        this.changRoomMoney = changRoomMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordermain.del_flag
     *
     * @return the value of ordermain.del_flag
     *
     * @mbg.generated Fri Apr 13 16:17:05 CST 2018
     */
    public Integer getDel_flag() {
        return del_flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordermain.delFlag
     *
     * @param del_flag the value for ordermain.del_flag
     *
     * @mbg.generated Thu Apr 12 17:56:44 CST 2018
     */
    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }
    @Override
    public String toString() {
        return "Ordermain{" +
                "ordID='" + ordID + '\'' +
                ", passengerOrReceiveID=" + passengerOrReceiveID +
                ", commodityName='" + commodityName + '\'' +
                ", name='" + name + '\'' +
                ", teamname='" + teamname + '\'' +
                ", receiveTargetID=" + receiveTargetID +
                ", status=" + state +
                ", rentOutTypeName=" + rentOutTypeName +
                ", stayNumber=" + stayNumber +
                ", predetermineDay='" + predetermineDay + '\'' +
                ", deposit=" + deposit +
                ", payWayID=" + payWayID +
                ", payWayName='" + payWayName + '\'' +
                ", arriveTime=" + arriveTime +
                ", roomNumber='" + roomNumber + '\'' +
                ", guestRoomLevelName='" + guestRoomLevelName + '\'' +
                ", orderTime=" + orderTime +
                ", registerTime=" + registerTime +
                ", payTime=" + payTime +
                ", sumConst=" + sumConst +
                ", delFlag=" + del_flag +
                '}';
    }
}