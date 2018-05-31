package nexus101.network.responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import nexus101.network.models.Attendance;

public class AttendanceResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("attendance")
    @Expose
    private List<Attendance> attendance = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public AttendanceResponse() {
    }

    /**
     *
     * @param message
     * @param status
     * @param attendance
     */
    public AttendanceResponse(String message, Integer status, List<Attendance> attendance) {
        super();
        this.message = message;
        this.status = status;
        this.attendance = attendance;
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

    public List<Attendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<Attendance> attendance) {
        this.attendance = attendance;
    }

}