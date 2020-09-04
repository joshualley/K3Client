package com.github.joshualley.k3client.params.impl;

import com.github.joshualley.k3client.params.RequestParam;

public class AuditParam extends BaseParam implements RequestParam {

    public String toJson() throws Exception{
        if(null == FormId || (null == Numbers && null == Ids)) {
            throw  new Exception("参数构建不正确!");
        }
        return super.toJSON().toString();
    }

    public String getRequestPath() {
        return "DynamicFormService.Audit";
    }

    private AuditParam(){}

    private String FormId;
    private long CreateOrgId = -1;
    private String[] Numbers;
    private long[] Ids;
    private boolean NetworkCtrl = false;
    private String InterationFlags;

    public static class Builder {

        private AuditParam auditParam = new AuditParam();

        /**
         * 构建审核参数
         * @return 审核参数对象
         */
        public AuditParam build() {
            return this.auditParam;
        }

        /**
         * 业务对象表单Id（必录）
         * @param formId 业务对象表单Id
         * @return 构建器
         */
        public Builder setFormId(String formId) {
            this.auditParam.FormId = formId;
            return this;
        }

        /**
         * 设置创建者组织内码（非必录）
         * @param createOrgId 创建者组织内码
         * @return 构建器
         */
        public Builder setCreateOrgId(long createOrgId) {
            this.auditParam.CreateOrgId = createOrgId;
            return this;
        }

        /**
         * 设置单据编码集合，格式：[No1,No2,...]（使用编码时必录）
         * @param numbers 单据编码集合
         * @return 构建器
         */
        public Builder setNumbers(String[] numbers) {
            this.auditParam.Numbers = numbers;
            return this;
        }

        /**
         * 设置单据内码集合，格式："Id1,Id2,..."（使用内码时必录）
         * @param ids 单据内码集合
         * @return 构建器
         */
        public Builder setIds(long[] ids) {
            this.auditParam.Ids = ids;
            return this;
        }

        /**
         * 设置是否启用网控，默认false（非必录）
         * @param networkCtrl 是否启用网控
         * @return 构建器
         */
        public Builder setNetworkCtrl(boolean networkCtrl) {
            this.auditParam.NetworkCtrl = networkCtrl;
            return this;
        }

        /**
         * 设置交互标志集合，格式："flag1;flag2;..."（非必录） 例如（允许负库存标识：STK_InvCheckResult）
         * @param interationFlags 交互标志集合
         * @return 构建器
         */
        public Builder setInterationFlags(String[] interationFlags) {
            this.auditParam.InterationFlags = String.join(";", interationFlags);
            return this;
        }
    }
}
