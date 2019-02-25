package com.client.criteriaQuery;

import com.config.HibernateUtil;
import com.model.Employee;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class max {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        try {
            //highest salary
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
            Root<Employee> root = query.from(Employee.class);
            query.select(builder.max(root.get("salary")));

            Integer integer = session.createQuery(query).uniqueResult();
            System.out.println("Max Salary : " + integer);

            //2nd highest salary
            CriteriaQuery<Employee> query1 = builder.createQuery(Employee.class);
            Root<Employee> root1 = query1.from(Employee.class);
            query1.orderBy(builder.desc(root1.get("salary")));
            Employee employee = session.createQuery(query1)
                    .setFirstResult(1)
                    .setMaxResults(1)
                    .getSingleResult();
            employee.display();
        } finally {
            session.close();
        }
    }
}
