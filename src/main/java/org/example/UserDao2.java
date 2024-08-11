//package org.example;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDao2 {
//    static Connection conn = null;
//
//    static {
//        try {
//            // 데이터베이스 연결 정보 문자열로 작성한 버전
//            // DB 접속에 필요한 Driver 클래스를 지정하는 문자열
//            String driver = "com.mysql.cj.jdbc.Driver";
//            // DB 에서 어떤 데이터베이스에 접속할지 정하는 문자열, 우리는 user_ex 를 선택해야 한다
//            String url = "jdbc:mysql://localhost:3306/user_ex";
//            // 접속 계정은 root 를 사용하므로 id 는 root 로 설정, 비번은 각자의 비번에 맞게 입력
//            String id = "root";
//            String password = "1234";
//
//            Class.forName(driver);
//            conn = DriverManager.getConnection(url, id, password);
//
//            if (conn != null) {
//                System.out.println("DB 접속에 성공!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void create (String email, String password){
//        String sql = "insert into user (email,password) values(?,?)";
//        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
//            pstmt.setString(1, email);
//            pstmt.setString(2, password);
//
//            int affectedRows = pstmt.executeUpdate();
//
//            if(affectedRows > 0){
//                System.out.println("회원정보 입력 성공");
//            } else {
//                System.out.println("회원정보 입력 실패");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void getAllUsers(){
//        List<UserVo> userList = new ArrayList<>();
//        String sql = "select * from users";
//
//        try(Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql)){
//
//            while(rs.next()){
//                int id = rs.getInt("id");
//                String email = rs.getString("email");
//                String password = rs.getNString("password");
//
//
//
//            }
//
//        }
//    }
//}
