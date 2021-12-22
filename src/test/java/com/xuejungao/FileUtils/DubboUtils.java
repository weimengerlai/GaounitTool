package com.xuejungao.FileUtils;

import com.google.gson.Gson;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.lang.reflect.Method;
import java.util.Map;

public class DubboUtils {

    // dunbo 接口测试用例覆盖

    /*
    * *
    * 1: inter 需要反射的类
    * 2: methodName 需需反射的方法
    * 3: map  请求参数
    * */
    public static void requestDubbo(String inter,String methodName,Map<String,Object> map){

        try {
            // 反射类
            Class<?> mClass = Class.forName(inter);
            // 上下文
            AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            // 获取类名字
            Object obj = context.getBean(getBeanName(inter));
            // 反射方法
            Method method = mClass.getMethod(methodName,new Class[]{Object.class});
            // 执行
            Object object = method.invoke(obj,new Gson().toJson(map));
        }catch (Exception e){

        }
    }

    public static String getBeanName(String str){
        String name = str.substring(str.lastIndexOf(".")+2,str.length());
        name = name.substring(0,1).toLowerCase() + name.substring(1,name.length());
        return name;
    }
}
