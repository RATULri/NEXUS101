package nexus101.network.uploads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.responses.InsertResponse;
import nexus101.network.services.CourseApiInterface;
import nexus101.network.uploads.callback.CourseTeacherInsertCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseTeacherUpload {

    CourseTeacherInsertCallback mCallback;
    CourseApiInterface apiService;

    public CourseTeacherUpload(CourseTeacherInsertCallback courseTeacherInsertCallback) {
        this.mCallback = courseTeacherInsertCallback;
    }

    public void run(int course_id, int teacher_id) {

        apiService = ApiClient.getClient().create(CourseApiInterface.class);
        Call<InsertResponse> call = apiService.asignTeacher(course_id, teacher_id);

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
