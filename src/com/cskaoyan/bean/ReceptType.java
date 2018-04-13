package com.cskaoyan.bean;

public class ReceptType {
    private int typeId;
    private String typeName;

    @Override
    public String toString() {
        return "ReceptType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
