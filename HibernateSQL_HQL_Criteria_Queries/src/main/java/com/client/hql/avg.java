package com.client.hql;

import com.config.HibernateUtil;
import org.hibernate.Session;

public class avg {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        try {
            //Hibernate 5
            String hql = "SELECT avg(salary) FROM Employee";
            org.hibernate.query.Query<Double> query = session.createQuery(hql);
            Double av = query.uniqueResult();
            System.out.println("Average salary : " + av);

            //Hibernate 4
            org.hibernate.Query query1 = session.createQuery(hql, Double.class);
            Double av2 = (Double) query1.uniqueResult();
            System.out.println("Average salary : " + av2);
        } finally {
            session.close();
            HibernateUtil.closeFactory();
        }
    }
}
