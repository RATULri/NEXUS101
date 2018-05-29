package nexus101.network.uploads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.responses.StudentInsertResponse;
import nexus101.network.services.StudentInsertApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentUpload {

    StudentInsertCallback mCallback;
    StudentInsertApiInterface apiService;

    public StudentUpload(StudentInsertCallback studentInsertCallback){
        this.mCallback = studentInsertCallback;
    }

    public void run(String name,
                    String email,
                    String password,
                    String address,
                    String dateOfBirth,
                    String bloodGroup,
                    String rollNo,
                    String registrationNo,
                    String session,
                    String hall) {

        apiService = ApiClient.getClient().create(StudentInsertApiInterface.class);
        Call<StudentInsertResponse> call = apiService.storeStudent(name, email, password, address, dateOfBirth, bloodGroup, rollNo, registrationNo, session, hall);

        call.enqueue(new Callback<StudentInsertResponse>() {
            @Override
            public void onResponse(Call<StudentInsertResponse> call, Response<StudentInsertResponse> response) {
                Log.d("Test", response.body().toString());
                if (response.body().getStatus().equals(1)){
                    mCallback.onInsertSuccess();
                }
                else {
                    mCallback.onInsertError();
                }

            }

            @Override
            public void onFailure(Call<StudentInsertResponse> call, Throwable t) {
                mCallback.onInsertError();
            }
        });

    }
}
