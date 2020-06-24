package com.atasilyas.hibernate.dirtychecking;

import com.atasilyas.hibernate.entity.User;
import com.atasilyas.hibernate.util.HibernateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Session;

import java.util.Date;

public class DirtyChecking
{
    public static void main(String[] args)
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            User user = getUser1();

            Integer id = (Integer) session.save(user);
            if (ObjectUtils.isNotEmpty(id)){
                user.setUserName("updated");
                //session.update(user);  Burada transaction içerisinde update calısmasa bile hibernate update yapar cunku persistent state object etkilenir.
            }

            session.getTransaction().commit();
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

}
