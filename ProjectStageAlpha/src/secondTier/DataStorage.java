package secondTier;

import java.util.ArrayList;
import java.sql.*;
import java.util.Collections;

import javax.swing.JOptionPane;

import thirdTier.DBAccess;
import exceptions.*;

// TODO: Auto-generated Javadoc
/**
 * The Class DataStorage.
 */
public class DataStorage {
	 	/** The d store. */
	 	// attribute22
		ArrayList<Electronics> dStore;
	 	/**
	 	 * Instantiates a new data storage.
	 	 */
	 	// constructor
	 	public DataStorage () {
	 		dStore = new ArrayList<Electronics>();
	 	}
	 	
	 	/**
	 	 * Adds all the database from the database into an arraylist for further use.
	 	 *
	 	 * @return the all
	 	 * @throws SQLException the SQL exception
	 	 */
	 	// methods
	 	public void getAll() throws SQLException {
			dStore.addAll (DBAccess.getAll());
		}
	 	
	 	/**
	 	 * Adds a new electronic object (Smartphone or Television) to the database<br />
	 	 * and the ArrayList, if the key is not found in the ArrayList,<br />
	 	 * and the database doesn't throw any Exceptions.<br />
	 	 *
	 	 * @param e the e
	 	 * @throws DuplicateException the duplicate exception
	 	 * @throws SQLException the SQL exception
	 	 */
	 	public void add (Electronics e) throws DuplicateException, SQLException {
	 		int index = find (e);
			if (index < 0) {
				DBAccess gdba = new DBAccess();
				gdba.addNew (e); // add the person data to the database, and then ...
					// ... add the person data to the ArrayList
					// NOTE: won't get here if either Exception is thrown
				dStore.add (e);
			} else {
				throw new DuplicateException ();
			}
	 	}
	 	
	 	/**
	 	 * The delete() method just accesses the data to see if its there<br />
	 	 * and return it if it is.<br />
	 	 * The deleteConfirm() method does the actual deletion if user goes ahead<br />
	 	 * with the delete.<br />
	 	 *
	 	 * @param e the e
	 	 * @return the person object
	 	 * @throws NotFoundException the not found exception
	 	 */
		public Electronics delete (Electronics e) throws NotFoundException {
			int index = find (e);
			if (index < 0) {
				throw new NotFoundException ();
			} else {
				e = dStore.get (index);
			}
			return e;
		}
		
		/**
		 * Delete confirmed.
		 *
		 * @param index the index
		 * @throws NotFoundException the not found exception
		 * @throws SQLException the SQL exception
		 */
		public void deleteConfirmed (int index) throws NotFoundException, SQLException {
			DBAccess gdba = new DBAccess();
			gdba.delete (dStore.get (index)); // remove Person from the database ...
			dStore.remove (index); // ... and then from ArrayList if no Exceptions are thrown
		}
	 	
	 	
	 	/**
	 	 * Searches the ArrayList using the binarySearch() method of the Collections class.
	 	 *
	 	 * @return the index of the item if found and a value <0 if not found
	 	 */ 
		
		public void sort() {
			Collections.sort (dStore);
		}
		
	 	/**
	 	 * A binary search used to find all the data that is stored in the electronics and is then used in Electronics get.
	 	 *
	 	 * @param e the e
	 	 * @return the int
	 	 */
	 	public int find(Electronics e) {
	 		int index = -1;

			index = Collections.binarySearch(dStore, e);
			JOptionPane.showMessageDialog(null, index);
			return index;
		}

		/**
	 	 * sets a varialbe "size" to the amount of items in dStore.
	 	 * @return the int
	 	 */
	 	public int size () {
	 		return dStore.size();
	 	}
	 	
	 	/**
	 	 * Getting the location of electronics.
	 	 * @param index the index
	 	 * @return the electronics
	 	 */
	 	public Electronics get (int index) {
	 		return dStore.get (index);
	 	}
	 	
	 	/**
	 	 * Gets the array list.
	 	 *
	 	 * @return the array list
	 	 */
	 	public ArrayList<Electronics> getArrayList() {
	 		return dStore;
	 	}

	
	 } // end class DataStorage


