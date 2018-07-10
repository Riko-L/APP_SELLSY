/**
 * 
 */
package com.sellsy.apientities;

/**
 * @author yves
 *
 */
public class Pagination {
    
    private int nbperpage=5;
    private int pagenum=1;
    
    
    public Pagination( int pagenum ,int nbperpage) {
        this.nbperpage = nbperpage;
        this.pagenum = pagenum;
    }


    public int getNbperpage() {
        return nbperpage;
    }


    public void setNbperpage(int nbperpage) {
        this.nbperpage = nbperpage;
    }


    public int getPagenum() {
        return pagenum;
    }


    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }


//    @Override
//    public String toString() {
//        return "Pagination [nbperpage=" + nbperpage + ", pagenum=" + pagenum + "]";
//   }
    
    

}
