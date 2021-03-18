package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/jm-1.1?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = con.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS `users` (\n" +
                    "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `lastName` VARCHAR(45) NULL,\n" +
                    "  `age` TINYINT NULL,\n" +
                    "  PRIMARY KEY (`id`))");

            PreparedStatement pr = con.prepareStatement("INSERT INTO users (name, lastName, age) Values (?, ?, ?)");
            User[] users = {new User("name0", "lastName0", (byte) 1),
                    new User("name1", "lastName1", (byte) 2),
                    new User("name2", "lastName2", (byte) 3),
                    new User("name3", "lastName3", (byte) 4)};
            for(User user : users) {
                pr.setString(1, user.getName());
                pr.setString(2, user.getLastName());
                pr.setByte(3, user.getAge());
                pr.execute();
                System.out.printf("User с именем – %s добавлен в базу данных%n", user.getName());
            }

            ResultSet resultSet = statement.executeQuery("select * from users");
            while(resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                System.out.println(user);
            }

            statement.executeUpdate("DELETE FROM users");
            statement.executeUpdate("DROP TABLE IF EXISTS users");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
