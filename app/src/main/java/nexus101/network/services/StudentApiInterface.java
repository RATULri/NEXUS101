package nexus101.network.services;

import nexus101.network.responses.StudentResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StudentApiInterface {
    @GET("students")
    Call<StudentResponse> getStudents();
}
