package cn.edu.scu.test20.bean;

import java.util.Date;

import cn.bmob.v3.BmobObject;

public class Version extends BmobObject {
    private Integer VersionID;
    private String Url;
    private Date Date;

    public Integer getVersionID() {
        return VersionID;
    }
    public String getUrl() {
        return Url;
    }
    public Date getDate() {
        return Date;
    }
}
