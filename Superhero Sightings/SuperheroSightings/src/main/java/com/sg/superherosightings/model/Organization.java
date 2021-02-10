/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.List;
import lombok.Data;

/**
 *
 * @author raulalvarado
 */
@Data
public class Organization {
    
    private int organizationId;
    private String name;
    private String description;
    private String phone;
    private String email;
    private Location location;
    private List<Person> members;
}
