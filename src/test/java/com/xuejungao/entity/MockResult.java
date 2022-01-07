package com.xuejungao.entity;

public class MockResult {

    // 通过url mock 返回数据
    // url
    private String mockUrl;
    // 需要mock的数据
    private String mockResult;
    // 实体类 做序列化的时候使用
    private String mockEntity;
    // dubbo 接口的方法
    private String mockMethod;

    public String getMockMethod() {
        return mockMethod;
    }

    public void setMockMethod(String mockMethod) {
        this.mockMethod = mockMethod;
    }

    public String getMockEntity() {
        return mockEntity;
    }

    public void setMockEntity(String mockEntity) {
        this.mockEntity = mockEntity;
    }

    public String getMockUrl() {
        return mockUrl;
    }

    public void setMockUrl(String mockUrl) {
        this.mockUrl = mockUrl;
    }

    public String getMockResult() {
        return mockResult;
    }

    public void setMockResult(String mockResult) {
        this.mockResult = mockResult;
    }
}
