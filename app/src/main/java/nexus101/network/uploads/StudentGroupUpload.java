package nexus101.network.uploads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.responses.InsertResponse;
import nexus101.network.services.StudentApiInterface;
import nexus101.network.uploads.callback.StudentGroupInsertCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentGroupUpload {

    StudentGroupInsertCallback mCallback;
    StudentApiInterface apiService;

    public StudentGroupUpload(StudentGroupInsertCallback studentGroupInsertCallback) {
        this.mCallback = studentGroupInsertCallback;
    }

    public void run(int student_id, int group_id) {

        apiService = ApiClient.getClient().create(StudentApiInterface.class);
        Call<InsertResponse> call = apiService.assignStudent(student_id, group_id);

        call.enqueue(new Callback<InsertResponse>() {
            @Override
            public void onResponse(Call<InsertResponse> call, Response<InsertResponse> response) {
                Log.d("Test", response.body().toString());
                if (response.body().getStatus().equals(1)) {
                    mCallback.onInsertSuccess();
                } else {
                    mCallback.onInsertError();
                }

            }

            @Override
            public void onFailure(Call<InsertResponse> call, Throwable t) {
                mCallback.onInsertError();
            }
        });
    }
}
