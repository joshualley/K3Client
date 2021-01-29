package com.github.joshualley.k3client.params.impl;

import com.google.gson.JsonObject;
import com.github.joshualley.k3client.params.RequestParam;

public class SaveParam extends BaseParam implements RequestParam {

    @Override
    public String toJson() throws Exception {
        if(null == FormId || null == Model) {
            throw new Exception("参数构建不正确！");
        }
        return super.toJSON().toString();
    }

    public String getRequestPath() {
        return "DynamicFormService.Save";
    }

    private SaveParam() {}

    private String FormId;
    private String[] NeedUpDateFields;
    private String[] NeedReturnFields;
    private boolean IsDeleteEntry = true;
    private String SubSystemId;
    private boolean IsVerifyBaseDataField = false;
    private boolean IsEntryBatchFill = true;
    private boolean ValidateFlag = true;
    private boolean NumberSearch = true;
    private String InterationFlags;
    private JsonObject Model;

    public static class Builder {
        private SaveParam saveParam = new SaveParam();

        /**
         * 构建保存对象
         * @return 保存参数对象
         */
        public SaveParam build() {
            return saveParam;
        }

        /**
         * 业务对象表单Id（必录）
         * @param formId 业务对象表单Id
         * @return
         */
        public Builder setFormId(String formId) {
            saveParam.FormId = formId;
            return this;
        }

        /**
         * 需要更新的字段，格式：[key1,key2,...] （非必录）
         * 注（更新单据体字段得加上单据体key）
         * @param needUpDateFields 需要更新的字段
         * @return
         */
        public Builder setNeedUpDateFields(String[] needUpDateFields) {
            saveParam.NeedUpDateFields = needUpDateFields;
            return this;
        }

        /**
         * 需返回结果的字段集合，格式：[key,entitykey.key,...]（非必录）
         * 注（返回单据体字段格式：entitykey.key）
         * @param needReturnFields 需返回结果的字段集合
         * @return
         */
        public Builder setNeedReturnFields(String[] needReturnFields) {
            saveParam.NeedReturnFields = needReturnFields;
            return this;
        }

        /**
         * 是否删除已存在的分录，默认true（非必录）
         * @param deleteEntry 是否删除已存在的分录
         * @return
         */
        public Builder setDeleteEntry(boolean deleteEntry) {
            saveParam.IsDeleteEntry = deleteEntry;
            return this;
        }

        /**
         * 表单所在的子系统内码，字符串类型（非必录)
         * @param subSystemId 表单所在的子系统内码
         * @return
         */
        public Builder setSubSystemId(String subSystemId) {
            saveParam.SubSystemId = subSystemId;
            return this;
        }

        /**
         * 是否验证所有的基础资料有效性，默认false（非必录）
         * @param verifyBaseDataField 是否验证所有的基础资料有效性
         * @return
         */
        public Builder setVerifyBaseDataField(boolean verifyBaseDataField) {
            saveParam.IsVerifyBaseDataField = verifyBaseDataField;
            return this;
        }

        /**
         * 是否批量填充分录，默认true（非必录）
         * @param entryBatchFill 是否批量填充分录
         * @return
         */
        public Builder setEntryBatchFill(boolean entryBatchFill) {
            saveParam.IsEntryBatchFill = entryBatchFill;
            return this;
        }

        /**
         * 否验证标志，默认true（非必录）
         * @param validateFlag 否验证标志
         * @return
         */
        public Builder setValidateFlag(boolean validateFlag) {
            saveParam.ValidateFlag = validateFlag;
            return this;
        }

        /**
         * 是否用编码搜索基础资料，默认true（非必录）
         * @param numberSearch 是否用编码搜索基础资料
         * @return
         */
        public Builder setNumberSearch(boolean numberSearch) {
            saveParam.NumberSearch = numberSearch;
            return this;
        }

        /**
         * 交互标志集合，格式："flag1;flag2;..."（非必录）
         * 例如（允许负库存标识：STK_InvCheckResult）
         * @param interationFlags 交互标志集合
         * @return
         */
        public Builder setInterationFlags(String[] interationFlags) {
            saveParam.InterationFlags = String.join(";", interationFlags);
            return this;
        }

        /**
         * 表单数据包（必录）
         * @param model 表单数据包
         * @return
         */
        public Builder setModel(JsonObject model) {
            saveParam.Model = model;
            return this;
        }
    }
}
