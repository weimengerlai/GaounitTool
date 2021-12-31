package com.xuejungao.entity;

public class Service {

    // 请求id
    private String id;
    // 请求方法
    private String method;
    // 请求url
    private String url;
    // 描述
    private String desc;
    // 请求类型
    private String type;
    // dubbo 接口请求的io
    private String dunboIp;
    // dubbo 请求的端口号
    private String dunboPort;
    // dubbo 请求的包名
    private String dubboPackage;
    // dubbo 请求的参数类型
    private String paramType;

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getDubboPackage() {
        return dubboPackage;
    }

    public void setDubboPackage(String dubboPackage) {
        this.dubboPackage = dubboPackage;
    }

    public String getDunboPort() {
        return dunboPort;
    }

    public void setDunboPort(String dunboPort) {
        this.dunboPort = dunboPort;
    }

    public String getDunboIp() {
        return dunboIp;
    }

    public void setDunboIp(String dunboIp) {
        this.dunboIp = dunboIp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    @Override
    public String toString() {
        return "Service{" +
                "id='" + id + '\'' +
                ", method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
