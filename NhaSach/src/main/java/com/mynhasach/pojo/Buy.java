package com.mynhasach.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Buy {
    private int id;
    private Date date;
    private BigDecimal total;
    private int supId;
    private int empId;

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
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the supId
     */
    public int getSupId() {
        return supId;
    }

    /**
     * @param supId the supId to set
     */
    public void setSupId(int supId) {
        this.supId = supId;
    }

    /**
     * @return the empId
     */
    public int getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
}
