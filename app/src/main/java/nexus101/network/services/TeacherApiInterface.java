package nexus101.network.services;

import nexus101.network.responses.TeacherResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TeacherApiInterface {
    @GET("teachers")
    Call<TeacherResponse> getTeachers();

    @POST("teacher/show")
    @FormUrlEncoded
    Call<TeacherResponse> getTeacherById(@Field("teacher_id") int id);
}
