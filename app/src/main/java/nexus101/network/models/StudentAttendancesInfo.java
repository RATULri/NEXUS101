package nexus101.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentAttendancesInfo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("course_id")
    @Expose
    private Integer courseId;
    @SerializedName("student_id")
    @Expose
    private Integer studentId;
    @SerializedName("isPresent")
    @Expose
    private Integer isPresent;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public StudentAttendancesInfo() {
    }

    /**
     *
     * @param updatedAt
     * @param id
     * @param studentId
     * @param createdAt
     * @param courseId
     * @param date
     * @param isPresent
     */
    public StudentAttendancesInfo(Integer id, Integer courseId, Integer studentId, Integer isPresent, String date, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.isPresent = isPresent;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(Integer isPresent) {
        this.isPresent = isPresent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}