package nexus101.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student {

    @SerializedName("student_info")
    @Expose
    private StudentInfo studentInfo;
    @SerializedName("user_info")
    @Expose
    private UserInfo userInfo;

    /**
     * No args constructor for use in serialization
     *
     */
    public Student() {
    }

    /**
     *
     * @param studentInfo
     * @param userInfo
     */
    public Student(StudentInfo studentInfo, UserInfo userInfo) {
        super();
        this.studentInfo = studentInfo;
        this.userInfo = userInfo;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}