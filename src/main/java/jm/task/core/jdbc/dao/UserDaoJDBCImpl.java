package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection;

    {
        try {
            connection = Util.getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws ClassNotFoundException {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "CREATE TABLE Users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age tinyint)");
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public void dropUsersTable() {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "DROP table users");
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {

        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try  {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert Users (name, lastname, age) values (?, ?, ? )");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    public void removeUserById(long id) {
        try  {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try  {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            List<User> listOfUsers = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                listOfUsers.add(user);
            }
            return listOfUsers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM users WHERE id > 0");
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {

        }
    }
}
