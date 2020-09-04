package com.github.joshualley.k3client.params.impl;

import com.github.joshualley.k3client.params.RequestParam;

public class DeleteParam extends BaseParam implements RequestParam {
    @Override
    public String toJson() throws Exception {
        if(null == FormId || (null == Numbers && null == Ids)) {
            throw new Exception("参数构建不正确！");
        }
        return super.toJSON().toString();
    }

    public String getRequestPath() {
        return "DynamicFormService.Delete";
    }

    private DeleteParam() {}

    private String FormId;
    private long CreateOrgId = 0;
    private String[] Numbers;
    private long[] Ids;
    private boolean NetworkCtrl = false;

    public static class Builder {
        private DeleteParam deleteParam = new DeleteParam();

        /**
         * 构建删除参数
         * @return 删除参数
         */
        public DeleteParam build() {
            return deleteParam;
        }

        /**
         * 设置业务对象表单Id（必录）
         * @param formId 业务对象表单Id
         * @return 构建器
         */
        public Builder setFormId(String formId) {
            this.deleteParam.FormId = formId;
            return this;
        }

        /**
         * 设置创建者组织内码（非必录）
         * @param createOrgId 创建者组织内码
         * @return 构建器
         */
        public Builder setCreateOrgId(long createOrgId) {
            this.deleteParam.CreateOrgId = createOrgId;
            return this;
        }

        /**
         * 设置单据编码集合，格式：[No1,No2,...]（使用编码时必录）
         * @param numbers 单据编码集合
         * @return 构建器
         */
        public Builder setNumbers(String[] numbers) {
            this.deleteParam.Numbers = numbers;
            return this;
        }

        /**
         * 设置单据内码集合，格式："Id1,Id2,..."（使用内码时必录）
         * @param ids 单据内码集合
         * @return 构建器
         */
        public Builder setIds(long[] ids) {
            this.deleteParam.Ids = ids;
            return this;
        }

        /**
         * 设置是否启用网控，默认false（非必录）
         * @param networkCtrl 是否启用网控
         * @return 构建器
         */
        public Builder setNetworkCtrl(boolean networkCtrl) {
            this.deleteParam.NetworkCtrl = networkCtrl;
            return this;
        }
    }

}
