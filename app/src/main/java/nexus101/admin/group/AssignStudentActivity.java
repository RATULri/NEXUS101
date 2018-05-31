package nexus101.admin.group;

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
import nexus101.network.downloads.GroupDownload;
import nexus101.network.downloads.StudentDownload;
import nexus101.network.downloads.TeacherDownload;
import nexus101.network.downloads.callback.GroupInfoDownloadCallBack;
import nexus101.network.downloads.callback.StudentInfoDownloadCallBack;
import nexus101.network.models.GroupInfo;
import nexus101.network.models.Student;
import nexus101.network.models.Teacher;
import nexus101.network.uploads.StudentGroupUpload;
import nexus101.network.uploads.callback.StudentGroupInsertCallback;

public class AssignStudentActivity extends AppCompatActivity implements View.OnClickListener, StudentInfoDownloadCallBack, GroupInfoDownloadCallBack, StudentGroupInsertCallback {

    private Spinner spinner_group, spinner_student;
    private Button assign;
    private List<GroupInfo> groups;
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign_student);

        initialize();
    }

    private void initialize() {
        spinner_group = findViewById(R.id.spinner_group);
        spinner_student = findViewById(R.id.spinner_student);
        assign = findViewById(R.id.assign);
        assign.setOnClickListener(this);

        new GroupDownload(this).run();
        new StudentDownload(this).run();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.assign){
            int student_id = students.get(spinner_student.getSelectedItemPosition()).getStudentInfo().getId();
            int group_id = groups.get(spinner_group.getSelectedItemPosition()).getId();

            new StudentGroupUpload(this).run(student_id, group_id);
        }
    }

    @Override
    public void onStudentInfoDownloadSuccess(List<Student> studentList) {
        students = studentList;
        List<String> student_names = new ArrayList<>();
        for(Student student : students){
            student_names.add(student.getUserInfo().getName()+"/n"+student.getStudentInfo().getRollNumber());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, student_names);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_student.setAdapter(arrayAdapter);
    }

    @Override
    public void onStudentInfoDownloadError() {

    }

    @Override
    public void onGroupInfoDownloadSuccess(List<GroupInfo> groupList) {
        groups = groupList;
        List<String> group_names = new ArrayList<>();
        for(GroupInfo groupInfo : groups){
            group_names.add(groupInfo.getGroupName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, group_names);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_group.setAdapter(arrayAdapter);
    }

    @Override
    public void onGroupInfoDownloadError() {

    }

    @Override
    public void onInsertSuccess() {
        Toast.makeText(getApplicationContext(),"Student assigned", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInsertError() {
        Toast.makeText(getApplicationContext(),"Student is not assigned", Toast.LENGTH_SHORT).show();
    }
}
