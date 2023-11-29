package Dao;

import Dto.comment.MentionOnDto;

import java.sql.*;
import java.util.ArrayList;

public class MentionDao {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/twitter_public";
    private String user = "root";
    private String password = "1234";

    // 언급 작성
    public boolean mentionOn(String mentioned_user_id, String comment_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "insert into mentioned_on (mentioned_user_id, comment_id) values (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, mentioned_user_id);
            pstmt.setString(2, comment_id);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            e.printStackTrace();
        } return false; // 유저중복시 null
    }

    //언급 보기(사용자 아이디 입력시)
    public ArrayList<MentionOnDto> selectMentionList(String mentioned_user_id){
        //1. JDBC Driver 로딩
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<MentionOnDto> list = null; // 결과데이터를 담을 배열
        String sql = "select * from mentioned_on where mentioned_user_id ='"+ mentioned_user_id + "'"; // sql문
        try(Connection conn = DriverManager.getConnection(url, user, password); // 2. DB서버 연결
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);)
        {
            // 5. SQL 결과 처리
            list = new ArrayList<>();
            while (rs.next()){
                String mentioned_user_Id = rs.getString("mentioned_user_id");
                String comment_id = rs.getString("comment_id");
                MentionOnDto dto = new MentionOnDto(mentioned_user_Id, comment_id);

                list.add(dto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return list;
    }
}
