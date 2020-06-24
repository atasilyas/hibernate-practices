package com.atasilyas.hibernate.crudwithdaoservicelayer;

import com.atasilyas.hibernate.entity.User;
import com.atasilyas.hibernate.crudwithdaoservicelayer.repository.UserRepositoryImpl;
import com.atasilyas.hibernate.crudwithdaoservicelayer.service.UserService;
import com.atasilyas.hibernate.crudwithdaoservicelayer.service.UserServiceImpl;

import java.util.Date;

public class Test
{

    public static void main(String[] args)
    {
        UserService userService = new UserServiceImpl(new UserRepositoryImpl());
        User user1 = getUser1();
        User user2 = getUser2();
        User user3 = getUser3();


        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        System.out.println("All users added");

        userService.delete(user2);
        System.out.println("user2 deleted");


        user1.setUserName("User1 updated");
        userService.update(user1);
        System.out.println("User1 updated");


        System.out.println( userService.findById(user2.getUserId()).getUserName() +"select atıldı.");


    }




    static User getUser1()
    {
        User user = new User();
        user.setCreateDate(new Date());
        user.setUserName("ilyas");
        user.setEmail("ilyas.atas1@gmail.com");
        user.setSalary(1234.34);
        return user;
    }

    static User getUser2()
    {
        User user = new User();
        user.setCreateDate(new Date());
        user.setUserName("selim");
        user.setEmail("ilyas.atas2@gmail.com");
        user.setSalary(1234.34);
        return user;
    }

    static User getUser3()
    {
        User user = new User();
        user.setCreateDate(new Date());
        user.setUserName("mesut");
        user.setEmail("ilyas.atas3@gmail.com");
        user.setSalary(1234.34);
        return user;
    }
}
