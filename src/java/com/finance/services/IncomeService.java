/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finance.services;

import com.finance.entity.Income;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author Sreejith
 */
@Path("income")
public class IncomeService {
    
    @EJB
    Income income;
    
    @GET
    @Path("{id}")
    public String getUserIncome(@PathParam("id") String id){
        String result = income.getIncomeDetails(id);
        if(result.isEmpty() || result.equalsIgnoreCase("[]")){
            System.out.println("Emplty");                    
            return null;
        }
        else{
            System.out.println(result);
            return result;
        }
        
    }
    
}
