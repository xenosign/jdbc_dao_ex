package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    static Connection conn = null;

    static {
        try {
            // 데이터베이스 연결 정보 문자열로 작성한 버전
            // DB 접속에 필요한 Driver 클래스를 지정하는 문자열
            String driver = "com.mysql.cj.jdbc.Driver";
            // DB 에서 어떤 데이터베이스에 접속할지 정하는 문자열, 우리는 user_ex 를 선택해야 한다
            String url = "jdbc:mysql://localhost:3306/user_ex";
            // 접속 계정은 root 를 사용하므로 id 는 root 로 설정, 비번은 각자의 비번에 맞게 입력
            String id = "root";
            String password = "1234";

            Class.forName(driver);
            conn = DriverManager.getConnection(url, id, password);

            if (conn != null) {
                System.out.println("DB 접속에 성공!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    // 사용자 정보를 데이터베이스에 삽입하는 메서드
    public void create(String email, String password) {
        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            int affetedRows = pstmt.executeUpdate();

            if (affetedRows > 0) {
                System.out.println("회원 추가 성공!");
            } else {
                System.out.println("회원 추가 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    // 모든 회원 정보를 조회하는 메서드
    public void getAllUsers() {
        List<UserVo> userList = new ArrayList<>();
        String sql = "SELECT id, email, password FROM users";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");

                UserVo user = new UserVo(id, email, password);
                userList.add(user);
            }
            userList.forEach((user) -> System.out.println(user));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
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
