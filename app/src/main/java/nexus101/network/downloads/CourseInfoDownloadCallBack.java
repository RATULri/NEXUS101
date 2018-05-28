package nexus101.network.downloads;

import java.util.List;

import nexus101.network.models.CoursesInfo;

public interface CourseInfoDownloadCallBack {
    void onCourseInfoDownloadSuccess(List<CoursesInfo> coursesInfo);
    void onCourseInfoDownloadError();
}
