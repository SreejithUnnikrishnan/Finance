/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finance.services;

import com.finance.entity.Report;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Sreejith
 */
@Path("report")
public class ReportService {

    @EJB
    Report report;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getUserReport(@PathParam("id") String id) {
        String result = report.generateReport(id);

        return result;

    }

}
