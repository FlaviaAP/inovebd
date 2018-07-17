package com.i9.daos.impls;

import com.i9.daos.BaseDao;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDaoImp implements BaseDao {
    @Resource
    DataSource dataSourceDefault;

    @Override
    public void transactionQuery(String sql) throws SQLException {
        Connection connection = null;
        try {
            connection = createConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Couldn't create the connection");
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
    }
    @Override
    public ResultSet searchQuery(String sql) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = createConnection();

        } catch (SQLException e) {
            System.out.println("Couldn't create the connection");
            e.printStackTrace();
        }
        Statement statement = connection.createStatement();
        statement.execute(sql);
        resultSet = statement.getResultSet();
        return resultSet;
    }
    

    private Connection createConnection() throws SQLException{
        Connection connection = dataSourceDefault.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    public void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close the connection");
            e.printStackTrace();
        }
    }

    public void closeQuery(ResultSet resultSet){
        try {
            resultSet.getStatement().getConnection().close();
        } catch (SQLException e) {
            System.out.println("Couldn't close the connection");
            e.printStackTrace();
        }
    }
}
