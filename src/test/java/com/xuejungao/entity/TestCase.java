package com.xuejungao.entity;

public class TestCase {

    // 测试用例唯一标识符
    private String id;
    // 测试用例描述
    private String desc;
    // 测试用例 Tag
    private String tag;
    // 请求参数
    private Call call;
    // 是否输出log null flase 表示不输出 true 输出
    private boolean isPrint;
    //返回结果对象
    private Assert anAssert;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public boolean isPrint() {
        return isPrint;
    }

    public void setPrint(boolean print) {
        isPrint = print;
    }

    public Assert getAnAssert() {
        return anAssert;
    }

    public void setAnAssert(Assert anAssert) {
        this.anAssert = anAssert;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "id='" + id + '\'' +
                ", desc='" + desc + '\'' +
                ", tag='" + tag + '\'' +
                ", call=" + call +
                ", isPrint=" + isPrint +
                ", anAssert=" + anAssert +
                '}';
    }
}
