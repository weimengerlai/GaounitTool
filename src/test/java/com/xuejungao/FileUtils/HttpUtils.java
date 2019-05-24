package com.xuejungao.FileUtils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class HttpUtils {

    // 网络请求
    public static String HttpFramework(String host_url,Map<String,String> map,String method){


        // 使用循环进行拼接
        // 定义变量 记录是第一个还是下马
        int index = 0;
        // 定义提交的参数
        String params = "";
        // 使用for循环进行遍历
        for (Map.Entry<String, String> entry : map.entrySet()) {

            // 判断为0 是第一次
            if(index == 0){

                // 拼接字符串
                params += entry.getKey()+"="+entry.getValue();

            }else {
                // 拼接字符串
                params += "&"+entry.getKey()+"="+entry.getValue();
            }

            index ++;

        }



        // 进行HTTP 请求
        try {

            // 讲 string 类型的url转为 URL
            URL url = new URL(host_url);
            // 打开请求
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置写入
            connection.setDoOutput(true);
            // 设置读取
            connection.setDoInput(true);
            // 设置网络请求方式
            connection.setRequestMethod(method);
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            // 设置文本类型 application/x-www-form-urlencoded
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            // 将json写入到请求里面
            // 获取流写入
            PrintWriter pw=new PrintWriter(connection.getOutputStream());
            // 把参数提交都body里面
            pw.print(params);
            pw.flush();
            pw.close();

            // 获取服务器返回状态 200 表示网络请求成功
            if(connection.getResponseCode() == 200){

                // InputStream 字节流
                // InputStreamReader 字符流
                // 读取返回内容
                // 获取数据进行解析
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                // 读取返回内容
                String result = reader.readLine();




                return result;
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "";
    }
}
