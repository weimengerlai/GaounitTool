package com.xuejungao.entity;

public class MockResult {

    // 通过url mock 返回数据
    // url
    private String mockUrl;
    // 需要mock的数据
    private String mockResult;

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
