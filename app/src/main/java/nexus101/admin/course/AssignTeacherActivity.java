package nexus101.admin.course;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nexus101.R;
import nexus101.network.downloads.CourseDownload;
import nexus101.network.downloads.TeacherDownload;
import nexus101.network.downloads.callback.CourseInfoDownloadCallBack;
import nexus101.network.downloads.callback.TeacherInfoDownloadCallBack;
import nexus101.network.models.CourseInfo;
import nexus101.network.models.GroupInfo;
import nexus101.network.models.Teacher;
import nexus101.network.models.TeacherInfo;
import nexus101.network.uploads.CourseTeacherUpload;
import nexus101.network.uploads.callback.CourseTeacherInsertCallback;

public class AssignTeacherActivity extends AppCompatActivity implements View.OnClickListener, TeacherInfoDownloadCallBack, CourseInfoDownloadCallBack, CourseTeacherInsertCallback {

    private Spinner spinner_course, spinner_teacher;
    private int course_id, teacher_id;
    private Button assign;
    private List<CourseInfo> courses;
    private List<Teacher> teachers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign_teacher);

        initialize();
    }

    private void initialize() {
        spinner_course = findViewById(R.id.spinner_course);
        spinner_teacher = findViewById(R.id.spinner_teacher);
        assign = findViewById(R.id.assign);
        assign.setOnClickListener(this);

        new CourseDownload(this).run();
        new TeacherDownload(this).run();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.assign){
            int course_id = courses.get(spinner_course.getSelectedItemPosition()).getId();
            int teacher_id = teachers.get(spinner_teacher.getSelectedItemPosition()).getTeacherInfo().getId();

            new CourseTeacherUpload(this).run(course_id,teacher_id);
        }
    }

    @Override
    public void onTeacherInfoDownloadSuccess(List<Teacher> teacherLists) {
        teachers = teacherLists;
        List<String> teacher_names = new ArrayList<>();
        for(Teacher teacher : teachers){
            teacher_names.add(teacher.getUserInfo().getName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teacher_names);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_teacher.setAdapter(arrayAdapter);
    }

    @Override
    public void onTeacherInfoDownloadError() {

    }

    @Override
    public void onCourseInfoDownloadSuccess(List<CourseInfo> courseInfo) {
        courses = courseInfo;
        List<String> course_names = new ArrayList<>();
        for(CourseInfo course : courses){
            course_names.add(course.getCourseName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course_names);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_course.setAdapter(arrayAdapter);
    }

    @Override
    public void onCourseInfoDownloadError() {

    }

    @Override
    public void onInsertSuccess() {
        Toast.makeText(getApplicationContext(),"Teacher assigned", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), AdminCourseActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onInsertError() {
        Toast.makeText(getApplicationContext(),"Teacher not assigned", Toast.LENGTH_SHORT).show();
    }
}
