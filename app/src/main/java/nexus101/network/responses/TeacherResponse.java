package nexus101.network.responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import nexus101.network.models.Teacher;

public class TeacherResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("teacher")
    @Expose
    private List<Teacher> teacher = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public TeacherResponse() {
    }

    /**
     *
     * @param message
     * @param status
     * @param teacher
     */
    public TeacherResponse(String message, Integer status, List<Teacher> teacher) {
        super();
        this.message = message;
        this.status = status;
        this.teacher = teacher;
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

    public List<Teacher> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<Teacher> teacher) {
        this.teacher = teacher;
    }

}