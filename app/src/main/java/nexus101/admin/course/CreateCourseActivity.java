package nexus101.admin.course;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nexus101.R;
import nexus101.network.downloads.GroupDownload;
import nexus101.network.downloads.callback.GroupInfoDownloadCallBack;
import nexus101.network.models.GroupInfo;
import nexus101.network.uploads.CourseUpload;
import nexus101.network.uploads.callback.CourseInsertCallback;

public class CreateCourseActivity extends AppCompatActivity implements GroupInfoDownloadCallBack, View.OnClickListener, CourseInsertCallback {

    private EditText course_name, course_code;
    private Spinner spinner;
    private Button create;
    String name, code;
    int id;
    List<GroupInfo> groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_course);
        getSupportActionBar().setTitle("Nexus 101");
        initialize();
    }

    private void initialize() {
        course_name = findViewById(R.id.course_name);
        course_code = findViewById(R.id.course_code);
        spinner = findViewById(R.id.spinner);
        create = findViewById(R.id.create);
        create.setOnClickListener(this);

        new GroupDownload(this).run();
    }

    @Override
    public void onGroupInfoDownloadSuccess(List<GroupInfo> groupInfos) {
        groups = groupInfos;
        List<String> groupnames = new ArrayList<>();
        for(GroupInfo groupInfo : groupInfos){
            groupnames.add(groupInfo.getGroupName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groupnames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onGroupInfoDownloadError() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.create){
            name = course_name.getText().toString();
            code = course_code.getText().toString();
            id = groups.get(spinner.getSelectedItemPosition()).getId();

            new CourseUpload(this).run(name,code,id);
        }
    }

    @Override
    public void onCourseInsertSucces() {
        Toast.makeText(getApplicationContext(),"Course inserted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), AdminCourseActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCourseInsertError() {
        Toast.makeText(getApplicationContext(),"Course not inserted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), AdminCourseActivity.class);
        startActivity(intent);
    }
}
