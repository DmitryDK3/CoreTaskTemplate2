package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("john","sss",(byte) 12);
        userDaoJDBC.saveUser("cindy","fsfs",(byte) 112);
        userDaoJDBC.saveUser("pavel","dfffff",(byte) 23);
        userDaoJDBC.saveUser("ivan","bbbb",(byte) 75);
        List<User> users = userDaoJDBC.getAllUsers();
        for (User u : users) {
            System.out.println(u);
        }
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
