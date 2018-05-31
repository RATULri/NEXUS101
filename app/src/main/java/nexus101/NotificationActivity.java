package nexus101;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import nexus101.student.StudentAttendanceActivity;
import nexus101.student.StudentHomeActivity;
import nexus101.student.StudentProfileActivity;

public class NotificationActivity extends AppCompatActivity {

    private TextView mTextMessage;

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
                /*case R.id.navigation_notification:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;*/
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
        setContentView(R.layout.activity_notification);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        Intent intent = new Intent(getApplicationContext(),StudentHomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_attendance:
                        intent = new Intent(getApplicationContext(),StudentAttendanceActivity.class);
                        startActivity(intent);
                        break;
                    /*case R.id.navigation_notification:
                        Toast.makeText(NotificationActivity.this, "Notification", Toast.LENGTH_SHORT).show();
                        break;*/
                    case R.id.navigation_profile:
                        intent = new Intent(getApplicationContext(),StudentProfileActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

}
