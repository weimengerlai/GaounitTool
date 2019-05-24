package com.xuejungao.entity;

public class SQL {

    private String database;
    private String exe_sql;
    private String sql_result;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getExe_sql() {
        return exe_sql;
    }

    public void setExe_sql(String exe_sql) {
        this.exe_sql = exe_sql;
    }

    public String getSql_result() {
        return sql_result;
    }

    public void setSql_result(String sql_result) {
        this.sql_result = sql_result;
    }

    @Override
    public String toString() {
        return "SQL{" +
                "database='" + database + '\'' +
                ", exe_sql='" + exe_sql + '\'' +
                ", sql_result='" + sql_result + '\'' +
                '}';
    }
}
