package com.atasilyas.hibernate.crudwithdaoservicelayer.repository;

import com.atasilyas.hibernate.entity.User;
import com.atasilyas.hibernate.util.HibernateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Session;

public class UserRepositoryImpl implements UserRepository
{
    Session session = null;

    public void save(User user)
    {
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
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

    public void update(User user)
    {
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(user);
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

    public User findById(Integer id)
    {
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.get(User.class, id);
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
        return null;
    }

    public void delete(User user)
    {
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
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
}
