package com.github.joshualley.k3client.params.impl;

import com.github.joshualley.k3client.params.RequestParam;

import java.util.Map;

public class PushParam extends BaseParam implements RequestParam {
    @Override
    public String toJson() throws Exception{
        if(null == FormId || (null == Numbers && null == Ids)) {
            throw new Exception("参数构建不正确!");
        }
        return super.toJson();
    }

    public String getRequestPath() {
        return "DynamicFormService.Push";
    }

    private PushParam(String formId, String[] numbers, long[] ids, long entryIds, String ruleId, String targetBillTypeId, long targetOrgId, String targetFormId, boolean isEnableDefaultRule, boolean isDraftWhenSaveFail, Map<String, String> customParams) {
        FormId = formId;
        Numbers = numbers;
        Ids = ids;
        EntryIds = entryIds;
        RuleId = ruleId;
        TargetBillTypeId = targetBillTypeId;
        TargetOrgId = targetOrgId;
        TargetFormId = targetFormId;
        IsEnableDefaultRule = isEnableDefaultRule;
        IsDraftWhenSaveFail = isDraftWhenSaveFail;
        CustomParams = customParams;
    }

    // 业务对象表单Id，字符串类型（必录）
    private String FormId;
    // 单据编码集合，数组类型，格式：[No1,No2,...]（使用编码时必录）
    private String[] Numbers;
    // 单据内码集合，字符串类型，格式："Id1,Id2,..."（使用内码时必录）
    private long[] Ids;
    // 分录内码集合，逗号分隔（分录下推时必录） 注（按分录下推时，单据内码和编码不需要填,否则按整单下推）
    private long EntryIds;
    // 转换规则内码，字符串类型（未启用默认转换规则时，则必录）
    private String RuleId;
    // 目标单据类型内码，字符串类型（非必录）
    private String TargetBillTypeId;
    // 目标组织内码，整型（非必录）
    private long TargetOrgId;
    // 目标单据FormId，字符串类型，（启用默认转换规则时，则必录）
    private String TargetFormId;
    // 是否启用默认转换规则，布尔类型，默认false（非必录）
    private boolean IsEnableDefaultRule = false;
    // 保存失败时是否暂存，布尔类型，默认false（非必录）  注（暂存的单据是没有编码的）
    private boolean IsDraftWhenSaveFail = false;
    // 自定义参数，字典类型，格式："{key1:value1,key2:value2,...}"（非必录）  注（传到转换插件的操作选项中，平台不会解析里面的值）
    private Map<String, String> CustomParams;

    public static class Builder {
        // 业务对象表单Id，字符串类型（必录）
        private String FormId;
        // 单据编码集合，数组类型，格式：[No1,No2,...]（使用编码时必录）
        private String[] Numbers;
        // 单据内码集合，字符串类型，格式："Id1,Id2,..."（使用内码时必录）
        private long[] Ids;
        // 分录内码集合，逗号分隔（分录下推时必录） 注（按分录下推时，单据内码和编码不需要填,否则按整单下推）
        private long EntryIds;
        // 转换规则内码，字符串类型（未启用默认转换规则时，则必录）
        private String RuleId;
        // 目标单据类型内码，字符串类型（非必录）
        private String TargetBillTypeId;
        // 目标组织内码，整型（非必录）
        private long TargetOrgId = 0;
        // 目标单据FormId，字符串类型，（启用默认转换规则时，则必录）
        private String TargetFormId;
        // 是否启用默认转换规则，布尔类型，默认false（非必录）
        private boolean IsEnableDefaultRule = false;
        // 保存失败时是否暂存，布尔类型，默认false（非必录）  注（暂存的单据是没有编码的）
        private boolean IsDraftWhenSaveFail = false;
        // 自定义参数，字典类型，格式："{key1:value1,key2:value2,...}"（非必录）  注（传到转换插件的操作选项中，平台不会解析里面的值）
        private Map<String, String> CustomParams;

        public PushParam build() {
            return new PushParam(
                    FormId, Numbers, Ids, EntryIds, RuleId,
                    TargetBillTypeId, TargetOrgId, TargetFormId,
                    IsEnableDefaultRule, IsDraftWhenSaveFail, CustomParams
            );
        }

        public Builder setFormId(String formId) {
            FormId = formId;
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

        public Builder setEntryIds(long entryIds) {
            EntryIds = entryIds;
            return this;
        }

        public Builder setRuleId(String ruleId) {
            RuleId = ruleId;
            return this;
        }

        public Builder setTargetBillTypeId(String targetBillTypeId) {
            TargetBillTypeId = targetBillTypeId;
            return this;
        }

        public Builder setTargetOrgId(long targetOrgId) {
            TargetOrgId = targetOrgId;
            return this;
        }

        public Builder setTargetFormId(String targetFormId) {
            TargetFormId = targetFormId;
            return this;
        }

        public Builder setEnableDefaultRule(boolean enableDefaultRule) {
            IsEnableDefaultRule = enableDefaultRule;
            return this;
        }

        public Builder setDraftWhenSaveFail(boolean draftWhenSaveFail) {
            IsDraftWhenSaveFail = draftWhenSaveFail;
            return this;
        }

        public Builder setCustomParams(Map<String, String> customParams) {
            CustomParams = customParams;
            return this;
        }
    }

}
