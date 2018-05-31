package nexus101.teacher;

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

import java.util.List;

import nexus101.NotificationActivity;
import nexus101.R;
import nexus101.admin.teacher.TeacherProfileEditActivity;
import nexus101.network.downloads.TeacherDownloadById;
import nexus101.network.downloads.callback.TeacherInfoDownloadCallBack;
import nexus101.network.models.Teacher;
import nexus101.network.uploads.TeacherUpdate;
import nexus101.network.uploads.callback.TeacherUpdateCallback;

public class TeacherProfileActivity extends AppCompatActivity implements TeacherUpdateCallback, View.OnClickListener, TeacherInfoDownloadCallBack {

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
    private ProgressDialog mProgressDialog;
    private Teacher teacher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);
        getSupportActionBar().setTitle("Profile");

        //teacher = (Teacher) getIntent().getSerializableExtra("teacher");
        initialize();

        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setBottomNav();

        getTeacher();
    }

    private void getTeacher(){
        mProgressDialog = new ProgressDialog(TeacherProfileActivity.this);
        mProgressDialog.setMessage("Loading info..");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        new TeacherDownloadById(this).run(1);
    }

    private void initialize() {
        et_name = findViewById(R.id.name);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.phone);
        et_bloodGroup = findViewById(R.id.blood_group);
        et_designation = findViewById(R.id.designation);

        bt_save = (Button) findViewById(R.id.save_button);
        bt_save.setOnClickListener(this);

        edit = findViewById(R.id.imageButton);
        edit.setOnClickListener(this);
    }

    private void setBottomNav() {
        navigation.setSelectedItemId(R.id.navigation_profile);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        Intent intent = new Intent(getApplicationContext(),TeacherHomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_attendance:
                        intent = new Intent(getApplicationContext(), TeacherAttendanceCourseSelectActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_notification:
                        intent = new Intent(getApplicationContext(), NotificationActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_profile:
                        Toast.makeText(TeacherProfileActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), TeacherHomeActivity.class);
        startActivity(intent);
    }

    public void setTeacherInfo(Teacher teacher) {
        et_name.setText(teacher.getUserInfo().getName());
        et_email.setText(teacher.getUserInfo().getEmail());
        et_phone.setText(teacher.getUserInfo().getPhoneNumber());
        et_bloodGroup.setText(teacher.getTeacherInfo().getBloodGroup());
        et_designation.setText(teacher.getTeacherInfo().getDesignation());
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

            mProgressDialog = new ProgressDialog(TeacherProfileActivity.this);
            mProgressDialog.setMessage("Updating...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

            new TeacherUpdate(this).run(teacher.getTeacherInfo().getId(), name, email, phone, bloodGroup, designation);
        }
    }

    @Override
    public void onUpdateSuccess() {
        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), TeacherProfileActivity.class));
    }

    @Override
    public void onUpdateError() {

    }

    @Override
    public void onTeacherInfoDownloadSuccess(List<Teacher> teachers) {
        mProgressDialog.dismiss();
        teacher = teachers.get(0);
        setTeacherInfo(teacher);
    }

    @Override
    public void onTeacherInfoDownloadError() {
        mProgressDialog.dismiss();
    }
}
