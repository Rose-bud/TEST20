package cn.edu.scu.test20.bean;

import cn.bmob.v3.BmobObject;

public class Video extends BmobObject {
    private Integer VideoID;
    private String Url;
    private String Title;
    private String Type;

    public Integer getVideoID() {
        return VideoID;
    }
    public String getUrl() {
        return Url;
    }
    public String getTitle() {
        return Title;
    }
    public String getType() {
        return Type;
    }
}
