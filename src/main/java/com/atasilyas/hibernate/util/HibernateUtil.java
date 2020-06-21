package com.atasilyas.hibernate.util;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;

    {
        System.out.println("inline is worked");
    }
    public HibernateUtil() {
        System.out.println("constructor is worked");
    }

    static {
        System.out.println("static block is worked");
        if (ObjectUtils.isEmpty(sessionFactory)) {
            try {

                standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
                MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
                Metadata metadata = metadataSources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (ObjectUtils.isNotEmpty(standardServiceRegistry)) {
                    StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
                }
            }
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
