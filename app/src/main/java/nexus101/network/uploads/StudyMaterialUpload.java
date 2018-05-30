package nexus101.network.uploads;

import android.util.Log;

import java.io.File;

import nexus101.network.responses.InsertResponse;
import nexus101.network.services.FileApiInterface;
import nexus101.network.uploads.callback.FileInsertCallback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudyMaterialUpload {

    FileInsertCallback mCallback;
    FileApiInterface apiService;

    public StudyMaterialUpload(FileInsertCallback fileInsertCallback){
        this.mCallback = fileInsertCallback;
    }

    public void run(String filePath, String course_id, String date){
        File file = new File(filePath);

        RequestBody reqFile = RequestBody.create(MediaType.parse("/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
        RequestBody courseId = RequestBody.create(MediaType.parse("text/plain"), course_id);
        RequestBody current_date = RequestBody.create(MediaType.parse("text/plain"), date);

        Call<InsertResponse> req = apiService.storeFile(body, courseId, current_date);
        req.enqueue(new Callback<InsertResponse>() {
            @Override
            public void onResponse(Call<InsertResponse> call, Response<InsertResponse> response) {
                Log.d("File", response.body().toString());
            }

            @Override
            public void onFailure(Call<InsertResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
