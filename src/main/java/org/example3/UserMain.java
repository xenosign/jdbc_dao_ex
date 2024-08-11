package org.example3;

public class UserMain {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        // 이름이 출력 안되는 회원 조회 메서드
        userDao.getAllUsers();
        // 이름이 출력되는 회원 조회 메서드
        userDao.getAllUsersWithName();
    }
}
