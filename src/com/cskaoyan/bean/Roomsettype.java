package com.cskaoyan.bean;

public class Roomsettype {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column roomsettype.roomsetTypeId
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    private Integer far_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column roomsettype.roomsetTypeName
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    private String attributeDetailsName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column roomsettype.del_flag
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    private Integer del_flag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column roomsettype.roomsetTypeId
     *
     * @return the value of roomsettype.roomsetTypeId
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    public Integer getFar_id() {
        return far_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column roomsettype.roomsetTypeId
     *
     * @param far_id the value for roomsettype.roomsetTypeId
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    public void setFar_id(Integer far_id) {
        this.far_id = far_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column roomsettype.roomsetTypeName
     *
     * @return the value of roomsettype.roomsetTypeName
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    public String getAttributeDetailsName() {
        return attributeDetailsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column roomsettype.roomsetTypeName
     *
     * @param attributeDetailsName the value for roomsettype.roomsetTypeName
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    public void setAttributeDetailsName(String attributeDetailsName) {
        this.attributeDetailsName = attributeDetailsName == null ? null : attributeDetailsName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column roomsettype.del_flag
     *
     * @return the value of roomsettype.del_flag
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    public Integer getDel_flag() {
        return del_flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column roomsettype.del_flag
     *
     * @param del_flag the value for roomsettype.del_flag
     *
     * @mbg.generated Tue Apr 10 17:39:01 CST 2018
     */
    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }
}