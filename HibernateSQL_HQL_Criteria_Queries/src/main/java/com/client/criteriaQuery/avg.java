package com.client.criteriaQuery;

import com.config.HibernateUtil;
import com.model.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class avg {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Double> query = builder.createQuery(Double.class);
            Root<Employee> root = query.from(Employee.class);
            query.select(builder.avg(root.get("salary")));
            Double av = session.createQuery(query).uniqueResult();
            System.out.println("Average salary : " + av);
        } finally {
            session.close();
            HibernateUtil.closeFactory();
        }
    }
}
