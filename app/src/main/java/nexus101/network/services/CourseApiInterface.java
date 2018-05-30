package nexus101.network.services;

import nexus101.network.responses.CourseResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CourseApiInterface {
    @GET("courses")
    Call<CourseResponse> getCourses();

    @POST("courseteacher/getCourseByTeacherId")
    @FormUrlEncoded
    Call<CourseResponse> getCoursesByTeacher(@Field("teacher_id") int id);
}
