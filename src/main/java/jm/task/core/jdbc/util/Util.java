package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util  {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/new_schema",
                    "root",
                    "Poopk!n47mysql"
            );
            System.out.println("Подключились");
            return connection;
        } catch (SQLException e) {
            System.out.println("Непрокатило");;
        }
        return null;
    }


    // реализуйте настройку соеденения с БД
}
