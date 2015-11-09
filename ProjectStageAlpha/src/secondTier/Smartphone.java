package secondTier;



// TODO: Auto-generated Javadoc
/**
 * The Class Smartphone.
 */
public class Smartphone extends Electronics{
	

	private String modelName, OperatingSystem, YearOfRelease;

	public Smartphone ( String mNu, String bN, int pr, int st, String mNa, String OS, String YoR){
		super ( mNu, bN , pr, st);
		modelName = mNa;
		OperatingSystem = OS;
		YearOfRelease = YoR;
	}
	
	public Smartphone (String mNu){
	super ();
	ModelNumber = mNu;
	modelName = null;
	OperatingSystem = null;
	YearOfRelease = null;
	}
	


	public Smartphone (){
		super ();
		modelName = null;
		OperatingSystem = null;
		YearOfRelease = null;
	}
		

	/**
	 * Instantiates a new smartphone.
	 *
	 * @param bN the b n
	 * @param mNu the m nu
	 * @param pr the pr
	 * @param st the st
	 * @param mNa the m na
	 * @param OS the os
	 * @param YoR the yo r
	 */

	/**
	 * Gets the model name.
	 *
	 * @return the model name
	 */
	public String getModelName(){
		return modelName;
	}
	
	/**
	 * Gets the operating system.
	 *
	 * @return the operating system
	 */
	public String getOperatingSystem(){
		return OperatingSystem;
	}
	
	/**
	 * Gets the year of release.
	 *
	 * @return the year of release
	 */
	public String getYearOfRelease(){
		return YearOfRelease;
	}
	
	/**
	 * Sets the model name.
	 *
	 * @param mNa the new model name
	 */
	public void setModelName (String mNa){
		modelName = mNa;
	}
	
	/**
	 * Sets the operating system.
	 *
	 * @param OS the new operating system
	 */
	public void setOperatingSystem (String OS){
		OperatingSystem = OS;
	}
	
	/**
	 * Sets the year of release.
	 *
	 * @param YoR the new year of release
	 */
	public void setYearOfRelease (String YoR){
		YearOfRelease = YoR;
	}
	
	public boolean equals (Electronics e) {
		boolean same = false;
		if (this.compareTo (e) == 0) {
			same = true;
		}
		return same;
	}


	@Override
	public int compareTo (Electronics e) throws ClassCastException {
	int compareVal;
	if (e instanceof Smartphone) {
		compareVal = (ModelNumber.compareTo (((Smartphone) e).getmNumber()));
	} else if (e instanceof Television) {
		compareVal = (ModelNumber.compareTo (((Television) e).getmNumber()));
	} else {
		throw new ClassCastException ();
	}
	return compareVal;
	}
	


}

	
	