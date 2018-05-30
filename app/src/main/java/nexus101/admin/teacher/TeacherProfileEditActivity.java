package nexus101.admin.teacher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import nexus101.R;
import nexus101.admin.AdminProfileActivity;
import nexus101.admin.course.AdminCourseActivity;
import nexus101.admin.group.AdminGroupActivity;
import nexus101.admin.student.AdminStudentAccountActivity;
import nexus101.admin.student.StudentProfileEditActivity;
import nexus101.admin.teacher.AdminTeacherAccountActivity;
import nexus101.network.models.Teacher;
import nexus101.network.services.TeacherUpdateApiInterface;
import nexus101.network.uploads.StudentUpdate;
import nexus101.network.uploads.TeacherUpdate;
import nexus101.network.uploads.callback.TeacherUpdateCallback;

public class TeacherProfileEditActivity extends AppCompatActivity implements TeacherUpdateCallback, View.OnClickListener {

    private String name;
    private String email;
    private String phone;
    private String bloodGroup;
    private String designation;

    private EditText et_name;
    private EditText et_email;
    private EditText et_phone;
    private EditText et_bloodGroup;
    private EditText et_designation;

    private Button bt_save;
    private ImageButton edit;

    private TextView mTextMessage;
    private BottomNavigationView navigation;
    private Teacher teacher;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_teacher_profile_edit);

        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setBottomNav();
        initialize();
        setTeacherInfo(teacher);
    }

    private void setBottomNav() {
        navigation.setSelectedItemId(R.id.navigation_teacher_account);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
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
                        intent = new Intent(getApplicationContext(), AdminTeacherAccountActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_profile:
                        intent = new Intent(getApplicationContext(), AdminProfileActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    private void initialize() {
        et_name = findViewById(R.id.name);
        et_email = findViewById(R.id.email);
        et_phone = findViewById(R.id.phone);
        et_bloodGroup = findViewById(R.id.blood_group);
        et_designation = findViewById(R.id.designation);
        bt_save = (Button) findViewById(R.id.save_button);
        bt_save.setOnClickListener(this);

        edit = findViewById(R.id.imageButton);
        edit.setOnClickListener(this);
    }

    public void setTeacherInfo(Teacher teacherInfo) {
        et_name.setText(teacher.getUserInfo().getName());
        et_email.setText(teacher.getUserInfo().getEmail());
        et_phone.setText(teacher.getUserInfo().getPhoneNumber());
        et_bloodGroup.setText(teacher.getTeacherInfo().getBloodGroup());
        et_designation.setText(teacher.getTeacherInfo().getDesignation());
    }

    @Override
    public void onUpdateSuccess() {

    }

    @Override
    public void onUpdateError() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageButton) {
            et_name.setEnabled(true);
            et_bloodGroup.setEnabled(true);
            et_designation.setEnabled(true);
            et_phone.setEnabled(true);
            bt_save.setVisibility(View.VISIBLE);
        }

        if (v.getId() == R.id.save_button) {
            name = et_name.getText().toString();
            email = et_email.getText().toString();
            bloodGroup = et_bloodGroup.getText().toString();
            designation = et_designation.getText().toString();
            phone = et_phone.getText().toString();

            mProgressDialog = new ProgressDialog(TeacherProfileEditActivity.this);
            mProgressDialog.setMessage("Submitting teacher information...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

            new TeacherUpdate(this).run(teacher.getTeacherInfo().getId(), name, email, phone, bloodGroup, designation);
        }
    }
}