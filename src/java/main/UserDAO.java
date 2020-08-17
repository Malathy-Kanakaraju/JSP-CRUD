package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 */
public class UserDAO {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    /* Establishes connection with MySQL */
    protected void connect() throws SQLException {
        if ((jdbcConnection == null) || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    /* Disconnects connection with MySQL */
    protected void disconnect() throws SQLException {
        if ((jdbcConnection != null) && (!jdbcConnection.isClosed())) {
            jdbcConnection.close();
        }
    }

    /* Inserts a new user */
    public boolean addUser(User user) throws SQLException {
        String sql = "INSERT INTO user (first_name, last_name, address) VALUES(?, ?, ?);";
        connect();

        PreparedStatement pstmt = jdbcConnection.prepareStatement(sql);
        pstmt.setString(1, user.getFirstName());
        pstmt.setString(2, user.getLastName());
        pstmt.setString(3, user.getAddress());

        boolean rowInserted = pstmt.executeUpdate() > 0;
        pstmt.close();
        disconnect();
        return rowInserted;
    }


    /* Inserts a new user */
    public void addUserMultiple(List<User> user) throws SQLException {
        connect();
        Statement pstmt = jdbcConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        for (User obj : user) {
            String sql = "INSERT INTO user (first_name, last_name, address) VALUES('"+obj.getFirstName()+"', '"+obj.getLastName()+"', '"+obj.getAddress()+"');";
            pstmt.addBatch(sql);
        }
        pstmt.executeBatch();
        pstmt.close();
        disconnect();
    }

    /* Show list of all users */
    public List<User> listUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user;";
        connect();

        Statement stmt = jdbcConnection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            userList.add(new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("address")));
        }
        rs.close();
        disconnect();
        return userList;
    }

    /* Deletes a user */
    public boolean deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM user WHERE id=?;";
        connect();

        PreparedStatement pstmt = jdbcConnection.prepareStatement(sql);
        pstmt.setInt(1, user.getId());

        boolean rowDeleted = pstmt.executeUpdate() > 0;
        pstmt.close();
        disconnect();
        return rowDeleted;
    }

    /* Update a user */
    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE user SET first_name = ?, last_name = ?, address = ? WHERE id=?;";
        connect();

        PreparedStatement pstmt = jdbcConnection.prepareStatement(sql);
        pstmt.setString(1, user.getFirstName());
        pstmt.setString(2, user.getLastName());
        pstmt.setString(3, user.getAddress());
        pstmt.setInt(4, user.getId());

        boolean rowUpdated = pstmt.executeUpdate() > 0;
        pstmt.close();
        disconnect();
        return rowUpdated;
    }

    /* Retrieve a user */
    public User getUser(int id) throws SQLException {
        String sql = "SELECT * FROM user WHERE id=?;";
        connect();
        User user = null;
        PreparedStatement pstmt = jdbcConnection.prepareStatement(sql);
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            user = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"));
        }
        pstmt.close();
        rs.close();
        disconnect();
        return user;
    }

}
