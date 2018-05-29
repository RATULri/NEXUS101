package nexus101.network.services;

import nexus101.network.responses.InsertResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface StudentInsertApiInterface {
    @POST("student/store")
    @FormUrlEncoded
    Call<InsertResponse> storeStudent(@Field("name") String name,
                                      @Field("email") String email,
                                      @Field("password") String password,
                                      @Field("address") String address,
                                      @Field("date_of_birth") String dateOfBirth,
                                      @Field("blood_group") String bloodGroup,
                                      @Field("roll_number") String rollNo,
                                      @Field("registration_number") String registrationNo,
                                      @Field("session") String session,
                                      @Field("attached_hall") String hall);
}
