package com.xuejungao.FileUtils;

import java.lang.reflect.Method;

// 纯代码调用
public class NativeUtils {


    /**
     * @Params : nativePackage 执行调用的包名
     * @Params : methtod 请求的方法
     * methtod : mParams 请求的参数 参数类型为object
     * */
    public static Object getNativeMetthod(String nativePackage,String methtod,Object mParams){
        try {
            // 对象
            Object mObject = null;
            // 实例化对象
            mObject = Class.forName(nativePackage).newInstance();
            //反射生成方法并调度
            Method method = mObject.getClass().getMethod(methtod, Object.class);
            // 执行调用方法，同时返回值
            Object retturnObject = method.invoke(mObject, mParams);
            // 返回数据
            return retturnObject;
        }catch (Exception e){
            // 异常情况的时候

        }
        return "";
    }

    /**
     * @Params : nativePackage 执行调用的包名
     * @Params : methtod 请求的方法
     * @Params : mParams 请求的参数 参数类型为String类型的可变参数
     * */
    public static Object getNativeMetthodString(String nativePackage,String methtod,Object mParams){
        try {
            // 对象
            Object mObject = null;
            // 实例化对象
            mObject = Class.forName(nativePackage).newInstance();
            //反射生成方法并调度
            Method method = mObject.getClass().getMethod(methtod, String.class);
            // 执行调用方法，同时返回值
            Object retturnObject = method.invoke(mObject, mParams);
            // 返回数据
            return retturnObject;
        }catch (Exception e){
            // 异常情况的时候

        }
        return "";
    }
}
