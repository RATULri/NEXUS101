package nexus101.network.downloads;

import java.util.List;

import nexus101.network.models.GroupsInfo;

public interface GroupInfoDownloadCallBack {
    void onGroupInfoDownloadSuccess(List<GroupsInfo> groupsInfo);
    void onGroupInfoDownloadError();
}
