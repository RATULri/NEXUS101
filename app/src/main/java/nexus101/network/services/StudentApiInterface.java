package nexus101.network.services;

import nexus101.network.models.Student;
import nexus101.network.responses.InsertResponse;
import nexus101.network.responses.StudentResponse;
import nexus101.network.responses.TeacherResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StudentApiInterface {
    @GET("students")
    Call<StudentResponse> getStudents();

    @POST("studentgroup/getStudentByGroupId")
    @FormUrlEncoded
    Call<StudentResponse> getStudentsbyGroupId(@Field("group_id") int id);

    @POST("studentgroup/store")
    @FormUrlEncoded
    Call<InsertResponse> assignStudent(@Field("student_id") int student_id, @Field("group_id") int group_id);
}
