package com.cskaoyan.bean;

public class Ordcheckinpassenger {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordcheckinpassenger.id
     *
     * @mbg.generated Wed Apr 11 11:16:49 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordcheckinpassenger.ordId
     *
     * @mbg.generated Wed Apr 11 11:16:49 CST 2018
     */
    private String ordid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordcheckinpassenger.checkinPassengerId
     *
     * @mbg.generated Wed Apr 11 11:16:49 CST 2018
     */
    private Integer checkinpassengerid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordcheckinpassenger.del_flag
     *
     * @mbg.generated Wed Apr 11 11:16:49 CST 2018
     */
    private Integer delFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordcheckinpassenger.id
     *
     * @return the value of ordcheckinpassenger.id
     *
     * @mbg.generated Wed Apr 11 11:16:49 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordcheckinpassenger.id
     *
     * @param id the value for ordcheckinpassenger.id
     *
     * @mbg.generated Wed Apr 11 11:16:49 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordcheckinpassenger.ordId
     *
     * @return the value of ordcheckinpassenger.ordId
     *
     * @mbg.generated Wed Apr 11 11:16:49 CST 2018
     */
    public String getOrdid() {
        return ordid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordcheckinpassenger.ordId
     *
     * @param ordid the value for ordcheckinpassenger.ordId
     *
     * @mbg.generated Wed Apr 11 11:16:49 CST 2018
     */
    public void setOrdid(String ordid) {
        this.ordid = ordid == null ? null : ordid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordcheckinpassenger.checkinPassengerId
     *
     * @return the value of ordcheckinpassenger.checkinPassengerId
     *
     * @mbg.generated Wed Apr 11 11:16:49 CST 2018
     */
    public Integer getCheckinpassengerid() {
        return checkinpassengerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordcheckinpassenger.checkinPassengerId
     *
     * @param checkinpassengerid the value for ordcheckinpassenger.checkinPassengerId
     *
     * @mbg.generated Wed Apr 11 11:16:49 CST 2018
     */
    public void setCheckinpassengerid(Integer checkinpassengerid) {
        this.checkinpassengerid = checkinpassengerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordcheckinpassenger.del_flag
     *
     * @return the value of ordcheckinpassenger.del_flag
     *
     * @mbg.generated Wed Apr 11 11:16:49 CST 2018
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordcheckinpassenger.del_flag
     *
     * @param delFlag the value for ordcheckinpassenger.del_flag
     *
     * @mbg.generated Wed Apr 11 11:16:49 CST 2018
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}