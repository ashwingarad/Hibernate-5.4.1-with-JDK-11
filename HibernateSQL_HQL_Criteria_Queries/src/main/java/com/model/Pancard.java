package com.model;

import javax.persistence.*;

@Entity
@Table
public class Pancard {
    @Id
    @GeneratedValue
    @Column(name = "eid")
    private Long id;

    @Column(name = "pan_num")
    private String pnum;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "eid", referencedColumnName = "id")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPnum() {
        return pnum;
    }

    public void setPnum(String pnum) {
        this.pnum = pnum;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void display() {
        System.out.println("\n**** Pancard information ****");
        System.out.println("Pan ID : " + this.id);
        System.out.println("Pancard Num : " + this.pnum);
    }
}
