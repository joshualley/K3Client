package com.github.joshualley.k3client.params.impl;

import com.github.joshualley.k3client.params.RequestParam;

public class SubmitParam extends BaseParam implements RequestParam {

    @Override
    public String toJson() throws Exception {
        if(null == FormId || (null == Numbers && null == Ids)) {
            throw new Exception("参数构建不正确！");
        }
        return super.toJSON().toString();
    }

    public String getRequestPath() {
        return "DynamicFormService.Submit";
    }

    private SubmitParam() {}

    private String FormId;
    private long CreateOrgId = -1;
    private String[] Numbers;
    private long[] Ids;
    private boolean NetworkCtrl = false;
    private long SelectedPostId = -1;

    public static class Builder {
        private SubmitParam submitParam = new SubmitParam();

        /**
         * 构建提交参数
         * @return 提交参数对象
         */
        public SubmitParam build() {
            return submitParam;
        }

        /**
         * 业务对象表单Id（必录）
         * @param formId 业务对象表单Id
         * @return
         */
        public Builder setFormId(String formId) {
            submitParam.FormId = formId;
            return this;
        }

        /**
         * 创建者组织内码（非必录）
         * @param createOrgId 创建者组织内码
         * @return
         */
        public Builder setCreateOrgId(long createOrgId) {
            submitParam.CreateOrgId = createOrgId;
            return this;
        }

        /**
         * 单据编码集合，格式：[No1,No2,...]（使用编码时必录）
         * @param numbers 单据编码集合
         * @return
         */
        public Builder setNumbers(String[] numbers) {
            submitParam.Numbers = numbers;
            return this;
        }

        /**
         * 单据内码集合，格式："Id1,Id2,..."（使用内码时必录）
         * @param ids 单据内码集合
         * @return
         */
        public Builder setIds(long[] ids) {
            submitParam.Ids = ids;
            return this;
        }

        /**
         * 是否启用网控，默认false（非必录）
         * @param networkCtrl 是否启用网控
         * @return
         */
        public Builder setNetworkCtrl(boolean networkCtrl) {
            submitParam.NetworkCtrl = networkCtrl;
            return this;
        }

        /**
         * 工作流发起员工岗位内码（非必录）
         * 注（员工身兼多岗时不传参默认取第一个岗位）
         * @param selectedPostId 工作流发起员工岗位内码
         * @return
         */
        public Builder setSelectedPostId(long selectedPostId) {
            submitParam.SelectedPostId = selectedPostId;
            return this;
        }
    }
}
