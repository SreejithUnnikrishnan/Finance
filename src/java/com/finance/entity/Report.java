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
 * @author Sreejith
 */
@Stateless
public class Report {

    public String generateReport(String id) {
        double incomeAmt = 0;
        double incomeBgt = 0;
        double expenseAmt = 0;
        double expenseBgt = 0;
        JsonArrayBuilder jarray = Json.createArrayBuilder();
        try (Connection connect = DatabaseConnection.getConnection()) {
            String query = "select SUM(amount) as \"amount\",SUM(budget) as \"budget\" from income where user_id = ? and start_date >= DATE_FORMAT(NOW() ,'%Y-%m-01') AND start_date <= LAST_DAY(now())";
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                incomeAmt = rs.getDouble("amount");
                incomeBgt = rs.getDouble("budget");
            }
            JsonObjectBuilder obj = Json.createObjectBuilder()
                    .add("name", "income")
                    .add("amount", incomeAmt)
                    .add("budget", incomeBgt);
            jarray.add(obj);
            query = "select SUM(amount) as \"amount\",SUM(budget) as \"budget\" from expense where user_id = ? and start_date >= DATE_FORMAT(NOW() ,'%Y-%m-01') AND start_date <= LAST_DAY(now())";
            pstmt = connect.prepareStatement(query);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                expenseAmt = rs.getDouble("amount");
                expenseBgt = rs.getDouble("budget");
            }
            obj = Json.createObjectBuilder()
                    .add("name", "expense")
                    .add("amount", expenseAmt)
                    .add("budget", expenseBgt);
            jarray.add(obj);
            if (!connect.isClosed()) {
                connect.close();
            }

        } catch (Exception ex) {
            System.out.println("Exception in user income check : " + ex.getMessage());

        }
        return jarray.build().toString();
    }

}
