package com.mynhasach.pojo;

public class Regulation {
    private int id;
    private int importMin;
    private int inventoryMaxWhenImport;
    private int inventoryMinWhenSell;
    private int debtMax;
    private int active;
    private int userRole;

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
     * @return the importMin
     */
    public int getImportMin() {
        return importMin;
    }

    /**
     * @param importMin the importMin to set
     */
    public void setImportMin(int importMin) {
        this.importMin = importMin;
    }

    /**
     * @return the inventoryMaxWhenImport
     */
    public int getInventoryMaxWhenImport() {
        return inventoryMaxWhenImport;
    }

    /**
     * @param inventoryMaxWhenImport the inventoryMaxWhenImport to set
     */
    public void setInventoryMaxWhenImport(int inventoryMaxWhenImport) {
        this.inventoryMaxWhenImport = inventoryMaxWhenImport;
    }

    /**
     * @return the inventoryMinWhenSell
     */
    public int getInventoryMinWhenSell() {
        return inventoryMinWhenSell;
    }

    /**
     * @param inventoryMinWhenSell the inventoryMinWhenSell to set
     */
    public void setInventoryMinWhenSell(int inventoryMinWhenSell) {
        this.inventoryMinWhenSell = inventoryMinWhenSell;
    }

    /**
     * @return the debtMax
     */
    public int getDebtMax() {
        return debtMax;
    }

    /**
     * @param debtMax the debtMax to set
     */
    public void setDebtMax(int debtMax) {
        this.debtMax = debtMax;
    }

    /**
     * @return the active
     */
    public int getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(int active) {
        this.active = active;
    }

    /**
     * @return the userRole
     */
    public int getUserRole() {
        return userRole;
    }

    /**
     * @param userRole the userRole to set
     */
    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }
    
}
