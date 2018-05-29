package nexus101.network.downloads;

import android.util.Log;

import nexus101.network.ApiClient;
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

    public void run() {

        apiService = ApiClient.getClient().create(StudentAttendanceApiInterface.class);
        Call<StudentAttendanceResponse> call = apiService.getAttendances();

        call.enqueue(new Callback<StudentAttendanceResponse>() {
            @Override
            public void onResponse(Call<StudentAttendanceResponse> call, Response<StudentAttendanceResponse> response) {
                Log.d("Test", response.body().getStudentAttendancesInfo().toString());
                if (response.body().getStatus().equals(1)){
                    mCallback.onStudentAttendanceInfoDownloadSuccess(response.body().getStudentAttendancesInfo());
                }
                else {
                    mCallback.onStudentAttendanceInfoDownloadError();
                }

            }

            @Override
            public void onFailure(Call<StudentAttendanceResponse> call, Throwable t) {
                mCallback.onStudentAttendanceInfoDownloadError();
            }
        });

    }
}
