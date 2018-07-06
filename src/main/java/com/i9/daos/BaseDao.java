package com.i9.daos;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BaseDao {

    void transactionQuery(String sql) throws SQLException;

    ResultSet searchQuery(String sql) throws SQLException;

    public void closeQuery(ResultSet resultSet);
}
