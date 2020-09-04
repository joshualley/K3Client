package com.github.joshualley.k3client.params.impl;

import com.github.joshualley.k3client.params.RequestParam;
import com.google.gson.JsonObject;

import java.util.Map;
import java.util.function.BiConsumer;

public class PushParam extends BaseParam implements RequestParam {
    @Override
    public String toJson() throws Exception{
        if(null == FormId || (null == Numbers && null == Ids)) {
            throw new Exception("参数构建不正确!");
        }
        return super.toJSON().toString();
    }

    public String getRequestPath() {
        return "DynamicFormService.Push";
    }

    private PushParam(){}

    private String FormId;
    private String[] Numbers;
    private long[] Ids;
    private long EntryIds;
    private String RuleId;
    private String TargetBillTypeId;
    private long TargetOrgId;
    private String TargetFormId;
    private boolean IsEnableDefaultRule = false;
    private boolean IsDraftWhenSaveFail = false;
    private JsonObject CustomParams;

    public static class Builder {

        private PushParam pushParam = new PushParam();

        /**
         * 构建下推参数
         * @return 下推参数对象
         */
        public PushParam build() {
            return pushParam;
        }

        /**
         * 业务对象表单Id，字符串类型（必录）
         * @param formId 业务对象表单Id
         * @return 构建器
         */
        public Builder setFormId(String formId) {
            pushParam.FormId = formId;
            return this;
        }

        /**
         * 单据编码集合，格式：[No1,No2,...]（使用编码时必录）
         * @param numbers 单据编码集合
         * @return 构建器
         */
        public Builder setNumbers(String[] numbers) {
            pushParam.Numbers = numbers;
            return this;
        }

        /**
         * 单据内码集合，格式："Id1,Id2,..."（使用内码时必录）
         * @param ids 单据内码集合
         * @return 构建器
         */
        public Builder setIds(long[] ids) {
            pushParam.Ids = ids;
            return this;
        }

        /**
         * 分录内码集合，逗号分隔（分录下推时必录） 注（按分录下推时，单据内码和编码不需要填,否则按整单下推）
         * @param entryIds 分录内码集合
         * @return 构建器
         */
        public Builder setEntryIds(long entryIds) {
            pushParam.EntryIds = entryIds;
            return this;
        }

        /**
         * 转换规则内码(唯一标识)（未启用默认转换规则时，则必录）
         * @param ruleId 转换规则唯一标识
         * @return 构建器
         */
        public Builder setRuleId(String ruleId) {
            pushParam.RuleId = ruleId;
            return this;
        }

        /**
         * 目标单据类型内码（非必录）
         * @param targetBillTypeId 目标单据类型内码
         * @return 构建器
         */
        public Builder setTargetBillTypeId(String targetBillTypeId) {
            pushParam.TargetBillTypeId = targetBillTypeId;
            return this;
        }

        /**
         * 目标组织内码（非必录）
         * @param targetOrgId 目标组织内码
         * @return 构建器
         */
        public Builder setTargetOrgId(long targetOrgId) {
            pushParam.TargetOrgId = targetOrgId;
            return this;
        }

        /**
         * 目标单据FormId，（启用默认转换规则时，则必录）
         * @param targetFormId 目标单据FormId
         * @return 构建器
         */
        public Builder setTargetFormId(String targetFormId) {
            pushParam.TargetFormId = targetFormId;
            return this;
        }

        /**
         * 是否启用默认转换规则，默认false（非必录）
         * @param enableDefaultRule
         * @return 构建器
         */
        public Builder setEnableDefaultRule(boolean enableDefaultRule) {
            pushParam.IsEnableDefaultRule = enableDefaultRule;
            return this;
        }

        /**
         * 保存失败时是否暂存，默认false（非必录）  注（暂存的单据是没有编码的）
         * @param draftWhenSaveFail 保存失败时是否暂存
         * @return 构建器
         */
        public Builder setDraftWhenSaveFail(boolean draftWhenSaveFail) {
            pushParam.IsDraftWhenSaveFail = draftWhenSaveFail;
            return this;
        }

        /**
         * 自定义参数，格式："{key1:value1,key2:value2,...}"（非必录）  注（传到转换插件的操作选项中，平台不会解析里面的值）
         * @param customParams 自定义参数
         * @return 构建器
         */
        public Builder setCustomParams(Map<String, String> customParams) {
            pushParam.CustomParams = new JsonObject();
            customParams.forEach(new BiConsumer<String, String>() {
                @Override
                public void accept(String key, String value) {
                    pushParam.CustomParams.addProperty(key, value);
                }
            });
            return this;
        }
    }

}
