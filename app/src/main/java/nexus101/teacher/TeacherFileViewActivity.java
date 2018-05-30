package nexus101.teacher;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nexus101.MainActivity;
import nexus101.NotificationActivity;
import nexus101.R;
import nexus101.adapters.FileListAdapter;
import nexus101.network.downloads.FileDownload;
import nexus101.network.downloads.FileInfoDownloadCallBack;
import nexus101.network.models.CourseInfo;
import nexus101.network.models.FileInfo;
import nexus101.network.uploads.StudyMaterialUpload;
import nexus101.network.uploads.callback.FileInsertCallback;
import nexus101.utils.FileUtils;

public class TeacherFileViewActivity extends AppCompatActivity implements FileInsertCallback, FileInfoDownloadCallBack {

    private TextView mTextMessage;
    private Button file_upload;
    private ProgressDialog mProgressDialog;
    private CourseInfo courseInfo;
    private RecyclerView recyclerView;
    private FileListAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_attendance:
                    mTextMessage.setText(R.string.title_attendance);
                    return true;
                case R.id.navigation_notification:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_profile);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_file_view);

        courseInfo = (CourseInfo) getIntent().getSerializableExtra("course");

        setTitle(courseInfo.getCourseName());

        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        new FileDownload(this).run(courseInfo.getId());

        file_upload = findViewById(R.id.btn_upload_file);
        file_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStoragePermissionGranted()){
                    Intent intent = new Intent()
                            .setType("*/*")
                            .setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select a file"), 123);
                }
            }
        });

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
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
                        intent = new Intent(getApplicationContext(),TeacherProfileActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123 && resultCode==RESULT_OK) {
            Uri uri = data.getData();
            mProgressDialog = new ProgressDialog(TeacherFileViewActivity.this);
            mProgressDialog.setMessage("File uploading");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            new StudyMaterialUpload(getApplicationContext(),this).run(uri, courseInfo.getId().toString(), df.format(c));
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Permission","Permission is granted");
                return true;
            } else {

                Log.v("Permission","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Permission","Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v("Permission","Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onFileUploadSuccess() {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "File uploaded", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onFileUploadError() {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "File not uploaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFileInfoDownloadSuccess(List<FileInfo> fileInfo) {
        adapter = new FileListAdapter(this, fileInfo);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        mProgressDialog.dismiss();
    }

    @Override
    public void onFileInfoDownloadError() {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "No files found", Toast.LENGTH_SHORT).show();
    }
}
