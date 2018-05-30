package nexus101.network.services;

import nexus101.network.responses.InsertResponse;
import nexus101.network.responses.UpdateResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TeacherUpdateApiInterface {
    @POST("teacher/update")
    @FormUrlEncoded
    Call<UpdateResponse> updateTeacher(@Field("id") int id,
                                       @Field("name") String name,
                                       @Field("email") String email,
                                       @Field("phone_number") String phone_number,
                                       @Field("blood_group") String bloodGroup,
                                       @Field("attached_hall") String designation);
}
