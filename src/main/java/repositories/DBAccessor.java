package repositories;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import util.Logger;
import static repositories.DBConfig.*;

/**
 * @author yuki.wakisaka
 */
public class DBAccessor {

    private static final DBAccessor instance = new DBAccessor();

    private static Logger logger = Logger.getLogger(DBAccessor.class.getSimpleName());

    public static DBAccessor getInstance() {
        return instance;
    }

    private static Connection getConnection() throws SQLException {
        logger.info("Start Connection: " + getUrl());
        return DriverManager.getConnection(getUrl(), getUser(), getPassword());
    }

    public Map<Integer, String> getUsers() throws SQLException {

        //Class.forName("com.mysql.jdbc.Driver"); // javaのversionによって要らない
        Map<Integer, String> result = new HashMap<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset = stmt.executeQuery("SELECT * FROM USERS;")) {
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            while (rset.next()) {
                result.put(rset.getInt(1), rset.getString(2));
            }
        }
        return result;
    }

    public int setUser(int id, String name) throws SQLException {

        int result;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT USERS VALUE(?, ?);")) {
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            result = pstmt.executeUpdate();
        }
        return result;
    }
    public int deleteUser(int id) throws SQLException {

        int result;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM USERS WHERE ID = ?;")) {
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();
        }
        return result;
    }

    public int editUser(int id, String name) throws SQLException {

        int result;

        try (Connection conn = getConnection();
             //String updateString = "update USERS" + "SET NAME = ? WHERE ID = ?";
             PreparedStatement pstmt = conn.prepareStatement("UPDATE USERS SET ? WHERE ?;")) {
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            result = pstmt.executeUpdate();
        }
        return result;
    }
}