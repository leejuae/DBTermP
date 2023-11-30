package Dao;

import Dto.comment.CommentDto;
import Dto.comment.CommentOnDto;
import Dto.comment.MentionOnDto;
import Dto.post.PostDto;
import Dto.user.UserDto;

import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

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

        String sql = "insert into comment(comment_id, content, commented_at, location, post_id, user_id) values (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, commentDto.getComment_id());
            pstmt.setString(2, commentDto.getContent());
            pstmt.setTimestamp(3, commentDto.getCommented_at());
            pstmt.setString(4, commentDto.getLocation());
            pstmt.setString(5, commentDto.getPost_id());
            pstmt.setString(6, commentDto.getUser_id());

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

        String sql = "insert into comments_on (post_id, comment_id, user_id, parent_id, date, content) values (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, commentOnDto.getPost_id());
            pstmt.setString(2, commentOnDto.getComment_id());
            pstmt.setString(3, commentOnDto.getUser_id());
            pstmt.setString(3, commentOnDto.getParent_id());
            pstmt.setTimestamp(3, commentOnDto.getDate());
            pstmt.setString(3, commentOnDto.getContent());

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
    public ArrayList<CommentOnDto> selectAllcommentOn(String comment_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<CommentOnDto> list = null; // 결과데이터를 담을 배열

        String sql = "select * from comments_on where comment_id ='"+comment_id+"'order by date desc"; // sql문
        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {
            // 5. SQL 결과 처리
            list = new ArrayList<>();
            while (rs.next()){
                String post_id = rs.getString("post_id");
                String comment_Id = rs.getString("comment_Id");
                String user_id = rs.getString("user_id");
                String parent_id = rs.getString("parent_id");
                Timestamp date = rs.getTimestamp("date");
                String content = rs.getString("content");
                CommentOnDto dto = new CommentOnDto(post_id, comment_Id, user_id, parent_id, date, content);

                list.add(dto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return list; // 찾은 유저가 없는 경우 null 반환
    }

    /**
     * Retrieves a list of comments based on the comment_id.
     *
     * @param comment_id The ID of the comment.
     * @return ArrayList of CommentDto objects if found; otherwise, an empty list.
     */
    public CommentDto getComment(String comment_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select * from comment where comment_id ='"+comment_id+"'"; // sql문
        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {
            // 5. SQL 결과 처리
            while (rs.next()){
                String comment_Id = rs.getString("comment_id");
                Timestamp commented_at = rs.getTimestamp("commented_at");
                String location = rs.getString("location");
                String content = rs.getString("content");
                String post_id = rs.getString("post_id");
                String user_id = rs.getString("user_id");

                return new CommentDto(comment_Id, commented_at, location, content, post_id, user_id);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return null;
    }

    public ArrayList<CommentDto> getAllComment(String comment_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<CommentDto> list = null; // 결과데이터를 담을 배열

        String sql = "select * from comment where comment_id ='"+comment_id+"'order by date desc"; // sql문
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
                String post_id = rs.getString("post_id");
                String user_id = rs.getString("user_id");
                CommentDto dto = new CommentDto(comment_Id, commented_at, location, content, post_id, user_id);

                list.add(dto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return list; // 찾은 유저가 없는 경우 null 반환
    }

    public int WriteChildComment(String comment_id, String user_id, String parent_id, String content)
    {
        Statement stmt = null;
        ResultSet rs = null;



        System.out.println("write child comment");


        try(Connection connection= DriverManager.getConnection(url, user, password);){
            stmt = connection.createStatement();

            String q1 = "select * from comment where comment_id = \"" + parent_id + "\";";
            rs = stmt.executeQuery(q1);

            if(!rs.next())
            {
                return 0;
            }


            q1 = "insert into comment_on values(comment_id, \"" + parent_id + "\", \"" + user_id + "\", null, now(), \""+ content+ "\");";


            stmt.executeUpdate(q1);

            return 1;
        }catch (SQLException e){
            e.printStackTrace();

            return -1;
        }
    }

    //return 0 cannot write comment
    //return 1 success write
    //return -1 error
    // 댓글 작성 메서드
    public int WriteComment(String user_id, String post_id, String content)
    {
        Statement stmt = null;
        ResultSet rs = null;


        try(Connection connection= DriverManager.getConnection(url, user, password);){
            stmt = connection.createStatement();

            String q1 = "select * from post where post_id = \"" + post_id + "\";";
            rs = stmt.executeQuery(q1);

            if(!rs.next())
            {
                return 0;
            }



            Timestamp date = new Timestamp(System.currentTimeMillis());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String generatedString = new String(array, Charset.forName("UTF-8"));
            q1 = "INSERT INTO comment VALUES ('" + generatedString + "', NOW(), NULL, '" + content + "', '" + post_id + "', '" + user_id + "')";


            stmt.executeUpdate(q1);

            return 1;
        }catch (SQLException e){
            e.printStackTrace();

            return -1;
        }
    }


    public CommentOnDto getCommentOn(String comment_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select * from comment_on where comment_id ='"+comment_id+"'"; // sql문
        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {
            // 5. SQL 결과 처리
            while (rs.next()){
                String post_id = rs.getString("post_id");
                String comment_Id = rs.getString("comment_Id");
                String user_id = rs.getString("user_id");
                String parent_id = rs.getString("parent_id");
                Timestamp date = rs.getTimestamp("date");
                String content = rs.getString("content");
                return new CommentOnDto(post_id, comment_Id, user_id, parent_id, date, content);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return null;
    }
}
