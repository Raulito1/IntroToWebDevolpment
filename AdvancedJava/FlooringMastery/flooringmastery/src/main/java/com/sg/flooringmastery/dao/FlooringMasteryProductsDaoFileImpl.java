/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.ProductData;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class FlooringMasteryProductsDaoFileImpl implements FlooringMasteryProductsDao {
    
    private static final String PRODUCTS_FILE = "products.txt";
    private static final String DELIMETER = ",";
    

    @Override
    public ProductData getProduct(String productType) throws FlooringMasteryPersistenceException {
        List<ProductData>products = loadProducts();
        if (products == null) {
            return null;
        } else {
            ProductData productChosen = products.stream().filter(p -> p.getProductType().equalsIgnoreCase(productType)).findFirst().orElse(null);
            return productChosen;
        }
     }
    
    private List<ProductData> loadProducts() throws FlooringMasteryPersistenceException {
        
        Scanner scanner;
        List<ProductData> products = new ArrayList<>();
        try {
            scanner = new Scanner(
                        new BufferedReader(
                            new FileReader(PRODUCTS_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                "-_- Couldnt load product data  into memory.", e);
        }
        
        String currentLine;
        String[] currentTokens;
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMETER);
            // not supposed to have more than 3 delimeters
            if (currentTokens.length == 3) {
             ProductData currentProduct = new ProductData();
             currentProduct.setProductType(currentTokens[0]);
             currentProduct.setProductCostPerSqFt(new BigDecimal(currentTokens[1]));
             currentProduct.setLaborCostPerSqFt(new BigDecimal(currentTokens[2]));
             products.add(currentProduct);
             // adding my currentProduct to my map
            } else {
                
            }
        }
        //close scanner
        scanner.close();
        if (!products.isEmpty()) {
            return products;
        } else {
            return null;
        }
    }
    
}
