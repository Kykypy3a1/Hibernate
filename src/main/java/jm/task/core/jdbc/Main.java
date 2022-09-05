package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("ivan","petrov", (byte) 24);
        userService.saveUser("petr","ivanov", (byte) 14);
        userService.saveUser("name1","petrov", (byte) 35);
        userService.saveUser("ilya","sidorov", (byte) 18);
        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
