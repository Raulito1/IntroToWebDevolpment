/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import lombok.Data;

/**
 *
 * @author raulalvarado
 */
@Data
public class Location {
    
    private int locationId;
    private String name;
    private String description;
    private String street;
    private String city;
    private String state;
    private String country;
    private double longitude;
    private double latitude;
    
}
