package Dao;

import Dto.follow.FollowDto;
import Dto.user.UserDto;

import java.sql.*;
import java.util.ArrayList;

public class FollowDao {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/twitter_public";
    private String user = "root";
    private String password = "1234";

    /**
     * Retrieves a list of followers based on the following_id.
     *
     * @param following_id The ID of the user being followed.
     * @return ArrayList of follower IDs if found; otherwise, an empty list.
     */
    public ArrayList<String> selectfollowerList(String following_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> list = null; // 결과데이터를 담을 배열
        String sql = "select * from follows where following_id = '" + following_id + "'"; // sql문

        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {

            // 5. SQL 결과 처리
            list = new ArrayList<>();
            while (rs.next()){
                String follower_id = rs.getString("follower_id");

                list.add(follower_id);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return list;
    }

    /**
     * Retrieves a list of users being followed based on the follower_id.
     *
     * @param follower_id The ID of the follower user.
     * @return ArrayList of following user IDs if found; otherwise, an empty list.
     */
    public ArrayList<String> selectfollowingList(String follower_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> list = null; // 결과데이터를 담을 배열
        String sql = "select * from follows where follower_id = '" + follower_id + "'"; // sql문

        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {
            // 5. SQL 결과 처리
            list = new ArrayList<>();
            while (rs.next()){
                String following_id = rs.getString("following_id");

                list.add(following_id);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return list;
    }

    /**
     * Retrieves the entire content of the follows table.
     *
     * @return ArrayList of FollowDto objects representing the follows table content.
     */
    public ArrayList<FollowDto> selectfollowList(){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<FollowDto> list = null; // 결과데이터를 담을 배열
        String sql = "select * from follows"; // sql문
        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {
            // 5. SQL 결과 처리
            list = new ArrayList<>();
            while (rs.next()){
                String following_id = rs.getString("following_id");
                String follower_id = rs.getString("follower_id");
                FollowDto dto = new FollowDto(following_id, follower_id);

                list.add(dto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return list;
    }

    // 팔로잉 추가
}
