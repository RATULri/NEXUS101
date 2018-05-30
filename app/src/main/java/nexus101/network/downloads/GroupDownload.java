package nexus101.network.downloads;

import nexus101.network.ApiClient;
import nexus101.network.downloads.callback.GroupInfoDownloadCallBack;
import nexus101.network.responses.GroupResponse;
import nexus101.network.services.GroupApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupDownload {

    GroupInfoDownloadCallBack mCallback;
    GroupApiInterface apiService;

    public GroupDownload(GroupInfoDownloadCallBack groupInfoDownloadCallBack){
        this.mCallback = groupInfoDownloadCallBack;
    }

    public void run() {

        apiService = ApiClient.getClient().create(GroupApiInterface.class);
        Call<GroupResponse> call = apiService.getGroups();

        call.enqueue(new Callback<GroupResponse>() {
            @Override
            public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                //Log.d("Test", response.body().toString());
                if (response.body().getStatus().equals(1)){
                    mCallback.onGroupInfoDownloadSuccess(response.body().getGroupInfo());
                }
                else {
                    mCallback.onGroupInfoDownloadError();
                }

            }

            @Override
            public void onFailure(Call<GroupResponse> call, Throwable t) {
                mCallback.onGroupInfoDownloadError();
            }
        });

    }
}
