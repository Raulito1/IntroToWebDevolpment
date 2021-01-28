/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.ui;

import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public class ClassRosterView {
    
    UserIO io = new UserIOConsoleImpl();
    
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Student IDs");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");
        
        return io.readInt("Please select from the above choices", 1, 5);
    }
    
    public Student getNewStudentInfo() {
        String studentId = io.readString("Please enter StudentID");
        String firstName = io.readString("Please enter First Name");
        String lastName = io.readString("Please enter Last Name");
        String cohort = io.readString("Please enter Cohort");
        Student currentStudent = new Student(studentId);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);
        return currentStudent;
    }
    
    public void displayCreateStudentBanner() {
        io.print("==== Create Student ====");
    }
    
    public void displayCreateSuccessBanner() {
        io.readString("Student successfully created. Please hit ENTER to continue");
    }
    
    public void displayStudentList(List<Student> studentList) {
        for (Student currentStudent : studentList) {
            io.print(currentStudent.getStudentId() + ": "
                    + currentStudent.getFirstName() + " "
                    + currentStudent.getLastName());
        }
        io.readString("Please hit ENTER to continue");
    }
    
    public void displayDisplayAllBanner() {
        io.print("==== Display All Students ====");
    }
}
