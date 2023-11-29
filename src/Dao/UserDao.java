package Dao;

import Dto.user.UserDto;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/twitter_public";
    private String user = "root";
    private String password = "15371537";

    /**
     * Retrieves details of all users.
     *
     * @return ArrayList of UserDto representing all users.
     */
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

    /**
     * Retrieves details of a specific user by their ID.
     *
     * @param user_id The ID of the user.
     * @return UserDto representing the specific user.
     */
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

    /**
     * Registers a new user.
     *
     * @param userDto The UserDto object containing user details.
     * @return true if registration is successful, false otherwise (such as duplicate user).
     */
    public boolean regUser(UserDto userDto){
        // Check for duplicate user before inserting
        if (isUserDuplicate(userDto.getUser_id())) {
            // Handle duplicate user error
            System.out.println("Error: Duplicate user");
            return false;
        }
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

    /**
     * Changes the password for a specific user.
     *
     * @param newPassword The new password.
     * @param user_id     The ID of the user.
     * @return true if password change is successful, false otherwise.
     */
    public boolean changePwUser(String newPassword, String user_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "update user set pw = ? where user_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPassword);
            pstmt.setString(2, user_id);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            e.printStackTrace();
        } return false;
    }

    /**
     * Checks if the user already exists in the database.
     *
     * @param user_id The ID of the user to check.
     * @return true if the user already exists; false otherwise.
     */
    private boolean isUserDuplicate(String user_id) {
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
                return true;

            }
        } catch (SQLException e){
            e.printStackTrace();
        } return false;
    }
}
