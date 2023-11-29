package Dao;

import Dto.comment.CommentDto;
import Dto.comment.CommentOnDto;
import Dto.comment.MentionOnDto;
import Dto.post.PostDto;
import Dto.user.UserDto;

import java.sql.*;
import java.util.ArrayList;

public class CommentDao {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/twitter_public";
    private String user = "root";
    private String password = "1234";

    /**
     * Adds a comment to the post
     *
     * @param commentDto The CommentDto object containing comment details.
     * @return true if the comment is successfully added; false if the user is a duplicate or an error occurs.
     */
    public boolean addCommment(CommentDto commentDto){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "insert into comment(comment_id, content, commented_at, location) values (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, commentDto.getComment_id());
            pstmt.setString(2, commentDto.getContent());
            pstmt.setTimestamp(3, commentDto.getCommented_at());
            pstmt.setString(4, commentDto.getLocation());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            e.printStackTrace();
        } return false; // 유저중복시 null
    }


    /**
     * Adds a comment on a post.
     *
     * @param commentOnDto The DTO containing post, comment, and user details.
     * @return true if the comment is successfully added; false if it's a duplicate or an error occurs.
     */
    public boolean commentsOn(CommentOnDto commentOnDto){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "insert into comments_on (post_id, comment_id, user_id) values (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, commentOnDto.getPost_id());
            pstmt.setString(2, commentOnDto.getComment_id());
            pstmt.setString(3, commentOnDto.getUser_id());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            e.printStackTrace();
        } return false; // 유저중복시 null
    }

    /**
     * Retrieves CommentOnDto based on the comment_id.
     *
     * @param comment_id The ID of the comment.
     * @return CommentOnDto object if found; otherwise, null.
     */
    public CommentOnDto selectAllcommentOn(String comment_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select * from comments_on where comment_id ='"+comment_id+"'"; // sql문
        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {
            // 5. SQL 결과 처리
            while (rs.next()){
                String post_id = rs.getString("post_id");
                String comment_Id = rs.getString("comment_id");
                String user_id = rs.getString("user_id");
                return new CommentOnDto(post_id, comment_Id, user_id);

            }
        } catch (SQLException e){
            e.printStackTrace();
        } return null;
    }

    /**
     * Retrieves a list of comments based on the comment_id.
     *
     * @param comment_id The ID of the comment.
     * @return ArrayList of CommentDto objects if found; otherwise, an empty list.
     */
    public ArrayList<CommentDto> getComment(String comment_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<CommentDto> list = null; // 결과데이터를 담을 배열

        String sql = "select * from comment where user_id ='"+comment_id+"'"; // sql문
        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {
            // 5. SQL 결과 처리
            list = new ArrayList<>();
            while (rs.next()){
                String comment_Id = rs.getString("comment_id");
                Timestamp commented_at = rs.getTimestamp("commented_at");
                String location = rs.getString("location");
                String content = rs.getString("content");
                CommentDto dto = new CommentDto(comment_Id, commented_at, location, content);

                list.add(dto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return list; // 찾은 유저가 없는 경우 null 반환
    }
}
