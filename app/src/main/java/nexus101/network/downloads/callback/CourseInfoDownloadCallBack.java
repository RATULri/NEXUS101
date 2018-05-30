package nexus101.network.downloads.callback;

import java.util.List;

import nexus101.network.models.CourseInfo;

public interface CourseInfoDownloadCallBack {
    void onCourseInfoDownloadSuccess(List<CourseInfo> courseInfo);
    void onCourseInfoDownloadError();
}
