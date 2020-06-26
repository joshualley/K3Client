package com.github.joshualy.k3client.http;

import com.github.joshualy.k3client.params.RequestParam;
import com.github.joshualy.k3client.params.impl.LoginParam;
import com.google.gson.JsonObject;
import okhttp3.*;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class K3Client {

    private static volatile K3Client client;
    /* 获取单例
     * DCL双重检查锁保证线程安全
     */
    public static K3Client getInstance() {
        if(null == client) {
            synchronized(K3Client.class) {
                if(null == client){
                    client = new K3Client();
                }
            }
        }
        return client;
    }

    private String domain;
    private OkHttpClient okHttpClient;
    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();
    private final String prefix = "/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.";
    private final String suffix = ".common.kdsvc";

    public K3Client setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String post(String url, String jsonData) throws Exception {
        RequestBody body = RequestBody.Companion.create(jsonData, MediaType.parse("application/json; charset=utf-8"));
        okhttp3.Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

    public JsonObject loginRequest(LoginParam param) throws Exception {
        assert domain == null : "未设置请求指向的域名.";
        if(null == okHttpClient){
            okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
                        cookieStore.put(httpUrl.host(), list);
                    }

                    @NotNull
                    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
                        List<Cookie> cookies = cookieStore.get(httpUrl.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                }).build();
        }
        String url = domain + prefix + param.getRequestPath() + suffix;
        return param.parseResponse(post(url, param.toJson()));
    }

    public void loginRequestAsync(LoginParam param, K3Response response) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JsonObject res = loginRequest(param);
                    response.onSuccess(res);
                } catch (Exception e) {
                    response.onError(e);
                }

            }
        }).start();
    }

    public JsonObject postRequest(RequestParam param) throws Exception {
        assert domain == null : "未设置请求指向的域名.";
        assert okHttpClient == null : "未进行登录.";
        String url = domain + prefix + param.getRequestPath() + suffix;
        return param.parseResponse(post(url, param.toJson()));
    }

    public void postRequestAsync(RequestParam param, K3Response response) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JsonObject res = postRequest(param);
                    response.onSuccess(res);
                } catch (Exception e) {
                    response.onError(e);
                }

            }
        }).start();
    }


    public interface K3Response {
        public void onSuccess(JsonObject res);
        public void onError(Exception e);
    }

}
