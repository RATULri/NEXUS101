package nexus101.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attendance {

    @SerializedName("attendance_info")
    @Expose
    private AttendanceInfo attendanceInfo;
    @SerializedName("course_info")
    @Expose
    private CourseInfo courseInfo;

    /**
     * No args constructor for use in serialization
     *
     */
    public Attendance() {
    }

    /**
     *
     * @param attendanceInfo
     * @param courseInfo
     */
    public Attendance(AttendanceInfo attendanceInfo, CourseInfo courseInfo) {
        super();
        this.attendanceInfo = attendanceInfo;
        this.courseInfo = courseInfo;
    }

    public AttendanceInfo getAttendanceInfo() {
        return attendanceInfo;
    }

    public void setAttendanceInfo(AttendanceInfo attendanceInfo) {
        this.attendanceInfo = attendanceInfo;
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

}
