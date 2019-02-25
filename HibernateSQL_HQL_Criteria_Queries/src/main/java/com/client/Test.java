package com.client;

import com.config.HibernateUtil;
import com.model.Department;
import com.model.Employee;
import com.model.Pancard;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSession();

        Pancard pancard = new Pancard();
        pancard.setPnum("JSH3YH8");

        Employee employee = new Employee();
        employee.setEname("ABC");
        employee.setSalary(54111);
        employee.setPancard(pancard);
        pancard.setEmployee(employee);

        Pancard pancard1 = new Pancard();
        pancard1.setPnum("LKD67HG");

        Employee employee1 = new Employee();
        employee1.setEname("PQR");
        employee1.setSalary(65478);
        employee1.setPancard(pancard1);
        pancard1.setEmployee(employee1);

        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add(employee);
        employeeSet.add(employee1);

        Department department = new Department();
        department.setDname("SPORT");
        department.setEmployeeSet(employeeSet);

        employee.setDepartment(department);
        employee1.setDepartment(department);

        session.save(employee);
        session.beginTransaction().commit();
        session.close();
    }
}
