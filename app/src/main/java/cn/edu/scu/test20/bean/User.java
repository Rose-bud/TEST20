package cn.edu.scu.test20.bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class User extends BmobUser {

    private String NickName;
    private String School;
    private BmobFile HeadPic;
    private Integer Point;

    public String getNickName() {
        return NickName;
    }
    public void setNickName(String NickName) {
        this.NickName = NickName;
    }
    public String getSchool() {
        return School;
    }
    public void setSchool(String School) {
        this.School = School;
    }
    public BmobFile getHeadPic() {
        return HeadPic;
    }
    public void setHeadPic(BmobFile HeadPic) {
        this.HeadPic = HeadPic;
    }
    public Integer getPoint() {
        return Point;
    }
    public void setPoint(Integer Point) {
        this.Point = Point;
    }
}
