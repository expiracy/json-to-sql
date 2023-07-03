package sql;

import java.sql.*;

public class SqlManager {
    private Connection connection;

    public SqlManager(String url) throws SQLException {
        this.connection = DriverManager.getConnection(url);
    }

    public void createTable(String name) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement("CREATE DATABASE ?");
        statement.setString(1, name);
        statement.executeUpdate();
    }
}
