package org.servlet.p2p_lottery.DAO;

import org.servlet.p2p_lottery.dbconnection.dbConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlacklistDAO {

    private static final String CHECK_BLACKLIST_QUERY = "SELECT COUNT(*) FROM blacklist WHERE name = ?";

    public static boolean isUserNameBlacklisted(String userName) {
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_BLACKLIST_QUERY)) {
            statement.setString(1, userName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            // Handle database access error
            e.printStackTrace(); // Consider logging the exception
        }
        return false;
    }
}
