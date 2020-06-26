package com.github.joshualley.k3client.params.impl;

import com.google.gson.*;
import com.github.joshualley.k3client.params.RequestParam;

public class QueryParam extends BaseParam implements RequestParam {
    @Override
    public String toJson() throws Exception {
        if(null == FormId || null == FieldKeys) {
            throw new Exception("参数构建不正确!");
        }
        JsonObject data = new JsonObject();
        JsonObject object = (JsonObject) new JsonParser().parse(super.toJson());
        object = object.getAsJsonObject("data");
        object.addProperty("FormId", FormId);
        data.add("data", object);
        return data.toString();
    }

    @Override
    public JsonObject parseResponse(String response) {
        JsonObject object = new JsonObject();
        JsonArray array = (JsonArray) new JsonParser().parse(response);
        String[] fields = FieldKeys.split(",");
        JsonArray data = new JsonArray();
        for (int i = 0; i < array.size(); i++) {
            if(i == 0){
                try {
                    JsonObject value = ((JsonArray)array.get(i)).get(0)
                            .getAsJsonObject()
                            .getAsJsonObject("Result")
                            .getAsJsonObject("ResponseStatus");
                    boolean IsSuccess = value.get("IsSuccess").getAsBoolean();
                    if(!IsSuccess) {
                        data.add(value);
                        break;
                    }
                }catch (Exception e) {
                    //System.out.println("e = [" + e.getMessage() + "]");
                }
            }
            JsonObject row = new JsonObject();
            for (int j = 0; j < fields.length; j++) {
                String value = ((JsonArray)array.get(i)).get(j).getAsString();
                row.addProperty(fields[j], value);
            }
            data.add(row);
        }
        object.add("data", data);
        return object;
    }

    public String getRequestPath() {
        return "DynamicFormService.ExecuteBillQuery";
    }

    private QueryParam(String formId, String fieldKeys, String filterString, String orderString, int topRowCount, int startRow, int limit) {
        FormId = formId;
        FieldKeys = fieldKeys;
        FilterString = filterString;
        OrderString = orderString;
        TopRowCount = topRowCount;
        StartRow = startRow;
        Limit = limit;
    }

    private String FormId;
    private String FieldKeys;
    private String FilterString;
    private String OrderString;
    private int TopRowCount;
    private int StartRow;
    private int Limit;

    public static class Builder {
        // 业务对象表单Id（必录）
        private String FormId;
        // 需查询的字段key集合，字符串类型，格式："key1,key2,..."（必录） 注（查询单据体内码,需加单据体Key和下划线,如：FEntryKey_FEntryId）
        private String[] FieldKeys;
        // 过滤条件，字符串类型（非必录）
        private String FilterString;
        // 排序字段，字符串类型（非必录）
        private String OrderString;
        // 返回总行数，整型（非必录）
        private int TopRowCount = -1;
        // 开始行索引，整型（非必录）
        private int StartRow = -1;
        // 最大行数，整型，不能超过2000（非必录）
        private int Limit = -1;

        public QueryParam build() {
            String keys = FieldKeys == null ? null : String.join(",", FieldKeys);
            return new QueryParam(FormId, keys, FilterString, OrderString, TopRowCount, StartRow, Limit);
        }

        public Builder setFormId(String formId) {
            FormId = formId;
            return this;
        }

        public Builder setFieldKeys(String[] fieldKeys) {
            FieldKeys = fieldKeys;
            return this;
        }

        public Builder setFilterString(String filterString) {
            FilterString = filterString;
            return this;
        }

        public Builder setOrderString(String orderString) {
            OrderString = orderString;
            return this;
        }

        public Builder setTopRowCount(int topRowCount) {
            TopRowCount = topRowCount;
            return this;
        }

        public Builder setStartRow(int startRow) {
            StartRow = startRow;
            return this;
        }

        public Builder setLimit(int limit) {
            Limit = limit;
            return this;
        }
    }
}
