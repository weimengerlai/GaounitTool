package com.xuejungao.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {


    // 遍历文件夹下面的所有文件
    // 返回文件夹符合的所有的文件路径
    public static List<File> getFileList(String strPath) {

        // 实例化 list
        List<File> filelist = new ArrayList<File>();

        // 获取FILE
        File dir = new File(strPath);
        // 得到所有的路径,数据列表
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        // 如果不为空,使用for循环进行遍历
        if (files != null) {
            // for 循环
            for (int i = 0; i < files.length; i++) {
                // 获取文件名字
                String fileName = files[i].getName();
                // 如果是文件夹,继续遍历
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("xml")) { // 判断文件名是否以.avi结尾
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("---" + strFileName);
                    filelist.add(files[i]);
                } else {
                    continue;
                }
            }

        }
        return filelist;
    }

}
