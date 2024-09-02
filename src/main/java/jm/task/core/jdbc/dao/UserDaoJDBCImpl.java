package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends User implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50), age TINYINT, PRIMARY KEY (id))";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            /*
            preparedStatement.setLong(1, getId());
            preparedStatement.setString(2, getName());
            preparedStatement.setString(3, getLastName());
            preparedStatement.setByte(4, getAge());
            */
            preparedStatement.executeUpdate();
            System.out.println("Таблица 'users' создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS users";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE ID=?";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();

        String sql = "SELECT * FROM users";
        Statement statement = null;
        try {
            Connection connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    public void cleanUsersTable() {

        String sql = "TRUNCATE TABLE users";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

