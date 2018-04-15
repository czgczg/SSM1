package com.cskaoyan.bean;

public class Deposit {
    private int id;
    private String depositRegisterTime;
    private double deposit;
    private String depositPayWayName;
    private String receiveTargeTypeName;
    private String ordId;
    private String receiveTeamName;
    private int receiveTargetID;
    private String roomNumber;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getReceiveTeamName() {
        return receiveTeamName;
    }

    public void setReceiveTeamName(String receiveTeamName) {
        this.receiveTeamName = receiveTeamName;
    }

    public int getReceiveTargetID() {
        return receiveTargetID;
    }

    public void setReceiveTargetID(int receiveTargetID) {
        this.receiveTargetID = receiveTargetID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepositRegisterTime() {
        return depositRegisterTime;
    }

    public void setDepositRegisterTime(String depositRegisterTime) {
        this.depositRegisterTime = depositRegisterTime;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public String getDepositPayWayName() {
        return depositPayWayName;
    }

    public void setDepositPayWayName(String depositPayWayName) {
        this.depositPayWayName = depositPayWayName;
    }

    public String getReceiveTargeTypeName() {
        return receiveTargeTypeName;
    }

    public void setReceiveTargeTypeName(String receiveTargeTypeName) {
        this.receiveTargeTypeName = receiveTargeTypeName;
    }

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", depositRegisterTime='" + depositRegisterTime + '\'' +
                ", deposit=" + deposit +
                ", depositPayWayName='" + depositPayWayName + '\'' +
                ", receiveTargeTypeName='" + receiveTargeTypeName + '\'' +
                ", ordId='" + ordId + '\'' +
                '}';
    }
}
