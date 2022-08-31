package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.dropUsersTable();
        userDao.createUsersTable();
        userDao.saveUser("ivan", "ivanov", (byte) 25);
        System.out.println(userDao.getAllUsers());
        UserDao userDao1 = new UserDaoHibernateImpl();
        userDao1.saveUser("Petr","petrov", (byte) 28);
    }
}
