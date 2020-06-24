package com.atasilyas.hibernate.eagerandlazyloading;

import com.atasilyas.hibernate.enbeddedobject.Address;
import com.atasilyas.hibernate.entity.User;
import com.atasilyas.hibernate.util.HibernateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test
{

    public static void main(String[] args)
    {

        User user = null;
        Session session = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(getUser1()); // save user

            session.getTransaction().commit();

             user  = session.get(User.class, 1);// if its eager it will select from beginin for address from here
            if (ObjectUtils.isNotEmpty(user)){
                user.getContactList(); // if its lazy it will select again for address from here
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (ObjectUtils.isNotEmpty(session))
            {
                session.close();
            }
        }

        if (ObjectUtils.isNotEmpty(user)){
            user.getContactList(); // lazy initiliazation exception alır çünkü session bitti ve proxy(persistet) objectten data cekilemiyor.
            //eger eager ise hata almaz çünkü basta zaten cekmişti tüm datayı.
        }
    }



    private static void updateUserById(Session session)
    {
        User user = session.get(User.class, 1);
        if (ObjectUtils.isNotEmpty(user))
        {
            user.setUserName("updateEdilen user");
            user.setEmail("update@gmail.com");

            session.update(user);
        }
        else
        {
            System.out.println("User bulunamadı.");
        }

    }

    private static void deleteUserById(Session session)
    {
        User user = session.get(User.class, 1);
        if (ObjectUtils.isNotEmpty(user))
        {
            session.delete(user);
        }
        else
        {
            System.out.println("User bulunamadı.");
        }

    }


    private static void getUserById(Session session)
    {
        /*burası cache deoldugu için aynı session da aynı daha save edilip sonra select cekilior bu data için select atılmaz*/
        User user = session.get(User.class, 1);
        if (ObjectUtils.isNotEmpty(user))
        {
            System.out.println(user.toString());
        }
        else
        {
            System.out.println("User bulunamadı.");
        }
    }

    static User getUser1()
    {
        User user = new User();
        Address address = new Address();
        List<Contact> contacts = new ArrayList<Contact>();
        Contact contact = new Contact("ilyas");
        Contact contact1 = new Contact("selim");
        Contact contact2 = new Contact("mustafa");
        Contact contact3 = new Contact("kasım");
        contacts.add(contact);
        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
        user.setContactList(contacts);
        address.setCity("van");
        address.setPinCode(232L);
        address.setStreet("karahan");
        user.setCreateDate(new Date());
        user.setUserName("ilyas");
        user.setEmail("ilyas.atas1@gmail.com");
        user.setSalary(1234.34);
        user.setHomeAddress(address);
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
