package nexus101.network.downloads;

import java.util.List;

import nexus101.network.models.Student;
import nexus101.network.models.Teacher;

public interface TeacherInfoDownloadCallBack {
    void onTeacherInfoDownloadSuccess(List<Teacher> teachers);
    void onTeacherInfoDownloadError();
}
