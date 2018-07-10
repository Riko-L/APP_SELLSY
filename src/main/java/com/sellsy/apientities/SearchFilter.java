/**
 * 
 */
package com.sellsy.apientities;

/**
 * @author yves
 *
 */
public class SearchFilter {
    
    private String contains;
    private String type;

    /**
     * 
     */
    public SearchFilter() {
       this.contains="";
       this.type="";
       
    }

    public SearchFilter(String type) {
        this.type= type;
        this.contains = "";
    }

    public SearchFilter(String contains, String type) {
        this.contains = contains;
        this.type = type;
    }

    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
}
