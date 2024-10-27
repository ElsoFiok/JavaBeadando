package com.beadando.javabeadando.service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import org.springframework.stereotype.Service;
import java.sql.PreparedStatement;

@Service
public class UserService {
    private static final String URL = "jdbc:mysql://localhost:3306/feladat";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public boolean authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = UserService.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("User exists.");
                    return true;
                } else {
                    System.out.println("User doesnt exist.");
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean saveUser(String username, String password) {

        if (authenticate(username, password)) {
            System.out.println("User exists.");
            return false;
        }else {
            String query = "INSERT INTO users (password, username) VALUES (?, ?)";
            try (Connection connection = UserService.connect();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, password);
                preparedStatement.setString(2, username);
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " new user added.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}
