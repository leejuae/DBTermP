package Dao;

import Dto.post.PostDto;
import Dto.user.UserDto;

import java.sql.*;

public class CommentDao {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/twitter_public";
    private String user = "root";
    private String password = "1234";
}
