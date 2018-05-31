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
import android.util.Log;
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
import nexus101.listeners.GroupItemClickListener;
import nexus101.network.downloads.GroupDownload;
import nexus101.network.downloads.callback.GroupInfoDownloadCallBack;
import nexus101.network.models.GroupInfo;
import nexus101.student.StudentHomeActivity;

public class AdminGroupActivity extends AppCompatActivity implements GroupInfoDownloadCallBack, GroupItemClickListener{

    private TextView mTextMessage;
    private BottomNavigationView navigation;
    private RecyclerView recyclerView;
    private GroupListAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ProgressDialog mProgressDialog;
    private FabSpeedDial fabSpeedDial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_group);

        recyclerView = (RecyclerView) findViewById(R.id.rv_group);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        new GroupDownload(AdminGroupActivity.this).run();

        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setBottomNav();
        setFabOptions();
    }

    private void setFabOptions() {
        fabSpeedDial = (FabSpeedDial)findViewById(R.id.fabGroupMenu);
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
    }

    private void setBottomNav() {
        navigation.setSelectedItemId(R.id.navigation_group);
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
    public void onGroupInfoDownloadSuccess(List<GroupInfo> groupInfo) {
        Log.d("Test", String.valueOf(groupInfo.size()));
        adapter = new GroupListAdapter(this, groupInfo, this);
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
    public void onItemClick(GroupInfo groupInfo) {
        Toast.makeText(AdminGroupActivity.this, "Groups", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(getApplicationContext(), StudentProfileEditActivity.class);
        //startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
