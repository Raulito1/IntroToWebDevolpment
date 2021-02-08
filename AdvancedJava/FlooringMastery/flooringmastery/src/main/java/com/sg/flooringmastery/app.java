/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.FlooringMasteryController;
import com.sg.flooringmastery.service.FlooringMasteryOrderValidationException;
import com.sg.flooringmastery.service.FlooringMasteryProductValidationException;
import com.sg.flooringmastery.service.FlooringMasteryStateValidationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author raulalvarado
 */
public class app {
    public static void main(String[] args) throws FlooringMasteryOrderValidationException, FlooringMasteryStateValidationException, FlooringMasteryProductValidationException {
//       UserIO myIO = new UserIOConsoleImpl();
//       FlooringMasteryView myView = new FlooringMasteryView(myIO);
//       FlooringMasteryOrdersDao orderDao = new FlooringMasteryOrdersProdFileImpl();
//       FlooringMasteryProductsDao productDao = new FlooringMasteryProductsDaoFileImpl();
//       FlooringMasteryStatesDao stateDao = new FlooringMasteryStatesDaoFileImpl();
//       FlooringMasteryAuditDao  auditDao = new FlooringMasteryAuditDaoImpl();
//       FlooringMasteryServiceLayer myService = new FlooringMasteryServiceLayerImpl(orderDao, productDao, stateDao,auditDao);
//       FlooringMasteryController controller = new FlooringMasteryController(myService, myView);
//       controller.run();
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = ctx.getBean("controller", FlooringMasteryController.class);
        controller.run();
    
    }
}
 