package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.SQLException;
import java.util.logging.Level;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);


         UserServiceImpl userService = new UserServiceImpl();
//         userService.dropUsersTable();
         userService.createUsersTable();
         userService.saveUser("Alex","dad",(byte) 27);
         userService.saveUser("Bob","dfgffd",(byte) 34);
         userService.saveUser("Fred","drrd",(byte) 22);
         userService.saveUser("Mack","rwd",(byte) 54);
         System.out.println(userService.getAllUsers());
         userService.cleanUsersTable();
         userService.dropUsersTable();
    }
}
