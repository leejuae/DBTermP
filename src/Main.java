import Dto.user.UserDto;
import GUI.Login;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        new Login();
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