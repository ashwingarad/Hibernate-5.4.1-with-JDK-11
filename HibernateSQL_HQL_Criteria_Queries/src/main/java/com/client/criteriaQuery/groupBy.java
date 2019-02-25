package com.client.criteriaQuery;

import com.config.HibernateUtil;
import com.model.Department;
import com.model.Employee;
import org.hibernate.Session;

import javax.persistence.criteria.*;
import java.util.List;

public class groupBy {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
            Root<Employee> root = query.from(Employee.class);

            Join<Employee, Department> departmentJoin = root.join("department");

            Selection selection = builder.count(root.get("ename"));
            Selection selection1 = root.get("department").get("dname");
            query.multiselect(selection, selection1);
            query.groupBy(root.get("department").get("dname"));

            List<Object[]> list = session.createQuery(query).getResultList();
            for (Object[] objects : list) {
                System.out.println(objects[0] + " : " + objects[1]);
            }

            /*Hibernate:
            Select d.dept_name, count(e.ename) from employee e inner join department d on e.d_id = d.dept_id group by
            d.dept_name*/
        } finally {
            session.close();
        }
    }
}
