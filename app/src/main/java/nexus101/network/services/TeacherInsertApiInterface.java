package nexus101.network.services;

import nexus101.network.responses.StudentInsertResponse;
import nexus101.network.responses.TeacherInsertResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TeacherInsertApiInterface {
    @POST("teacher/store")
    @FormUrlEncoded
    Call<TeacherInsertResponse> storeTeacher(@Field("name") String name,
                                             @Field("email") String email,
                                             @Field("password") String password,
                                             @Field("blood_group") String bloodGroup,
                                             @Field("designation") String designation);
}
