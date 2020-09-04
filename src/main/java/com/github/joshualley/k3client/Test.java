package com.github.joshualley.k3client;

import com.github.joshualley.k3client.http.K3Client;
import com.github.joshualley.k3client.http.K3Response;
import com.github.joshualley.k3client.params.impl.LoginParam;
import com.github.joshualley.k3client.params.impl.QueryParam;
import com.github.joshualley.k3client.params.impl.SaveParam;
import com.github.joshualley.k3client.params.impl.ViewParam;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.github.joshualley.k3client.params.impl.*;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Test {

    public static void main(String[] args) {
        Login();
    }

    public static void Login() {
        String url = "http://yourdomain.com";
        K3Client client = K3Client.getInstance().setDomain(url);
        LoginParam param = new LoginParam.Builder()
                .setAcctID("5d3ea17f85b053")
                .setUsername("admin")
                .setPassword("123456")
                .build();
        client.loginRequestAsync(param, new K3Response() {
            @Override
            public void onSuccess(JsonObject res) {
                //TestSave();
                TestQuery();
            }

            @Override
            public void onError(Exception e) {
                System.out.println("登录时发生错误：" + e.getMessage());
            }
        });
    }

    public static void TestSave() {
        K3Client client = K3Client.getInstance();
        JsonObject object = new JsonObject();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(new Date());
        object.addProperty("FCreateDate", d);
        JsonObject FDirectorPost = new JsonObject();
        FDirectorPost.addProperty("FNUMBER", "GW000383");
        object.add("FDirectorPost", FDirectorPost);
        JsonArray FEntity = new JsonArray();
        JsonObject row = new JsonObject();
        row.addProperty("FEvaContent", "这个月要干的事");
        row.addProperty("FEvaDetail", "还挺多的");
        row.addProperty("FWeight", 50);
        row.addProperty("FNote", "备个注");
        FEntity.add(row);
        object.add("FEntity", FEntity);
        SaveParam saveParam = new SaveParam.Builder()
                .setFormId("ora_Task_PersonalReport")
                .setModel(object)
                .build();
        client.postRequestAsync(saveParam, new K3Response() {
            @Override
            public void onSuccess(JsonObject res) {
                System.out.println("res : " + res.toString());
            }

            @Override
            public void onError(Exception e) {
                System.out.println("e = [" + e + "]");
            }
        });

    }

    public static void TestQuery() {
        K3Client client = K3Client.getInstance();
        QueryParam queryParam = new QueryParam.Builder()
                .setFormId("ora_CRM_Niche")
                .setFieldKeys(new String[]{"FID", "FDocumentStatus"})
                .setFilterString("FDocumentStatus='D'")
                .build();
        client.postRequestAsync(queryParam, new K3Response() {
            @Override
            public void onSuccess(JsonObject res) {
                System.out.println(res.toString());
            }

            @Override
            public void onError(Exception e) {
                System.out.println("e = [" + e.getMessage() + "]");
            }
        });
    }

    public static void TestView() {
        K3Client client = K3Client.getInstance();
        ViewParam viewParam = new ViewParam.Builder()
                .setFormId("ora_CRM_Niche")
                .setNumber("")
                .build();
        try {
            JsonObject res = client.postRequest(viewParam);
            System.out.println(res.toString());
        } catch (Exception e) {
            System.out.println("e = [" + e.getMessage() + "]");
        }
    }


}
