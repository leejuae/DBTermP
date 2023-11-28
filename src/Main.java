import Dao.UserDao;
import Dto.user.UserDto;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        UserDao dao = new UserDao();

        System.out.println("전체조회");
        ArrayList<UserDto> list = dao.selectUserList();
        print(list);

        System.out.println();
        System.out.println("상세조회");
        UserDto user = dao.selectUser("testID_01");
        print(user);

        System.out.println();
        System.out.println("회원가입");

        if(dao.regUser(user))
            System.out.println("존재하는 유저입니다");
        user.setUser_id("1111");
        user.setPw("1111");
        user.setName("1111");
        user.setProfile_pic("1111");
        user.setIntroduction("1111");
        user.setCreated_at(timestamp);

        if(dao.regUser(user))
            System.out.println("존재하는 유저입니다");
        else
            System.out.println("회원가입 완료");

        System.out.println();
        System.out.println("상세조회");
        user = dao.selectUser("1111");
        print(user);
    }

    private static void print(UserDto user){
        System.out.println(user);
    }


    private static void print(ArrayList<UserDto> list){
        for(UserDto dto:list){
            System.out.println(dto);
        }
    }
}