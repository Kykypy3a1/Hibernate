package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao1 = new UserDaoHibernateImpl();
        userDao1.createUsersTable();
        userDao1.saveUser("ivan", "petrov", (byte) 24);
        userDao1.saveUser("petr", "ivanov", (byte) 14);
        userDao1.saveUser("name1", "petrov", (byte) 35);
        userDao1.saveUser("ilya", "sidorov", (byte) 18);
        userDao1.removeUserById(7);
        userDao1.getAllUsers();
        userDao1.cleanUsersTable();
        userDao1.dropUsersTable();
    }
}
