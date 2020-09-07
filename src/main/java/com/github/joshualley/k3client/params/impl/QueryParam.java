package com.github.joshualley.k3client.params.impl;

import com.google.gson.*;
import com.github.joshualley.k3client.params.RequestParam;

public class QueryParam extends BaseParam implements RequestParam {
    @Override
    public String toJson() throws Exception {
        if(null == FormId || null == FieldKeys) {
            throw new Exception("参数构建不正确!");
        }
        JsonObject object = new JsonObject();
        JsonObject data = super.toJSON().getAsJsonObject("data");
        data.addProperty("FormId", FormId);
        object.add("data", data);
        return object.toString();
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

    private QueryParam() {}

    private String FormId;
    private String FieldKeys;
    private String FilterString;
    private String OrderString;
    private int TopRowCount = -1;
    private int StartRow = -1;
    private int Limit = -1;

    public static class Builder {
        private String FormId;
        private String[] FieldKeys;
        private String FilterString;
        private String OrderString;
        private int TopRowCount = -1;
        private int StartRow = -1;
        private int Limit = -1;

        private QueryParam queryParam = new QueryParam();

        /**
         * 构建请求参数
         * @return 请求参数对象
         */
        public QueryParam build() {
            return queryParam;
        }

        /**
         * 业务对象表单Id（必录）
         * @param formId 业务对象表单Id
         * @return 构建器
         */
        public Builder setFormId(String formId) {
            queryParam.FormId = formId;
            return this;
        }

        /**
         * 需查询的字段key集合，格式："key1,key2,..."（必录）
         * 注:
         *   查询单据体内码,需加单据体Key和下划线,如：FEntryKey_FEntryId
         *   查询基础资料属性时，格式为[基础资料字段名].[属性字段名]，如：FMaterialID.FName
         * @param fieldKeys 需查询的字段key集合
         * @return 构建器
         */
        public Builder setFieldKeys(String[] fieldKeys) {
            queryParam.FieldKeys = String.join(",", fieldKeys);
            return this;
        }

        /**
         * 过滤条件（非必录）
         * @param filterString 过滤条件
         * @return 构建器
         */
        public Builder setFilterString(String filterString) {
            queryParam.FilterString = filterString;
            return this;
        }

        /**
         * 排序字段（非必录）
         * @param orderString 排序字段
         * @return 构建器
         */
        public Builder setOrderString(String orderString) {
            queryParam.OrderString = orderString;
            return this;
        }

        /**
         * 返回总行数（非必录）
         * @param topRowCount 返回总行数
         * @return 构建器
         */
        public Builder setTopRowCount(int topRowCount) {
            queryParam.TopRowCount = topRowCount;
            return this;
        }

        /**
         * 开始行索引（非必录）
         * @param startRow 开始行索引
         * @return 构建器
         */
        public Builder setStartRow(int startRow) {
            queryParam.StartRow = startRow;
            return this;
        }

        /**
         * 最大行数，不能超过2000（非必录）
         * @param limit 最大行数
         * @return 构建器
         */
        public Builder setLimit(int limit) {
            queryParam.Limit = limit;
            return this;
        }
    }
}
