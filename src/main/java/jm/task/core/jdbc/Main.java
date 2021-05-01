package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("john","sss",(byte) 12);
        userService.saveUser("cindy","fsfs",(byte) 112);
        userService.saveUser("pavel","dfffff",(byte) 23);
        userService.saveUser("ivan","bbbb",(byte) 75);
        List<User> users = userService.getAllUsers();
        for (User u : users) {
            System.out.println(u);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
