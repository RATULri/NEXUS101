package nexus101.network.services;

import nexus101.network.responses.UpdateResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface StudentUpdateApiInterface {
    @POST("student/update")
    @FormUrlEncoded
    Call<UpdateResponse> updateStudent(@Field("id") int id,
                                       @Field("name") String name,
                                       @Field("email") String email,
                                       @Field("phone_number") String phone_number,
                                       @Field("address") String address,
                                       @Field("date_of_birth") String dateOfBirth,
                                       @Field("blood_group") String bloodGroup,
                                       @Field("roll_number") String rollNo,
                                       @Field("registration_number") String registrationNo,
                                       @Field("session") String session,
                                       @Field("attached_hall") String hall);
}
