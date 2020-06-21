package com.atasilyas.hibernate.crudoperations;

import com.atasilyas.hibernate.util.HibernateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Session;

import java.util.Date;

public class SavePersistTest {

    public static void main(String[] args) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();


            Integer userId = (Integer) session.save(getUser1()); /*save edilen user id'si doner*/
            session.persist(getUser2());/*void doner*/
            session.saveOrUpdate(getUser3());/*varsa update yoksa persist*/
            getUserById(session);/*select atmak için id li user nedir*/
            updateUserById(session); /*custom update*/
            deleteUserById(session);


            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void updateUserById(Session session) {
        User user = session.get(User.class, 1);
        if (ObjectUtils.isNotEmpty(user)) {
            user.setUserName("updateEdilen user");
            user.setEmail("update@gmail.com");

            session.update(user);
        }else {
            System.out.println("User bulunamadı.");
        }

    }

    private static void deleteUserById(Session session) {
        User user = session.get(User.class, 1);
        if (ObjectUtils.isNotEmpty(user)) {
            session.delete(user);
        }else {
            System.out.println("User bulunamadı.");
        }

    }


    private static void getUserById(Session session) {
        /*burası cache deoldugu için aynı session da aynı daha save edilip sonra select cekilior bu data için select atılmaz*/
        User user = session.get(User.class, 1);
        if (ObjectUtils.isNotEmpty(user)) {
            System.out.println(user.toString());
        }else {
            System.out.println("User bulunamadı.");
        }
    }

    static User getUser1() {
        User user = new User();
        user.setCreateDate(new Date());
        user.setUserName("ilyas");
        user.setEmail("ilyas.atas1@gmail.com");
        user.setSalary(1234.34);
        return user;
    }

    static User getUser2() {
        User user = new User();
        user.setCreateDate(new Date());
        user.setUserName("selim");
        user.setEmail("ilyas.atas2@gmail.com");
        user.setSalary(1234.34);
        return user;
    }

    static User getUser3() {
        User user = new User();
        user.setCreateDate(new Date());
        user.setUserName("mesut");
        user.setEmail("ilyas.atas3@gmail.com");
        user.setSalary(1234.34);
        return user;
    }
}
