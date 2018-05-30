package nexus101.network.services;

import nexus101.network.responses.FileResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FileApiInterface {
    @GET("studymaterial/getFilesById")
    Call<FileResponse> getFile();
}
