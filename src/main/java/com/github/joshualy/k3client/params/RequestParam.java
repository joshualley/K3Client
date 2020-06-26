package com.github.joshualy.k3client.params;

import com.google.gson.JsonObject;

public interface RequestParam {
    public String toJson() throws Exception;
    public String getRequestPath();
    public JsonObject parseResponse(String response);
}
