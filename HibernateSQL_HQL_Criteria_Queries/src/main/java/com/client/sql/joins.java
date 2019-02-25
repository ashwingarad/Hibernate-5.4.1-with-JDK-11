package com.client.sql;

import com.config.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class joins {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        try {
            /*List of employees whose are working in Software department*/

            //Hibernate 4
            String sql = "Select * From employee e " +
                    "inner join department d on e.d_id=d.dept_id " +
                    "where d.dept_name='Software'";
            SQLQuery<Object[]> listQuery = session.createSQLQuery(sql);
            List<Object[]> employeeList = listQuery.getResultList();
            employeeList.forEach(obj -> {
                System.out.println(obj[0] + "\t" + obj[1] + "\t" + obj[2]);
            });

            //Hibernate 5.2 onward
            NativeQuery<Object[]> listQuery1 = session.createNativeQuery(sql);
            List<Object[]> employeeList1 = listQuery1.getResultList();
            employeeList1.forEach(obj -> {
                System.out.println(obj[0] + "\t" + obj[1] + "\t" + obj[2]);
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
