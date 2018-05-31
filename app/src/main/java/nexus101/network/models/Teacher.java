package nexus101.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Teacher implements Serializable{

    @SerializedName("teacher_info")
    @Expose
    private TeacherInfo teacherInfo;
    @SerializedName("user_info")
    @Expose
    private UserInfo userInfo;

    /**
     * No args constructor for use in serialization
     *
     */
    public Teacher() {
    }

    /**
     *
     * @param teacherInfo
     * @param userInfo
     */
    public Teacher(TeacherInfo teacherInfo, UserInfo userInfo) {
        super();
        this.teacherInfo = teacherInfo;
        this.userInfo = userInfo;
    }

    public TeacherInfo getTeacherInfo() {
        return teacherInfo;
    }

    public void setTeacherInfo(TeacherInfo teacherInfo) {
        this.teacherInfo = teacherInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}