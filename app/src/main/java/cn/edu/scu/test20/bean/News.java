package cn.edu.scu.test20.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class News extends BmobObject {
    private Integer NewsID;
    private String Content;
    private String Abstract;
    private String Title;
    private String Type;
    private Integer ImageID;

    public Integer getNewsID() {
        return NewsID;
    }
    public String getContent() {
        return Content;
    }
    public String Abstract() {
        return Abstract;
    }
    public String getTitle() {
        return Title;
    }
    public String getType() {
        return Type;
    }
    public Integer getImageID() {
        return ImageID;
    }

}
