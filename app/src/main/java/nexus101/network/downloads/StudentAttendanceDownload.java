package nexus101.network.downloads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.downloads.callback.StudentAttendanceInfoDownloadCallBack;
import nexus101.network.responses.AttendanceResponse;
import nexus101.network.responses.StudentAttendanceResponse;
import nexus101.network.services.StudentAttendanceApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentAttendanceDownload {

    StudentAttendanceInfoDownloadCallBack mCallback;
    StudentAttendanceApiInterface apiService;

    public StudentAttendanceDownload(StudentAttendanceInfoDownloadCallBack studentAttendanceInfoDownloadCallBack){
        this.mCallback = studentAttendanceInfoDownloadCallBack;
    }

    public void run(int student_id, int course_id) {

        apiService = ApiClient.getClient().create(StudentAttendanceApiInterface.class);
        Call<AttendanceResponse> call = apiService.getAttendances(student_id, course_id);

        call.enqueue(new Callback<AttendanceResponse>() {
            @Override
            public void onResponse(Call<AttendanceResponse> call, Response<AttendanceResponse> response) {
                Log.d("Test", response.body().toString());
                if (response.body().getStatus().equals(1)){
                    mCallback.onStudentAttendanceInfoDownloadSuccess(response.body().getAttendance());
                }
                else {
                    mCallback.onStudentAttendanceInfoDownloadError();
                }

            }

            @Override
            public void onFailure(Call<AttendanceResponse> call, Throwable t) {
                mCallback.onStudentAttendanceInfoDownloadError();
            }
        });

    }
}
