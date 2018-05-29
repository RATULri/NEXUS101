package nexus101.network.responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import nexus101.network.models.GroupsInfo;

public class GroupResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("groups_info")
    @Expose
    private List<GroupsInfo> groupsInfo = null;

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
     * @param groupsInfo
     */
    public GroupResponse(String message, Integer status, List<GroupsInfo> groupsInfo) {
        super();
        this.message = message;
        this.status = status;
        this.groupsInfo = groupsInfo;
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

    public List<GroupsInfo> getGroupsInfo() {
        return groupsInfo;
    }

    public void setGroupsInfo(List<GroupsInfo> groupsInfo) {
        this.groupsInfo = groupsInfo;
    }

}