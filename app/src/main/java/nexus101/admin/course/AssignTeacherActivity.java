package nexus101.admin.course;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nexus101.R;

public class AssignTeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign_teacher);
        getSupportActionBar().setTitle("Nexus 101");
    }
}
