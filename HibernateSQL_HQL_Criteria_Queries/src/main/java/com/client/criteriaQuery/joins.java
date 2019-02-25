package com.client.criteriaQuery;

import com.config.HibernateUtil;
import com.model.Department;
import com.model.Employee;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class joins {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        try {
            /*List of employees whose are working in Software department*/
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Employee> empCriteriaQuery = builder.createQuery(Employee.class);
            Root<Employee> root = empCriteriaQuery.from(Employee.class);

            Join<Employee, Department> department = root.join("department");
            empCriteriaQuery.select(root).where(builder.equal(department.get("dname"), "Software"));
            List<Employee> employeeList = session.createQuery(empCriteriaQuery).getResultList();
            for (Employee employee : employeeList) {
                employee.display();
                employee.getDepartment().display();
            }

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
