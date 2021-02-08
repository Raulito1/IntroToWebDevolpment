/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author raulalvarado
 */
public class ProductData {
    private String productType;
    private BigDecimal productCostPerSqFt;
    private BigDecimal laborCostPerSqFt;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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
    
    
}
