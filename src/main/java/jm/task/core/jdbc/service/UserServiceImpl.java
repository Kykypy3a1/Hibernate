package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoHibernateImpl udj = new UserDaoHibernateImpl();

    public void createUsersTable() throws SQLException, ClassNotFoundException {
        udj.createUsersTable();
    }

    public void dropUsersTable() {
        udj.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        udj.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        udj.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return udj.getAllUsers();
    }

    public void cleanUsersTable() {
        udj.cleanUsersTable();
    }
}
