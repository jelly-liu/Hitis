package org.hitis.node;

import org.hitis.util.HitisUtil;

public class SqlAndValues {
    private String sql;
    private Object[] values;

    public SqlAndValues(){

    }

    public SqlAndValues(String sql, Object[] values) {
        this.sql = sql;
        this.values = values;
    }

    public String getSql() {
        return sql;
    }

    public SqlAndValues setSql(String sql) {
        this.sql = sql;
        return this;
    }

    public Object[] getValues() {
        return values;
    }

    public SqlAndValues setValues(Object[] values) {
        this.values = values;
        return this;
    }
}
