package com.xuejungao.entity;

import java.util.LinkedHashMap;

public class Call {

    private String service;
    private LinkedHashMap<String,String> map;


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public LinkedHashMap<String, String> getMap() {
        return map;
    }

    public void setMap(LinkedHashMap<String, String> map) {
        this.map = map;
    }
}
