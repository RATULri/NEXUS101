package nexus101.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FileInfo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("file_name")
    @Expose
    private String fileName;
    @SerializedName("file_path")
    @Expose
    private String filePath;
    @SerializedName("course_id")
    @Expose
    private Integer courseId;
    @SerializedName("upload_date")
    @Expose
    private String uploadDate;
    @SerializedName("remove_date")
    @Expose
    private String removeDate;
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
    public FileInfo() {
    }

    /**
     *
     * @param updatedAt
     * @param id
     * @param createdAt
     * @param removeDate
     * @param fileName
     * @param filePath
     * @param courseId
     * @param uploadDate
     */
    public FileInfo(Integer id, String fileName, String filePath, Integer courseId, String uploadDate, String removeDate, String createdAt, String updatedAt) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.courseId = courseId;
        this.uploadDate = uploadDate;
        this.removeDate = removeDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(String removeDate) {
        this.removeDate = removeDate;
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