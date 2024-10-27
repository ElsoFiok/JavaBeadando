package com.beadando.javabeadando.service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import org.springframework.stereotype.Service;
import java.sql.PreparedStatement;

@Service
public class UserService {
    private static final String URL = "jdbc:mysql://localhost:3306/feladat"; // Use your database URL
    private static final String USER = "root"; // Replace with your database username
    private static final String PASSWORD = ""; // Replace with your database password
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public boolean authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?"; // Parameterized query
        try (Connection connection = UserService.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check if a result is found, meaning credentials are valid
                if (resultSet.next()) {
                    System.out.println("User authenticated successfully.");
                    return true; // Set to true if credentials are valid
                } else {
                    System.out.println("Invalid username or password.");
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Invalid credentials
    }
    public void saveUser(String username, String password) {



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

    }
}
