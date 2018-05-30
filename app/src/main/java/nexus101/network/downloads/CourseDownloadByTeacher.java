package nexus101.network.downloads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.downloads.callback.CourseInfoDownloadCallBack;
import nexus101.network.responses.CourseResponse;
import nexus101.network.services.CourseApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseDownloadByTeacher {

    CourseInfoDownloadCallBack mCallback;
    CourseApiInterface apiService;

    public CourseDownloadByTeacher(CourseInfoDownloadCallBack courseInfoDownloadCallBack){
        this.mCallback = courseInfoDownloadCallBack;
    }

    public void run(int id) {

        apiService = ApiClient.getClient().create(CourseApiInterface.class);
        Call<CourseResponse> call = apiService.getCoursesByTeacher(id);

        call.enqueue(new Callback<CourseResponse>() {
            @Override
            public void onResponse(Call<CourseResponse> call, Response<CourseResponse> response) {
                Log.d("Test", response.body().toString());
                if (response.body().getStatus().equals(1)){
                    mCallback.onCourseInfoDownloadSuccess(response.body().getCourseInfo());
                }
                else {
                    mCallback.onCourseInfoDownloadError();
                }

            }

            @Override
            public void onFailure(Call<CourseResponse> call, Throwable t) {
                mCallback.onCourseInfoDownloadError();
            }
        });

    }

}
