package nexus101.network.uploads;

import android.util.Log;

import nexus101.network.ApiClient;
import nexus101.network.responses.LoginResponse;
import nexus101.network.services.LoginApiInterface;
import nexus101.network.uploads.callback.LoginCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInfoUpload {

    LoginCallback mCallback;
    LoginApiInterface apiService;

    public LoginInfoUpload(LoginCallback loginCallback){
        this.mCallback = loginCallback;
    }

    public void run(String email, String password){

        apiService = ApiClient.getClient().create(LoginApiInterface.class);
        Call<LoginResponse> call = apiService.login(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("Test", response.body().toString());
                if (response.body().getStatus().equals(1)){
                    mCallback.onLoginSuccess(response.body().getUserInfo());
                }
                else {
                    mCallback.onLoginFailed();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mCallback.onLoginFailed();
            }
        });

    }
}
