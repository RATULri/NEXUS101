package nexus101.network.services;

import nexus101.network.responses.StudentAttendanceResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StudentAttendanceApiInterface {
    @GET("attendance/getAttendancelistByStudentId")
    Call<StudentAttendanceResponse> getAttendances();
}
