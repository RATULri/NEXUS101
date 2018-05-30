package nexus101.admin.course;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import nexus101.R;
import nexus101.adapters.CourseListAdapter;
import nexus101.admin.AdminProfileActivity;
import nexus101.admin.student.AdminStudentAccountActivity;
import nexus101.admin.teacher.AdminTeacherAccountActivity;
import nexus101.admin.group.AdminGroupActivity;
import nexus101.listeners.CourseItemClickListener;
import nexus101.network.downloads.CourseDownload;
import nexus101.network.downloads.callback.CourseInfoDownloadCallBack;
import nexus101.network.models.CourseInfo;

public class AdminCourseActivity extends AppCompatActivity implements CourseInfoDownloadCallBack, CourseItemClickListener{

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
                case R.id.navigation_group:
                    mTextMessage.setText("Group");
                    return true;
                case R.id.navigation_course:
                    mTextMessage.setText("Course");
                    return true;
                case R.id.navigation_student_account:
                    mTextMessage.setText("Student Acc");
                    return true;
                case R.id.navigation_teacher_account:
                    mTextMessage.setText("Teacher Acc");
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText("Profile");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_course);


        FabSpeedDial fabSpeedDial = (FabSpeedDial)findViewById(R.id.fabCourseMenu);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                Toast.makeText(AdminCourseActivity.this, ""+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.rv_course);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        new CourseDownload(AdminCourseActivity.this).run();

        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setBottomNav();
    }

    private void setBottomNav() {
        navigation.setSelectedItemId(R.id.navigation_student_account);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_group:
                        Intent intent = new Intent(getApplicationContext(), AdminGroupActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_course:
                        Toast.makeText(AdminCourseActivity.this, "Courses", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_student_account:
                        intent = new Intent(getApplicationContext(), AdminStudentAccountActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_teacher_account:
                        intent = new Intent(getApplicationContext(), AdminTeacherAccountActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_profile:
                        intent = new Intent(getApplicationContext(),AdminProfileActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
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
    public void onItemClick(CourseInfo courseInfo) {
        //Intent intent = new Intent(getApplicationContext(), StudentProfileEditActivity.class);
        //startActivity(intent);
    }

}
