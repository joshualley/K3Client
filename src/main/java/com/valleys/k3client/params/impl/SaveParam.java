package com.valleys.k3client.params.impl;

import com.google.gson.JsonObject;
import com.valleys.k3client.params.RequestParam;

public class SaveParam extends BaseParam implements RequestParam {

    @Override
    public String toJson() throws Exception {
        if(null == FormId || null == Model) {
            throw new Exception("参数构建不正确！");
        }
        return super.toJson();
    }

    public String getRequestPath() {
        return "DynamicFormService.Save";
    }

    private SaveParam(String formId, String[] needUpDateFields, String[] needReturnFields, boolean isDeleteEntry, String subSystemId, boolean isVerifyBaseDataField, boolean isEntryBatchFill, boolean validateFlag, boolean numberSearch, String[] interationFlags, JsonObject model) {
        FormId = formId;
        NeedUpDateFields = needUpDateFields;
        NeedReturnFields = needReturnFields;
        IsDeleteEntry = isDeleteEntry;
        SubSystemId = subSystemId;
        IsVerifyBaseDataField = isVerifyBaseDataField;
        IsEntryBatchFill = isEntryBatchFill;
        ValidateFlag = validateFlag;
        NumberSearch = numberSearch;
        InterationFlags = interationFlags;
        Model = model;
    }

    // 业务对象表单Id，字符串类型（必录）
    private String FormId;
    // 需要更新的字段，数组类型，格式：[key1,key2,...] （非必录）注（更新单据体字段得加上单据体key）
    private String[] NeedUpDateFields;
    // 需返回结果的字段集合，数组类型，格式：[key,entitykey.key,...]（非必录） 注（返回单据体字段格式：entitykey.key）
    private String[] NeedReturnFields;
    // 是否删除已存在的分录，布尔类型，默认true（非必录）
    private boolean IsDeleteEntry = true;
    // 表单所在的子系统内码，字符串类型（非必录)
    private String SubSystemId;
    // 是否验证所有的基础资料有效性（非必录)
    private boolean IsVerifyBaseDataField = false;
    // 是否批量填充分录，默认true（非必录）
    private boolean IsEntryBatchFill = true;
    // 是否验证标志，布尔类型，默认true（非必录）
    private boolean ValidateFlag = true;
    // 是否用编码搜索基础资料，布尔类型，默认true（非必录）
    private boolean NumberSearch = true;
    // 交互标志集合，字符串类型，分号分隔，格式："flag1;flag2;..."（非必录） 例如（允许负库存标识：STK_InvCheckResult）
    private String[] InterationFlags;
    // 表单数据包，JSON类型（必录）
    private JsonObject Model;

    public static class Builder {
        // 业务对象表单Id，字符串类型（必录）
        private String FormId;
        // 需要更新的字段，数组类型，格式：[key1,key2,...] （非必录）注（更新单据体字段得加上单据体key）
        private String[] NeedUpDateFields;
        // 需返回结果的字段集合，数组类型，格式：[key,entitykey.key,...]（非必录） 注（返回单据体字段格式：entitykey.key）
        private String[] NeedReturnFields;
        // 是否删除已存在的分录，布尔类型，默认true（非必录）
        private boolean IsDeleteEntry = true;
        // 表单所在的子系统内码，字符串类型（非必录
        private String SubSystemId;
        // 是否验证所有的基础资料有效性
        private boolean IsVerifyBaseDataField = false;
        // 是否批量填充分录，默认true（非必录）
        private boolean IsEntryBatchFill = true;
        // 是否验证标志，布尔类型，默认true（非必录）
        private boolean ValidateFlag = true;
        // 是否用编码搜索基础资料，布尔类型，默认true（非必录）
        private boolean NumberSearch = true;
        // 交互标志集合，字符串类型，分号分隔，格式："flag1;flag2;..."（非必录） 例如（允许负库存标识：STK_InvCheckResult）
        private String[] InterationFlags;
        // 表单数据包，JSON类型（必录）
        private JsonObject Model;

        public SaveParam build() {
            return new SaveParam(
                    FormId, NeedUpDateFields, NeedReturnFields, IsDeleteEntry,
                    SubSystemId, IsVerifyBaseDataField, IsEntryBatchFill, ValidateFlag,
                    NumberSearch, InterationFlags, Model
            );
        }

        public Builder setFormId(String formId) {
            FormId = formId;
            return this;
        }

        public Builder setNeedUpDateFields(String[] needUpDateFields) {
            NeedUpDateFields = needUpDateFields;
            return this;
        }

        public Builder setNeedReturnFields(String[] needReturnFields) {
            NeedReturnFields = needReturnFields;
            return this;
        }

        public Builder setDeleteEntry(boolean deleteEntry) {
            IsDeleteEntry = deleteEntry;
            return this;
        }

        public Builder setSubSystemId(String subSystemId) {
            SubSystemId = subSystemId;
            return this;
        }

        public Builder setVerifyBaseDataField(boolean verifyBaseDataField) {
            IsVerifyBaseDataField = verifyBaseDataField;
            return this;
        }

        public Builder setEntryBatchFill(boolean entryBatchFill) {
            IsEntryBatchFill = entryBatchFill;
            return this;
        }

        public Builder setValidateFlag(boolean validateFlag) {
            ValidateFlag = validateFlag;
            return this;
        }

        public Builder setNumberSearch(boolean numberSearch) {
            NumberSearch = numberSearch;
            return this;
        }

        public Builder setInterationFlags(String[] interationFlags) {
            InterationFlags = interationFlags;
            return this;
        }

        public Builder setModel(JsonObject model) {
            Model = model;
            return this;
        }
    }
}
