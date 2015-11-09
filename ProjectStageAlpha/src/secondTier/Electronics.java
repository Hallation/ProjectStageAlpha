package secondTier;


// TODO: Auto-generated Javadoc
/**
 * The Class Electronics.
 */
public abstract class Electronics implements Comparable<Electronics> {
	

	/** The price. */
	protected double price;
	
	/** The stock. */
	protected int stock;
	
	/** The Model number. */
	protected String BrandName, ModelNumber;
	
	/**
	 * Instantiates a new electronics.
	 */
	public Electronics () {
		ModelNumber = null;
		BrandName = null;
		price = 0;
		stock = 0;
		
		
	}
	
	
	/**
	 * Instantiates a new electronics.
	 *
	 * @param bName the b name
	 * @param mNumber the m number
	 * @param pri the pri
	 * @param stck the stck
	 */
	public Electronics ( String mNumber, String bName, int pri, int stck){
		
		ModelNumber = mNumber;
		BrandName = bName;
		price = pri;
		stock = stck;
		
		
	}
	
	/**
	 * Sets the brand name.
	 *
	 * @param bName the new brand name
	 */
	public void setBrandName (String bName){
		BrandName = bName;
	}
	
	/**
	 * Sets the model number.
	 *
	 * @param mNumber the new model number
	 */
	public void setModelNumber (String mNumber){
		ModelNumber = mNumber;
	}

	/**
	 * Sets the price.
	 *
	 * @param price2 the new price
	 */
	public void setprice (double price2){
		price = price2;
	}
	
	/**
	 * Sets the stock.
	 *
	 * @param stck the new stock
	 */
	public void setstock (int stck){
		stock = stck;
	}
	
	/**
	 * Gets the b name.
	 *
	 * @return the b name
	 */
	public String getbName (){
		return BrandName;
	}
	
	/**
	 * Gets the m number.
	 *
	 * @return the m number
	 */
	public String getmNumber () {
		return ModelNumber;
	}
	
	/**
	 * Gets the stck.
	 *
	 * @return the stck
	 */
	public int getstck () {
		return stock;
	}
	
	/**
	 * Gets the pri.
	 *
	 * @return the pri
	 */
	public double getpri () {
		return price;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString () {
		return "\nBrand Name: " + BrandName + "\nModel Number: " +  ModelNumber +  "\nPrice: $" + price + "\nStock: " + stock;
	}
		
}


