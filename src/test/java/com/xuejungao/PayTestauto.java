package com.xuejungao;


import com.xuejungao.core.Gunit;
import com.xuejungao.core.InterceptorClasses;
import com.xuejungao.core.Interceptorimpl;

import org.junit.Test;
import org.junit.runner.RunWith;

// 自动化测试用例的入口类
// RunWith 单元测试入口
@RunWith(Gunit.class)
// 注解进去我们自己定义的注解,执行我们自己希望在测试用例执行之前执行的方法
@InterceptorClasses(value = {Interceptorimpl.class}
            , ids = {"login_dubbo_user_name"}
//        ,tags = {"login_user_name"}

        )
public class PayTestauto {

    // 定义模板测试方法
    @Test
    public void test(){

    }

}
