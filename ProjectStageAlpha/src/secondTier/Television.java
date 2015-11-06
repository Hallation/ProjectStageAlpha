package secondTier;

// TODO: Auto-generated Javadoc
/**
 * The Class Television.
 */
public class Television extends Electronics{

	/** The Screen type. */
	private String ScreenSize, Resolution, ScreenType;
	
	/**
	 * Instantiates a new television.
	 * @param string 
	 */
	public Television (String tN) {
		super ();
		ScreenSize = null;
		Resolution = null;
		ScreenType = null;
	}
	public Television () {
		super ();
		ScreenSize = null;
		Resolution = null;
		ScreenType = null;
	}
	
	
	/**
	 * Instantiates a new television.
	 *
	 * @param bN the b n
	 * @param mNu the m nu
	 * @param pr the pr
	 * @param st the st
	 * @param sS the s s
	 * @param Res the res
	 * @param sType the s type
	 */
	public Television (String bN, String mNu, int pr, int st, String sS, String Res, String sType){
		super (bN, mNu, pr, st);
		ScreenSize = sS;
		Resolution = Res;
		ScreenType = sType;
	}
	

	/**
	 * Gets the screen size.
	 *
	 * @return the screen size
	 */
	public String getScreenSize(){
		return ScreenSize;
	}
	
	/**
	 * Gets the resolution.
	 *
	 * @return the resolution
	 */
	public String getResolution(){
		return Resolution;
	}
	
	/**
	 * Gets the screen type.
	 *
	 * @return the screen type
	 */
	public String getScreenType(){
		return ScreenType;
	}
	
	/**
	 * Sets the screen size.
	 *
	 * @param sS the new screen size
	 */
	public void setScreenSize(String sS){
		ScreenSize = sS;
	}
	
	/**
	 * Sets the resolution.
	 *
	 * @param Res the new resolution
	 */
	public void setResolution(String Res){
		Resolution = Res;
	}
	
	/**
	 * Sets the screen type.
	 *
	 * @param sType the new screen type
	 */
	public void setScreenType(String sType){
		ScreenType = sType;
	}

	/* (non-Javadoc)
	 * @see secondTier.Electronics#toString()
	 */
	/**
	 * If Person object compares as equal in studentNumber or employeeNumber,
	 * then the Person objects are regarded as the same object.
	 *
	 * @param e the e
	 * @return true or false
	 */
		public boolean equals (Electronics e) {
			boolean same = false;
			if (this.compareTo (e) == 0) {
				same = true;
			}
			return same;
		}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
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



	
