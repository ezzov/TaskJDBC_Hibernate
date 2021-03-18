package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserDao userJDBC = new UserDaoJDBCImpl();

        userJDBC.createUsersTable();
        userJDBC.saveUser("name0", "lastName0", (byte) 1);
        userJDBC.saveUser("name1", "lastName1", (byte) 2);
        userJDBC.saveUser("name2", "lastName2", (byte) 3);
        userJDBC.saveUser("name3", "lastName3", (byte) 4);
        List<User> list = userJDBC.getAllUsers();
        for(User user : list) {
            System.out.println(user);
        }
        userJDBC.cleanUsersTable();
        userJDBC.dropUsersTable();

        UserDao userHibernate = new UserDaoHibernateImpl();

        userHibernate.createUsersTable();
        userHibernate.saveUser("name0", "lastName0", (byte) 1);
        userHibernate.saveUser("name1", "lastName1", (byte) 2);
        userHibernate.saveUser("name2", "lastName2", (byte) 3);
        userHibernate.saveUser("name3", "lastName3", (byte) 4);
        List<User> listH = userJDBC.getAllUsers();
        for(User user : listH) {
            System.out.println(user);
        }
        userHibernate.cleanUsersTable();
        userHibernate.dropUsersTable();




    }
}
