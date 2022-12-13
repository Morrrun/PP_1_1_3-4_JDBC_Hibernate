package jm.task.core.jdbc.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String HOST = "jdbc:mysql://localhost:3306/dbtest";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private final Connection connection;

    public Util() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
