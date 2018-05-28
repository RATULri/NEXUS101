package nexus101.network.downloads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.responses.CourseResponse;
import nexus101.network.responses.TeacherResponse;
import nexus101.network.services.CourseApiInterface;
import nexus101.network.services.TeacherApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseDownload {

    CourseInfoDownloadCallBack mCallback;
    CourseApiInterface apiService;

    public CourseDownload(CourseInfoDownloadCallBack courseInfoDownloadCallBack){
        this.mCallback = courseInfoDownloadCallBack;
    }

    public void run() {

        apiService = ApiClient.getClient().create(CourseApiInterface.class);
        Call<CourseResponse> call = apiService.getCourses();

        call.enqueue(new Callback<CourseResponse>() {
            @Override
            public void onResponse(Call<CourseResponse> call, Response<CourseResponse> response) {
                Log.d("Test", response.body().getCoursesInfo().toString());
                if (response.body().getStatus().equals(1)){
                    mCallback.onCourseInfoDownloadSuccess(response.body().getCoursesInfo());
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
