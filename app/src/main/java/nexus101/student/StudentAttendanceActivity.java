package nexus101.student;

import android.app.ProgressDialog;
import android.content.Intent;
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
import nexus101.adapters.AttendanceAdapter;
import nexus101.adapters.FileListAdapter;
import nexus101.network.downloads.StudentAttendanceDownload;
import nexus101.network.downloads.callback.StudentAttendanceInfoDownloadCallBack;
import nexus101.network.models.Attendance;
import nexus101.network.models.CourseInfo;
import nexus101.network.models.StudentAttendancesInfo;

public class StudentAttendanceActivity extends AppCompatActivity implements StudentAttendanceInfoDownloadCallBack {

    private TextView mTextMessage;
    private BottomNavigationView navigation;
    private CourseInfo courseInfo;

    private RecyclerView recyclerView;
    private AttendanceAdapter adapter;
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
        setContentView(R.layout.activity_attendance);

        courseInfo = (CourseInfo) getIntent().getSerializableExtra("course");

        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mTextMessage = findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setBottomNav();
        getAttendances();
    }

    private void getAttendances(){
        mProgressDialog = new ProgressDialog(StudentAttendanceActivity.this);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        new StudentAttendanceDownload(this).run(1, courseInfo.getId());
    }

    private void setBottomNav() {
        navigation.setSelectedItemId(R.id.navigation_attendance);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        Intent intent = new Intent(getApplicationContext(),StudentHomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_attendance:
                        Toast.makeText(StudentAttendanceActivity.this, "StudentAttendanceActivity", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_notification:
                        intent = new Intent(getApplicationContext(), NotificationActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_profile:
                        intent = new Intent(getApplicationContext(),StudentProfileActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onStudentAttendanceInfoDownloadSuccess(List<Attendance> attendances) {
        adapter = new AttendanceAdapter(this, attendances);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        mProgressDialog.dismiss();
    }

    @Override
    public void onStudentAttendanceInfoDownloadError() {
        mProgressDialog.dismiss();
    }
}
