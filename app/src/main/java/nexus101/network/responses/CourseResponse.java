package nexus101.network.responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import nexus101.network.models.CoursesInfo;

public class CourseResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("courses_info")
    @Expose
    private List<CoursesInfo> coursesInfo = null;

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
     * @param coursesInfo
     */
    public CourseResponse(String message, Integer status, List<CoursesInfo> coursesInfo) {
        super();
        this.message = message;
        this.status = status;
        this.coursesInfo = coursesInfo;
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

    public List<CoursesInfo> getCoursesInfo() {
        return coursesInfo;
    }

    public void setCoursesInfo(List<CoursesInfo> coursesInfo) {
        this.coursesInfo = coursesInfo;
    }

}