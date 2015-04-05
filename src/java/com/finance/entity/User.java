/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finance.entity;

import com.finance.database.DatabaseConnection;
import java.awt.BorderLayout;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.stream.JsonParser;

/**
 *
 * @author c0644881
 */
@Stateful
public class User {

    private String name;
    private String password;
    private String security_question;
    private String security_answer;
    private int id;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurity_question() {
        return security_question;
    }

    public void setSecurity_question(String security_question) {
        this.security_question = security_question;
    }

    public String getSecurity_answer() {
        return security_answer;
    }

    public void setSecurity_answer(String security_answer) {
        this.security_answer = security_answer;
    }

    public String checkUser(String id, String password) {
        String result = "";
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE id = ? and password = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                JsonObjectBuilder obj = Json.createObjectBuilder()
                        .add("id", rs.getInt("id"))
                        .add("name", rs.getString("name"));
                result = obj.build().toString();
            }

        } catch (SQLException ex) {
            System.out.println("Exception raised in checkUser: " + ex.getMessage());

        }
        return result;
    }

    public String addUser(String userDetails) {
        JsonParser parser = Json.createParser(new StringReader(userDetails));
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
        String user = map.get("name");
        String password = map.get("password");
        String security_question = map.get("question");
        String security_answer = map.get("answer");
        String query = "INSERT INTO users (name, password, security_question, security_answer) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user);
            pstmt.setString(2, password);
            pstmt.setString(3, security_question);
            pstmt.setString(4, security_answer);
            changes = pstmt.executeUpdate();
            ResultSet userRS = pstmt.getGeneratedKeys();
            userRS.next();
            int newId = userRS.getInt(1);
            if (changes > 0) {

                return Integer.toString(newId);
            } else {
                return "0";
            }

        } catch (SQLException ex) {
            System.out.println("Sql Exception: " + ex.getMessage());
            return "0";
        }

    }

}
