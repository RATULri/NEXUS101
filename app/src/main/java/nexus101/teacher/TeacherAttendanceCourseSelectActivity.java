package nexus101.teacher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import nexus101.NotificationActivity;
import nexus101.R;
import nexus101.adapters.CourseListAdapter;
import nexus101.listeners.CourseItemClickListener;
import nexus101.network.downloads.CourseDownloadByTeacher;
import nexus101.network.downloads.callback.CourseInfoDownloadCallBack;
import nexus101.network.models.CourseInfo;
import nexus101.student.StudentHomeActivity;
import nexus101.student.StudentProfileActivity;

public class TeacherAttendanceCourseSelectActivity extends AppCompatActivity implements CourseInfoDownloadCallBack, CourseItemClickListener {

    private TextView mTextMessage;
    private BottomNavigationView navigation;

    private RecyclerView recyclerView;
    private CourseListAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ProgressDialog mProgressDialog;


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
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_profile);
                    return true;
                /*case R.id.navigation_notification:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;*/
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_attendance);
        getSupportActionBar().setTitle("Attendance");

        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setBottomNav();


        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        SharedPreferences prefs = getSharedPreferences("nexus101", MODE_PRIVATE);
        int id = prefs.getInt("student_id", 0);

        if (id != 0){
            new CourseDownloadByTeacher(this).run(id);
        }

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
                        Toast.makeText(TeacherAttendanceCourseSelectActivity.this, "Teacher Attendance Activity", Toast.LENGTH_SHORT).show();
                        break;
                    /*case R.id.navigation_notification:
                        intent = new Intent(getApplicationContext(), NotificationActivity.class);
                        startActivity(intent);
                        break;*/
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
    public void onItemClick(CourseInfo courseInfo) {
        Intent intent = new Intent(getApplicationContext(), TeacherAttendanceTakingActivity.class);
        intent.putExtra("course", courseInfo);
        startActivity(intent);
    }

    @Override
    public void onCourseInfoDownloadSuccess(List<CourseInfo> courseInfo) {
        adapter = new CourseListAdapter(this, courseInfo, this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        mProgressDialog.dismiss();
    }

    @Override
    public void onCourseInfoDownloadError() {
        mProgressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), TeacherHomeActivity.class);
        startActivity(intent);
    }

}
