package com.valleys.k3client.params.impl;

import com.google.gson.JsonObject;
import com.valleys.k3client.params.RequestParam;

public class UnAuditParam extends BaseParam implements RequestParam {
    @Override
    public String toJson() throws Exception {
        if(null == FormId || (null == Numbers && null == Ids)) {
            throw new Exception("参数构建不正确！");
        }
        return super.toJson();
    }

    public String getRequestPath() {
        return "DynamicFormService.UnAudit";
    }

    private UnAuditParam(String formId, long createOrgId, String[] numbers, long[] ids, boolean networkCtrl, String[] interationFlags) {
        FormId = formId;
        CreateOrgId = createOrgId;
        Numbers = numbers;
        Ids = ids;
        NetworkCtrl = networkCtrl;
        InterationFlags = interationFlags;
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
    // 交互标志集合，字符串类型，分号分隔，格式："flag1;flag2;..."（非必录） 例如（允许负库存标识：STK_InvCheckResult）
    private String[] InterationFlags;

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
        // 交互标志集合，字符串类型，分号分隔，格式："flag1;flag2;..."（非必录） 例如（允许负库存标识：STK_InvCheckResult）
        private String[] InterationFlags;

        public UnAuditParam build() {
            return new UnAuditParam(FormId, CreateOrgId, Numbers, Ids, NetworkCtrl, InterationFlags);
        }

        public Builder setFormId(String formId) {
            FormId = formId;
            return this;
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

        public Builder setInterationFlags(String[] interationFlags) {
            InterationFlags = interationFlags;
            return this;
        }
    }
}
