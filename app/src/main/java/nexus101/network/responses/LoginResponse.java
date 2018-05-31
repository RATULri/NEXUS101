package nexus101.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import nexus101.network.models.UserInfo;

public class LoginResponse {

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("user_info")
        @Expose
        private UserInfo userInfo;
        @SerializedName("id")
        @Expose
        private Integer id;

        /**
         * No args constructor for use in serialization
         *
         */
        public LoginResponse() {
        }

        /**
         *
         * @param id
         * @param message
         * @param status
         * @param userInfo
         */
        public LoginResponse(String message, Integer status, UserInfo userInfo, Integer id) {
            super();
            this.message = message;
            this.status = status;
            this.userInfo = userInfo;
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

}