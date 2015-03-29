/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finance.services;

import com.finance.entity.Category;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author Sreejith
 */
@Path("category")
public class CategoryService {
    @EJB
    Category category;
    
    @GET
    @Path("{type}")
    public String getCategory(@PathParam("type") String type){
        String result = category.getCategory(type);
        return result;
    }
    
    
}
