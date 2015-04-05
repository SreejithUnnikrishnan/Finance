/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finance.entity;

import com.finance.database.DatabaseConnection;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.stream.JsonParser;

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
            if (!connection.isClosed()) {
                connection.close();
            }

            return jarray.build().toString();

        } catch (Exception ex) {
            System.out.println("Exception thrown in Get user Expense Details: " + ex.getMessage());
            return null;
        }
    }

    public String insertExpense(String details) {
        JsonParser parser = Json.createParser(new StringReader(details));
        Map<String, String> map = new HashMap<>();
        String name = "", value;
        while (parser.hasNext()) {
            JsonParser.Event evt = parser.next();
            switch (evt) {
                case KEY_NAME:
                    name = parser.getString();
                    break;
                case VALUE_STRING:
                    value = parser.getString();
                    map.put(name, value);
                    break;
                case VALUE_NUMBER:
                    value = Integer.toString(parser.getInt());
                    map.put(name, value);
                    break;
            }
        }
        int changes = 0;
        String category = map.get("name");
        String budget = map.get("budget");
        String start_date = map.get("start_date");
        String user_id = map.get("user_id");
        String amount = map.get("amount");
        int count = checkUserExpense(category, user_id);
        if (count == -1) {
            String query = "INSERT INTO expense (name, budget, start_date, user_id, amount) VALUES (?, ?, now(), ?, ?)";
            try (Connection connection = DatabaseConnection.getConnection()) {
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, category);
                pstmt.setString(2, budget);
                pstmt.setString(3, user_id);
                pstmt.setString(4, amount);
                changes = pstmt.executeUpdate();
                if (!connection.isClosed()) {
                    connection.close();
                }
                if (changes > 0) {
                    return "1";
                } else {
                    return "0";
                }
            } catch (SQLException ex) {
                System.out.println("Sql Exception in Expense insert: " + ex.getMessage());
                return "0";
            }
        } else {
            String query = "update expense set budget = ?, start_date = now(), amount = amount + ? where id = ?";
            try (Connection connection = DatabaseConnection.getConnection()) {
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, budget);
                pstmt.setString(2, amount);
                pstmt.setInt(3, count);
                changes = pstmt.executeUpdate();
                if (!connection.isClosed()) {
                    connection.close();
                }
                if (changes > 0) {
                    return "1";
                } else {
                    return "0";
                }
            } catch (SQLException ex) {
                System.out.println("Sql Exception in expense update: " + ex.getMessage());
                return "0";
            }

        }
    }

    private int checkUserExpense(String category, String user_id) {
        try (Connection connect = DatabaseConnection.getConnection()) {
            String query = "select * from expense where name = ? and user_id = ? and start_date >= DATE_FORMAT(NOW() ,'%Y-%m-01') AND start_date <= LAST_DAY(now())";
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.setString(1, category);
            pstmt.setString(2, user_id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return -1;
            }

        } catch (Exception ex) {
            System.out.println("Exception in user expense check : " + ex.getMessage());
            return -1;
        }
    }

    public String deleteExpense(String id) {
        int count = 0;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE from expense where id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, id);
            count = pstmt.executeUpdate();
            if (!connection.isClosed()) {
                connection.close();
            }
            if (count > 0) {
                return "success";
            } else {

                return "fail";
            }

        } catch (Exception ex) {

            System.out.println("Exception in delete expense details: " + ex.getMessage());
            return "fail";
        }
    }

}
