package com.xuejungao.FileUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.oracle.tools.packager.Log;
import com.xuejungao.entity.DubboModel;
import org.apache.commons.net.telnet.TelnetClient;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.alibaba.dubbo.common.utils.StringUtils;

import java.io.*;
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



    public  static String send(DubboModel mDubboModel) {

        TelnetClient telnetClient = null;

        try {
            // 设置格式户乱码
            telnetClient = new TelnetClient("VT220");
            // 设置默认请求时间
            telnetClient.setDefaultTimeout(5000);
            // 获取ip 和端口号
            telnetClient.connect(mDubboModel.getHostIp(), mDubboModel.getPort());
            // 获取输入流
            InputStream in = telnetClient.getInputStream();
            // 获取输出流
            PrintStream out = new PrintStream(telnetClient.getOutputStream());
            // 设置请求参数
            String command = makeCommand(mDubboModel.getServiceName(), mDubboModel.getMethodName(), mDubboModel.getRequestJson());
            // 打印类型
            System.out.println("send: {}" + command);
            // 写入回车
            out.println("\r\n");
            // 写入请求参数
            out.println(command);
            // 写入回车
            out.println("\r\n");
            // 刷新流u
            out.flush();
            // 获取返回内容
            StringBuilder sb = new StringBuilder();
            // 获取缓存流
            BufferedInputStream bi = new BufferedInputStream(in);

            // 第二步转为字节流
            InputStreamReader inputStreamReader = new InputStreamReader(in,"UTF-8");
            // 转为 缓存流
            BufferedReader reader = new BufferedReader(inputStreamReader);


            String result;
            do {
                byte[] buffer = new byte[1024];
                int len = bi.read(buffer);
                System.out.println("当前返回结果" + len);
                if (len <= -1) {
                    break;
                }
                result = new String(buffer, 0, len, "GBK");
                sb.append(result);
                System.out.println("最终结果" + result);
            } while(!result.endsWith("dubbo>"));
            // 退出
            out.println("exit");
            // 刷新
            out.flush();
            // 断开链接
            telnetClient.disconnect();
            // 获取值
            String ret = sb.toString().trim();
            // 转义符
            String lineSeparator = System.getProperty("line.separator", "\n");
            if (StringUtils.isNotEmpty(ret)) {
                ret = ret.split(lineSeparator)[0];
            }
            // 打印日志
            System.out.println("receive: {}" + ret);
            result = ret.replaceAll(" ", "");
            String str2 = result.replaceAll("\r\n|\r|\n", "");
            return str2;
//            // 转为json
//            result = JSON.toJSONString(JSON.parse(ret), new SerializerFeature[]{SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat});
//            return result;
        } catch (Exception var22) {
            System.out.println("当前的异常是"+var22);
        } finally {
            try {
                if (null != telnetClient) {
                    telnetClient.disconnect();
                }
            } catch (Exception var21) {
                var21.printStackTrace();
            }

        }

        return "";
    }

    private static String makeCommand(String serviceName, String methodName, String json) {
        return StringUtil.format("invoke {}.{}({})", new Object[]{serviceName, methodName, json});
    }
}
