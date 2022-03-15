package com.xuejungao.saxxml;


import com.xuejungao.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class SaxHandler extends DefaultHandler {


    // 定义对象
    private TestCase testCase;
    // 声明call对象
    private Call call;
    // 定义vales
    private String value = "";
    // 定义map集合用来接收参数
    private LinkedHashMap<String,String> map = new LinkedHashMap<>();
    // 定义map的key变量
    private String mapRequestKey= "";
    // 定义成员对象
    private Assert anAssert;
    // 定义期望返回数据的结果对象
    private Result result;
    // SQL 语句期望值
    private SQL sql;
    // mock 地址
    private MockResult mMockResult;
    // 定义 类型用来在有换行特殊情况进行判断
    private String name;
    // 期望返回的结果
    private String JsonRsult="";
    // 需要mock的结果
    private String mockResult = "";
    // 执行SQL的时候的期望值
    private String SQLResult = "";
    // 需要执行的SQL 语句
    private String needSql = "";
    // List列表
    private List<MockResult> mMockResultList = new ArrayList<>();

    // 定义列表
    private List<TestCase> testCaseList = new ArrayList<TestCase>();

    // 定义一个对外提供对象的方法
    public  List<TestCase> getTestCaseList(){

        return testCaseList;
    }


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }


    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        name = qName;
        // 进行判断实例化
        if(qName.equals("case")){

            testCase = new TestCase();
            // 清空列表
            mMockResultList.clear();

            // 获取属性个数
            int number = attributes.getLength();
            // 使用for循环
            for (int i=0;i<number;i++){

                if(attributes.getQName(i).equals("id")){

                    testCase.setId(attributes.getValue(i));
                }

                if(attributes.getQName(i).equals("desc")){

                    testCase.setDesc(attributes.getValue(i));
                }

                if(attributes.getQName(i).equals("tag")){

                    testCase.setTag(attributes.getValue(i));
                }
            }
        }


        // 判断 call
        if(qName.equals("call")){

            // 实例化对象
            call = new Call();
            // 参数全部遍历完成,我们需要将map设置为空,因为下一条测试用例也有参数,不能带有上一条测试用例的参数
            map = new LinkedHashMap<>();


            // 获取属性
            // 获取属性个数
            int number = attributes.getLength();
            // 使用for循环
            for (int i=0;i<number;i++){

                if(attributes.getQName(i).equals("service")){

                    call.setService(attributes.getValue(i));
                }

            }

        }

        // 往下继续判断
        if(qName.equals("param")){

            // 获取属性
            int number = attributes.getLength();

            for (int i=0;i<number;i++){

                if(attributes.getQName(i).equals("name")){

                    // 获取值加入到map集合
                    mapRequestKey= attributes.getValue(i);
                }
            }

        }

        // 断言内容的获取
        if(qName.equals("Assert")){

            // 实例化对象
            anAssert = new Assert();
        }

        // 判断返回结果
        if(qName.equals("result")){


            //实例化
            result = new Result();

            // 获取属性
            int number = attributes.getLength();

            // 使用for循环
            for (int i=0;i<number;i++){

                if(attributes.getQName(i).equals("statue")){

                    result.setStatue(attributes.getValue(i));
                }

            }
        }
        // SQL
        if(qName.equals("SQL")){

            // 实例化对象
            sql = new SQL();

            // 获取属性
            int number = attributes.getLength();

            for (int i=0;i<number;i++){

                if(attributes.getQName(i).equals("database")){

                    sql.setDatabase(attributes.getValue(i));
                }
            }

        }
        // MockResult
        if(qName.equals("MockResult")){
            // 实例化对象
            mMockResult = new MockResult();
            // 获取属性
            int number = attributes.getLength();

            for (int i=0;i<number;i++){

                if(attributes.getQName(i).equals("mockUrl")){
                    // 获取url 值设置
                    mMockResult.setMockUrl(attributes.getValue(i));

                }
                if(attributes.getQName(i).equals("mockMethod")){
                    // 获取url 值设置
                    mMockResult.setMockMethod(attributes.getValue(i));

                }
                if(attributes.getQName(i).equals("mockEntity")){
                    // 获取url 值设置
                    mMockResult.setMockEntity(attributes.getValue(i));

                }
            }
        }
        // 开始每一条测试用例之前将我们以前的期望值设置为""
        if(qName.equals("JsonRsult")){

            JsonRsult = "";
        }
        // 开始每一条测试用例之前将我们以前的期望值设置为""
        if(qName.equals("MockResult")){

            mockResult = "";
        }
        // 开始每一条测试用例之前将我们以前的期望值设置为""
        if(qName.equals("sql_result")){

            SQLResult = "";
        }
        // needSql
        if(qName.equals("exe_sql")){

            needSql = "";
        }
    }



    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        // 在遍历标签结束的时候通过key将值加进去
        if(qName.equals("param")){

            String str1 = value.replaceAll(" ", "");
            String str2 = str1.replaceAll("\r\n|\r|\n", "");

            // 加入值
            map.put(mapRequestKey,str2);
            System.out.print("当前自动化工具请求的参数:"+mapRequestKey+"=="+str2);

        }

        // call
        if(qName.equals("call")){

            // 将map加入到call里面
            call.setMap(map);


            System.out.println("当前map是:"+map);

        }


        // print
        if(qName.equals("print")){

            // 设置为true 表示在控制台输出日志
            testCase.setPrint(true);
        }


        // JsonRsult
        if(qName.equals("JsonRsult")){

            String str1 = JsonRsult.replaceAll(" ", "");
            String str2 = str1.replaceAll("\r\n|\r|\n", "");
            // 设置属性
            result.setJsonRsult(str2);
        }

        // MockResult
        if(qName.equals("MockResult")){

            String str1 = mockResult.replaceAll(" ", "");
            String str2 = str1.replaceAll("\r\n|\r|\n", "");
            // 设置属性
            mMockResult.setMockResult(str2);
            mMockResultList.add(mMockResult);
        }


        // exe_sql  needSql
        if(qName.equals("exe_sql")){
            String str2 = needSql.replaceAll("\r\n|\r|\n", "").trim();
            sql.setExe_sql(str2.trim());
        }

        // sql_result
        if(qName.equals("sql_result")){
            String str1 = SQLResult.replaceAll(" ", "");
            String str2 = str1.replaceAll("\r\n|\r|\n", "");
            sql.setSql_result(str2);
        }

        // SQL
        if(qName.equals("SQL")){

            anAssert.setSql(sql);
        }

        // result
        if(qName.equals("result")){
            // 设置期望返回数据对象
            anAssert.setmResult(result);
            // 设置mock对象数据
            anAssert.setmMockResult(mMockResultList);
        }

        // Assert
        if(qName.equals("Assert")){

            testCase.setAnAssert(anAssert);
        }

        // call
        if(qName.equals("call")){

            testCase.setCall(call);
        }

        // case
        if(qName.equals("case")){

            // 加入到列表里面
            testCaseList.add(testCase);
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);


        value = new String(ch,start,length);

        // 打印 value
        System.out.print("获取的节点是:"+value+"=="+length);

        // 判断是不是 JsonRsult
        if(name.equals("JsonRsult")){

            JsonRsult += value;
        }
        // 判断是不是 JsonRsult
        if(name.equals("MockResult")){

            mockResult += value;
        }
        // 判断是不是 JsonRsult
        if(name.equals("sql_result")){

            SQLResult += value;
        }
        // needSql
        if(name.equals("exe_sql")){

            needSql += value;
        }
    }
}
