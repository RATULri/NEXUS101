package nexus101.network.services;

import nexus101.network.responses.InsertResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GroupInsertApiInterface {
    @POST("group/store")
    @FormUrlEncoded
    Call<InsertResponse> storeGroup(@Field("group_name")String group_name, @Field("semester_year") String semester_year);
}
