package nexus101.admin.group;

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
import nexus101.adapters.GroupListAdapter;
import nexus101.admin.course.AdminCourseActivity;
import nexus101.admin.AdminProfileActivity;
import nexus101.admin.student.AdminStudentAccountActivity;
import nexus101.admin.teacher.AdminTeacherAccountActivity;
import nexus101.listeners.CourseItemClickListener;
import nexus101.listeners.GroupItemClickListener;
import nexus101.network.downloads.CourseDownload;
import nexus101.network.downloads.CourseInfoDownloadCallBack;
import nexus101.network.downloads.GroupDownload;
import nexus101.network.downloads.GroupInfoDownloadCallBack;
import nexus101.network.models.CoursesInfo;
import nexus101.network.models.GroupsInfo;

public class AdminGroupActivity extends AppCompatActivity implements GroupInfoDownloadCallBack, GroupItemClickListener{

    private TextView mTextMessage;
    private BottomNavigationView navigation;

    private RecyclerView recyclerView;
    private GroupListAdapter adapter;
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
        setContentView(R.layout.activity_admin_group);


        FabSpeedDial fabSpeedDial = (FabSpeedDial)findViewById(R.id.fabGroupMenu);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                //Toast.makeText(AdminGroupActivity.this, ""+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), CreateGroupActivity.class);
                startActivity(intent);
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rv_group);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        new GroupDownload(AdminGroupActivity.this).run();

        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setBottomNav();
        navigation.setSelectedItemId(R.id.navigation_student_account);
    }

    private void setBottomNav() {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_group:
                        Toast.makeText(AdminGroupActivity.this, "Groups", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_course:
                        Intent intent = new Intent(getApplicationContext(), AdminCourseActivity.class);
                        startActivity(intent);
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
    public void onGroupInfoDownloadSuccess(List<GroupsInfo> groupsInfo) {
        adapter = new GroupListAdapter(this, groupsInfo, this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        mProgressDialog.dismiss();
    }

    @Override
    public void onGroupInfoDownloadError() {
        mProgressDialog.dismiss();
    }

    @Override
    public void onItemClick(GroupsInfo groupsInfo) {
        Toast.makeText(AdminGroupActivity.this, "Groups", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(getApplicationContext(), StudentProfileEditActivity.class);
        //startActivity(intent);
    }

}
