package nexus101.network.responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import nexus101.network.models.GroupInfo;

public class GroupResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("group_info")
    @Expose
    private List<GroupInfo> groupInfo = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GroupResponse() {
    }

    /**
     *
     * @param message
     * @param status
     * @param groupInfo
     */
    public GroupResponse(String message, Integer status, List<GroupInfo> groupInfo) {
        super();
        this.message = message;
        this.status = status;
        this.groupInfo = groupInfo;
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

    public List<GroupInfo> getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(List<GroupInfo> groupInfo) {
        this.groupInfo = groupInfo;
    }

}