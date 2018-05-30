package nexus101.network.uploads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.responses.InsertResponse;
import nexus101.network.services.StudentInsertApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentInsert {

    StudentInsertCallback mCallback;
    StudentInsertApiInterface apiService;

    public StudentInsert(StudentInsertCallback studentInsertCallback){
        this.mCallback = studentInsertCallback;
    }

    public void run(String name,
                    String email,
                    String phone,
                    String password,
                    String address,
                    String dateOfBirth,
                    String bloodGroup,
                    String rollNo,
                    String registrationNo,
                    String session,
                    String hall) {

        apiService = ApiClient.getClient().create(StudentInsertApiInterface.class);
        Call<InsertResponse> call = apiService.storeStudent(name, email, phone, password, address, dateOfBirth, bloodGroup, rollNo, registrationNo, session, hall);

        call.enqueue(new Callback<InsertResponse>() {
            @Override
            public void onResponse(Call<InsertResponse> call, Response<InsertResponse> response) {
                Log.d("Test", response.body().toString());
                if (response.body().getStatus().equals(1)){
                    mCallback.onInsertSuccess();
                }
                else {
                    mCallback.onInsertError();
                }

            }

            @Override
            public void onFailure(Call<InsertResponse> call, Throwable t) {
                mCallback.onInsertError();
            }
        });

    }
}
