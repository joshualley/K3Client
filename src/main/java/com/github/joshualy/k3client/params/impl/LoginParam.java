package com.github.joshualy.k3client.params.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.github.joshualy.k3client.params.RequestParam;

import java.util.Date;
import java.util.UUID;

public class LoginParam extends BaseParam implements RequestParam {

    @Override
    public String toJson() throws Exception {
        if(null == acctID || null == username && null == password) {
            throw new Exception("参数构建不正确!");
        }
        JsonObject object = new JsonObject();
        object.addProperty("format", 1);
        object.addProperty("useragent", "ApiClient");
        String uuid = UUID.randomUUID().toString();
        object.addProperty("rid", uuid);
        JsonArray array = new JsonArray();
        array.add(acctID);
        array.add(username);
        array.add(password);
        array.add(lcid);
        object.add("parameters", array);
        Date date = new Date();
        object.addProperty("timestamp", date.getTime());
        object.addProperty("v", "1.0");
        return object.toString();
    }

    public String getRequestPath() {
        return "AuthService.ValidateUser";
    }

    private LoginParam(String acctID, String username, String password, int lcid) {
        this.acctID = acctID;
        this.username = username;
        this.password = password;
        this.lcid = lcid;
    }

    private String acctID;
    private String username;
    private String password;
    private int lcid;

    public static class Builder {

        // 帐套Id，字符串类型（必录）
        private String acctID;
        // 用户名称，字符串类型（必录）
        private String username;
        // 用户密码，字符串类型（必录）
        private String password;
        // 语言标识，整型（非必录）
        private int lcid = 2052;

        public LoginParam build() {
            return new LoginParam(acctID, username, password, lcid);
        }


        public Builder setAcctID(String acctID) {
            this.acctID = acctID;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setLcid(int lcid) {
            this.lcid = lcid;
            return this;
        }
    }
}
