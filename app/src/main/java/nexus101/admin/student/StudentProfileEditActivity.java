package nexus101.admin.student;

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
import nexus101.admin.teacher.AdminTeacherAccountActivity;
import nexus101.admin.course.AdminCourseActivity;
import nexus101.admin.group.AdminGroupActivity;
import nexus101.network.models.Student;

public class StudentProfileEditActivity extends AppCompatActivity implements View.OnClickListener {

    private String name;
    private String email;
    private String address;
    private String dateOfBirth;
    private String bloodGroup;
    private String rollNo;
    private String registrationNo;
    private String session;
    private String hall;

    private EditText et_name;
    private EditText et_email;
    private EditText et_address;
    private EditText et_dateOfBirth;
    private EditText et_bloodGroup;
    private EditText et_rollNo;
    private EditText et_registrationNo;
    private EditText et_session;
    private EditText et_hall;

    private Button bt_save;
    private ImageButton edit;

    private TextView mTextMessage;
    private BottomNavigationView navigation;
    private Student student;

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
        setContentView(R.layout.activity_admin_student_profile_edit);

        student = (Student) getIntent().getSerializableExtra("student");

        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setBottomNav();
        navigation.setSelectedItemId(R.id.navigation_student_account);
        initialize();
        setStudentInfo(student);
    }

    private void setStudentInfo(Student student) {
        et_name.setText(student.getUserInfo().getName());
        et_email.setText(student.getUserInfo().getEmail());
        et_address.setText(student.getStudentInfo().getAddress());
        et_dateOfBirth.setText(student.getStudentInfo().getDateOfBirth());
        et_bloodGroup.setText(student.getStudentInfo().getBloodGroup());
        et_rollNo.setText(student.getStudentInfo().getRollNumber());
        et_registrationNo.setText(student.getStudentInfo().getRegistrationNumber());
        et_session.setText(student.getStudentInfo().getSession());
        et_hall.setText(student.getStudentInfo().getAttachedHall());
    }

    private void initialize() {
        et_name = findViewById(R.id.name);
        et_email =  findViewById(R.id.email);
        et_address =  findViewById(R.id.address);
        et_dateOfBirth =  findViewById(R.id.date_of_birth);
        et_bloodGroup =  findViewById(R.id.blood_group);
        et_rollNo =  findViewById(R.id.roll_no);
        et_registrationNo =  findViewById(R.id.reg_no);
        et_session =  findViewById(R.id.session);
        et_hall =  findViewById(R.id.hall);

        bt_save = (Button) findViewById(R.id.save_button);
        bt_save.setOnClickListener(this);

        edit = findViewById(R.id.imageButton);
        edit.setOnClickListener(this);
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
                        intent = new Intent(getApplicationContext(),AdminTeacherAccountActivity.class);
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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imageButton){
            et_name.setEnabled(true);
            et_email.setEnabled(true);
            et_dateOfBirth.setEnabled(true);
            et_address.setEnabled(true);
            et_bloodGroup.setEnabled(true);
            et_rollNo.setEnabled(true);
            et_registrationNo.setEnabled(true);
            et_session.setEnabled(true);
            et_hall.setEnabled(true);
            bt_save.setVisibility(View.VISIBLE);
        }
    }
}
