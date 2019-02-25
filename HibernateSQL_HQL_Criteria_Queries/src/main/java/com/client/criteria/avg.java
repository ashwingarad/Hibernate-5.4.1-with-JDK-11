package com.client.criteria;

import com.config.HibernateUtil;
import com.model.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class avg {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.setProjection(Projections.avg("salary"));
            Double av = (Double) criteria.uniqueResult();
            System.out.println("Average salary : " + av);
        } finally {
            session.close();
            HibernateUtil.closeFactory();
        }
    }
}
