package cn.edu.scu.test20.bean;

import cn.bmob.v3.BmobObject;

public class News extends BmobObject {
    private Integer NewsID;
    private String Content;
    private String Abstract;
    private String Title;
    private String Type;
    private String imageSrc;

    public Integer getNewsID() {
        return NewsID;
    }
    public String getContent() {
        return Content;
    }
    public String getAbstract() {
        return Abstract;
    }
    public String getTitle() {
        return Title;
    }
    public String getType() {
        return Type;
    }
    public String getimageSrc() {
        return imageSrc;
    }

}
