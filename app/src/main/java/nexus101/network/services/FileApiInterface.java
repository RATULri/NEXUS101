package nexus101.network.services;

import nexus101.network.responses.FileResponse;
import nexus101.network.responses.InsertResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileApiInterface {
    @POST("studymaterial/getFilesById")
    @FormUrlEncoded
    Call<FileResponse> getFiles(@Field("course_id") int course_id);

    @Multipart
    @POST("studymaterial/store")
    Call<InsertResponse> storeFile(@Part MultipartBody.Part file, @Part("course_id") RequestBody course_id, @Part("upload_date") RequestBody upload_date);
}
