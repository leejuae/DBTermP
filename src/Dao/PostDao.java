package Dao;

import Dto.post.PostDto;
import Dto.user.UserDto;

import java.sql.*;
import java.util.ArrayList;

public class PostDao {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/twitter_public";
    private String user = "root";
    private String password = "1234";
    // 글쓰기
    public boolean writePost (PostDto postDto){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "insert into post(post_id, content, posted_at, location) values (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, postDto.getPost_id());
            pstmt.setString(2, postDto.getContent());
            pstmt.setTimestamp(3, postDto.getTimeStamp());
            pstmt.setString(4, postDto.getLocation());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            e.printStackTrace();
        } return false; // 유저중복시 null
    }

    // 특정 유저의 포스트 목록
    public ArrayList<PostDto> selectAllSpecificUserPost(String user_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<PostDto> list = null; // 결과데이터를 담을 배열

        String sql = "select * from post where user_id ='"+user_id+"'"; // sql문
        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {
            // 5. SQL 결과 처리
            list = new ArrayList<>();
            while (rs.next()){
                String post_id = rs.getString("post_id");
                Timestamp posted_at = rs.getTimestamp("posted_at");
                String content = rs.getString("content");
                String location = rs.getString("location");
                PostDto dto = new PostDto(post_id, posted_at, content, location);

                list.add(dto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return list; // 찾은 유저가 없는 경우 null 반환
    }

    // 특정 포스트
    public PostDto selectOnePost(String post_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select * from post where user_id ='"+post_id+"'"; // sql문
        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {
            // 5. SQL 결과 처리
            while (rs.next()){
                String post_Id = rs.getString("post_id");
                Timestamp posted_at = rs.getTimestamp("posted_at");
                String content = rs.getString("content");
                String location = rs.getString("location");
                return new PostDto(post_Id, posted_at, content, location);

            }
        } catch (SQLException e){
            e.printStackTrace();
        } return null; // 찾은 유저가 없는 경우 null 반환
    }

    // 전체 포스트 목록
    public ArrayList<PostDto> selectAllPost(){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<PostDto> list = null; // 결과데이터를 담을 배열
        String sql = "select * from post order by posted_at desc"; // sql문
        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {
            // 5. SQL 결과 처리
            list = new ArrayList<>();
            while (rs.next()){
                String post_id = rs.getString("post_id");
                Timestamp posted_at = rs.getTimestamp("posted_at");
                String content = rs.getString("content");
                String location = rs.getString("location");
                PostDto dto = new PostDto(post_id, posted_at, content, location);

                list.add(dto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return list;
    }
}
