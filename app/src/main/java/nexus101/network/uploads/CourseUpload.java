package nexus101.network.uploads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.responses.InsertResponse;
import nexus101.network.services.CourseApiInterface;
import nexus101.network.uploads.callback.CourseInsertCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseUpload {

    CourseInsertCallback mCallback;
    CourseApiInterface apiService;

    public CourseUpload(CourseInsertCallback courseInsertCallback) {
        this.mCallback = courseInsertCallback;
    }

    public void run(String name, String code, int group_id) {

        apiService = ApiClient.getClient().create(CourseApiInterface.class);
        Call<InsertResponse> call = apiService.storeCourse(name, code, group_id);

        call.enqueue(new Callback<InsertResponse>() {
            @Override
            public void onResponse(Call<InsertResponse> call, Response<InsertResponse> response) {
                Log.d("Test", response.body().toString());
                if (response.body().getStatus().equals(1)) {
                    mCallback.onCourseInsertSucces();
                } else {
                    mCallback.onCourseInsertError();
                }

            }

            @Override
            public void onFailure(Call<InsertResponse> call, Throwable t) {
                mCallback.onCourseInsertError();
            }
        });
    }
}