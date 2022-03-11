package com.xuejungao.entity;

import java.util.List;

public class Assert {

    // 返回结果 期望
    private Result mResult;
    // 执行sql
    private SQL sql;
    // mock数据
    private List<MockResult> mMockResult;

    public List<MockResult> getmMockResult() {
        return mMockResult;
    }

    public void setmMockResult(List<MockResult> mMockResultList) {
        this.mMockResult = mMockResultList;
    }

    public Result getmResult() {
        return mResult;
    }

    public void setmResult(Result mResult) {
        this.mResult = mResult;
    }


    public SQL getSql() {
        return sql;
    }

    public void setSql(SQL sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "Assert{" +
                "mResult=" + mResult +
                ", sql=" + sql +
                ", mMockResult=" + mMockResult +
                '}';
    }
}
