package com.github.joshualley.k3client.params.impl;

import com.github.joshualley.k3client.params.RequestParam;

public class UnAuditParam extends BaseParam implements RequestParam {
    @Override
    public String toJson() throws Exception {
        if(null == FormId || (null == Numbers && null == Ids)) {
            throw new Exception("参数构建不正确！");
        }
        return super.toJSON().toString();
    }

    public String getRequestPath() {
        return "DynamicFormService.UnAudit";
    }

    private UnAuditParam() {}

    private String FormId;
    private long CreateOrgId = -1;
    private String[] Numbers;
    private long[] Ids;
    private boolean NetworkCtrl = false;
    private String InterationFlags;

    public static class Builder {

        private UnAuditParam unAuditParam = new UnAuditParam();

        /**
         * 构建反审核参数
         * @return 反审核参数对象
         */
        public UnAuditParam build() {
            return unAuditParam;
        }

        /**
         * 业务对象表单Id（必录）
         * @param formId 业务对象表单Id
         * @return
         */
        public Builder setFormId(String formId) {
            unAuditParam.FormId = formId;
            return this;
        }

        /**
         * 创建者组织内码（非必录）
         * @param createOrgId 创建者组织内码
         * @return
         */
        public Builder setCreateOrgId(long createOrgId) {
            unAuditParam.CreateOrgId = createOrgId;
            return this;
        }

        /**
         * 单据编码集合，格式：[No1,No2,...]（使用编码时必录）
         * @param numbers 单据编码集合
         * @return
         */
        public Builder setNumbers(String[] numbers) {
            unAuditParam.Numbers = numbers;
            return this;
        }

        /**
         * 单据内码集合，格式："Id1,Id2,..."（使用内码时必录）
         * @param ids 单据内码集合
         * @return
         */
        public Builder setIds(long[] ids) {
            unAuditParam.Ids = ids;
            return this;
        }

        /**
         * 是否启用网控，默认false（非必录）
         * @param networkCtrl 是否启用网控
         * @return
         */
        public Builder setNetworkCtrl(boolean networkCtrl) {
            unAuditParam.NetworkCtrl = networkCtrl;
            return this;
        }

        /**
         * 交互标志集合，格式："flag1;flag2;..."（非必录）
         * 例如（允许负库存标识：STK_InvCheckResult）
         * @param interationFlags 交互标志集合
         * @return
         */
        public Builder setInterationFlags(String[] interationFlags) {
            unAuditParam.InterationFlags = String.join(";", interationFlags);
            return this;
        }
    }
}
