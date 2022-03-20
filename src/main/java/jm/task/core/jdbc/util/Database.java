package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database db = new Database();
    private Connection conn;

    static {
        Database db = Database.getInstance();
        db.connect("testDatabase");
    }

    private Database() {}

    public Connection getConnection() {
        return conn;
    }

    public static Database getInstance() {
        return db;
    }

    public void connect(String database) {

        String server = "localhost";
        String port = "3306";
        String user = "secretuser";
        String pass = "456654";

        String url = String.format("jdbc:mysql://%s:%s/%s", server, port, database);

        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void disconnect() throws SQLException {
        conn.close();
    }

}