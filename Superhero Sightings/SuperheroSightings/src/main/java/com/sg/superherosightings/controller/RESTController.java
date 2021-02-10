/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperHeroSightingsDao;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Person;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superpower;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author raulalvarado
 */
@Controller
public class RESTController {
    
    // DAO Dependency Injection
    SuperHeroSightingsDao dao;
    // Injecting DAO in Controller
    @Inject
    public RESTController(SuperHeroSightingsDao dao) {
        this.dao = dao;
    }
    
    @ResponseBody
    @RequestMapping(value = "/editPower", method = RequestMethod.GET)
    public Superpower getSuperpowerToEdit(HttpServletRequest request) {
        String id = request.getParameter("superpowerId");
        int superpowerId = Integer.parseInt(id);
        return dao.getSuperpower(superpowerId);
    }

    @ResponseBody
    @RequestMapping(value = "/editPerson", method = RequestMethod.GET)
    public Person getPersonToEdit(HttpServletRequest request) {
        String id = request.getParameter("personId");
        int personId = Integer.parseInt(id);
        return dao.getPerson(personId);
    }

    @ResponseBody
    @RequestMapping(value = "/editLocation", method = RequestMethod.GET)
    public Location getLocationToEdit(HttpServletRequest request) {
        String id = request.getParameter("locationId");
        int locationId = Integer.parseInt(id);
        return dao.getLocation(locationId);
    }
    
    @ResponseBody
    @RequestMapping(value = "/editOrganization", method = RequestMethod.GET)
    public Organization getOrganizationToEdit(HttpServletRequest request) {
        String id = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(id);
        return dao.getOrganization(organizationId);
    }
    
    @ResponseBody
    @RequestMapping(value = "/editSighting", method = RequestMethod.GET)
    public Sighting getSightingToEdit(HttpServletRequest request) {
        String id = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(id);
        return dao.getSighting(sightingId);
    }
}
