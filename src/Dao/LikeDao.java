package Dao;

import Dto.like.CommentLikeDto;
import Dto.like.PostLikeDto;
import Dto.user.UserDto;

import java.sql.*;
import java.util.ArrayList;

public class LikeDao {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/twitter_public";
    private String user = "root";
    private String password = "1234";

    /**
     * Adds a post like to the database.
     *
     * @param postLikeDto The PostLikeDto object containing user and post details.
     * @return true if the like is successfully added; false if it's a duplicate or an error occurs.
     */
    public boolean addPostLike(PostLikeDto postLikeDto){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO likes_post (user_id, post_id) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, postLikeDto.getUser_id());
            pstmt.setString(2, postLikeDto.getPost_id());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            e.printStackTrace();
        } return false; // 중복시 null
    }

    /**
     * Adds a comment like to the database.
     *
     * @param commentLikeDto The CommentLikeDto object containing user and comment details.
     * @return true if the like is successfully added; false if it's a duplicate or an error occurs.
     */
    public boolean addCommentLike(CommentLikeDto commentLikeDto){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO likes_comment (user_id, post_id) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, commentLikeDto.getUser_id());
            pstmt.setString(2, commentLikeDto.getComment_id());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            e.printStackTrace();
        } return false; // 중복시 null
    }

    /**
     * Retrieves the list of users who liked a particular post.
     *
     * @param post_id The ID of the post.
     * @return ArrayList of PostLikeDto objects representing users who liked the post.
     */
    public ArrayList<PostLikeDto> selectPostLike(String post_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<PostLikeDto> list = null; // 결과데이터를 담을 배열
        String sql = "select * from likes_post where='"+post_id+"'"; // sql문
        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {
            // 5. SQL 결과 처리
            list = new ArrayList<>();
            while (rs.next()){
                String user_id = rs.getString("user_id");
                String post_Id = rs.getString("post_id");
                PostLikeDto dto = new PostLikeDto(user_id, post_Id);

                list.add(dto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return list;
    }

    /**
     * Retrieves the list of users who liked a particular comment.
     *
     * @param comment_id The ID of the comment.
     * @return ArrayList of CommentLikeDto objects representing users who liked the comment.
     */
    public ArrayList<CommentLikeDto> selectCommentLike(String comment_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<CommentLikeDto> list = null; // 결과데이터를 담을 배열
        String sql = "select * from likes_comment where='"+comment_id+"'"; // sql문
        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {
            // 5. SQL 결과 처리
            list = new ArrayList<>();
            while (rs.next()){
                String user_id = rs.getString("user_id");
                String post_Id = rs.getString("comment_id");
                CommentLikeDto dto = new CommentLikeDto(user_id, post_Id);

                list.add(dto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return list;
    }
}
