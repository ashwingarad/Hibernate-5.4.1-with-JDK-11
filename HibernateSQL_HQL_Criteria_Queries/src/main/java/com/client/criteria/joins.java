package com.client.criteria;

import com.config.HibernateUtil;
import com.model.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class joins {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        try {
            /*List of employees whose are working in Software department*/
            Criteria empCriteria = session.createCriteria(Employee.class);
            Criteria deptCriteria = empCriteria.createCriteria("department");
            deptCriteria.add(Restrictions.eq("dname", "Software"));
            List<Employee> employeeList = empCriteria.list();
            for (Employee employee : employeeList) {
                employee.display();
                employee.getDepartment().display();
            }
        } finally {
            session.close();
            HibernateUtil.closeFactory();
        }
    }
}
