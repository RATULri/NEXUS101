package nexus101.network.responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import nexus101.network.models.FileInfo;

public class FileResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("file_info")
    @Expose
    private List<FileInfo> fileInfo = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public FileResponse() {
    }

    /**
     *
     * @param message
     * @param status
     * @param fileInfo
     */
    public FileResponse(String message, Integer status, List<FileInfo> fileInfo) {
        super();
        this.message = message;
        this.status = status;
        this.fileInfo = fileInfo;
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

    public List<FileInfo> getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(List<FileInfo> fileInfo) {
        this.fileInfo = fileInfo;
    }

}