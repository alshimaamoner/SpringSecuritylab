package com.company;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserServiceImpl implements UserService {
    UserService userServicel;

   public static List<User> users=new ArrayList<User>();
    User user;
    @Override
    public User getUser(Integer id) {
        System.out.println("Get User By id .......");

        User user = null;
        for(int i=0;i<users.size();i++){
            if(id==users.get(i).getId()){
                user= users.get(i);

            }
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        System.out.println("get ALl Users.......");
        return users;

    }

    @Override
    public void printUsers(List<User> users) {
        System.out.println("print User ");
      for(User user : users)
        System.out.println(user);



    }

    @Override
    public User addUser(User user) {
        System.out.println("User Added.......");
        users.add(user);
        return user;
    }

    @Override
    public String updateUser(User user) {
        String check="fail";
        System.out.println("User update.......");
         for(int i=0;i<users.size();i++){
             if(user.getId()==users.get(i).getId()){
                 users.set(i,user);
                 check="updated Succesfully";
             }
         }
        return check;
    }

    @Override
    public User deleteUser(Integer id) {
        User user = null;
        System.out.println("User delete.......");
        for(int i=0;i<users.size();i++){
            if(id==users.get(i).getId()){
               user= users.get(i);
                users.remove(user);

            }
        }
        return user;
    }

    @Override
    public User addUsers(User user) {
        System.out.println("PreAuthorize User.......");
        users.add(user);
        return user;
    }

    @Override
    public void pointCutTest() {
        System.out.println("Success");
    }
}
