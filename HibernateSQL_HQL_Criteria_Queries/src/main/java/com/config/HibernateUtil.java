package com.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {
    private static SessionFactory factory = null;

    private HibernateUtil() {
    }

    private static SessionFactory configureFactory() {
        /*StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();*/

        Configuration configuration = new Configuration().configure();
        return configuration.buildSessionFactory();
    }

    public static Session getSession() {
        if (factory == null) {
            synchronized (HibernateUtil.class) {
                factory = configureFactory();
            }
        }
        return factory.openSession();
    }

    public static void closeFactory() {
        if (factory != null) {
            factory.close();
            factory = null;
        }
    }

    public static SessionFactory getFactory() {
        if (factory == null) {
            configureFactory();
        }
        return factory;
    }
}
