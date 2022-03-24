package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

//        Database db = Database.getInstance();
//        db.connect("testDatabase");

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Timur", "Pekhaev", (byte) 15);
        System.out.println("User с именем " + userService.getAllUsers().get(0).getName() + " был добавлен в базу данных");

        userService.saveUser("James", "Kingston", (byte) 29);
        System.out.println("User с именем " + userService.getAllUsers().get(1).getName() + " был добавлен в базу данных");

        userService.saveUser("Ivan", "Ivanov", (byte) 47);
        System.out.println("User с именем " + userService.getAllUsers().get(2).getName() + " был добавлен в базу данных");

        userService.saveUser("John", "Purcell", (byte) 33);
        System.out.println("User с именем " + userService.getAllUsers().get(3).getName() + " был добавлен в базу данных");

//        userService.removeUserById(2);

        userService.getAllUsers().forEach(x -> System.out.println(x));

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
