package com.client.sql;

import com.config.HibernateUtil;
import com.model.Employee;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class max {
    public static void main(String... args) {
        Session session = HibernateUtil.getSession();
        try {
            //highest salary
            String sql = "Select max(salary) From employee";
            NativeQuery<Integer> query = session.createNativeQuery(sql);
            Integer sal = query.getSingleResult();
            System.out.println("Max salary : " + sal);

            //2nd highest salary
            String sql1 = "Select * From employee order by salary desc";
            NativeQuery<Employee> query1 = session.createNativeQuery(sql1, Employee.class);
            Employee employee = query1
                    .setFirstResult(1)
                    .setMaxResults(1)
                    .uniqueResult();
            System.out.println("\n\n2nd Max salary");
            employee.display();

           /*
            SELECT *
            FROM  employee
            ORDER BY salary DESC
            LIMIT 2 , 1
            */
        } finally {
            session.close();
            HibernateUtil.closeFactory();
        }
    }
}
