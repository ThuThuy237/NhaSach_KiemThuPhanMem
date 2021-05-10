package com.mynhasach.pojo;

import java.math.BigDecimal;

public class OrderDetail {
    public OrderDetail(int id, int bookId, int orderId, int quantity, BigDecimal price) {
        this.id = id;
        this.bookId = bookId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetail(int bookId, int quantity, BigDecimal price) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetail(int bookId, int orderId, int quantity, BigDecimal price) {
        this.bookId = bookId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetail() {
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
     * @return the bookId
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * @param bookId the bookId to set
     */
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
    private int id;
    private int bookId;
    private int orderId;
    private int quantity;
    private BigDecimal price;
}
