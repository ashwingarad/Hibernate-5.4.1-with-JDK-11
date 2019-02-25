package com.client.hql;

import com.config.HibernateUtil;
import com.model.Employee;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class joins {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        try {
            /*List of employees whose are working in Software department*/

            //Hibernate 4
            String hql = "From Employee e inner join e.department d where d.dname='Software'";
            Query<Object[]> listQuery = session.createQuery(hql);
            List<Object[]> employeeList = listQuery.getResultList();
            employeeList.forEach(obj -> {
                Employee employee = (Employee) obj[0];
                employee.display();
                System.out.println(obj[1]);
            });

            //Hibernate 5.2 onward
            org.hibernate.query.Query<Object[]> listQuery1 = session.createQuery(hql);
            List<Object[]> employeeList1 = listQuery1.getResultList();
            employeeList1.forEach(obj -> {
                Employee employee = (Employee) obj[0];
                employee.display();
                System.out.println(obj[1]);
            });

            /*
             * select employee.id, employee.ename, employee.salary, employee.d_id
             * from employee
             * inner join department on employee.d_id = department.dept_id
             * where department.dept_name = Software
             * */
        } finally {
            session.close();
            HibernateUtil.closeFactory();
        }
    }
}
