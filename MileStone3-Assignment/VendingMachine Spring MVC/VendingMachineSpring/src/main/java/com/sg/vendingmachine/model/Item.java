/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.model;

import java.math.BigDecimal;
import java.util.Objects;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author raulalvarado
 */
public class Item {
    private int itemId;
    @NotEmpty(message="choose an itemId")
    private String itemName;
    private BigDecimal itemPrice;
    private int itemQty;
    // creatig constructors
    public Item() {
        
    }
    
    public Item(int itemId, String itemName, BigDecimal itemPrice, int itemQty) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
    }
    // Setters and Getters

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }
    // adding Equals and HashCode

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.itemId;
        hash = 29 * hash + Objects.hashCode(this.itemName);
        hash = 29 * hash + Objects.hashCode(this.itemPrice);
        hash = 29 * hash + this.itemQty;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.itemId != other.itemId) {
            return false;
        }
        if (this.itemQty != other.itemQty) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemPrice, other.itemPrice)) {
            return false;
        }
        return true;
    }
    // adding toString 
    @Override
    public String toString() {
        return "Item{" + "itemId" + itemId + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemQty=" + itemQty + '}';
    }
}
