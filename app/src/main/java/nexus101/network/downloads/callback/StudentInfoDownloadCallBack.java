package nexus101.network.downloads.callback;

import java.util.List;

import nexus101.network.models.Student;

public interface StudentInfoDownloadCallBack {
    void onStudentInfoDownloadSuccess(List<Student> students);
    void onStudentInfoDownloadError();
}
