package com.github.joshualley.k3client.http;

import com.google.gson.JsonObject;

public interface K3Response {
    public void onSuccess(JsonObject res);
    public void onError(Exception e);
}
