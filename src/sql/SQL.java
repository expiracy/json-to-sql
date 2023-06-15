package sql;

import java.sql.*;

public class SQL {
    private Connection connection;

    public SQL(String url) throws SQLException {
        this.connection = DriverManager.getConnection(url);
    }

    public void createTable(String name) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement("CREATE DATABASE ?");
        statement.setString(1, name);
        statement.executeUpdate();
    }
}
