package nexus101.admin.student;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import nexus101.R;
import nexus101.network.uploads.StudentInsertCallback;
import nexus101.network.uploads.StudentUpload;

public class CreateNewStudentActivity extends AppCompatActivity implements View.OnClickListener, StudentInsertCallback {

    private String name;
    private String email;
    private String password;
    private String address;
    private String dateOfBirth;
    private String bloodGroup;
    private String rollNo;
    private String registrationNo;
    private String session;
    private String hall;

    private EditText et_name;
    private EditText et_email;
    private EditText et_password;
    private EditText et_address;
    private EditText et_dateOfBirth;
    private EditText et_bloodGroup;
    private EditText et_rollNo;
    private EditText et_registrationNo;
    private EditText et_session;
    private EditText et_hall;

    private Button bt_submit;

    private Calendar myCalendar;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_new_student);

        initialize();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            myCalendar = Calendar.getInstance();
        }
    }

    private void initialize() {
        et_name = findViewById(R.id.edit_name);
        et_email =  findViewById(R.id.edit_email);
        et_password =  findViewById(R.id.edit_password);
        et_address =  findViewById(R.id.edit_address);
        et_dateOfBirth =  findViewById(R.id.edit_date_of_birth);
        et_bloodGroup =  findViewById(R.id.edit_blood_group);
        et_rollNo =  findViewById(R.id.edit_roll_no);
        et_registrationNo =  findViewById(R.id.edit_reg_no);
        et_session =  findViewById(R.id.edit_session);
        et_hall =  findViewById(R.id.edit_hall);

        bt_submit = (Button) findViewById(R.id.submit);
        bt_submit.setOnClickListener(this);

        et_dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateNewStudentActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.submit){
            name = et_name.getText().toString();
            email = et_email.getText().toString();
            password = et_password.getText().toString();
            address = et_address.getText().toString();
            dateOfBirth = et_dateOfBirth.getText().toString();
            bloodGroup = et_bloodGroup.getText().toString();
            rollNo = et_rollNo.getText().toString();
            registrationNo = et_registrationNo.getText().toString();
            session = et_session.getText().toString();
            hall = et_hall.getText().toString();

            if(name.length() < 1){
                et_name.setError("Name can't be null");
            }

            if(email.length() < 1){
                et_email.setError("Email can't be null");
            }

            if(password.length() < 1){
                et_password.setError("Password can't be null");
            }

            if(rollNo.length() < 1){
                et_rollNo.setError("Roll number can't be null");
            }

            if(registrationNo.length() < 1){
                et_registrationNo.setError("Registration number can't be null");
            }

            if(session.length() < 1){
                et_session.setError("Session can't be null");
            }

            if(hall.length() < 1){
                et_hall.setError("Hall name can't be null");
            }

            else{
                mProgressDialog = new ProgressDialog(CreateNewStudentActivity.this);
                mProgressDialog.setMessage("Submitting student information...");
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                new StudentUpload(this).run(name,email,password,address,dateOfBirth,bloodGroup,rollNo,registrationNo,session,hall);
            }
        }
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @SuppressLint("NewApi")
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    @SuppressLint("NewApi")
    private void updateLabel() {
        String myFormat = "dd-mm-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        et_dateOfBirth.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onInsertSuccess() {
        mProgressDialog.dismiss();
        Toast.makeText(CreateNewStudentActivity.this,"Student Inserted",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), AdminStudentAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void onInsertError() {
        mProgressDialog.dismiss();
        Toast.makeText(CreateNewStudentActivity.this,"Sorry, Student couldn't be inserted..",Toast.LENGTH_SHORT).show();
    }
}
