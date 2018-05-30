package nexus101.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseInfo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("course_code")
    @Expose
    private String courseCode;
    @SerializedName("group_id")
    @Expose
    private Integer groupId;
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
    public CourseInfo() {
    }

    /**
     *
     * @param updatedAt
     * @param id
     * @param groupId
     * @param createdAt
     * @param courseCode
     * @param courseName
     */
    public CourseInfo(Integer id, String courseName, String courseCode, Integer groupId, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.groupId = groupId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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