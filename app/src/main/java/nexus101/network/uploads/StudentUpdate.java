package nexus101.network.uploads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.responses.UpdateResponse;
import nexus101.network.services.StudentUpdateApiInterface;
import nexus101.network.uploads.callback.StudentUpdateCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentUpdate {

        StudentUpdateCallback mCallback;
        StudentUpdateApiInterface apiService;

        public StudentUpdate(StudentUpdateCallback studentUpdateCallback){
            this.mCallback = studentUpdateCallback;
        }

        public void run(int id,
                        String name,
                        String email,
                        String phone,
                        String address,
                        String dateOfBirth,
                        String bloodGroup,
                        String rollNo,
                        String registrationNo,
                        String session,
                        String hall) {

            apiService = ApiClient.getClient().create(StudentUpdateApiInterface.class);
            Call<UpdateResponse> call = apiService.updateStudent(id, name, email, phone, address, dateOfBirth, bloodGroup, rollNo, registrationNo, session, hall);

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
