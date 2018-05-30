package nexus101.network.services;

import nexus101.network.responses.InsertResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TeacherInsertApiInterface {
    @POST("teacher/store")
    @FormUrlEncoded
    Call<InsertResponse> storeTeacher(@Field("name") String name,
                                      @Field("email") String email,
                                      @Field("phone_number") String phone,
                                      @Field("password") String password,
                                      @Field("blood_group") String bloodGroup,
                                      @Field("designation") String designation);
}
