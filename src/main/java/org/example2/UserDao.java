package org.example2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    // 사용자 정보를 데이터베이스에 삽입하는 메서드
    public void create(UserVo newUser) {
        try {
            // 1. Driver 커넥터 설정
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            System.out.println("1. 드라이버 설정 OK");

            // 2. DB 연결
            String url = "jdbc:mysql://localhost:3306/user_ex";
            String id = "root";
            String password = "1234";
            Connection conn = DriverManager.getConnection(url, id, password);

            // 3. SQL 문 생성
            String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newUser.getEmail());
            pstmt.setString(2, newUser.getPassword());

            int affetedRows = pstmt.executeUpdate();

            if (affetedRows > 0) {
                System.out.println("회원 추가 성공!");
            } else {
                System.out.println("회원 추가 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 모든 회원 정보를 조회하는 메서드
    public void getAllUsers() {
        try {
            // 1. Driver 커넥터 설정
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            System.out.println("1. 드라이버 설정 OK");

            // 2. DB 연결
            String url = "jdbc:mysql://localhost:3306/user_ex";
            String id = "root";
            String password = "1234";
            Connection conn = DriverManager.getConnection(url, id, password);

            List<UserVo> userList = new ArrayList<>();
            String sql = "SELECT id, email, password FROM users";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int userid = rs.getInt("id");
                String email = rs.getString("email");
                String userpassword = rs.getString("password");

                UserVo user = new UserVo(userid, email, userpassword);
                userList.add(user);
            }
            userList.forEach((user) -> System.out.println(user));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 회원 정보를 수정하는 메서드
    public void updateUser(int id, String newEmail, String newPassword) {
        String sql = "UPDATE users SET email = ?, password = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newEmail);
            pstmt.setString(2, newPassword);
            pstmt.setInt(3, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("회원 정보 수정 성공!");
            } else {
                System.out.println("회원 정보 수정 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 회원 정보를 삭제하는 메서드
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("회원 삭제 성공!");
            } else {
                System.out.println("회원 삭제 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 테이블을 합친 뒤, 회원의 이름 정보까지 전부 출력하는 메서드
    public void getAllUsersWithName() {
        String sql = "SELECT users.id, users.email, users.password, user_info.name " +
                "FROM users " +
                "JOIN user_info ON users.id = user_info.id";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String name = rs.getString("name");

                System.out.printf("ID: %d, Email: %s, Password: %s, Name: %s%n", id, email, password, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
