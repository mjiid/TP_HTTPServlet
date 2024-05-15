package org.servlet.p2p_lottery.DAO;

import org.servlet.p2p_lottery.dbconnection.dbConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LotteryDAO {

    private static final String INSERT_USER_WINNINGS = "INSERT INTO lottery_winnings.users (name, winnings) VALUES (?, ?)";
    private static final String SELECT_USER_WINNINGS = "SELECT winnings FROM lottery_winnings.users WHERE name = ?";

    public static void saveUserWinnings(String userName, double winnings) {
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_WINNINGS)) {
            statement.setString(1, userName);
            statement.setDouble(2, winnings);
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle database access error
            e.printStackTrace(); // Consider logging the exception
        }
    }

    public static double getUserWinnings(String userName) {
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_WINNINGS)) {
            statement.setString(1, userName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("winnings");
                }
            }
        } catch (SQLException e) {
            // Handle database access error
            e.printStackTrace(); // Consider logging the exception
        }
        return 0.0; // Default return value if user not found or error occurred
    }
}
