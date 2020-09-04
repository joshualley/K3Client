package com.github.joshualley.k3client.params.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.github.joshualley.k3client.params.RequestParam;

import java.util.Date;
import java.util.UUID;

public class LoginParam extends BaseParam implements RequestParam {

    @Override
    public String toJson() throws Exception {
        return super.toJSON().getAsJsonObject("data").toString();
    }

    public String getRequestPath() {
        return "AuthService.ValidateUser";
    }

    private LoginParam() {}

    private int format = 1;
    private String useragent = "ApiClient";
    private String rid = UUID.randomUUID().toString();
    private String[] parameters;
    private long timestamp = new Date().getTime();
    private String v = "1.0";

    public static class Builder {
        private String acctID;
        private String username;
        private String password;
        private int lcid = 2052;

        private LoginParam loginParam = new LoginParam();

        /**
         * 构建登录参数
         * @return 登录参数对象
         */
        public LoginParam build() {
            assert (null == acctID && null == username && null == password) : "参数构建不正确!";
            loginParam.parameters = new String[]{acctID, username, password, String.valueOf(lcid)};
            return loginParam;
        }

        /**
         *  设置数据中心ID （必录）
         * @param acctID 数据中心ID
         * @return 构建器
         */
        public Builder setAcctID(String acctID) {
            this.acctID = acctID;
            return this;
        }

        /**
         * 设置用户名（必录）
         * @param username 用户名
         * @return 构建器
         */
        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        /**
         * 设置密码（必录）
         * @param password 密码
         * @return 构建器
         */
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * 设置本地语言ID，默认中文2052（非必录）
         * @param lcid 本地语言ID
         * @return 构建器
         */
        public Builder setLcid(int lcid) {
            this.lcid = lcid;
            return this;
        }
    }
}
