package nexus101.network.downloads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.responses.FileResponse;
import nexus101.network.services.FileApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FileDownload {
    FileInfoDownloadCallBack mCallback;
    FileApiInterface apiService;

    public FileDownload(FileInfoDownloadCallBack fileInfoDownloadCallBack){
        this.mCallback = fileInfoDownloadCallBack;
    }

    public void run(int course_id) {

        apiService = ApiClient.getClient().create(FileApiInterface.class);
        Call<FileResponse> call = apiService.getFiles(course_id);

        call.enqueue(new Callback<FileResponse>() {
            @Override
            public void onResponse(Call<FileResponse> call, Response<FileResponse> response) {
                Log.d("Test", response.body().toString());
                if (response.body().getStatus().equals(1)){
                    mCallback.onFileInfoDownloadSuccess(response.body().getFileInfo());
                }
                else {
                    mCallback.onFileInfoDownloadError();
                }

            }

            @Override
            public void onFailure(Call<FileResponse> call, Throwable t) {
                mCallback.onFileInfoDownloadError();
            }
        });

    }
}
