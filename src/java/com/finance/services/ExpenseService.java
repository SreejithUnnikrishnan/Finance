/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finance.services;


import com.finance.entity.Expense;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Sreejith
 */
@Path("expense")
public class ExpenseService {
    @EJB
    Expense expense;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getUserExpense(@PathParam("id") String id) {
        String result = expense.getExpenseDetails(id);
        if (result.isEmpty() || result.equalsIgnoreCase("[]")) {
            System.out.println("Emplty");
            return null;
        } else {
            System.out.println(result);
            return result;
        }

    }
    
}
