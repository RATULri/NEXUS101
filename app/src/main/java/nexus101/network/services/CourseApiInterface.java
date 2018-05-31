package nexus101.network.services;

import nexus101.network.responses.CourseResponse;
import nexus101.network.responses.InsertResponse;
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

    @POST("course/getCourseByStudentId")
    @FormUrlEncoded
    Call<CourseResponse> getCoursesByStudent(@Field("student_id") int id);

    @POST("course/store")
    @FormUrlEncoded
    Call<InsertResponse> storeCourse(@Field("course_name") String name, @Field("course_code") String code, @Field("group_id") int id);

    @POST("courseteacher/store")
    @FormUrlEncoded
    Call<InsertResponse> asignTeacher(@Field("course_id") int course_id, @Field("teacher_id") int teacher_id);
}
