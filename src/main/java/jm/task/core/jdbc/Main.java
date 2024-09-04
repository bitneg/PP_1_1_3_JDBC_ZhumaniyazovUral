package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.logging.Logger;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
    final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
        UserService userService=new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Bobby","Doe", (byte) 12);
        userService.saveUser("Billy","Ducky", (byte) 31);
        userService.saveUser("Harry","Pet", (byte) 51);
        userService.saveUser("Marry","Davis", (byte) 17);
        logger.info(userService.getAllUsers().toString());
       userService.cleanUsersTable();
       userService.dropUsersTable();
    }
}
