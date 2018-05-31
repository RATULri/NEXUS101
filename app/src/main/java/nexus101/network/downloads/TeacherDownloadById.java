package nexus101.network.downloads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.downloads.callback.TeacherInfoDownloadCallBack;
import nexus101.network.responses.TeacherResponse;
import nexus101.network.services.TeacherApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherDownloadById {
    TeacherInfoDownloadCallBack mCallback;
    TeacherApiInterface apiService;

    public TeacherDownloadById(TeacherInfoDownloadCallBack teacherInfoDownloadCallBack){
        this.mCallback = teacherInfoDownloadCallBack;
    }

    public void run(int teacher_id) {

        apiService = ApiClient.getClient().create(TeacherApiInterface.class);
        Call<TeacherResponse> call = apiService.getTeacherById(teacher_id);

        call.enqueue(new Callback<TeacherResponse>() {
            @Override
            public void onResponse(Call<TeacherResponse> call, Response<TeacherResponse> response) {
                //Log.d("Test", response.body().toString());
                if (response.body().getStatus().equals(1)){
                    mCallback.onTeacherInfoDownloadSuccess(response.body().getTeacher());
                }
                else {
                    mCallback.onTeacherInfoDownloadError();
                }

            }

            @Override
            public void onFailure(Call<TeacherResponse> call, Throwable t) {
                mCallback.onTeacherInfoDownloadError();
            }
        });

    }
}
