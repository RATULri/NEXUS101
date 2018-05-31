package nexus101;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import nexus101.admin.group.AdminGroupActivity;
import nexus101.admin.student.AdminStudentAccountActivity;
import nexus101.network.models.UserInfo;
import nexus101.network.uploads.callback.LoginCallback;
import nexus101.network.uploads.LoginInfoUpload;
import nexus101.student.StudentHomeActivity;
import nexus101.teacher.TeacherHomeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoginCallback {

    private String email, password;
    private EditText et_email, et_password;
    private Button login;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Nexus 101");
        login = findViewById(R.id.login);
        et_email = findViewById(R.id.email);
        et_password = findViewById(R.id.password);
        login.setOnClickListener(this);

        checkLogin();

        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminGroupActivity.class));
            }
        });*/
    }

    private void checkLogin() {
        SharedPreferences prefs = getSharedPreferences("nexus101", MODE_PRIVATE);
        int status = prefs.getInt("login_status", 0);
        Log.d("Login", String.valueOf(status));

        if(status == 1){
            if(prefs.getString("user_type",null) =="Student"){
                startActivity(new Intent(getApplicationContext(), StudentHomeActivity.class));
                finish();
            }
            else if(prefs.getString("user_type",null) =="Teacher"){
                startActivity(new Intent(getApplicationContext(), TeacherHomeActivity.class));
                finish();
            }
            else {
                startActivity(new Intent(getApplicationContext(), AdminGroupActivity.class));
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login){
            email = et_email.getText().toString();
            password = et_password.getText().toString();
            if(email.length()>0 && password.length() >0){
                mProgressDialog = new ProgressDialog(MainActivity.this);
                mProgressDialog.setMessage("Singing in...");
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();

                new LoginInfoUpload(this).run(email, password);
            }
        }
    }

    @Override
    public void onLoginSuccess(UserInfo userInfo, int id) {
        mProgressDialog.dismiss();
        if(userInfo.getUserType().equals("Student")){
            SharedPreferences.Editor editor = getSharedPreferences("nexus101", MODE_PRIVATE).edit();
            editor.putInt("login_status", 1);
            editor.putString("user_type", "Student");
            editor.putInt("student_id", id);
            editor.apply();
            startActivity(new Intent(getApplicationContext(), StudentHomeActivity.class));
        }
        else if(userInfo.getUserType().equals("Teacher")){
            SharedPreferences.Editor editor = getSharedPreferences("nexus101", MODE_PRIVATE).edit();
            editor.putInt("login_status", 1);
            editor.putString("user_type", "Teacher");
            editor.putInt("teacher_id", id);
            editor.apply();
            startActivity(new Intent(getApplicationContext(), TeacherHomeActivity.class));
        }

        else {
            SharedPreferences.Editor editor = getSharedPreferences("nexus101", MODE_PRIVATE).edit();
            editor.putInt("login_status", 1);
            editor.putString("user_type", "Admin");
            editor.putInt("admin_id", id);
            editor.apply();
            startActivity(new Intent(getApplicationContext(), AdminGroupActivity.class));
        }
    }

    @Override
    public void onLoginFailed() {
        mProgressDialog.dismiss();
    }
}
