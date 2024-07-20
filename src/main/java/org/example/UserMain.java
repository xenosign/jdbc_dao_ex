package org.example;

public class UserMain {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

//        userDao.create("tetz2", "1234");
        userDao.getAllUsers();

    }
}
