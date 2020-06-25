package com.valleys.k3client.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class K3Request {
    public void TestPost() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            URL url = new URL("http://10.1.11.250/k3cloud/Kingdee.BOS.WebApi.ServicesStub.AuthService.ValidateUser.common.kdsvc");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write("".getBytes());
            if(urlConnection.getResponseCode() == 200) {
                InputStream inputStream = urlConnection.getInputStream();
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(bytes)) != -1) {
                    stringBuffer.append(new String(bytes, 0, len));
                }
            }

        } catch (Exception e) {

        }

    }
}
