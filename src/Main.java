import Dao.UserDao;
import Dto.user.UserDto;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UserDao dao = new UserDao();

        System.out.println("전체조회");
        ArrayList<UserDto> list = dao.selectList();
        print(list);

        System.out.println();
        System.out.println("상세조회");
        UserDto user = dao.selectUser("testID_01");
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