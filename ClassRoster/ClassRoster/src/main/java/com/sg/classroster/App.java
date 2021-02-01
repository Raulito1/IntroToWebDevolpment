/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster;

import com.sg.classroster.controller.ClassRosterController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author raulalvarado
 */
public class App {
    public static void main(String[] args) {
        // Instantiate the UserIO implementation
        //UserIO myIo = new UserIOConsoleImpl();
        // Instantiate the View and wire the UserIO implementation into it
        //ClassRosterView myView = new ClassRosterView(myIo);
        // Instantiate the DAO
        //ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        // Instantiate Audit DAO
        //ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
        // Instaniate the Service Layer and wire the DAO and Audit DAO into it
        //ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        //ClassRosterController controller = 
        //        new ClassRosterController(myService, myView);
        // Kick off the controller
        //controller.run();
        
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassRosterController controller =
                ctx.getBean("controller", ClassRosterController.class);
        controller.run();
    }
}
