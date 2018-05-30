package nexus101.admin.group;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nexus101.R;
import nexus101.network.uploads.GroupInsert;
import nexus101.network.uploads.callback.GroupInsertCallback;

public class CreateGroupActivity extends AppCompatActivity implements View.OnClickListener, GroupInsertCallback {

    private String group_name;
    private String semester;
    private EditText et_group, et_semester;
    private Button btn_create;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_group);

        initialize();
    }

    private void initialize() {
        et_group = findViewById(R.id.et_group_name);
        et_semester = findViewById(R.id.et_semester);
        btn_create = findViewById(R.id.btn_create);
        btn_create.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_create){

            group_name = et_group.getText().toString();
            semester = et_semester.getText().toString();

            if (group_name.length() < 1 ){
                et_group.setError("Group name can't be null");
            }
            else if(semester.length() < 1){
                et_semester.setError("Semester can't be null");
            }
            else {
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("Please wait...");
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();

                new GroupInsert(this).run(group_name,semester);
            }
        }
    }

    @Override
    public void onGroupInsertSucces() {
        mProgressDialog.dismiss();
        Toast.makeText(CreateGroupActivity.this,"Group Inserted",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), AdminGroupActivity.class);
        startActivity(intent);
    }

    @Override
    public void onGroupInsertError() {
        mProgressDialog.dismiss();
        Toast.makeText(CreateGroupActivity.this,"Group not Inserted",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), AdminGroupActivity.class);
        startActivity(intent);
    }
}
