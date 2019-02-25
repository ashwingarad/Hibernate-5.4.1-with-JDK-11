package com.client.sql;

import com.config.HibernateUtil;
import org.hibernate.Session;

import java.math.BigDecimal;

public class avg {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        try {
            //Hibernate 5
            String sql = "SELECT avg(salary) FROM employee";
            org.hibernate.query.NativeQuery<BigDecimal> query = session.createNativeQuery(sql);
            BigDecimal av = query.uniqueResult();
            System.out.println("Average salary : " + av);

            //Hibernate 4
            org.hibernate.SQLQuery query1 = session.createSQLQuery(sql);
            BigDecimal av2 = (BigDecimal) query1.uniqueResult();
            System.out.println("Average salary : " + av2);
        } finally {
            session.close();
            HibernateUtil.closeFactory();
        }
    }
}
