package GUI;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class SQLMethods {
    // Connection 객체를 저장할 변수 선언 (SQLMethods 클래스에서 사용할 Connection 객체)
    // MYSQL 연결을 나타내는 connection 객체를 저장합니다.
	public static Connection con;

    // 데이터베이스 연결 초기화 메서드
	public static void init() {
		Connection connection =  null;
		connection =  null;
        try{
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 연결할 데이터베이스 URL 및 사용자 정보
            String url = "jdbc:mysql://localhost:3306/twitter_public";
            String user = "root", passwd = "1234";
            // 연결
            connection = DriverManager.getConnection(url, user, passwd);
        }catch (ClassNotFoundException e){
            e.printStackTrace();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        con = connection;
	}
    // Connection 객체 반환
	public static Connection GetCon()
	{

        Connection connection =  null;
        connection =  null;
        try{
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 연결할 데이터베이스 URL 및 사용자 정보
            String url = "jdbc:mysql://localhost:3306/twitter_public";
            String user = "root", passwd = "1234";
            // 연결
            connection = DriverManager.getConnection(url, user, passwd);
        }catch (ClassNotFoundException e){
            e.printStackTrace();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        con = connection;


		return con;
	}

    // 쿼리 실행 및 결과 반환하는 메서드 (SELECT)
    public static ResultSet ExecuteQuery(Connection con, String q1){
        ResultSet result = null;
        try{
            Statement stmt = con.createStatement();
            result = stmt.executeQuery(q1);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return result;
    }

    // 쿼리 실행 및 결과 반환하는 메서드 (INSERT, UPDATE, DELETE)
    public static int ExecuteUpdate(Connection con, String q1){
        int result = 0;
        try{
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(q1);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 게시글 작성 메서드
    public static void WritePost(Connection connection, String user_id, String content, String[] tags)
    {
        Statement stmt = null;
        ResultSet rs = null;

        try{
            stmt = connection.createStatement();
            String q1 = "select count(user_id) from post where user_id = \"" + user_id + "\";";
            rs = stmt.executeQuery(q1);
            if(!rs.next())
                return;


            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String generatedString = new String(array, Charset.forName("UTF-8"));

            q1 = "insert into post values(\"" + generatedString + "\", now(), null, \"" + content + "\", \"" + user_id + "\");";
            stmt.executeUpdate(q1);

//            int imgCount = imgs.length;

//            if(imgCount > 0)
//            {
//                int cnt = 0;
//                while(true){
//                    if(cnt == imgCount)
//                        break;
//                    String imDir = imgs[cnt];
//
//                    if(imDir.compareTo("") == 0)
//                        break;
//
//                    String q2 = "select MAX(post_id) from posts";
//                    ResultSet rs2 = stmt.executeQuery(q2);
//                    int id = 0;
//                    if(rs2.next())
//                        id = rs2.getInt(1);
//
//                    q2 = "insert into images values(null, \"" + imDir + "\", \"" + id + "\");";
//                    stmt.executeUpdate(q2);
//                    cnt++;
//                }
//            }

            int tagCount = tags.length;

            if(tagCount > 0) {
                while(tagCount > 0) {
                    tagCount--;
                    String q2 = "select MAX(post_id) from post";
                    ResultSet rs2 = stmt.executeQuery(q2);
                    int id = 0;
                    if(rs2.next())
                        id = rs2.getInt(1);

                    q2 = "insert into mentioned_on values(\"" + tags[tagCount] + "\", \"" + id+ "\");";
                    stmt.executeUpdate(q2);

                }


            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
