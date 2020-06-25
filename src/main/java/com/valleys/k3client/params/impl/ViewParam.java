package com.valleys.k3client.params.impl;

import com.google.gson.JsonObject;
import com.valleys.k3client.params.RequestParam;

public class ViewParam extends BaseParam implements RequestParam {
    public String toJson() throws Exception {
        if(null == FormId || (null == Number && -1 == Id)) {
            throw new Exception("参数构建不正确！");
        }
        return super.toJson();
    }

    public String getRequestPath() {
        return "DynamicFormService.View";
    }

    public ViewParam(String formId, long createOrgId, String number, long id) {
        FormId = formId;
        CreateOrgId = createOrgId;
        Number = number;
        Id = id;
    }

    // 业务对象表单Id，字符串类型（必录）
    private String FormId;
    // 创建者组织内码，字符串类型（非必录）
    private long CreateOrgId;
    // 单据编码，字符串类型（使用编码时必录）
    private String Number;
    // 表单内码（使用内码时必录）
    private long Id;

    public static class Builder {
        // 业务对象表单Id，字符串类型（必录）
        private String FormId;
        // 创建者组织内码，字符串类型（非必录）
        private long CreateOrgId = -1;
        // 单据编码，字符串类型（使用编码时必录）
        private String Number;
        // 表单内码（使用内码时必录）
        private long Id = -1;

        public ViewParam build() {
            return new ViewParam(FormId, CreateOrgId, Number, Id);
        }

        public Builder setFormId(String formId) {
            FormId = formId;
            return this;
        }

        public Builder setCreateOrgId(long createOrgId) {
            CreateOrgId = createOrgId;
            return this;
        }

        public Builder setNumber(String number) {
            Number = number;
            return this;
        }

        public Builder setId(long id) {
            Id = id;
            return this;
        }
    }
}
