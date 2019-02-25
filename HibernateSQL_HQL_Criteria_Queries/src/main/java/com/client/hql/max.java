package com.client.hql;

import com.config.HibernateUtil;
import com.model.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class max {
    public static void main(String... args) {
        Session session = HibernateUtil.getSession();
        try {
            //highest salary
            String hql = "Select max(salary) From Employee";
            Query<Integer> query = session.createQuery(hql);
            Integer sal = query.getSingleResult();
            System.out.println("Max salary : " + sal);

            //2nd highest salary
            String hql1 = "From Employee order by salary desc";
            Query<Employee> query1 = session.createQuery(hql1);
            Employee employee = query1
                    .setFirstResult(1)
                    .setMaxResults(1)
                    .uniqueResult();
            System.out.println("2nd Max salary");
            employee.display();
        } finally {
            session.close();
        }
    }
}
