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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author raulalvarado
 */
@Controller
public class SuperHeroSightingsController {
    
    // DAO Dependency Injection
    private SuperHeroSightingsDao dao;
    
    // Injecting DAO in Controller
    @Inject
    public SuperHeroSightingsController(SuperHeroSightingsDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String displayIndexPage(Model model) {
        List<Sighting> sightingList = dao.getLastTenSightings();
        model.addAttribute("sightingList", sightingList);
        return "index";
    }
    // ===========================
    // SUPERHEROES 
    // ===========================
    @RequestMapping(value = "/superheroes", method = RequestMethod.GET)
    public String displaySuperheroesPage(Model model) {
        List<Superpower> superpowerList = dao.getAllSuperpowers();
        List<Person> personList = dao.getAllPersons();

        model.addAttribute("personList", personList);
        model.addAttribute("superpowerList", superpowerList);
        return "superheroes";
    }

    @RequestMapping(value = "/heroDetails", method = RequestMethod.GET)
    public String displaySuperheroDetails(HttpServletRequest request, Model model) {
        String id = request.getParameter("personId");
        int personId = Integer.parseInt(id);
        Person person = dao.getPerson(personId);
        List<Organization> organizationList = dao.getOrganizationsByPersonId(personId);
        model.addAttribute("person", person);
        model.addAttribute("organizationList", organizationList);
        return "heroDetails";
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String addSuperhero(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        // powers is an array of superpowerIds
        String[] powers = request.getParameterValues("powers");
        List<Superpower> powerList = new ArrayList<>();
        // parse the power into an int then use the dao to find the power
        for (String currentPower : powers) {
            int powerId = Integer.parseInt(currentPower);
            powerList.add(dao.getSuperpower(powerId));
        }

        Person person = new Person();
        person.setName(name);
        person.setDescription(description);
        person.setSuperpowers(powerList);
        dao.addPerson(person);
        return "redirect:superheroes";
    }

    @RequestMapping(value = "/editPerson", method = RequestMethod.POST)
    public String editSuperhero(HttpServletRequest request) {
        String id = request.getParameter("personId");
        int personId = Integer.parseInt(id);
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        // powers is an array of superpowerIds
        String[] powers = request.getParameterValues("powers");
        List<Superpower> powerList = new ArrayList<>();
        // parse the power into an int then use the dao to find the power
        for (String currentPower : powers) {
            int powerId = Integer.parseInt(currentPower);
            powerList.add(dao.getSuperpower(powerId));
        }

        Person person = dao.getPerson(personId);
        person.setName(name);
        person.setDescription(description);
        person.setSuperpowers(powerList);
        dao.updatePerson(person);
        return "redirect:superheroes";
    }

    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET)
    public String deleteSuperhero(HttpServletRequest request) {
        String id = request.getParameter("personId");
        int personId = Integer.parseInt(id);
        dao.removePerson(personId);
        return "redirect:superheroes";
    }
    // =========================
    // SUPERPOWERS
    // =========================
    @RequestMapping(value = "/superpowers", method = RequestMethod.GET)
    public String displaySuperpowersPage(Model model) {
        List<Superpower> superpowerList = dao.getAllSuperpowers();
        model.addAttribute("superpowerList", superpowerList);
        return "superpowers";
    }

    @RequestMapping(value = "/powerDetails", method = RequestMethod.GET)
    public String displaySuperpowerDetails(HttpServletRequest request, Model model) {
        String id = request.getParameter("superpowerId");
        int superpowerId = Integer.parseInt(id);
        Superpower superpower = dao.getSuperpower(superpowerId);
        model.addAttribute("superpower", superpower);
        return "powerDetails";
    }

    @RequestMapping(value = "/addSuperpower", method = RequestMethod.POST)
    public String addSuperpower(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Superpower superpower = new Superpower();
        superpower.setName(name);
        superpower.setDescription(description);
        dao.addSuperpower(superpower);
        return "redirect:superpowers";
    }

    @RequestMapping(value = "/editSuperpower", method = RequestMethod.POST)
    public String editSuperpower(HttpServletRequest request, Model model) {
        String id = request.getParameter("superpowerId");
        int superpowerId = Integer.parseInt(id);
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Superpower superpower = dao.getSuperpower(superpowerId);
        superpower.setName(name);
        superpower.setDescription(description);
        dao.updateSuperpower(superpower);
        return "redirect:superpowers";
    }

    @RequestMapping(value = "/deletePower", method = RequestMethod.GET)
    public String deleteSuperpower(HttpServletRequest request) {
        String id = request.getParameter("superpowerId");
        int superpowerId = Integer.parseInt(id);
        dao.removeSuperpower(superpowerId);
        return "redirect:superpowers";
    }
    // ===========================
    // ORGANIZATIONS
    // ===========================
    @RequestMapping(value = "/organizations", method = RequestMethod.GET)
    public String displayOrganizationsPage(Model model) {
        List<Organization> organizationList = dao.getAllOrganizations();
        List<Person> personList = dao.getAllPersons();
        List<Location> locationList = dao.getAllLocations();
        model.addAttribute("organizationList", organizationList);
        model.addAttribute("personList", personList);
        model.addAttribute("locationList", locationList);
        return "organizations";
    }

    @RequestMapping(value = "/organizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {
        String id = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(id);
        Organization organization = dao.getOrganization(organizationId);
        model.addAttribute("organization", organization);
        return "organizationDetails";
    }

    @RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
    public String addOrganization(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        // The form passes the locationId
        String strLocation = request.getParameter("location");
        int locationId = Integer.parseInt(strLocation);
        Location location = dao.getLocation(locationId);
        // members is an array of personIds
        String[] members = request.getParameterValues("persons");
        List<Person> personList = new ArrayList<>();
        // parse the personId into an int then use the dao to find the person
        for (String currentMember : members) {
            int personId = Integer.parseInt(currentMember);
            personList.add(dao.getPerson(personId));
        }

        Organization organization = new Organization();
        organization.setName(name);
        organization.setDescription(description);
        organization.setPhone(phone);
        organization.setEmail(email);
        organization.setLocation(location);
        organization.setMembers(personList);
        dao.addOrganization(organization);
        return "redirect:organizations";
    }

    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(HttpServletRequest request, Model model) {
        String id = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(id);
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        // The form passes the locationId
        String strLocation = request.getParameter("location");
        int locationId = Integer.parseInt(strLocation);
        Location location = dao.getLocation(locationId);
        // members is an array of personIds
        String[] members = request.getParameterValues("persons");
        List<Person> personList = new ArrayList<>();
        // parse the personId into an int then use the dao to find the person
        for (String currentMember : members) {
            int personId = Integer.parseInt(currentMember);
            personList.add(dao.getPerson(personId));
        }

        Organization organization = dao.getOrganization(organizationId);
        organization.setName(name);
        organization.setDescription(description);
        organization.setPhone(phone);
        organization.setEmail(email);
        organization.setLocation(location);
        organization.setMembers(personList);
        dao.updateOrganization(organization);
        return "redirect:organizations";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String id = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(id);
        dao.removeOrganization(organizationId);
        return "redirect:organizations";
    }
    // =========================
    // LOCATIONS
    // =========================
    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public String displayLocationsPage(Model model) {
        List<Location> locationList = dao.getAllLocations();
        model.addAttribute("locationList", locationList);
        return "locations";
    }

    @RequestMapping(value = "/locationDetails", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) {
        String id = request.getParameter("locationId");
        int locationId = Integer.parseInt(id);
        Location location = dao.getLocation(locationId);
        model.addAttribute("location", location);
        return "locationDetails";
    }

    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    public String addLocation(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String strLongitude = request.getParameter("longitude");
        String strLatitude = request.getParameter("latitude");
        double longitude = Double.parseDouble(strLongitude);
        double latitude = Double.parseDouble(strLatitude);

        Location location = new Location();
        location.setName(name);
        location.setDescription(description);
        location.setStreet(street);
        location.setCity(city);
        location.setState(state);
        location.setCountry(country);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        dao.addLocation(location);
        return "redirect:locations";
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(HttpServletRequest request, Model model) {
        String id = request.getParameter("locationId");
        int locationId = Integer.parseInt(id);
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String strLongitude = request.getParameter("longitude");
        String strLatitude = request.getParameter("latitude");
        // FIGURE OUT NULLSSSSSSSSSSSSSS
        Double longitude;
        Double latitude;
        try {
            longitude = Double.parseDouble(strLongitude);
        } catch (NumberFormatException e) {
            longitude = null;

        }
        try {
            latitude = Double.parseDouble(strLatitude);
        } catch (NumberFormatException e) {
            latitude = null;
        }
        Location location = dao.getLocation(locationId);
        location.setName(name);
        location.setDescription(description);
        location.setStreet(street);
        location.setCity(city);
        location.setState(state);
        location.setCountry(country);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        dao.updateLocation(location);
        return "redirect:locations";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String id = request.getParameter("locationId");
        int locationId = Integer.parseInt(id);
        dao.removeLocation(locationId);
        return "redirect:locations";
    }
    // ========================
    // SIGHTINGS
    // ========================
    @RequestMapping(value = "/sightings", method = RequestMethod.GET)
    public String displaySightingsPage(Model model) {
        List<Sighting> sightingList = dao.getAllSightings();
        List<Location> locationList = dao.getAllLocations();
        List<Person> personList = dao.getAllPersons();
        model.addAttribute("sightingList", sightingList);
        model.addAttribute("locationList", locationList);
        model.addAttribute("personList", personList);
        return "sightings";
    }

    @RequestMapping(value = "/sightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String id = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(id);
        Sighting sighting = dao.getSighting(sightingId);
        model.addAttribute("sighting", sighting);
        return "sightingDetails";
    }

    @RequestMapping(value = "/addSighting", method = RequestMethod.POST)
    public String addSighting(HttpServletRequest request) {
        String strLocation = request.getParameter("location");
        int locationId = Integer.parseInt(strLocation);
        Location location = dao.getLocation(locationId);
        String strDate = request.getParameter("date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(strDate, formatter);
        // persons is an array of personIds
        String[] persons = request.getParameterValues("persons");
        List<Person> personList = new ArrayList<>();
        // parse each id into an int and get persons from dao
        for (String currentPerson : persons) {
            int personId = Integer.parseInt(currentPerson);
            personList.add(dao.getPerson(personId));
        }
        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        sighting.setPersons(personList);
        sighting.setDate(date);
        dao.addSighting(sighting);
        return "redirect:sightings";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(HttpServletRequest request, Model model) {
        String id = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(id);
        String strLocation = request.getParameter("location");
        int locationId = Integer.parseInt(strLocation);
        Location location = dao.getLocation(locationId);
        String strDate = request.getParameter("date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(strDate, formatter);
        // persons is an array of personIds
        String[] persons = request.getParameterValues("persons");
        List<Person> personList = new ArrayList<>();
        // parse each id into an int and get persons from dao
        for (String currentPerson : persons) {
            int personId = Integer.parseInt(currentPerson);
            personList.add(dao.getPerson(personId));
        }
        Sighting sighting = dao.getSighting(sightingId);
        sighting.setLocation(location);
        sighting.setPersons(personList);
        sighting.setDate(date);
        dao.updateSighting(sighting);
        return "redirect:sightings";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        String id = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(id);
        dao.removeSighting(sightingId);
        return "redirect:sightings";
    }
}
