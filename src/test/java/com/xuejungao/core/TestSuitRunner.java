package com.xuejungao.core;

import com.xuejungao.entity.Service;
import com.xuejungao.entity.TestCase;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.util.HashMap;
import java.util.Map;

// 这个类代表是每一条测试用例
// TestCase
public class TestSuitRunner extends BlockJUnit4ClassRunner {


    private TestCase testCase;
    private Map<String,Service> map = new HashMap<>();
    /**
     * Creates a BlockJUnit4ClassRunner to run {@code klass}
     *
     * @param klass
     * @throws InitializationError if the test class is malformed.
     */
    public TestSuitRunner(Class<?> klass, TestCase testCase,Map<String,Service> map) throws InitializationError {
        super(klass);
        this.testCase = testCase;
        this.map = map;
    }


    // 测试用例运行的地方
    // 自定义测试用例执行之前执行的方法
    // 测试用例执行之后执行的方法
    // 测试用例执行 FrameworkMethod 就是测试用例执行类
    // 每一条测试用例执行的时候的方法

    // Statement 用来执行测试用例
    // 重写 Statement

    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {

        // 实例化自定义的 Statement 用来帮助执行测试用例的
        TestCaseStatement testCaseStatement = new TestCaseStatement(super.methodInvoker(method, test),testCase,map);
        // 添加注解
        // 获取注解对象
        InterceptorClasses interceptorClasses = test.getClass().getAnnotation(InterceptorClasses.class);
        // 获取注解里面的值
        Class<?>[] classes = interceptorClasses.value();

        // 使用for循环遍历
        try
        {
            for (Class<?> klas : classes)
            {
                // 实例化对象到列表里面
                testCaseStatement.addInterceptor((Interceptor)klas.newInstance());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return testCaseStatement;
    }


    // 返回测试用例的名字
    // 决定我们测试用例叫什么名字
    @Override
    protected String testName(FrameworkMethod method) {

        // 修噶测试用例名字
        return testCase.getId();
    }
}
