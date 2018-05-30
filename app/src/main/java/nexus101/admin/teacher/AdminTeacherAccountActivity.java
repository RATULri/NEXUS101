package nexus101.admin.teacher;

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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import nexus101.R;
import nexus101.adapters.TeacherListAdapter;
import nexus101.admin.AdminProfileActivity;
import nexus101.admin.course.AdminCourseActivity;
import nexus101.admin.group.AdminGroupActivity;
import nexus101.admin.student.AdminStudentAccountActivity;
import nexus101.listeners.TeacherItemClickListener;
import nexus101.network.downloads.TeacherDownload;
import nexus101.network.downloads.callback.TeacherInfoDownloadCallBack;
import nexus101.network.models.Teacher;

public class AdminTeacherAccountActivity extends AppCompatActivity implements TeacherInfoDownloadCallBack, TeacherItemClickListener{

    private TextView mTextMessage;
    private BottomNavigationView navigation;

    private RecyclerView recyclerView;
    private TeacherListAdapter adapter;
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
        setContentView(R.layout.activity_admin_teacher_account);


        //Create New Teacher Account
        Button create = findViewById(R.id.create_teacher);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateNewTeacherActivity.class);
                startActivity(intent);
            }
        });

        //Call the teacher download
        recyclerView = (RecyclerView) findViewById(R.id.rv_teacher);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        new TeacherDownload(AdminTeacherAccountActivity.this).run();

        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setBottomNav();
        navigation.setSelectedItemId(R.id.navigation_teacher_account);
    }

    private void setBottomNav() {
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
                        intent = new Intent(getApplicationContext(), AdminCourseActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_student_account:
                        intent = new Intent(getApplicationContext(), AdminStudentAccountActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_teacher_account:
                        Toast.makeText(AdminTeacherAccountActivity.this, "Teacher Accounts", Toast.LENGTH_SHORT).show();
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
    public void onTeacherInfoDownloadSuccess(List<Teacher> teachers) {
        adapter = new TeacherListAdapter(this, teachers, this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        mProgressDialog.dismiss();
    }

    @Override
    public void onTeacherInfoDownloadError() {
        mProgressDialog.dismiss();
    }

    @Override
    public void onItemClick(Teacher teacher) {
        Intent intent = new Intent(getApplicationContext(), TeacherProfileEditActivity.class);
        startActivity(intent);
    }

}
