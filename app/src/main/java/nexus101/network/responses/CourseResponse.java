package nexus101.network.responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import nexus101.network.models.CourseInfo;

public class CourseResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("course_info")
    @Expose
    private List<CourseInfo> courseInfo = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public CourseResponse() {
    }

    /**
     *
     * @param message
     * @param status
     * @param courseInfo
     */
    public CourseResponse(String message, Integer status, List<CourseInfo> courseInfo) {
        super();
        this.message = message;
        this.status = status;
        this.courseInfo = courseInfo;
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

    public List<CourseInfo> getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(List<CourseInfo> courseInfo) {
        this.courseInfo = courseInfo;
    }

}