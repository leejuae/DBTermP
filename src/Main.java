import Dao.FollowDao;
import Dao.UserDao;
import Dto.follow.FollowDto;
import Dto.user.UserDto;
import GUI.Login;
import GUI.SignIn;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        new Login();
//        new SignIn();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        UserDao dao = new UserDao();

        UserDto user1 = new UserDto();
        user1.setUser_id("2222");
        user1.setPw("2222");
        user1.setName("2222");
        user1.setProfile_pic("2222");
        user1.setIntroduction("2222");
        user1.setCreated_at(timestamp);

        if(dao.regUser(user1))
            System.out.println("존재하는 유저입니다");
        else
            System.out.println("회원가입 완료");


        UserDto user = new UserDto("1111","1111","1111","1111","1111",timestamp);
        print(user);

        System.out.println();
        System.out.println("회원가입");

//        if(dao.regUser(user))
//            System.out.println("존재하는 유저입니다");
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

        if(dao.changePwUser("1234", "1111")) {
            System.out.println("비밀번호 변경완료");
            user = dao.selectUser("1111");
            print(user);
        }
        else
            System.out.println("비밀번호 변경실패");

//        FollowDao followDao = new FollowDao();
//        System.out.println("전체조회");
//        ArrayList<FollowDto> list = followDao.selectfollowList();
//        print(list);

//        FollowDao followDao = new FollowDao();
//        System.out.println("팔로워 목록조회");
//        ArrayList<String>list2 = followDao.selectfollowerList("testID_10");
//        print(list2);
//
//        System.out.println("팔로잉 목록조회");
//        list2 = followDao.selectfollowingList("testID_01");
//        print(list2);
    }

    private static void print(UserDto user){
        System.out.println(user);
    }


    private static void print(ArrayList<String> list){
        for(String dto:list){
            System.out.println(dto);
        }



    }
}