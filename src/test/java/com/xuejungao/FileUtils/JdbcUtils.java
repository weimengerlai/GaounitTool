package com.xuejungao.FileUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xuejungao.dao.LoginMapperDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JdbcUtils {

    // 加载链接数据库的方法
    public static List<String> SelectSQl(String sql,String path){
        // 声明列表类型
        List<String> jsonList = new ArrayList<>();

        // 打印路径
        System.out.println("当前路径是:"+path);


        // 使用流读取
        File file = new File(path);
        // 使用file刘
        try {
            // 读取内容返回流
            InputStream inputStream = new FileInputStream(file);
            // 获取 sesstion 工厂
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
            // 火球sesstion
            SqlSession sqlSession = factory.openSession();

            // 获取
            LoginMapperDao loginMapperDao = sqlSession.getMapper(LoginMapperDao.class);

            // 执行查询数据库的语句
            List<Map>  mapList = loginMapperDao.findLoginSql(sql);
            // 打印
//            System.out.println("当前的长度是:"+mapList.size());
            // 对map进行遍历
            for(Map map : mapList){
                // 实例化 josnobject
                JsonObject jsonObject = new JsonObject();
                // 迭代器
                Iterator iterator = map.entrySet().iterator();
                // 迭代 遍历
                while (iterator.hasNext()){

                    Map.Entry entry = (Map.Entry) iterator.next();
                    // 获取key
                    Object key = entry.getKey();
                    // 获取值
                    Object value = entry.getValue();

//                    System.out.println("key==="+key+"value====="+value);
                    // 加入到对象里面
                    jsonObject.addProperty(key+"",value+"");
                }
                // 加入到List里面
                jsonList.add(new Gson().toJson(jsonObject));

            }
            return jsonList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return jsonList;
    }
}
