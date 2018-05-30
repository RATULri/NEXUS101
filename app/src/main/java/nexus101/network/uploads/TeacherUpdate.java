package nexus101.network.uploads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.responses.UpdateResponse;
import nexus101.network.services.TeacherUpdateApiInterface;
import nexus101.network.uploads.callback.TeacherUpdateCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherUpdate {
    TeacherUpdateCallback mCallback;
    TeacherUpdateApiInterface apiService;

    public TeacherUpdate (TeacherUpdateCallback teacherUpdateCallback){
        this.mCallback = teacherUpdateCallback;
    }

    public void run(int id,
                    String name,
                    String email,
                    String phone,
                    String bloodGroup,
                    String designation) {

        apiService = ApiClient.getClient().create(TeacherUpdateApiInterface.class);
        Call<UpdateResponse> call = apiService.updateTeacher(id, name, email, phone, bloodGroup,designation);

        call.enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                Log.d("Test", response.body().toString());
                if (response.body().getStatus().equals(1)){
                    mCallback.onUpdateSuccess();
                }
                else {
                    mCallback.onUpdateError();
                }

            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                mCallback.onUpdateError();
            }
        });

    }

}
