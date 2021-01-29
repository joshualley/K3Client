package com.github.joshualley.k3client.params.impl;

import com.github.joshualley.k3client.params.RequestParam;

public class ViewParam extends BaseParam implements RequestParam {
    public String toJson() throws Exception {
        if(null == FormId || (null == Number && -1 == Id)) {
            throw new Exception("参数构建不正确！");
        }
        return super.toJSON().toString();
    }

    public String getRequestPath() {
        return "DynamicFormService.View";
    }

    public ViewParam() {}

    private String FormId;
    private long CreateOrgId = -1;
    private String Number;
    private long Id = -1;

    public static class Builder {
        private ViewParam viewParam = new ViewParam();

        /**
         * 构建单据查询参数
         * @return 单据查询参数对象
         */
        public ViewParam build() {
            return viewParam;
        }

        /**
         * 业务对象表单Id（必录）
         * @param formId 业务对象表单Id
         * @return 构建器
         */
        public Builder setFormId(String formId) {
            viewParam.FormId = formId;
            return this;
        }

        /**
         * 创建者组织内码（非必录）
         * @param createOrgId 创建者组织内码
         * @return 构建器
         */
        public Builder setCreateOrgId(long createOrgId) {
            viewParam.CreateOrgId = createOrgId;
            return this;
        }

        /**
         * 单据编码（使用编码时必录）
         * @param number 单据编码
         * @return 构建器
         */
        public Builder setNumber(String number) {
            viewParam.Number = number;
            return this;
        }

        /**
         * 表单内码（使用内码时必录）
         * @param id 表单内码
         * @return 构建器
         */
        public Builder setId(long id) {
            viewParam.Id = id;
            return this;
        }
    }
}
