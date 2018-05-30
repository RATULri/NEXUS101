package nexus101.network.downloads.callback;

import java.util.List;

import nexus101.network.models.GroupInfo;

public interface GroupInfoDownloadCallBack {
    void onGroupInfoDownloadSuccess(List<GroupInfo> groupInfo);
    void onGroupInfoDownloadError();
}
