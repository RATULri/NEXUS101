package nexus101.network.services;

import nexus101.network.responses.InsertResponse;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface StudyMaterialUpload {
    /*@POST()
    @Multipart
    Call<InsertResponse> uploadFile(@Part, @Part MultipartBody.Part file);*/
}
