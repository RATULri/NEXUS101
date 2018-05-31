package nexus101.network.services;

import java.util.List;

import nexus101.network.responses.AttendanceResponse;
import nexus101.network.responses.InsertResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface StudentAttendanceApiInterface {
    @POST("attendance/getAttendancelistByStudentId")
    @FormUrlEncoded
    Call<AttendanceResponse> getAttendances(@Field("student_id") int student_id, @Field("course_id") int course_id);

    @POST("attendance/store")
    @FormUrlEncoded
    Call<InsertResponse> storeAttendance(@Field("course_id") int id, @Field("student_id") List<Integer> student_id, @Field("isPresent") List<Integer> isPresent, @Field("date") String date);
}
