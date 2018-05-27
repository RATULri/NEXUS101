package nexus101.network.downloads;

import java.util.List;

import nexus101.network.models.Student;

public interface StudentInfoDownloadCallBack {
    void onStudentInfoDownloadSuccess(List<Student> studentss);
    void onStudentInfoDownloadError();
}
