package nexus101.network.services;

import nexus101.network.responses.CourseResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CourseApiInterface {
    @GET("courses")
    Call<CourseResponse> getCourses();
}
