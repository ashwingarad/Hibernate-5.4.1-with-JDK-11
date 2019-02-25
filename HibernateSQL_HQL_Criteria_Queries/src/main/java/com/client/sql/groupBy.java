package com.client.sql;

import com.config.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class groupBy {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();

        try {
            String sql = "Select d.dept_name, count(e.ename) " +
                    "from employee e " +
                    "inner join department d on " +
                    "e.d_id=d.dept_id " +
                    "group by d.dept_name";

            SQLQuery query = session.createSQLQuery(sql);
            List<Object[]> objects = query.getResultList();
            for (Object[] objects1 : objects) {
                System.out.println(objects1[0] + " : " + objects1[1]);
            }

            System.out.println("\n\n");
            //Hibernate 5.2 onwards
            NativeQuery<Object[]> nativeQuery = session.createNativeQuery(sql);
            List<Object[]> objects1 = nativeQuery.getResultList();
            for (Object[] objects2 : objects1) {
                System.out.println(objects2[0] + " : " + objects2[1]);
            }
        } finally {
            session.close();
        }
    }
}
