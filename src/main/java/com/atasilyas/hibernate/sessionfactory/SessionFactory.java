package com.atasilyas.hibernate.sessionfactory;

import com.atasilyas.hibernate.util.HibernateUtil;
import org.hibernate.Session;

public class SessionFactory {

    public static void main(String[] args) {


        Session session = null;
        // first static second inline last constructor will work.
        HibernateUtil hibernateUtil = new HibernateUtil();

        //creating a session factory and open a session for db opertions..

        try {
             session = HibernateUtil.getSessionFactory().openSession();
            String SQL = "select version()";
            String result = (String) session.createNativeQuery(SQL).getSingleResult();
            System.out.println(result);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
