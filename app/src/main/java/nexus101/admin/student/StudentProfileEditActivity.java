package nexus101.admin.student;

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
import android.widget.Toast;

import nexus101.R;
import nexus101.admin.AdminProfileActivity;
import nexus101.admin.teacher.AdminTeacherAccountActivity;
import nexus101.admin.course.AdminCourseActivity;
import nexus101.admin.group.AdminGroupActivity;
import nexus101.network.models.Student;
import nexus101.network.uploads.StudentUpdate;
import nexus101.network.uploads.callback.StudentUpdateCallback;

public class StudentProfileEditActivity extends AppCompatActivity implements View.OnClickListener, StudentUpdateCallback {

    private String name;
    private String email;
    private String phone;
    private String address;
    private String dateOfBirth;
    private String bloodGroup;
    private String rollNo;
    private String registrationNo;
    private String session;
    private String hall;

    private EditText et_name;
    private EditText et_email;
    private EditText et_phone;
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

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_student_profile_edit);

        student = (Student) getIntent().getSerializableExtra("student");

        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = findViewById(R.id.navigation);
        setBottomNav();
        initialize();
        setStudentInfo(student);
    }

    private void setStudentInfo(Student student) {
        et_name.setText(student.getUserInfo().getName());
        et_email.setText(student.getUserInfo().getEmail());
        et_phone.setText(student.getUserInfo().getPhoneNumber());
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
        et_phone =  findViewById(R.id.phone);
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
            et_dateOfBirth.setEnabled(true);
            et_address.setEnabled(true);
            et_bloodGroup.setEnabled(true);
            et_rollNo.setEnabled(true);
            et_registrationNo.setEnabled(true);
            et_session.setEnabled(true);
            et_hall.setEnabled(true);
            et_phone.setEnabled(true);
            bt_save.setVisibility(View.VISIBLE);
        }

        if(v.getId() == R.id.save_button){
            name = et_name.getText().toString();
            email = et_email.getText().toString();
            address = et_address.getText().toString();
            dateOfBirth = et_dateOfBirth.getText().toString();
            bloodGroup = et_bloodGroup.getText().toString();
            rollNo = et_rollNo.getText().toString();
            registrationNo = et_registrationNo.getText().toString();
            session = et_session.getText().toString();
            hall = et_hall.getText().toString();
            phone = et_phone.getText().toString();

            mProgressDialog = new ProgressDialog(StudentProfileEditActivity.this);
            mProgressDialog.setMessage("Submitting student information...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

            new StudentUpdate(this).run(student.getStudentInfo().getId(), name,email,phone,address,dateOfBirth,bloodGroup,rollNo,registrationNo,session,hall);
        }
    }

    @Override
    public void onUpdateSuccess() {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(),"Student Updated", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), AdminStudentAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void onUpdateError() {
        mProgressDialog.dismiss();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), AdminStudentAccountActivity.class);
        startActivity(intent);
    }
}
