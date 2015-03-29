/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.finance.entity;

import com.finance.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author c0644881
 */
@Stateless
public class Expense {
    private int id;
    private String name;
    private String start_date;
    private double budget;
    private String user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getExpenseDetails(String id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            JsonArrayBuilder jarray = Json.createArrayBuilder();
            String query = "SELECT * FROM expense WHERE user_id = ? and start_date >= DATE_FORMAT(NOW() ,'%Y-%m-01') AND start_date <= LAST_DAY(now())";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                JsonObjectBuilder obj = Json.createObjectBuilder()
                        .add("id", rs.getInt("id"))
                        .add("name", rs.getString("name"))
                        .add("budget", rs.getDouble("budget"))
                        .add("start_date", (rs.getDate("start_date")).toString())
                        .add("user_id", rs.getInt("user_id"))
                        .add("amount", rs.getDouble("amount"));
                jarray.add(obj);
            }

            return jarray.build().toString();

        } catch (Exception ex) {
            System.out.println("Exception thrown in Get user Expense Details: " + ex.getMessage());
            return null;
        }
    }

    
    
    
}
