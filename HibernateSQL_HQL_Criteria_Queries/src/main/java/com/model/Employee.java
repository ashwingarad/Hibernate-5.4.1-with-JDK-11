package com.model;

import javax.persistence.*;

@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ename;

    @Column
    private Integer salary;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Pancard pancard;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "d_id")
    private Department department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Pancard getPancard() {
        return pancard;
    }

    public void setPancard(Pancard pancard) {
        this.pancard = pancard;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void display() {
        System.out.println("\n**** Employee information ****");
        System.out.println("ID : " + this.id);
        System.out.println("Name : " + this.ename);
        System.out.println("Salary : " + this.salary);
    }
}
