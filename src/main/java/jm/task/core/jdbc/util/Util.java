package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util  {
    public static SessionFactory setUp() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLInnoDBDialect");
        configuration.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/new_schema");
        configuration.setProperty("hibernate.connection.driver_class","com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.username","root");
        configuration.setProperty("hibernate.connection.password","Poopk!n47mysql");
        configuration.setProperty("show_sql","true");
        configuration.setProperty("hibernate.hbm2ddl.auto","update");
        configuration.addAnnotatedClass(User.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(registry);
    }
    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/new_schema",
                    "root",
                    "Poopk!n47mysql"
            );
            System.out.println("Подключились");
            return connection;
        } catch (SQLException e) {
            System.out.println("Непрокатило");
        }
        return null;
    }


    // реализуйте настройку соеденения с БД
}
