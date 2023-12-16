package com.example.hepl_demo.model;


import jakarta.persistence.*;

@Entity
@Table(name="hepl_employee")
public class HeplEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="id")
    private long id;

    @Column(name="emp_name")
    private long empName;

    @Column(name="emp_name1")
    private String empName1;


    public HeplEmployee() {

    }

    public HeplEmployee(long id, long empName) {
        this.id = id;
        this.empName = empName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmpName() {
        return empName;
    }

    public void setEmpName(long empName) {
        this.empName = empName;
    }

    public String getEmpName1() {
        return empName1;
    }

    public void setEmpName1(String empName1) {
        this.empName1 = empName1;
    }
}
