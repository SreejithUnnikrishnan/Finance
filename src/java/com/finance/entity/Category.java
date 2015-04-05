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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author c0644881
 */
@Stateless
public class Category {
    private String name;
    private String cat_type;
    private int category_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCat_type() {
        return cat_type;
    }

    public void setCat_type(String cat_type) {
        this.cat_type = cat_type;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    
    public String getCategory(String type){
         JsonArrayBuilder jarray = Json.createArrayBuilder();
        try(Connection connection = DatabaseConnection.getConnection()){
            String query = "select id,name from categories where cat_type = ?";
            PreparedStatement ptst = connection.prepareStatement(query);
            ptst.setString(1, type);
            ResultSet rs = ptst.executeQuery();
            while(rs.next()){
                JsonObjectBuilder obj = Json.createObjectBuilder()
                        .add("id", rs.getInt("id"))
                        .add("name", rs.getString("name"));
                jarray.add(obj);
            }
            if(!connection.isClosed()){
                connection.close();
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Exception in getting categories: " +ex.getMessage());
            
        }
        return jarray.build().toString();
    }
    
    
}
