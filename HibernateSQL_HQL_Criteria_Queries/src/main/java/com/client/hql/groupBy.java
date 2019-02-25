package com.client.hql;

import com.config.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class groupBy {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();

        try {
            String hql = "Select d.dname, count(e.ename) " +
                    "from Employee e " +
                    "inner join e.department d " +
                    "group by d.dname";

            Query query = session.createQuery(hql);
            List<Object[]> objects = query.getResultList();
            for (Object[] objects1 : objects) {
                System.out.println(objects1[0] + " : " + objects1[1]);
            }

            System.out.println("\n\n");
            //Hibernate 5.2 onwards
            org.hibernate.query.Query<Object[]> nativeQuery = session.createQuery(hql);
            List<Object[]> objects1 = nativeQuery.getResultList();
            for (Object[] objects2 : objects1){
                System.out.println(objects2[0] + " : " + objects2[1]);
            }
        } finally {
            session.close();
        }
    }
}
