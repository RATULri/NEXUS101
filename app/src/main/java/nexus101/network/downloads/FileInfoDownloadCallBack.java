package nexus101.network.downloads;

import java.util.List;

import nexus101.network.models.FileInfo;

public interface FileInfoDownloadCallBack {
    void onFileInfoDownloadSuccess(List<FileInfo> fileInfo);
    void onFileInfoDownloadError();
}
