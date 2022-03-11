package com.xuejungao.core;


import com.google.gson.Gson;
import com.xuejungao.FileUtils.*;
import com.xuejungao.dao.LoginMapperDao;
import com.xuejungao.entity.*;
import org.junit.runners.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class TestCaseStatement extends Statement {


    @Autowired
    private LoginMapperDao mLoginMapperDao;

    // 声明 Statement
    private Statement mStatement;
    // 定义 LIst 传过来
    private TestCase testCase;
    // 定义 map
    private Map<String,Service> serviceMap = new HashMap<>();

    // 定义一个列表
    private List<Interceptor> interceptorList = new ArrayList<Interceptor>(1);

    // 构造函数
    public TestCaseStatement(Statement mStatement,TestCase testCase,Map<String,Service> serviceMap) {
        this.mStatement = mStatement;
        this.testCase = testCase;
        this.serviceMap = serviceMap;
    }

    //

    // 测试用例真正执行的地方
    @Override
    public void evaluate() throws Throwable {

        // 测试用例执行之前执行
        // 使用for循环
        for (Interceptor interceptor:interceptorList){

            // 测试用例执行之前的方法
            interceptor.beforeCase();
        }

        // 执行测试用例
        mStatement.evaluate();

        // 进行网络请求,同时对返回数据进行断言
        HttpCase();

        // 使用for 循环
        for (Interceptor interceptor:interceptorList){

            // 测试用例执行之后执行
            interceptor.afterCase();
        }


        // 测试用例执行之后执行

    }


    // 添加主机进来的东西
    public void addInterceptor(Interceptor interceptor)
    {
        interceptorList.add(interceptor);
    }

    // 定义网络请求的方法
    public void  HttpCase(){


        Service service = serviceMap.get(testCase.getCall().getService());

        String url = service.getUrl();

        LinkedHashMap<String,String> map = testCase.getCall().getMap();

        String method = service.getMethod();

        // 保存mock数据
        mock_save(testCase);

        //  执行SQL的断言
        exe_sql(testCase);



        // 打印请求参数
        System.out.println(testCase.getId()+"请求的URL是:"+service.getUrl());
        System.out.println(testCase.getId()+"请求的参数是:"+map.toString());
        System.out.println(testCase.getId()+"请求的方法是:"+service.getMethod());


        // 接口请求返回的数据
        String json = "";
        // 判断当前是http接口 还是dubbo接口
        if(service.getType().equals(HttpOrDubboEnun.HTTP_TYPE.getValue())){
            // 调用封装的网络请求
            json = HttpUtils.HttpFramework(url,map,method);
        }

        if(service.getType().equals(HttpOrDubboEnun.DUBBO_TYPE.getValue())){
            // 实例化对象
            DubboModel mDubboModel = new DubboModel();
            mDubboModel.setHostIp(service.getDunboIp());
            mDubboModel.setPort(Integer.valueOf(service.getDunboPort()));
            mDubboModel.setServiceName(service.getDubboPackage());
            mDubboModel.setMethodName(service.getMethod());
            // invoke con.gaoxuejun.login.LoginDubbo.findByNamePwd("zhangsan","123456")
            String params = "";
            // 判断当前的参数是String类型还是Json
            if(service.getParamType().equals(DubboParamEnum.STRING_PARAMS.getValue())){
                // 计算当前位置
                int position = 0;
                // 移除mock
                map.remove("mockUrl");
                map.remove("mockJson");
                // 遍历请求参数
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if(position < map.size() -1){
                        params += "\"" + entry.getValue() + "\",";
                    }else {
                        params += "\"" + entry.getValue() + "\"";
                    }
                    // 自增 1
                    position +=1;
                    System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
                }
            }
            // 如果是json类型
            if(service.getParamType().equals(DubboParamEnum.JSON_PARAMS.getValue())){
                // 转为json
                Gson mGson = new Gson();
                // 转为json
                params = mGson.toJson(map);
            }
            // 设置请求参数
            mDubboModel.setRequestJson(params);
            // 执行请求
            json = DubboUtils.send(mDubboModel);
            // 打印请求参数
            System.out.println(testCase.getId()+"dunbo请求参数:"+params);
        }


        // 测试纯代码的jar 包
        if(service.getType().equals(HttpOrDubboEnun.NATIVE_TYPE.getValue())){

        }

        // 打印返回结果
        System.out.println(testCase.getId()+"服务器返回的结果是:"+json);

        // 进行断言
        // 获取 Assert 对象
        Assert mAssert =  testCase.getAnAssert();

        // 判断为空不为空 null
        if(null == mAssert){

            System.out.print("没有Assert标签,请添加Assert标签进行断言");

            return;
        }

        // 获取 result对象
        Result mResult = mAssert.getmResult();

        // 判断空
        if(null == mResult){

            System.out.print("没有result标签,请添加result标签进行断言");

            return;
        }

        // 获取内容进行断言
        // 判断连个json 是不是一样
        if(json.equals(mResult.getJsonRsult()) || json.contains(mResult.getJsonRsult())){

            // 如果 期望值 和 返回值是相同的设置为 true
            assert true;

        }else {

            System.out.println("期望值是:"+mResult.getJsonRsult());
            System.out.println("实际值是:"+json);
            // 如果期望值 和 返回值不想通设置为 false
            assert false;
        }
    }

    // 插入mock数据
    public void mock_save(TestCase testCase){

        if(testCase.getAnAssert() == null){
           return;
        }
        // 请求之前首先mock
        List<MockResult> mMockResultList = testCase.getAnAssert().getmMockResult();
        // 执行mock
        for (MockResult mMockResult : mMockResultList){
            //
            if(mMockResult.getMockMethod() == null){
               return;
            }
            if(mMockResult.getMockEntity() == null){
                return;
            }
            // 进行mcok数据
            try {
                // 解析数据转为对象
                Gson mGson = new Gson();
                String mockJson = mMockResult.getMockResult();
                Class clazz = Class.forName(mMockResult.getMockEntity());
                System.out.println(testCase.getId()+"dunbo请求参数mcok对象:"+mockJson+"====="+clazz);
                // json转为对象
                Object mObject = mGson.fromJson(mockJson,clazz);
                System.out.println(testCase.getId()+"dunbo请求参数mcok对象:"+mObject);
                // 保存的key
                String key = mMockResult.getMockUrl()+"."+mMockResult.getMockMethod();
                // 保存到redis
                RedisClient.getInstance().set(mObject,key);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // 执行SQL的方法
    public void exe_sql(TestCase testCase){

        if(testCase.getAnAssert().getSql() == null){
            // 有内容
            return;
        }
        SQL mSQL = testCase.getAnAssert().getSql();

        if(StringUtil.isNull(mSQL.getDatabase())){
            return;
        }
        if(StringUtil.isNull(mSQL.getExe_sql())){
            return;
        }
        if(StringUtil.isNull(mSQL.getSql_result())){
            return;
        }
        if(StringUtil.isNull(mSQL.getDatabase())){
            return;
        }

//        // 执行 sql
//        Map<String,String> map = mLoginMapperDao.findLoginByName(mSQL.getExe_sql());
//
//        System.out.println("当期那查询返回的数据"+map);
    }
}
