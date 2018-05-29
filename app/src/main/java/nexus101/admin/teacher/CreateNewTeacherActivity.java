package nexus101.admin.teacher;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nexus101.R;
import nexus101.network.uploads.TeacherInsertCallBack;
import nexus101.network.uploads.TeacherInsert;

public class CreateNewTeacherActivity extends AppCompatActivity implements View.OnClickListener, TeacherInsertCallBack{

    private String name;
    private String email;
    private String password;
    private String bloodGroup;
    private String designation;

    private EditText et_name;
    private EditText et_email;
    private EditText et_password;
    private EditText et_bloodGroup;
    private EditText et_designation;

    private Button bt_submit;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_new_teacher);

        initialize();
    }

    private void initialize() {
        et_name = (EditText) findViewById(R.id.edit_name);
        et_email = (EditText) findViewById(R.id.edit_email);
        et_password = (EditText) findViewById(R.id.edit_password);
        et_bloodGroup = (EditText) findViewById(R.id.edit_blood_group);
        et_designation = (EditText) findViewById(R.id.edit_designation);

        bt_submit = (Button) findViewById(R.id.submit);
        bt_submit.setOnClickListener(this);
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.submit){
            name = et_name.getText().toString();
            email = et_email.getText().toString();
            password = et_password.getText().toString();
            bloodGroup = et_bloodGroup.getText().toString();
            designation = et_designation.getText().toString();

            if(name.length() < 1){
                et_name.setError("Name can't be null");
            }

            if(email.length() < 1){
                et_email.setError("Email can't be null");
            }

            if(password.length() < 1){
                et_password.setError("Password can't be null");
            }

            if(designation.length() < 1){
                et_designation.setError("Designation can't be null");
            }

            else{
                mProgressDialog = new ProgressDialog(CreateNewTeacherActivity.this);
                mProgressDialog.setMessage("Submitting teacher information...");
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                new TeacherInsert(this).run(name,email,password,bloodGroup,designation);
            }
        }
    }

    @Override
    public void onInsertSuccess() {
        mProgressDialog.dismiss();
        Toast.makeText(CreateNewTeacherActivity.this, "Teacher inserted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), AdminTeacherAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void onInsertError() {
        mProgressDialog.dismiss();
        Toast.makeText(CreateNewTeacherActivity.this, "Sorry, teacher couldn't be inserted", Toast.LENGTH_SHORT).show();
    }
}
