package nexus101;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "";

    @GET("")
    Call<List<User>> getUser();

}
