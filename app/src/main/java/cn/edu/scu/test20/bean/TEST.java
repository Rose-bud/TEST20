package cn.edu.scu.test20.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class TEST extends BmobObject {
    private String Name;
    private String StudentID;
    private String School;
    private String Password;
    private String Phone;
    private BmobFile HeadPic;
    private Integer Point;

    public TEST(){
        this.setTableName("User");
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        this.Name = name;
    }
    public String getStudentID() {
        return StudentID;
    }
    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }
    public String getSchool() {
        return School;
    }
    public void setSchool(String School) {
        this.School = School;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }
    public String getPhone() {
        return Phone;
    }
    public void setPhone(String Phone) {
        this.Phone = Phone;
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
