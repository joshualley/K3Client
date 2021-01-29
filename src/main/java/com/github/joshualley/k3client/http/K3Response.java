package com.github.joshualley.k3client.http;

import com.google.gson.JsonObject;

public interface K3Response {
    void onSuccess(JsonObject res);
    default void onError(Exception e) {}
}
