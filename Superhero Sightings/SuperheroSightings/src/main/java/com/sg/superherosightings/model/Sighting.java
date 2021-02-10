/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

/**
 *
 * @author raulalvarado
 */
@Data
public class Sighting {
    
    private int sightingId;
    private Location location;
    private LocalDate date;
    private List<Person> persons;
}
