package com.olgaruban.dao;

import com.olgaruban.model.User;
import com.olgaruban.service.Settings;

import java.sql.*;
import java.util.List;

public class UserDaoImpl implements UserDao{
    private final Connection connection;

    public UserDaoImpl() {
        final Settings settings = Settings.getInstance();
        try {
            Class.forName(settings.value("jdbc.driver_class"));
            this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    public int add(User user) {
        try (final PreparedStatement statement = this.connection.prepareStatement("insert into \"User\" (first_name, last_name, email, password) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not create new user");
    }

    public boolean deleteById(int id) {
        return false;
    }

    public User update(User user) {
        return null;
    }

    public List<User> getList() {
        return null;
    }

    public void close() {

    }
}
