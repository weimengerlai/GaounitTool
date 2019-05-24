package com.xuejungao.entity;

public class Assert {

    private Result mResult;
    private SQL sql;

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
                '}';
    }
}
