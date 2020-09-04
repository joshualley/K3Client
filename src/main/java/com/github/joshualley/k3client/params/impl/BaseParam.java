package com.github.joshualley.k3client.params.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Field;

public class BaseParam {
    public JsonObject toJSON() throws Exception {
        Class clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        JsonObject object = new JsonObject();
        JsonObject data = new JsonObject();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            String name = field.getName();
            Object value = field.get(this);
            if(value == null) continue;

            if (type == String.class) {
                if ("FormId".equals(name)) {
                    object.addProperty(name, value.toString());
                } else {
                    data.addProperty(name, value.toString());
                }
            } else if(type == JsonObject.class) {
                data.add(name, (JsonObject)value);
            }
            else if(type.isArray()) {
                Class<?> componentType = type.getComponentType();
                JsonArray array = new JsonArray();
                if(componentType == String.class) {
                    for(String item : (String[])value){
                        array.add(item);
                    }
                } else if (componentType == long.class) {
                    for(long item : (long[])value){
                        array.add(item);
                    }
                } else if (componentType == int.class) {
                    for(int item : (int[])value){
                        array.add(item);
                    }
                } else {
                    throw new Exception(name + "被定义为不受支持的数组类型：" + componentType);
                }
                data.add(name, array);
            } else if (type == int.class){
                int v = Integer.valueOf(String.valueOf(value));
                if(v == -1) continue;
                data.addProperty(name, v);
            } else if (type == long.class){
                long v = Long.valueOf(String.valueOf(value));
                if(v == -1) continue;
                data.addProperty(name, v);
            } else if (type == boolean.class){
                boolean v = Boolean.valueOf(String.valueOf(value));
                data.addProperty(name, v);
            } else {
                throw new Exception(name + "被定义为不受支持的字段类型：" + type);
            }
        }
        object.add("data", data);
        return object;
    }

    public JsonObject parseResponse(String response) {
        return (JsonObject) new JsonParser().parse(response);
    }
}
