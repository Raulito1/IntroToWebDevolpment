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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author raulalvarado
 */
public class SuperHeroSightingsDaoDbImpl implements SuperHeroSightingsDao {
    
    // JDBC Template Dependency Injection
    private JdbcTemplate jdbcTemplate;
    // setter for Jdbc
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    // creating a class for SQL QUERIES
    private static final class SqlQueries {
        
        // =============================
        // PERSONS QUERIES
        // =============================
        private static final String INSERT_PERSON
                = "INSERT INTO persons (name, description) "
                + "VALUES (?, ?)";

        private static final String DELETE_PERSON
                = "DELETE FROM persons WHERE personId = ?";

        private static final String UPDATE_PERSON
                = "UPDATE persons SET name = ?, description = ? "
                + "WHERE personId = ?";

        private static final String SELECT_PERSON
                = "SELECT * FROM persons WHERE personId = ?";

        private static final String SELECT_ALL_PERSONS
                = "SELECT * FROM persons";

        private static final String INSERT_PERSONS_SUPERPOWERS
                = "INSERT INTO persons_superpowers (personId, superpowerId) "
                + "VALUES (?, ?)";

        private static final String DELETE_PERSONS_SUPERPOWERS_BY_PERSON_ID
                = "DELETE FROM persons_superpowers WHERE personId = ?";

        private static final String DELETE_PERSONS_ORGANIZATIONS_BY_PERSON_ID
                = "DELETE FROM persons_organizations WHERE personId = ?";

        private static final String DELETE_SIGHTINGS_PERSONS_BY_PERSON_ID
                = "DELETE FROM sightings_persons WHERE personId = ?";

        private static final String SELECT_PERSONS_BY_ORGANIZATION_ID
                = "SELECT p.personId, p.name, p.description "
                + "FROM persons p "
                + "JOIN persons_organizations po ON p.personId = po.personId "
                + "WHERE po.organizationId = ?";

        private static final String SELECT_PERSONS_BY_LOCATION_ID
                = "SELECT p.personId, p.name, p.description "
                + "FROM persons p "
                + "JOIN sightings_persons sp ON p.personId = sp.personId "
                + "JOIN sightings s on sp.sightingId = s.sightingId "
                + "WHERE s.locationId = ?";
        // ====================================
        // SUPERPOWER QUERIES
        // ====================================
        private static final String INSERT_SUPERPOWER
                = "INSERT INTO superpowers (name, description) "
                + "VALUES (?, ?)";

        private static final String DELETE_SUPERPOWER
                = "DELETE FROM superpowers WHERE superpowerId = ?";

        private static final String UPDATE_SUPERPOWER
                = "UPDATE superpowers SET name = ?, description = ? "
                + "WHERE superpowerId = ?";

        private static final String SELECT_SUPERPOWER
                = "SELECT * FROM superpowers WHERE superpowerId = ?";

        private static final String SELECT_ALL_SUPERPOWERS
                = "SELECT * FROM superpowers";

        private static final String SELECT_SUPERPOWERS_BY_PERSON_ID
                = "SELECT sp.superpowerId, sp.name, sp.description "
                + "FROM superpowers sp "
                + "JOIN persons_superpowers ps ON sp.superpowerId = ps.superpowerId "
                + "WHERE ps.personId = ?";

        private static final String DELETE_PERSONS_SUPERPOWERS_BY_SUPERPOWER_ID
                = "DELETE FROM persons_superpowers WHERE superpowerId = ?";
        // =================================
        // LOCATIONS QUERIES
        // =================================
        private static final String INSERT_LOCATION
                = "INSERT INTO locations (name, description, street, city, state, "
                + "country, longitude, latitude) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        private static final String DELETE_LOCATION
                = "DELETE FROM locations WHERE locationId = ?";

        private static final String UPDATE_LOCATION
                = "UPDATE locations SET name = ?, description = ?, street = ?, city = ?, "
                + "state = ?, country = ?, longitude = ?, latitude = ? "
                + "WHERE locationId = ?";

        private static final String SELECT_LOCATION
                = "SELECT * FROM locations WHERE locationId = ?";

        private static final String SELECT_ALL_LOCATIONS
                = "SELECT * FROM locations";

        private static final String DELETE_SIGHTINGS_BY_LOCATION_ID
                = "DELETE FROM sightings WHERE locationId = ?";

        private static final String SELECT_LOCATIONS_BY_PERSON_ID
                = "SELECT l.locationId, l.name, l.description, l.street, l.city, "
                + "l.state, l.country, l.longitude, l.latitude "
                + "FROM locations l "
                + "JOIN sightings s ON l.locationID = s.locationID "
                + "JOIN sightings_persons sp ON s.sightingID = sp.sightingID "
                + "WHERE personID = ? "
                + "GROUP BY locationID";
        // =================================
        // ORGANIZATION QUERIES
        // =================================
        private static final String INSERT_ORGANIZATION
                = "INSERT INTO organizations (name, description, phone, email, locationID) "
                + "VALUES (?, ?, ?, ?, ?)";

        private static final String DELETE_ORGANIZATION
                = "DELETE FROM organizations WHERE organizationId = ?";

        private static final String UPDATE_ORGANIZATION
                = "UPDATE organizations SET name = ?, description = ?, "
                + "phone = ?, email = ?, locationId = ? "
                + "WHERE organizationId = ?";

        private static final String SELECT_ORGANIZATION
                = "SELECT * FROM organizations WHERE organizationId = ?";

        private static final String SELECT_ALL_ORGANIZATIONS
                = "SELECT * FROM organizations";

        private static final String SELECT_LOCATION_BY_ORGANIZATION_ID
                = "SELECT l.locationId, l.name, l.description, l.street, l.city, l.state, "
                + "l.country, l.longitude, l.latitude FROM Locations l "
                + "JOIN organizations o ON l.locationID = o.locationId "
                + "WHERE o.organizationId = ?";

        private static final String INSERT_PERSONS_ORGANIZATIONS
                = "INSERT INTO Persons_Organizations (organizationID, personID) "
                + "VALUES (?, ?)";

        private static final String DELETE_PERSONS_ORGANIZATIONS_BY_ORGANIZATION_ID
                = "DELETE FROM Persons_Organizations WHERE organizationID = ?";

        private static final String SELECT_ORGANIZATIONS_BY_PERSON_ID
                = "SELECT o.organizationID, o.name, o.description, o.phone, o.email "
                + "FROM Organizations o "
                + "JOIN Persons_Organizations po ON o.organizationID = po.organizationID "
                + "WHERE po.personID = ?";

        private static final String UPDATE_ORGANIZATIONS_SET_LOCATION_TO_NULL_BY_LOCATION_ID
                = "UPDATE Organizations SET locationID = null "
                + "WHERE locationID = ?";
        // =================================
        // SIGHTINGS QUERIES
        // =================================
        private static final String INSERT_SIGHTING
                = "INSERT INTO sightings (locationID, date) "
                + "VALUES (?, ?)";

        private static final String DELETE_SIGHTING
                = "DELETE FROM sightings WHERE sightingId = ?";

        private static final String UPDATE_SIGHTING
                = "UPDATE sightings SET locationId = ?, date = ? "
                + "WHERE sightingID = ?";

        private static final String SELECT_SIGHTING
                = "SELECT * FROM sightings WHERE sightingId = ?";

        private static final String SELECT_ALL_SIGHTINGS
                = "SELECT * FROM sightings";

        private static final String SELECT_PERSONS_BY_SIGHTING_ID
                = "SELECT p.personId, p.name, p.description "
                + "FROM persons p "
                + "JOIN sightings_persons sp ON p.personId = sp.personId "
                + "WHERE sp.sightingId = ?";

        private static final String SELECT_LOCATION_BY_SIGHTING_ID
                = "SELECT l.locationId, l.name, l.description, l.street, l.city, "
                + "l.state, l.country, l.longitude, l.latitude FROM locations l "
                + "JOIN sightings s ON l.locationId = s.locationId "
                + "WHERE s.sightingId = ?";

        private static final String SELECT_SIGHTINGS_BY_DATE
                = "SELECT sightingId, date FROM sightings WHERE date = ?";

        private static final String INSERT_SIGHTINGS_PERSONS
                = "INSERT INTO sightings_persons (sightingId, personId) VALUES (?, ?)";

        private static final String DELETE_SIGHTINGS_PERSONS_BY_SIGHTING_ID
                = "DELETE FROM sightings_persons WHERE sightingId = ?";

        private static final String SELECT_LAST_TEN_SIGHTINGS
                = "SELECT * FROM sightings "
                + "ORDER BY date DESC LIMIT 0,10";
    }
    // ===========================
    // PERSONS IMOPLEMENTATION
    // ===========================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPerson(Person person) {
        jdbcTemplate.update(SqlQueries.INSERT_PERSON,
                person.getName(),
                person.getDescription());
        person.setPersonId(getLastInsertId());
        // update the Persons_Superpowers table
        insertPersonsSuperpowers(person);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removePerson(int personId) {
        // delete Persons_Superpowers relationship
        jdbcTemplate.update(SqlQueries.DELETE_PERSONS_SUPERPOWERS_BY_PERSON_ID, personId);
        // delete Persons_Organizations relationship
        jdbcTemplate.update(SqlQueries.DELETE_PERSONS_ORGANIZATIONS_BY_PERSON_ID, personId);
        // delete Sightings_Persons relationship
        jdbcTemplate.update(SqlQueries.DELETE_SIGHTINGS_PERSONS_BY_PERSON_ID, personId);
        // delete person
        jdbcTemplate.update(SqlQueries.DELETE_PERSON, personId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updatePerson(Person person) {
        jdbcTemplate.update(SqlQueries.UPDATE_PERSON,
                person.getName(),
                person.getDescription(),
                person.getPersonId());
        // delete Persons_Superpowers relationship then reset them
        jdbcTemplate.update(SqlQueries.DELETE_PERSONS_SUPERPOWERS_BY_PERSON_ID, person.getPersonId());
        insertPersonsSuperpowers(person);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Person getPerson(int personId) {
        try {
            Person person = jdbcTemplate.queryForObject(SqlQueries.SELECT_PERSON,
                    new PersonMapper(),
                    personId);
            // get superpowers for person
            person.setSuperpowers(findSuperpowersForPerson(person));
            return person;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Person> getAllPersons() {
        try {
            List<Person> personList = jdbcTemplate.query(SqlQueries.SELECT_ALL_PERSONS, new PersonMapper());
            // set the superpowers for each person
            return associateSuperpowersWithPersons(personList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Person> getPersonsByLocationId(int locationId) {
        try {
            List<Person> personList = jdbcTemplate.query(SqlQueries.SELECT_PERSONS_BY_LOCATION_ID,
                    new PersonMapper(),
                    locationId);
            // add superpowers to persons
            return associateSuperpowersWithPersons(personList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Person> getPersonsByOrganizationId(int organizationId) {
        try {
            List<Person> personList = jdbcTemplate.query(SqlQueries.SELECT_PERSONS_BY_ORGANIZATION_ID,
                    new PersonMapper(),
                    organizationId);
            // add superpowers to persons
            return associateSuperpowersWithPersons(personList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    // =================================
    // LOCATIONS IMPLEMENTATION
    // =================================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SqlQueries.INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getCountry(),
                location.getLongitude(),
                location.getLatitude());
        location.setLocationId(getLastInsertId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeLocation(int locationId) {
        // delete Sightings relationship
        jdbcTemplate.update(SqlQueries.DELETE_SIGHTINGS_BY_LOCATION_ID, locationId);
        // update location for organizations and set to null
        jdbcTemplate.update(SqlQueries.UPDATE_ORGANIZATIONS_SET_LOCATION_TO_NULL_BY_LOCATION_ID, locationId);
        // delete location
        jdbcTemplate.update(SqlQueries.DELETE_LOCATION, locationId);
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SqlQueries.UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getCountry(),
                location.getLongitude(),
                location.getLatitude(),
                location.getLocationId());
    }

    @Override
    public Location getLocation(int locationId) {
        try {
            Location location = jdbcTemplate.queryForObject(SqlQueries.SELECT_LOCATION,
                    new LocationMapper(),
                    locationId);
            return location;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        try {
            return jdbcTemplate.query(SqlQueries.SELECT_ALL_LOCATIONS, new LocationMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Location> getLocationsByPersonId(int personId) {
        try {
            return jdbcTemplate.query(SqlQueries.SELECT_LOCATIONS_BY_PERSON_ID,
                    new LocationMapper(),
                    personId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    // ===============================
    // ORGANIZATIONS IMPLEMENTATION
    // ===============================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SqlQueries.INSERT_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getPhone(),
                organization.getEmail(),
                organization.getLocation().getLocationId());
        organization.setOrganizationId(getLastInsertId());
        // Update the Persons_Organizations table
        insertPersonsOrganizations(organization);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeOrganization(int organizationId) {
        // Delete Persons_Organizations relationship first
        jdbcTemplate.update(SqlQueries.DELETE_PERSONS_ORGANIZATIONS_BY_ORGANIZATION_ID, organizationId);
        // Delete organization
        jdbcTemplate.update(SqlQueries.DELETE_ORGANIZATION, organizationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SqlQueries.UPDATE_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getPhone(),
                organization.getEmail(),
                organization.getLocation().getLocationId(),
                organization.getOrganizationId());
        // Delete Persons_Organizations relationship then update
        jdbcTemplate.update(SqlQueries.DELETE_PERSONS_ORGANIZATIONS_BY_ORGANIZATION_ID, organization.getOrganizationId());
        insertPersonsOrganizations(organization);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization getOrganization(int organizationId) {
        try {
            Organization organization = jdbcTemplate.queryForObject(SqlQueries.SELECT_ORGANIZATION,
                    new OrganizationMapper(),
                    organizationId);
            // get persons for organization
            organization.setMembers(findPersonsForOrganization(organization));
            // get location for organization
            organization.setLocation(findLocationForOrganization(organization));
            return organization;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Organization> getAllOrganizations() {
        try {
            List<Organization> organizationList = jdbcTemplate.query(SqlQueries.SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
            // Set the superpowers for each person
            return associateLocationAndPersonsWithOrganizations(organizationList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Organization> getOrganizationsByPersonId(int personId) {
        try {
            List<Organization> organizationList = jdbcTemplate.query(SqlQueries.SELECT_ORGANIZATIONS_BY_PERSON_ID,
                    new OrganizationMapper(),
                    personId);
            // get location and members for organizations
            return associateLocationAndPersonsWithOrganizations(organizationList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    // ====================================
    // SUPERPOWERS IMPLEMENTATION
    // ====================================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperpower(Superpower superpower) {
        jdbcTemplate.update(SqlQueries.INSERT_SUPERPOWER,
                superpower.getName(),
                superpower.getDescription());
        superpower.setSuperpowerId(getLastInsertId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeSuperpower(int superpowerId) {
        // Delete Persons_Superpowers relationship first
        jdbcTemplate.update(SqlQueries.DELETE_PERSONS_SUPERPOWERS_BY_SUPERPOWER_ID, superpowerId);
        // Delete superpower
        jdbcTemplate.update(SqlQueries.DELETE_SUPERPOWER, superpowerId);
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        jdbcTemplate.update(SqlQueries.UPDATE_SUPERPOWER,
                superpower.getName(),
                superpower.getDescription(),
                superpower.getSuperpowerId());
    }

    @Override
    public Superpower getSuperpower(int superpowerId) {
        try {
            return jdbcTemplate.queryForObject(SqlQueries.SELECT_SUPERPOWER,
                    new SuperpowerMapper(),
                    superpowerId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        try {
            return jdbcTemplate.query(SqlQueries.SELECT_ALL_SUPERPOWERS, new SuperpowerMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    // =================================
    // SIGHTINGS IMPLEMENTATION
    // ==================================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SqlQueries.INSERT_SIGHTING,
                sighting.getLocation().getLocationId(),
                sighting.getDate().toString());
        sighting.setSightingId(getLastInsertId());
        // update the Sightings_Persons table
        insertSightingsPersons(sighting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeSighting(int sightingId) {
        // delete Sightings_Persons relationship
        jdbcTemplate.update(SqlQueries.DELETE_SIGHTINGS_PERSONS_BY_SIGHTING_ID, sightingId);
        // delete sighting
        jdbcTemplate.update(SqlQueries.DELETE_SIGHTING, sightingId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SqlQueries.UPDATE_SIGHTING,
                sighting.getLocation().getLocationId(),
                sighting.getDate().toString(),
                sighting.getSightingId());
        // Delete Sightings_Persons relationship then update
        jdbcTemplate.update(SqlQueries.DELETE_SIGHTINGS_PERSONS_BY_SIGHTING_ID, sighting.getSightingId());
        insertSightingsPersons(sighting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting getSighting(int sightingId) {
        try {
            Sighting sighting = jdbcTemplate.queryForObject(SqlQueries.SELECT_SIGHTING,
                    new SightingMapper(),
                    sightingId);
            // get persons for sighting
            sighting.setPersons(findPersonsForSighting(sighting));
            // get location for sighting
            sighting.setLocation(findLocationForSighting(sighting));
            return sighting;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Sighting> getAllSightings() {
        try {
            List<Sighting> sightingList = jdbcTemplate.query(SqlQueries.SELECT_ALL_SIGHTINGS, new SightingMapper());
            // associate location and persons with each sighting
            return associateLocationAndPersonsWithSighting(sightingList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Sighting> getSightingsByDate(LocalDate date) {
        try {
            List<Sighting> sightingList = jdbcTemplate.query(SqlQueries.SELECT_SIGHTINGS_BY_DATE,
                    new SightingMapper(),
                    date.toString());
            return associateLocationAndPersonsWithSighting(sightingList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Sighting> getLastTenSightings() {
        try {
            List<Sighting> sightingList = jdbcTemplate.query(SqlQueries.SELECT_LAST_TEN_SIGHTINGS, 
                    new SightingMapper());
            return associateLocationAndPersonsWithSighting(sightingList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    // ============================
    // HELPER METHODS
    // ============================
    private int getLastInsertId() {
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    private void insertPersonsSuperpowers(Person person) {
        final int personId = person.getPersonId();
        final List<Superpower> superpowers = person.getSuperpowers();
        // Update the Persons_Superpowers table with an entry for
        // every superpower the person has
        for (Superpower currentPower : superpowers) {
            jdbcTemplate.update(SqlQueries.INSERT_PERSONS_SUPERPOWERS,
                    personId,
                    currentPower.getSuperpowerId());
        }
    }

    private List<Superpower> findSuperpowersForPerson(Person person) {
        try {
            return jdbcTemplate.query(SqlQueries.SELECT_SUPERPOWERS_BY_PERSON_ID,
                    new SuperpowerMapper(),
                    person.getPersonId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private List<Person> associateSuperpowersWithPersons(List<Person> personList) {
        for (Person currentPerson : personList) {
            // Add superpowers to current person
            currentPerson.setSuperpowers(findSuperpowersForPerson(currentPerson));
        }
        return personList;
    }

    private void insertPersonsOrganizations(Organization organization) {
        final int organizationId = organization.getOrganizationId();
        final List<Person> members = organization.getMembers();
        // Update the Persons_Organizations table with an entry for
        // every person the organization has
        for (Person currentPerson : members) {
            jdbcTemplate.update(SqlQueries.INSERT_PERSONS_ORGANIZATIONS,
                    organizationId,
                    currentPerson.getPersonId());
        }
    }

    private List<Person> findPersonsForOrganization(Organization organization) {
        try {
            List<Person> personList = jdbcTemplate.query(SqlQueries.SELECT_PERSONS_BY_ORGANIZATION_ID,
                    new PersonMapper(),
                    organization.getOrganizationId());
            // add superpowers to persons
            return associateSuperpowersWithPersons(personList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private Location findLocationForOrganization(Organization organization) {
        try {
            Location location = jdbcTemplate.queryForObject(SqlQueries.SELECT_LOCATION_BY_ORGANIZATION_ID,
                    new LocationMapper(),
                    organization.getOrganizationId());
            return location;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private List<Organization> associateLocationAndPersonsWithOrganizations(List<Organization> organizationList) {
        for (Organization currentOrganization : organizationList) {
            // Add location to current organization
            currentOrganization.setLocation(findLocationForOrganization(currentOrganization));
            // Add persons to current organization
            currentOrganization.setMembers(findPersonsForOrganization(currentOrganization));
        }
        return organizationList;
    }

    private void insertSightingsPersons(Sighting sighting) {
        final int sightingId = sighting.getSightingId();
        final List<Person> persons = sighting.getPersons();
        // Update the Sightings_Persons table with an entry for
        // every person the sighting has
        for (Person currentPerson : persons) {
            jdbcTemplate.update(SqlQueries.INSERT_SIGHTINGS_PERSONS,
                    sightingId,
                    currentPerson.getPersonId());
        }
    }

    private List<Person> findPersonsForSighting(Sighting sighting) {
        try {
            List<Person> persons = jdbcTemplate.query(SqlQueries.SELECT_PERSONS_BY_SIGHTING_ID,
                    new PersonMapper(),
                    sighting.getSightingId());
            return associateSuperpowersWithPersons(persons);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private Location findLocationForSighting(Sighting sighting) {
        try {
            return jdbcTemplate.queryForObject(SqlQueries.SELECT_LOCATION_BY_SIGHTING_ID,
                    new LocationMapper(),
                    sighting.getSightingId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private List<Sighting> associateLocationAndPersonsWithSighting(List<Sighting> sightingList) {
        for (Sighting currentSighting : sightingList) {
            // Add location to current sighting
            currentSighting.setLocation(findLocationForSighting(currentSighting));
            // Add persons to current organization
            currentSighting.setPersons(findPersonsForSighting(currentSighting));
        }
        return sightingList;
    }

    // MAPPER METHODS
    // ===================================================
    private static final class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int i) throws SQLException {
            Person p = new Person();
            p.setPersonId(rs.getInt("personID"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            return p;
        }

    }

    private static final class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int i) throws SQLException {
            Superpower sp = new Superpower();
            sp.setSuperpowerId(rs.getInt("superpowerID"));
            sp.setName(rs.getString("name"));
            sp.setDescription(rs.getString("description"));
            return sp;
        }

    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location l = new Location();
            l.setLocationId(rs.getInt("locationID"));
            l.setName(rs.getString("name"));
            l.setDescription(rs.getString("description"));
            l.setStreet(rs.getString("street"));
            l.setCity(rs.getString("city"));
            l.setState(rs.getString("state"));
            l.setCountry(rs.getString("country"));
            l.setLongitude(rs.getDouble("longitude"));
            l.setLatitude(rs.getDouble("latitude"));
            return l;
        }

    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization o = new Organization();
            o.setOrganizationId(rs.getInt("organizationID"));
            o.setName(rs.getString("name"));
            o.setDescription(rs.getString("description"));
            o.setPhone(rs.getString("phone"));
            o.setEmail(rs.getString("email"));
            return o;
        }

    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();
            s.setSightingId(rs.getInt("sightingID"));
            s.setDate(rs.getTimestamp("date").toLocalDateTime().toLocalDate());
            return s;
        }

    }
}
