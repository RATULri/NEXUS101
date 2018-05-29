package nexus101.network.responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import nexus101.network.models.StudentAttendancesInfo;

public class StudentAttendanceResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("student_attendances_info")
    @Expose
    private List<StudentAttendancesInfo> studentAttendancesInfo = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public StudentAttendanceResponse() {
    }

    /**
     *
     * @param message
     * @param status
     * @param studentAttendancesInfo
     */
    public StudentAttendanceResponse(String message, Integer status, List<StudentAttendancesInfo> studentAttendancesInfo) {
        super();
        this.message = message;
        this.status = status;
        this.studentAttendancesInfo = studentAttendancesInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<StudentAttendancesInfo> getStudentAttendancesInfo() {
        return studentAttendancesInfo;
    }

    public void setStudentAttendancesInfo(List<StudentAttendancesInfo> studentAttendancesInfo) {
        this.studentAttendancesInfo = studentAttendancesInfo;
    }

}