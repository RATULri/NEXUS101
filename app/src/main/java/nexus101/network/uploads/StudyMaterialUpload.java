package nexus101.network.uploads;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;

import nexus101.network.ApiClient;
import nexus101.network.responses.InsertResponse;
import nexus101.network.services.FileApiInterface;
import nexus101.network.uploads.callback.FileInsertCallback;
import nexus101.utils.FileUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudyMaterialUpload {

    FileInsertCallback mCallback;
    FileApiInterface apiService;
    Context context;

    public StudyMaterialUpload(Context context, FileInsertCallback fileInsertCallback){
        this.mCallback = fileInsertCallback;
        this.context = context;
    }

    public void run(Uri uri, String course_id, String date){

        File file = FileUtils.getFile(context,uri);

        RequestBody reqFile = RequestBody.create(MediaType.parse(context.getContentResolver().getType(uri)), FileUtils.getFile(context, uri));
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
        RequestBody courseId = RequestBody.create(MediaType.parse("text/plain"), course_id);
        RequestBody current_date = RequestBody.create(MediaType.parse("text/plain"), date);

        apiService = ApiClient.getClient().create(FileApiInterface.class);
        Call<InsertResponse> req = apiService.storeFile(body, courseId, current_date);

        req.enqueue(new Callback<InsertResponse>() {
            @Override
            public void onResponse(Call<InsertResponse> call, Response<InsertResponse> response) {
                Log.d("File", response.body().toString());
                if (response.body().getStatus().equals(1)){
                    mCallback.onFileUploadSuccess();
                }
                else{
                    mCallback.onFileUploadError();
                }
            }

            @Override
            public void onFailure(Call<InsertResponse> call, Throwable t) {
                t.printStackTrace();
                mCallback.onFileUploadError();
            }
        });
    }
}
