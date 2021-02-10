/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Person;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superpower;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface SuperHeroSightingsDao {
    
     // Persons interface
    public void addPerson(Person person);
    public void removePerson(int personId);
    public void updatePerson(Person person);
    public Person getPerson(int personId);
    public List<Person> getAllPersons();
    public List<Person> getPersonsByLocationId(int locationId);
    public List<Person> getPersonsByOrganizationId(int organizationId);
    
    // Locations Interface
    public void addLocation(Location location);
    public void removeLocation(int locationId);
    public void updateLocation(Location location);
    public Location getLocation(int locationId);
    public List<Location> getAllLocations();
    public List<Location> getLocationsByPersonId(int personId);
    
    // Organizations Interface
    public void addOrganization(Organization organization);
    public void removeOrganization(int organizationId);
    public void updateOrganization(Organization organization);
    public Organization getOrganization(int organizationId);
    public List<Organization> getAllOrganizations();
    public List<Organization> getOrganizationsByPersonId(int personId);
    
    // Superpowers Interface
    public void addSuperpower(Superpower superpower);
    public void removeSuperpower(int superpowerId);
    public void updateSuperpower(Superpower superpower);
    public Superpower getSuperpower(int superpowerId);
    public List<Superpower> getAllSuperpowers();
    
    // Sightings Interface
    public void addSighting(Sighting sighting);
    public void removeSighting(int sightingId);
    public void updateSighting(Sighting sighting);
    public Sighting getSighting(int sightingId);
    public List<Sighting> getAllSightings();
    public List<Sighting> getSightingsByDate(LocalDate date);
    public List<Sighting> getLastTenSightings();
}
