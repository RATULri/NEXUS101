package nexus101.network.uploads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.responses.InsertResponse;
import nexus101.network.services.TeacherInsertApiInterface;
import nexus101.network.uploads.callback.TeacherInsertCallBack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherInsert {

    TeacherInsertCallBack mCallBack;
    TeacherInsertApiInterface apiService;

    public TeacherInsert(TeacherInsertCallBack teacherInsertCallBack) {
        this.mCallBack = teacherInsertCallBack;
    }

    public void run(String name,
                    String email,
                    String phone,
                    String password,
                    String bloodGroup,
                    String designation) {

        apiService = ApiClient.getClient().create(TeacherInsertApiInterface.class);
        Call<InsertResponse> call = apiService.storeTeacher(name, email, phone, password, bloodGroup, designation);

        call.enqueue(new Callback<InsertResponse>() {
            @Override
            public void onResponse(Call<InsertResponse> call, Response<InsertResponse> response) {
                Log.d("Test", response.body().toString());
                if (response.body().getStatus().equals(1)){
                    mCallBack.onInsertSuccess();
                }
                else {
                    mCallBack.onInsertError();
                }

            }

            @Override
            public void onFailure(Call<InsertResponse> call, Throwable t) {
                mCallBack.onInsertError();
            }
        });

    }
}
