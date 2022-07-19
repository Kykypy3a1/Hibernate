package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static void main(String[] args) {

    }

    public static void getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306";
        String username = "root";
        String password = "Risetotop_13";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("we're connected");
        }
    }
}
