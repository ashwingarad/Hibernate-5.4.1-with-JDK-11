package com.client.criteria;

import com.config.HibernateUtil;
import com.model.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

public class max {
    public static void main(String... args) {
        Session session = HibernateUtil.getSession();

        //Max salary
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.setProjection(Projections.max("salary"));
        Integer sal = (Integer) criteria.uniqueResult();
        session.close();
        System.out.println("Max Salary : " + sal);

        //2nd highest salary
        Session session1 = HibernateUtil.getSession();
        Criteria criteria1 = session1.createCriteria(Employee.class);
        criteria1.addOrder(Order.desc("salary"));
        criteria1.setFirstResult(1);
        criteria1.setMaxResults(1);
        Employee employee = (Employee) criteria1.uniqueResult();
        employee.display();
        session.close();
    }
}
