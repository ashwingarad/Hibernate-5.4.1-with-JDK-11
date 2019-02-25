package com.client.criteria;

import com.config.HibernateUtil;
import com.model.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import java.util.List;

public class groupBy {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();

        try {
            Criteria empCriteria = session.createCriteria(Employee.class, "emp");
            empCriteria.createAlias("emp.department", "dept");

            ProjectionList deptList = Projections.projectionList()
                    .add(Projections.groupProperty("dept.dname"))
                    .add(Projections.count("emp.ename"), "Employee Name");
            empCriteria.setProjection(deptList);

            List<Object[]> list = empCriteria.list();
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