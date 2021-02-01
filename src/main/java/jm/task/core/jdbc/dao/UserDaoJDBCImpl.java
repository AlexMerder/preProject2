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
    PreparedStatement preparedStatement = null;
    Statement statement = Util.getMySQLConnection().createStatement();



    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {
    }

    public void createUsersTable() throws SQLException, ClassNotFoundException {
        preparedStatement = Util.getMySQLConnection().prepareStatement("CREATE TABLE user(\n" +
                "    id bigint auto_increment primary key,\n" +
                "    name varchar(255),\n" +
                "    lastName varchar(255),\n" +
                "    age tinyint\n" +
                ");");
        preparedStatement.executeUpdate();
        System.out.println("База данных создана");
    }

    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE user;";
        try{
            statement.executeUpdate(sql);
        } catch (SQLException ignored){
        }

        System.out.println("База данных удалена");
    }
    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {
        preparedStatement = Util.getMySQLConnection().prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)");
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,lastName);
        preparedStatement.setInt(3,age);
        preparedStatement.executeUpdate();
        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException, ClassNotFoundException {
        preparedStatement = Util.getMySQLConnection().prepareStatement("DELETE FROM user  WHERE id = (?);");
        preparedStatement.setInt(1, (int) id);
        preparedStatement.executeUpdate();
        System.out.println("User под номером " + id + " удален");
    }

    public List<User> getAllUsers() throws SQLException {
        String sql = "Select id,name,lastName,age from user";
        List<User> list = new ArrayList<>();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            Long userId = rs.getLong(1);
            String userName = rs.getString(2);
            String userLastName = rs.getString(3);
            byte userAge = rs.getByte(4);
            list.add(new User(userId, userName,userLastName,userAge));
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "delete from user";
        statement.executeUpdate(sql);
        System.out.println("Таблица очищена");
    }
}
