package nexus101.network.uploads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.responses.InsertResponse;
import nexus101.network.services.GroupInsertApiInterface;
import nexus101.network.uploads.callback.GroupInsertCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupInsert {

    GroupInsertCallback mCallback;
    GroupInsertApiInterface apiService;

    public GroupInsert(GroupInsertCallback groupInsertCallback){
        this.mCallback = groupInsertCallback;
    }

    public void run(String group, String semester){

        apiService = ApiClient.getClient().create(GroupInsertApiInterface.class);
        Call<InsertResponse> call = apiService.storeGroup(group, semester);

        call.enqueue(new Callback<InsertResponse>() {
            @Override
            public void onResponse(Call<InsertResponse> call, Response<InsertResponse> response) {
                Log.d("Test", response.body().toString());
                if (response.body().getStatus().equals(1)){
                    mCallback.onGroupInsertSucces();
                }
                else {
                    mCallback.onGroupInsertError();
                }

            }

            @Override
            public void onFailure(Call<InsertResponse> call, Throwable t) {
                mCallback.onGroupInsertError();
            }
        });

    }

}
