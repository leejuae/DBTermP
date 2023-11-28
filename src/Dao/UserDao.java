package Dao;

import Dto.user.UserDto;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/twitter_public";
    private String user = "root";
    private String password = "1234";

    //회원 전체 조회
    public ArrayList<UserDto> selectUserList(){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<UserDto> list = null; // 결과데이터를 담을 배열
        String sql = "select * from user"; // sql문
        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {
            // 5. SQL 결과 처리
            list = new ArrayList<>();
            while (rs.next()){
                String user_id = rs.getString("user_id");
                String pw = rs.getString("pw");
                String name = rs.getString("name");
                String profile_pic = rs.getString("profile_pic");
                String introduction = rs.getString("introduction");
                Timestamp created_at = rs.getTimestamp("created_at");
                UserDto dto = new UserDto(user_id, pw, name, profile_pic, introduction, created_at);

                list.add(dto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return list;
    }

    // 회원 상세 조회
    public UserDto selectUser(String user_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select * from user where user_id ='"+user_id+"'"; // sql문
        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {
            // 5. SQL 결과 처리
            while (rs.next()){
                String userId = rs.getString("user_id");
                String pw = rs.getString("pw");
                String name = rs.getString("name");
                String profile_pic = rs.getString("profile_pic");
                String introduction = rs.getString("introduction");
                Timestamp created_at = rs.getTimestamp("created_at");
                return new UserDto(userId, pw, name, profile_pic, introduction, created_at);

            }
        } catch (SQLException e){
            e.printStackTrace();
        } return null; // 찾은 유저가 없는 경우 null 반환
    }

    // 회원가입
    public boolean regUser(UserDto userDto){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO user (user_id, pw, name, profile_pic, introduction, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userDto.getUser_id());
            pstmt.setString(2, userDto.getPw());
            pstmt.setString(3, userDto.getName());
            pstmt.setString(4, userDto.getProfile_pic());
            pstmt.setString(5, userDto.getIntroduction());
            pstmt.setTimestamp(6, userDto.getCreated_at());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            e.printStackTrace();
        } return false; // 유저중복시 null
    }
}
