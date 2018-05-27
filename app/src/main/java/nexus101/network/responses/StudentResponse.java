package nexus101.network.responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import nexus101.network.models.Student;

public class StudentResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("student")
    @Expose
    private List<Student> student = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public StudentResponse() {
    }

    /**
     *
     * @param message
     * @param student
     * @param status
     */
    public StudentResponse(String message, Integer status, List<Student> student) {
        super();
        this.message = message;
        this.status = status;
        this.student = student;
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

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

}