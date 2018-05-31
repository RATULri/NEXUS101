package nexus101.network.services;

import nexus101.network.responses.GroupResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GroupApiInterface {
    @GET("groups")
    Call<GroupResponse> getGroups();

    @POST("studentgroup/show")
    @FormUrlEncoded
    Call<GroupResponse> getGroupByStudent(@Field("student_id") int id);

}
