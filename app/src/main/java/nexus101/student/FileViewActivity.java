package nexus101.student;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nexus101.NotificationActivity;
import nexus101.R;
import nexus101.adapters.FileListAdapter;
import nexus101.network.downloads.FileDownload;
import nexus101.network.downloads.FileInfoDownloadCallBack;
import nexus101.network.models.CourseInfo;
import nexus101.network.models.FileInfo;
import nexus101.network.uploads.StudyMaterialUpload;
import nexus101.network.uploads.callback.FileInsertCallback;
import nexus101.teacher.TeacherAttendanceCourseSelectActivity;
import nexus101.teacher.TeacherFileViewActivity;
import nexus101.teacher.TeacherHomeActivity;
import nexus101.teacher.TeacherProfileActivity;

public class FileViewActivity extends AppCompatActivity implements FileInfoDownloadCallBack {

    private TextView mTextMessage;
    private BottomNavigationView navigation;
    private ProgressDialog mProgressDialog;
    private CourseInfo courseInfo;
    private RecyclerView recyclerView;
    private FileListAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

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
                /*case R.id.navigation_notification:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;*/
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
        setContentView(R.layout.activity_student_file_view);
        getSupportActionBar().setTitle("Nexus 101");

        courseInfo = (CourseInfo) getIntent().getSerializableExtra("course");

        setTitle(courseInfo.getCourseName());

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        mTextMessage = (TextView) findViewById(R.id.message);
        setBottomNav();

        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        new FileDownload(this).run(courseInfo.getId());
    }

    private void setBottomNav() {
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
                        Toast.makeText(FileViewActivity.this, "StudentFileActivity", Toast.LENGTH_SHORT).show();
                        break;
                    /*case R.id.navigation_notification:
                        intent = new Intent(getApplicationContext(), NotificationActivity.class);
                        startActivity(intent);
                        break;*/
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
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onFileInfoDownloadSuccess(List<FileInfo> fileInfo) {
        adapter = new FileListAdapter(this, fileInfo);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        mProgressDialog.dismiss();
    }

    @Override
    public void onFileInfoDownloadError() {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "No files found", Toast.LENGTH_SHORT).show();
    }
}
