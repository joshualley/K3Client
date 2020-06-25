package com.valleys.k3client.params.impl;

import com.valleys.k3client.params.RequestParam;

public class SubmitParam extends BaseParam implements RequestParam {

    @Override
    public String toJson() throws Exception {
        if(null == FormId || (null == Numbers && null == Ids)) {
            throw new Exception("参数构建不正确！");
        }
        return super.toJson();
    }

    public String getRequestPath() {
        return "DynamicFormService.Submit";
    }

    private SubmitParam(String formId, long createOrgId, String[] numbers, long[] ids, boolean networkCtrl, long selectedPostId) {
        FormId = formId;
        CreateOrgId = createOrgId;
        Numbers = numbers;
        Ids = ids;
        NetworkCtrl = networkCtrl;
        SelectedPostId = selectedPostId;
    }

    // 业务对象表单Id，字符串类型（必录）
    private String FormId;
    // 创建者组织内码，字符串类型（非必录）
    private long CreateOrgId = 0;
    // 单据编码集合，数组类型，格式：[No1,No2,...]（使用编码时必录）
    private String[] Numbers;
    // 单据内码集合，字符串类型，格式："Id1,Id2,..."（使用内码时必录）
    private long[] Ids;
    // 是否启用网控，布尔类型，默认false（非必录）
    private boolean NetworkCtrl = false;
    // 工作流发起员工岗位内码，整型（非必录） 注（员工身兼多岗时不传参默认取第一个岗位）
    private long SelectedPostId = 0;

    public static class Builder {
        // 业务对象表单Id，字符串类型（必录）
        private String FormId;
        // 创建者组织内码，字符串类型（非必录）
        private long CreateOrgId = 0;
        // 单据编码集合，数组类型，格式：[No1,No2,...]（使用编码时必录）
        private String[] Numbers;
        // 单据内码集合，字符串类型，格式："Id1,Id2,..."（使用内码时必录）
        private long[] Ids;
        // 是否启用网控，布尔类型，默认false（非必录）
        private boolean NetworkCtrl = false;
        // 工作流发起员工岗位内码，整型（非必录） 注（员工身兼多岗时不传参默认取第一个岗位）
        private long SelectedPostId = 0;

        public SubmitParam build() {
            return new SubmitParam(FormId, CreateOrgId, Numbers, Ids, NetworkCtrl, SelectedPostId);
        }

        public void setFormId(String formId) {
            FormId = formId;
        }

        public Builder setCreateOrgId(long createOrgId) {
            CreateOrgId = createOrgId;
            return this;
        }

        public Builder setNumbers(String[] numbers) {
            Numbers = numbers;
            return this;
        }

        public Builder setIds(long[] ids) {
            Ids = ids;
            return this;
        }

        public Builder setNetworkCtrl(boolean networkCtrl) {
            NetworkCtrl = networkCtrl;
            return this;
        }

        public Builder setSelectedPostId(long selectedPostId) {
            SelectedPostId = selectedPostId;
            return this;
        }
    }
}
