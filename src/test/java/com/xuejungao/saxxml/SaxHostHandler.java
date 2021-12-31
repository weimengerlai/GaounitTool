package com.xuejungao.saxxml;

import com.xuejungao.entity.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxHostHandler extends DefaultHandler {


    // 声明列表
    private List<Service> serviceList = new ArrayList<>();
    // 声明对象
    Service mService;

    // 提供对外访问的方法
    public List<Service> getServiceList(){

        return serviceList;
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

        // 标签遍历开始的时候实例化对象
        if(qName.equals("HTTP")){

            mService = new Service();

            // 获取属性个数
            int number = attributes.getLength();

            // 使用 for 循环遍历属性
            for (int i=0;i<number;i++){

                if(attributes.getQName(i).equals("id")){

                    // 获取值,进行设置
                    mService.setId(attributes.getValue(i));
                }

                if(attributes.getQName(i).equals("method")){

                    // 获取值,进行设置
                    mService.setMethod(attributes.getValue(i));
                }

                if(attributes.getQName(i).equals("url")){

                    // 获取值,进行设置
                    mService.setUrl(attributes.getValue(i));
                }

                if(attributes.getQName(i).equals("desc")){

                    // 获取值,进行设置
                    mService.setDesc(attributes.getValue(i));
                }
                if(attributes.getQName(i).equals("dunboPort")){

                    // 获取值,进行设置
                    mService.setDunboPort(attributes.getValue(i));
                }
                if(attributes.getQName(i).equals("dunboIp")){

                    // 获取值,进行设置
                    mService.setDunboIp(attributes.getValue(i));
                }
                if(attributes.getQName(i).equals("dubboPackage")){

                    // 获取值,进行设置
                    mService.setDubboPackage(attributes.getValue(i));
                }
                if(attributes.getQName(i).equals("paramType")){

                    // 获取值,进行设置
                    mService.setParamType(attributes.getValue(i));
                }
            }
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        // 结束遍历的时候将对象加入列表里面
        if(qName.equals("HTTP")){

            serviceList.add(mService);
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

    }
}
