package nexus101.network.services;

import java.util.List;

import nexus101.network.responses.InsertResponse;
import nexus101.network.responses.StudentAttendanceResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StudentAttendanceApiInterface {
    @GET("attendance/getAttendancelistByStudentId")
    Call<StudentAttendanceResponse> getAttendances();

    @POST("attendance/store")
    @FormUrlEncoded
    Call<InsertResponse> storeAttendance(@Field("course_id") int id, @Field("student_id") List<Integer> student_id, @Field("isPresent") List<Integer> isPresent, @Field("date") String date);
}
