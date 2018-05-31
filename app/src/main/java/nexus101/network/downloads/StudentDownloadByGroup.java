package nexus101.network.downloads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.downloads.callback.StudentInfoDownloadCallBack;
import nexus101.network.responses.StudentResponse;
import nexus101.network.services.StudentApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentDownloadByGroup {
    StudentInfoDownloadCallBack mCallback;
    StudentApiInterface apiService;

    public StudentDownloadByGroup(StudentInfoDownloadCallBack studentInfoDownloadCallBack){
        this.mCallback = studentInfoDownloadCallBack;
    }

    public void run(int id) {

        apiService = ApiClient.getClient().create(StudentApiInterface.class);
        Call<StudentResponse> call = apiService.getStudentsbyGroupId(id);

        call.enqueue(new Callback<StudentResponse>() {
            @Override
            public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response) {
                //Log.d("Test", response.body().getStudent().toString());

                if (response.body().getStatus().equals(1)){
                    mCallback.onStudentInfoDownloadSuccess(response.body().getStudent());
                }
                else {
                    mCallback.onStudentInfoDownloadError();
                }

            }

            @Override
            public void onFailure(Call<StudentResponse> call, Throwable t) {

                mCallback.onStudentInfoDownloadError();
            }
        });

    }
}
