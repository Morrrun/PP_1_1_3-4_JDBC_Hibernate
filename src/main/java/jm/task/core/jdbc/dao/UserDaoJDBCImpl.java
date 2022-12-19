package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util CON = new Util();

    private final String nameTable = "users";
    private final String DROP_TABLE = "DROP TABLE ";
    private final String CREATE_TABLE = "CREATE TABLE " + nameTable + "" +
            "(" +
            "    id       INT auto_increment PRIMARY KEY," +
            "    name     VARCHAR(100) NOT NULL," +
            "    lastname VARCHAR(100) NOT NULL," +
            "    age      INT          NOT NULL" +
            ")" +
            "    collate = utf8_general_ci";

    private final String INSERT_INTO_PREPARED = "INSERT INTO " + nameTable + " (name, lastname, age) VALUES(?, ?, ?)";
    private final String GET_DATA = "SELECT * FROM " + nameTable + "";
    private final String DELETE_USER_BY_ID = "DELETE FROM " + nameTable + " WHERE id = ?";
    private final String CLEAN_TABLE = "DELETE FROM " + nameTable + "";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = CON.getConnection().createStatement();
             PreparedStatement preparedStatement = CON.getConnection()
                     .prepareStatement(CREATE_TABLE)) {

            ResultSet res = statement.executeQuery("SHOW TABLES");

            if (res.next() && res.getString(1).equals("users")) {
                System.out.println("Таблица уже существует!");
            } else {
                preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = CON.getConnection().createStatement();
             PreparedStatement preparedStatement = CON.getConnection().prepareStatement(DROP_TABLE + nameTable)) {

            ResultSet res = statement.executeQuery("SHOW TABLES");

            if (res.next() && res.getString(1).equals("users")) {
                preparedStatement.executeUpdate();
            } else {
                System.out.println("Таблица не существует!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = CON.getConnection().prepareStatement(INSERT_INTO_PREPARED)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            System.out.println("User с именем - " + name + " добавлен в базу данных");

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = CON.getConnection().prepareStatement(DELETE_USER_BY_ID)) {
            preparedStatement.setInt(1, (int) id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userArrayList = new ArrayList<>();
        try (PreparedStatement preparedStatement = CON.getConnection().prepareStatement(GET_DATA)) {
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                User user = new User(res.getString("name"),
                        res.getString("lastname"),
                        res.getByte("age"));
                user.setId(res.getLong("id"));
                userArrayList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userArrayList;
    }

    public void cleanUsersTable()  {
        try (PreparedStatement preparedStatement = CON.getConnection()
                .prepareStatement(CLEAN_TABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
