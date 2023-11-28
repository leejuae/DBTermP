import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLMethods {
    public static Connection con;

    public static void init() {
        Connection connection = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter_public";
            String user = "root", passwd = "15371537";

            connection = DriverManager.getConnection(url, user, passwd);
            System.out.println(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        con = connection;
    }

    public static Connection GetCon() {
        return con;
    }

    public static ResultSet ExecuteQuery (Connection con, String q1) {
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(q1);
            return rs;
        } catch (SQLException e){
            e.printStackTrace();
        }

        return rs;
    }

    public static int ExecuteUpdate (Connection con, String q1) {
        int result = 0;
        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(q1);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static int CreateUser (Connection con, String user_id, String pw, String name, Timestamp created_at, String profile_pic, String introduction) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String q1 = "select user_id from user where user_id = ?";
            pstmt = con.prepareStatement(q1);
            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();

            // 중복된 아이디
            if (rs.next())
                return 0;

            else {
                String q2 = "insert into user(user_id, pw, name, created_at, profile_pic, introduction) values (?, ?, ?, ?, ?, ?)";
                pstmt = con.prepareStatement(q2);

                pstmt.setString(1, user_id);
                pstmt.setString(2, pw);
                pstmt.setString(3, name);
                pstmt.setTimestamp(4, created_at);
                pstmt.setString(5, profile_pic);
                pstmt.setString(6, introduction);

                int count = pstmt.executeUpdate();

                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static void ChangePassword (Connection con, String newPassword, String user_id){
        PreparedStatement pstmt = null;

        try {
            String q1 = "update user set pw = ? where user_id = ?";

            pstmt = con.prepareStatement(q1);

            pstmt.setString(1, newPassword);
            pstmt.setString(2, user_id);

            int count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Follows (Connection con, String following_id, String follower_id) {
        PreparedStatement pstmt = null;

        try {
            String q1 = "insert into follows (following_id, follower_id) values (?, ?)";

            pstmt = con.prepareStatement(q1);

            pstmt.setString(1, following_id);
            pstmt.setString(2, follower_id);

            int count = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //------------------------아직추가안함----------------------------
    public static void ListFollowings (Connection con, String following_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> result = new ArrayList<>();

        try {
            String q1 = "select follower_id as following from follows where following_id = ?";

            pstmt = con.prepareStatement(q1);
            pstmt.setString(1, following_id);
            rs = pstmt.executeQuery();

            while (rs.next())
                result.add(rs.getString(1));

            for (String s : result)
                System.out.println(s);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //-------------------아직추가안함
    public static void ListFollowers (Connection con, String follower_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> result = new ArrayList<>();

        try {
            String q1 = "select following_id as follower from follows where follower_id = ?";

            pstmt = con.prepareStatement(q1);
            pstmt.setString(1, follower_id);
            rs = pstmt.executeQuery();

            while (rs.next())
                result.add(rs.getString(1));

            for (String s : result)
                System.out.println(s);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void Post (Connection con, String post_id, String content, Timestamp posted_at, String location) {
        PreparedStatement pstmt = null;

        try {
            String q1 = "insert into post(post_id, content, posted_at, location) values (?, ?, ?, ?)";

            pstmt = con.prepareStatement(q1);

            pstmt.setString(1, post_id);
            pstmt.setString(2, content);
            pstmt.setTimestamp(3, posted_at);
            pstmt.setString(4, location);

            int count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //---------아직추가안함
    public static void Posts (Connection con, String user_id, String post_id) {
        PreparedStatement pstmt = null;

        try {
            String q1 = "insert into posts (user_id, post_id) values (?, ?)";

            pstmt = con.prepareStatement(q1);

            pstmt.setString(1, user_id);
            pstmt.setString(2, post_id);

            int count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void comment (Connection con, String comment_id, String content, Timestamp commented_at, String location) {
        PreparedStatement pstmt = null;

        try {
            String q1 = "insert into comment(comment_id, content, commented_at, location) values (?, ?, ?, ?)";

            pstmt = con.prepareStatement(q1);

            pstmt.setString(1, comment_id);
            pstmt.setString(2, content);
            pstmt.setTimestamp(3, commented_at);
            pstmt.setString(4, location);

            int count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void comments_on (Connection con, String post_id, String comment_id, String user_id) {
        PreparedStatement pstmt = null;

        try {
            String q1 = "insert into comments_on (post_id, comment_id, user_id) values (?, ?, ?)";

            pstmt = con.prepareStatement(q1);

            pstmt.setString(1, post_id);
            pstmt.setString(2, comment_id);
            pstmt.setString(3, user_id);

            int count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mentioned_on (Connection con, String mentioned_user_id, String comment_id) {
        PreparedStatement pstmt = null;

        try {
            String q1 = "insert into mentioned_on (mentioned_user_id, comment_id) values (?, ?)";

            pstmt = con.prepareStatement(q1);

            pstmt.setString(1, mentioned_user_id);
            pstmt.setString(2, comment_id);

            int count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//----------아직
    public static void Show_User_Info (Connection con, String user_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String q1 = "select name, profile_pic, introduction from user where user_id = ?";

            pstmt = con.prepareStatement(q1);
            rs = pstmt.executeQuery();

            String user_name = rs.getString(1);
            String profile_pic = rs.getString(2);
            String introduction = rs.getString(3);

            System.out.println("user_name = " + user_name);
            System.out.println("user_id = " + user_id);
            System.out.println("profile_pic = " + profile_pic);
            System.out.println("introduction = " + introduction);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    //----------아직
    public static void Get_PostID (Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String q1 = "select post_id from post order by posted_at desc";

            pstmt = con.prepareStatement(q1);
            rs = pstmt.executeQuery();

            String post_id = rs.getString(1);

            System.out.println("post_id = " + post_id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //----------아직
    public static void Get_Content_Info (Connection con, String post_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String q1 = "select content, location, posted_at from post where post_id = ?";

            pstmt = con.prepareStatement(q1);
            rs = pstmt.executeQuery();

            String content = rs.getString(1);
            String location = rs.getString(2);
            String posted_at = rs.getString(3);

            System.out.println("content = " + content);
            System.out.println("location = " + location);
            System.out.println("posted_at = " + posted_at);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
