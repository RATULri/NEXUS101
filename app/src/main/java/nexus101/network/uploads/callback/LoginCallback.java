package nexus101.network.uploads;

import nexus101.network.models.UserInfo;

public interface LoginCallback {
    void onLoginSuccess(UserInfo userInfo);
    void onLoginFailed();
}
