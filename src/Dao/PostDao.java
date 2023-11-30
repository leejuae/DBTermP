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

        String sql = "insert into post(post_id, content, posted_at, location, user_id) values (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, postDto.getPost_id());
            pstmt.setString(2, postDto.getContent());
            pstmt.setTimestamp(3, postDto.getTimeStamp());
            pstmt.setString(4, postDto.getLocation());
            pstmt.setString(5, postDto.getUser_id());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            e.printStackTrace();
        } return false; // 유저중복시 null
    }

    /**
     * Retrieves a list of posts for a specific user.
     *
     * @param user_id The ID of the user.
     * @return ArrayList of PostDto objects representing posts by the user.
     */
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
                String user_Id = rs.getString("user_id");
                PostDto dto = new PostDto(post_id, posted_at, content, location, user_id);

                list.add(dto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return list; // 찾은 유저가 없는 경우 null 반환
    }

    /**
     * Retrieves details of a specific post by its ID.
     *
     * @param post_id The ID of the post.
     * @return PostDto representing the specific post.
     */
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
                String user_id = rs.getString("user_id");
                return new PostDto(post_Id, posted_at, content, location, user_id);

            }
        } catch (SQLException e){
            e.printStackTrace();
        } return null; // 찾은 유저가 없는 경우 null 반환
    }

    /**
     * Retrieves details of all posts.
     *
     * @return ArrayList of PostDto representing all posts.
     */
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
                String user_id = rs.getString("user_id");
                PostDto dto = new PostDto(post_id, posted_at, content, location, user_id);

                list.add(dto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return list;
    }
}
