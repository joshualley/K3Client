# K3Client of Java Version
## Usages:
对金蝶云星空的WebApi的请求进行了一次封装，用法如下：
1. 登录
   ```java
    String url = "http://yourdomain.com";
    K3Client client = K3Client.getInstance().setDomain(url);
    LoginParam param = new LoginParam.Builder()
            .setAcctID("数据中心ID")
            .setUsername("用户名")
            .setPassword("密码")
            .build();
    client.loginRequestAsync(param, new K3Client.K3Response() {
        @Override
        public void onSuccess(JsonObject res) {
            // Do Something.
        }

        @Override
        public void onError(Exception e) {
            System.out.println("登录时发生错误：" + e.getMessage());
        }
    });
   ```
2. 其他查询，登录之后才能够使用，以单据查询为例
   ```java
   K3Client client = K3Client.getInstance();
   QueryParam queryParam = new QueryParam.Builder()
           .setFormId("ora_CRM_Niche")
           .setFieldKeys(new String[]{"FID", "FDocumentStatus"})
           .setFilterString("FDocumentStatus='D'")
           .build();
   client.postRequestAsync(queryParam, new K3Client.K3Response() {
       @Override
       public void onSuccess(JsonObject res) {
           System.out.println(res.toString());
       }

       @Override
       public void onError(Exception e) {
           System.out.println("e = [" + e.getMessage() + "]");
       }
   });
   ```
   查询时对Cloud返回的结果做了一下解析，由Json数组转为了Json对象数组，如下：
   ```json
    {"data":[
       {"FID":"100477","FDocumentStatus":"D"},
       {"FID":"100655","FDocumentStatus":"D"},
       {"FID":"100685","FDocumentStatus":"D"}
    ]}
    ```
3. 保存这类需要提交数据的操作，需要自己构建Json对象，对Json的使用方面，依赖了谷歌的Gson。这里演示一下Save操作的请求。
   ```java
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
    row.addProperty("FWeight", 100);
    row.addProperty("FNote", "备个注");
    FEntity.add(row);
    object.add("FEntity", FEntity);
    SaveParam saveParam = new SaveParam.Builder()
            .setFormId("ora_Task_PersonalReport")
            .setModel(object)
            .build();
    client.postRequestAsync(saveParam, new K3Client.K3Response() {
        @Override
        public void onSuccess(JsonObject res) {
            System.out.println("res : " + res.toString());
        }

        @Override
        public void onError(Exception e) {
            System.out.println("e = [" + e + "]");
        }
    });
    ```
    这是对一个二开单据工作计划的保存，方法不够优雅，建议使用时创建对应的实体类，利用Gson将实体类转为JsonObject。
4. Others:

   其他操作的用法类似。
5. 如果不想使用异步回调，可以这么调用：
   ```java
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
    ```
6. 就这么多了...
