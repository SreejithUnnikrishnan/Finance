/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finance.services;

import com.finance.entity.Income;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
    @Produces("application/json")
    public String getUserIncome(@PathParam("id") String id) {
        System.out.println("Inside get in income service");
        String result = income.getIncomeDetails(id);
//        if (result.isEmpty() || result.equalsIgnoreCase("[]")) {
//            System.out.println("Emplty");
//            return null;
//        } else {
//            System.out.println(result);
            return result;
      //  }

    }
    
    @GET
    @Path("{id}/{name}")
    public double getBudget(@PathParam("id") String id,@PathParam("name") String name){
        double bud = income.getBudgetAmount(id,name);        
        return bud;
    }

    @POST
    @Consumes("application/json")
    public String insertUserIncome(String details) {
        String result = income.insertIncome(details);
        return result;

    }

    @DELETE
    @Path("{id}/{name}")
    public String deleteUserIncome(@PathParam("id") String id,@PathParam("name") String name) {
        String result = income.deleteIncome(id,name);
        return result;

    }

}
