package nexus101.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import nexus101.R;

public class TeacherAttendanceTakingActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_attendance_taking);

        mTextMessage = (TextView) findViewById(R.id.message);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), TeacherAttendanceCourseSelectActivity.class);
        startActivity(intent);
    }

}
