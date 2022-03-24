package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {}

    @Override
    public void createUsersTable() {
        var conn = Database.getInstance().getConnection();

        try (var stmt = conn.createStatement()) {
            conn.setAutoCommit(false);

            stmt.executeUpdate("create table if not exists users (id int primary key AUTO_INCREMENT," +
                    " name TEXT, lastName TEXT, age TINYINT)");

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void dropUsersTable() {
        var conn = Database.getInstance().getConnection();

        try (var stmt = conn.createStatement()) {
            conn.setAutoCommit(false);

            stmt.executeUpdate("drop table if exists users");

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        var conn = Database.getInstance().getConnection();

        try (var stmt = conn.prepareStatement("insert into users (name, lastName, age) values (?, ?, ?)")) {
            conn.setAutoCommit(false);

            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);

            stmt.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        var conn = Database.getInstance().getConnection();

        try (var stmt = conn.prepareStatement("delete from users where id=?")) {
            conn.setAutoCommit(false);

            stmt.setLong(1, id);

            stmt.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        var conn = Database.getInstance().getConnection();

        try (var stmt = conn.createStatement()) {
            conn.setAutoCommit(false);

            var rs = stmt.executeQuery("select id, name, lastName, age from users order by id");

            while (rs.next()) {
                var id = rs.getLong("id");
                var name = rs.getString("name");
                var secondName = rs.getString("lastName");
                var age = rs.getByte("age");

                users.add(new User(id, name, secondName, age));
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {

        var conn = Database.getInstance().getConnection();

        try (var stmt = conn.createStatement()) {
            conn.setAutoCommit(false);

            stmt.executeUpdate("TRUNCATE table users");

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
