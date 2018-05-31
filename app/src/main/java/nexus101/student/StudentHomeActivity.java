package nexus101.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import nexus101.NotificationActivity;
import nexus101.R;
import nexus101.network.downloads.GroupDownloadByStudent;
import nexus101.network.downloads.callback.GroupInfoDownloadCallBack;
import nexus101.network.models.GroupInfo;

public class StudentHomeActivity extends AppCompatActivity implements GroupInfoDownloadCallBack, View.OnClickListener {

    private TextView mTextMessage;
    private BottomNavigationView navigation;
    private TextView sem;
    private Button btn_file;
    private int group_id;

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
        setContentView(R.layout.activity_student_home);

        sem = findViewById(R.id.current_semester);
        btn_file = findViewById(R.id.btn_file);
        btn_file.setOnClickListener(this);

        mTextMessage = findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setBottomNav();

        new GroupDownloadByStudent(this).run(1);
    }

    private void setBottomNav() {
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        Toast.makeText(StudentHomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_attendance:
                        Intent intent = new Intent(getApplicationContext(), StudentAttendanceCourseSelectActivity.class);
                        startActivity(intent);
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
    public void onGroupInfoDownloadSuccess(List<GroupInfo> groupInfo) {
        group_id = groupInfo.get(0).getId();
        sem.setText(groupInfo.get(0).getGroupName());
    }

    @Override
    public void onGroupInfoDownloadError() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_file){
            Intent intent = new Intent(StudentHomeActivity.this, StudentFileCourseSelectActivity.class);
            /*intent.putExtra("group_id", String.valueOf(group_id));*/
            startActivity(intent);
        }
    }
}