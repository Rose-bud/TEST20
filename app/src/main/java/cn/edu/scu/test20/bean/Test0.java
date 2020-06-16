package cn.edu.scu.test20.bean;

import cn.bmob.v3.BmobObject;

public class Test0 extends BmobObject {
    private Integer TestID;
    private String Data;
    private String Title;

    public Test0(){
        this.setTableName("Test");
    }

    public Integer getTestID() {
        return TestID;
    }
    public String getData() {
        return Data;
    }
    public String getTitle() {
        return Title;
    }
}
