package com.xuejungao.core;

public interface Interceptor {


    // 测试用例执行之前的方法
    void beforeCase();

    // 测试用例执行之后的方法
    void afterCase();

}
