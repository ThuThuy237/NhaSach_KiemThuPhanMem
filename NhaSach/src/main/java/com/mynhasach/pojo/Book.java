/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.pojo;

import java.math.BigDecimal;

/**
 *
 * @author thuy
 */
public class Book {
    private int id;
    private String name;
    private String author;
    private int inventory;
    private BigDecimal importPrice;

    @Override
    public String toString() {
        return name;
    }

    private BigDecimal price;
    private String image;
    private int categoryId;

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book() {
    }

    public Book(String name, String author, int inventory, BigDecimal importPrice, BigDecimal price, String image, int categoryId) {
        this.name = name;
        this.author = author;
        this.inventory = inventory;
        this.importPrice = importPrice;
        this.price = price;
        this.image = image;
        this.categoryId = categoryId;
    }

    public Book(int id, String name, String author, int inventory, BigDecimal importPrice, BigDecimal price, String image, int categoryId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.inventory = inventory;
        this.importPrice = importPrice;
        this.price = price;
        this.image = image;
        this.categoryId = categoryId;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the inventory
     */
    public int getInventory() {
        return inventory;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    /**
     * @return the importPrice
     */
    public BigDecimal getImportPrice() {
        return importPrice;
    }

    /**
     * @param importPrice the importPrice to set
     */
    public void setImportPrice(BigDecimal importPrice) {
        this.importPrice = importPrice;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
}
