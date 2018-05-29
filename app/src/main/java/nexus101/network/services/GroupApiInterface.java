package nexus101.network.services;

import nexus101.network.responses.GroupResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GroupApiInterface {
    @GET("groups")
    Call<GroupResponse> getGroups();
}
