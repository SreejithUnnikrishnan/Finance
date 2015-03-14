/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.finance.entity;

/**
 *
 * @author c0644881
 */
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
    
    
}
