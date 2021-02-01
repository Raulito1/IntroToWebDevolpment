/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dto;

/**
 *
 * @author raulalvarado
 */
public class Student {
    
    private String firstName;
    private String lastName;
    private String studentId;
    private String cohort;// JAVA or .NET + cohort month/year
    
    @Override
    public String toString() {
        return "ID: " + studentId + " |Name: " + firstName + " "
                + lastName + " |Cohort: " + cohort;
    }
    
    public Student(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }
    
    
}
