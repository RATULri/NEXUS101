package nexus101.teacher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nexus101.NotificationActivity;
import nexus101.R;
import nexus101.adapters.CourseListAdapter;
import nexus101.adapters.StudentAttendanceAdapter;
import nexus101.network.downloads.StudentDownloadByGroup;
import nexus101.network.downloads.callback.StudentInfoDownloadCallBack;
import nexus101.network.models.CourseInfo;
import nexus101.network.models.Student;
import nexus101.network.uploads.callback.AttendanceInsertCallback;
import nexus101.network.uploads.AttendanceUpload;
import nexus101.student.StudentHomeActivity;
import nexus101.student.StudentProfileActivity;

public class TeacherAttendanceTakingActivity extends AppCompatActivity implements StudentInfoDownloadCallBack, View.OnClickListener, AttendanceInsertCallback {

    private TextView mTextMessage;
    private BottomNavigationView navigation;

    private RecyclerView recyclerView;
    private StudentAttendanceAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ProgressDialog mProgressDialog;
    private CourseInfo courseInfo;
    private Button save;
    private TextView date;
    private String current_date;
    private int couser_id;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_attendance:
                    mTextMessage.setText(R.string.title_attendance);
                    return true;
                case R.id.navigation_notification:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_profile);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_attendance_taking);

        courseInfo = (CourseInfo) getIntent().getSerializableExtra("course");

        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setBottomNav();

        save = findViewById(R.id.save_button);
        save.setOnClickListener(this);

        date = findViewById(R.id.date_view);
        getDate();

        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        new StudentDownloadByGroup(this).run(courseInfo.getGroupId());
    }

    private void getDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        current_date = df.format(c);
        date.setText(current_date);
    }

    private void setBottomNav() {
        navigation.setSelectedItemId(R.id.navigation_attendance);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        Intent intent = new Intent(getApplicationContext(),TeacherHomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_attendance:
                        Toast.makeText(TeacherAttendanceTakingActivity.this, "Attendance", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_notification:
                        intent = new Intent(getApplicationContext(), NotificationActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_profile:
                        intent = new Intent(getApplicationContext(),TeacherProfileActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onStudentInfoDownloadSuccess(List<Student> students) {
        adapter = new StudentAttendanceAdapter(this, students);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        mProgressDialog.dismiss();
    }

    @Override
    public void onStudentInfoDownloadError() {
        mProgressDialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        List<Integer> student_id = new ArrayList<>();
        List<Integer> isPresent = new ArrayList<>();
        couser_id = courseInfo.getId();
        if (v.getId() == R.id.save_button){
            for(Student student : adapter.checkedStudents){
                student_id.add(student.getStudentInfo().getId());
                isPresent.add(1);
            }
            for(Student student : adapter.uncheckedStudents){
                student_id.add(student.getStudentInfo().getId());
                isPresent.add(0);
            }
            new AttendanceUpload(this).run(couser_id, student_id, isPresent, current_date);
        }
    }

    @Override
    public void onAttendanceInsertSuccess() {
        Toast.makeText(getApplicationContext(),"Attendance inserted successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), TeacherHomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAttendanceInsertError() {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), TeacherAttendanceCourseSelectActivity.class);
        startActivity(intent);
    }
}
