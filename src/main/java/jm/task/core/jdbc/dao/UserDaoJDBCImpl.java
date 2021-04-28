package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users(id INT PRIMARY KEY AUTO_INCREMENT , name varchar(255), lastName varchar(255), age INT  )");
            statement.executeUpdate();
            System.out.println("Таблица создана");
        } catch (SQLException es) {
            System.out.println("Непрокатило с созданием");
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DROP TABLE IF EXISTS users");
            statement.executeUpdate();
            System.out.println("Таблица удалена");
        } catch (SQLException es) {
            System.out.println("Непрокатило с удалением");
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT users(name, LastName, age) VALUES ('"+name+ "','"+lastName+"', "+age+")");
            statement.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных", name);
        } catch (SQLException es) {
            System.out.println("Непрокатило с добавлением");
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = "+id);
            statement.executeUpdate();
            System.out.printf("Юзер %d удален",id);
        } catch (SQLException es) {
            System.out.println("Непрокатило с удалением");
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = Util.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                byte age = (byte) rs.getInt("age");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                User user = new User(name, lastName, age);
                users.add(user);

            }

        } catch (SQLException es) {
            System.out.println("ОШибка получения юзеров");
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE users");
            System.out.println("Таблица юзеров очищена");
        } catch (SQLException es) {
            System.out.println("ОШибка очистки таблицы юзеров");
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
