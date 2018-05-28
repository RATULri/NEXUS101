package nexus101.network.services;

import nexus101.network.responses.TeacherResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TeacherApiInterface {
    @GET("teachers")
    Call<TeacherResponse> getTeachers();
}
