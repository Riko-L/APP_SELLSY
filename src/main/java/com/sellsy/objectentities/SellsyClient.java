/**
 * 
 */
package com.sellsy.objectentities;

import com.sellsy.coreConnector.SellsyApiResponse;

/**
 * @author eric
 *
 */
public class SellsyClient extends SellsyObject {

	private String name;
	private String mainContactName;

	public SellsyClient() {
	       
	    }

	public SellsyClient(SellsyApiResponse apiResponse) {
		super(apiResponse);
		  this.name = this.getAttributeValue("name");
		  this.mainContactName = this.getAttributeValue("mainContactName");
	    }

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mainContactName
	 */
	public String getMainContactName() {
		return mainContactName;
	}

	/**
	 * @param mainContactName
	 *            the mainContactName to set
	 */
	public void setMainContactName(String mainContactName) {
		this.mainContactName = mainContactName;
	}

	
	@Override
    public String toString() {
        return "SellsyClient [name=" + name + ", mainContactName=" + mainContactName + ", Id="
                + getId() + ", OwnerId=" + getOwnerid() +"]";
    }
	
}
