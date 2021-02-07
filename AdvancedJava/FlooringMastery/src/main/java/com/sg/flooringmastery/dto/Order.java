/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author raulalvarado
 */
public class Order {
    
    private LocalDate date;
    private int orderNum;
    private String customerName;
    private String productType;
    private String stateAbbr;
    private BigDecimal area;
    private BigDecimal productCost;
    private BigDecimal laborCost;
    private BigDecimal productCostPerSqFt;
    private BigDecimal laborCostPerSqFt;
    private BigDecimal taxRate;
    private BigDecimal tax;
    private BigDecimal total;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orederNum) {
        this.orderNum = orederNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getProductCost() {
        return productCost;
    }

    public void setProductCost(BigDecimal productCost) {
        this.productCost = productCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getProductCostPerSqFt() {
        return productCostPerSqFt;
    }

    public void setProductCostPerSqFt(BigDecimal productCostPerSqFt) {
        this.productCostPerSqFt = productCostPerSqFt;
    }

    public BigDecimal getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }

    public void setLaborCostPerSqFt(BigDecimal laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.date);
        hash = 61 * hash + this.orderNum;
        hash = 61 * hash + Objects.hashCode(this.customerName);
        hash = 61 * hash + Objects.hashCode(this.productType);
        hash = 61 * hash + Objects.hashCode(this.stateAbbr);
        hash = 61 * hash + Objects.hashCode(this.area);
        hash = 61 * hash + Objects.hashCode(this.productCost);
        hash = 61 * hash + Objects.hashCode(this.laborCost);
        hash = 61 * hash + Objects.hashCode(this.productCostPerSqFt);
        hash = 61 * hash + Objects.hashCode(this.laborCostPerSqFt);
        hash = 61 * hash + Objects.hashCode(this.taxRate);
        hash = 61 * hash + Objects.hashCode(this.tax);
        hash = 61 * hash + Objects.hashCode(this.total);
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
        final Order other = (Order) obj;
        if (this.orderNum != other.orderNum) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.stateAbbr, other.stateAbbr)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.productCost, other.productCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.productCostPerSqFt, other.productCostPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPerSqFt, other.laborCostPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        return true;
    }
    
    
}
