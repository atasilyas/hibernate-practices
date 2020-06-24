package com.atasilyas.hibernate.onetoone;

import com.atasilyas.hibernate.eagerandlazyloading.Contact;
import com.atasilyas.hibernate.enbeddedobject.Address;
import com.atasilyas.hibernate.entity.User;
import com.atasilyas.hibernate.util.HibernateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Session;


public class Test
{

    public static void main(String[] args)
    {

        Customer customer = null;
        Session session = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();


            customer = getCustomer();
            session.save(customer); //burada eger casdcade all degilse transient object save exception fırlatır child save edilmedigi için

            session.getTransaction().commit();

            session.get(Customer.class, 1);
            session.get(Role.class, 1);

            session.get(User.class, 1);

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






    static Customer getCustomer()
    {
        Customer customer = new Customer();
        customer.setCustomerNo("1212121");
        Role role = Role.builder().roleName("ROLE_USER").build();
        customer.setRole(role);
        return customer;
    }

}
