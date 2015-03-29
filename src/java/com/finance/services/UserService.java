/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finance.services;

import com.finance.entity.User;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Sreejith
 */
@Path("user")
public class UserService {

    @EJB
    User user;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getUser(@PathParam("id") String id, String password) {
        String userDetails = user.checkUser(id, password);
        if (userDetails.isEmpty()) {
            return "false";
        } else {
            return userDetails;
        }
    }
    
    @POST
    @Consumes("application/json")
    public String insertUser(String userDetails){
        String result = user.addUser(userDetails);
        return null;
    }
    

}
