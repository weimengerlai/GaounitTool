package com.xuejungao.FileUtils;


public enum HttpOrDubboEnun {

    HTTP_TYPE("网络请求http","http"),
    DUBBO_TYPE("网络请求dubbo","dubbo"),
    NATIVE_TYPE("纯java代码","native"),

    ;

    // 参数名称
    private String name;
    // 参数值
    private String value;

    HttpOrDubboEnun(String name, String value) {
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
