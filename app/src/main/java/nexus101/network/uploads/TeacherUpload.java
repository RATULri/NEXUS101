package nexus101.network.uploads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.responses.TeacherInsertResponse;
import nexus101.network.services.TeacherInsertApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherUpload {

    TeacherInsertCallBack mCallBack;
    TeacherInsertApiInterface apiService;

    public TeacherUpload(TeacherInsertCallBack teacherInsertCallBack) {
        this.mCallBack = teacherInsertCallBack;
    }

    public void run(String name,
                    String email,
                    String password,
                    String bloodGroup,
                    String designation) {

        apiService = ApiClient.getClient().create(TeacherInsertApiInterface.class);
        Call<TeacherInsertResponse> call = apiService.storeTeacher(name, email, password, bloodGroup, designation);

        call.enqueue(new Callback<TeacherInsertResponse>() {
            @Override
            public void onResponse(Call<TeacherInsertResponse> call, Response<TeacherInsertResponse> response) {
                Log.d("Test", response.body().toString());
                if (response.body().getStatus().equals(1)){
                    mCallBack.onInsertSuccess();
                }
                else {
                    mCallBack.onInsertError();
                }

            }

            @Override
            public void onFailure(Call<TeacherInsertResponse> call, Throwable t) {
                mCallBack.onInsertError();
            }
        });

    }
}
