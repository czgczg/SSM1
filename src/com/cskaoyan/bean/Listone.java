package com.cskaoyan.bean;

/**
 * author:czg
 * 查属性表的id对应的下拉框的值
 */
public class Listone {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column listone.id
     *
     * @mbg.generated Thu Apr 12 14:05:27 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column listone.attributeID
     *
     * @mbg.generated Thu Apr 12 14:05:27 CST 2018
     */
    private Integer attributeID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column listone.attributeDetailsName
     *
     * @mbg.generated Thu Apr 12 14:05:27 CST 2018
     */
    private String attributeDetailsName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column listone.del_flag
     *
     * @mbg.generated Thu Apr 12 14:05:27 CST 2018
     */
    private Integer del_flag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column listone.id
     *
     * @return the value of listone.id
     *
     * @mbg.generated Thu Apr 12 14:05:27 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column listone.id
     *
     * @param id the value for listone.id
     *
     * @mbg.generated Thu Apr 12 14:05:27 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column listone.attributeID
     *
     * @return the value of listone.attributeID
     *
     * @mbg.generated Thu Apr 12 14:05:27 CST 2018
     */
    public Integer getAttributeID() {
        return attributeID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column listone.attributeID
     *
     * @param attributeID the value for listone.attributeID
     *
     * @mbg.generated Thu Apr 12 14:05:27 CST 2018
     */
    public void setAttributeID(Integer attributeID) {
        this.attributeID = attributeID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column listone.attributeDetailsName
     *
     * @return the value of listone.attributeDetailsName
     *
     * @mbg.generated Thu Apr 12 14:05:27 CST 2018
     */
    public String getAttributeDetailsName() {
        return attributeDetailsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column listone.attributeDetailsName
     *
     * @param attributeDetailsName the value for listone.attributeDetailsName
     *
     * @mbg.generated Thu Apr 12 14:05:27 CST 2018
     */
    public void setAttributeDetailsName(String attributeDetailsName) {
        this.attributeDetailsName = attributeDetailsName == null ? null : attributeDetailsName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column listone.del_flag
     *
     * @return the value of listone.del_flag
     *
     * @mbg.generated Thu Apr 12 14:05:27 CST 2018
     */
    public Integer getDel_flag() {
        return del_flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column listone.del_flag
     *
     * @param del_flag the value for listone.del_flag
     *
     * @mbg.generated Thu Apr 12 14:05:27 CST 2018
     */
    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }


    @Override
    public String toString() {
        return "Listone{" +
                "id=" + id +
                ", attributeID=" + attributeID +
                ", attributeDetailsName='" + attributeDetailsName + '\'' +
                ", del_flag=" + del_flag +
                '}';
    }
}