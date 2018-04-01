package com.olgaruban.dao;

import com.olgaruban.model.Role;
import com.olgaruban.model.User;
import com.olgaruban.service.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    private final Connection connection;

    public UserDaoImpl() {
        final Settings settings = Settings.getInstance();
        try {
            Class.forName(settings.value("jdbc.driver_class"));
            this.connection = DriverManager.getConnection(
                    settings.value("jdbc.url"),
                    settings.value("jdbc.username"),
                    settings.value("jdbc.password")
            );
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    public int add(User user) {
        try (final PreparedStatement statement = this.connection.prepareStatement(
                "insert into \"User\" (first_name, last_name, email, password, role) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRoleString());
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
        List<User> users = new ArrayList<>();
        try (final Statement statement = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             final ResultSet rs = statement.executeQuery("select * from \"User\"")) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("position"),
                        rs.getString("email"),
                        Role.valueOf(rs.getString("role"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void close() {

    }
}
