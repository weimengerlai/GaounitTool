package com.xuejungao.FileUtils;

public enum DubboParamEnum {

    STRING_PARAMS("String类型参数","String"),
    JSON_PARAMS("JSON类型参数","JSON"),
    ;


    // 参数名称
    private String name;
    // 参数值
    private String value;

    DubboParamEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
