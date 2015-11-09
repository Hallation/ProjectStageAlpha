package thirdTier;
import java.util.ArrayList;	// for ArrayList of Objects

import javax.swing.JOptionPane;

import java.sql.*;			// for SQL
import exceptions.*;
import secondTier.*;

// TODO: Auto-generated Javadoc
/**
 * The Class DBAccess.
 * Does everything to the database, from the initial loading and selecting of records to delete and update.
 * It is essentially the middle man from the secondTier to the database.
 */
public class DBAccess {

	/** The an electronic. */
	private static Electronics anElectronic;

	/** The url. */
	private static String url;
	
	/** The a connection. */
	private static Connection aConnection;
	
	/** The a statement. */
	private static Statement aStatement;

		// Implement the three static methods ************
		// initialise & terminate - called from PersonWorker
		// getAll - called from DataStorage
	/**
		 * A static method to establish the database connection.
		 *
		 * @throws ClassNotFoundException the class not found exception
		 * @throws SQLException the SQL exception
		 */
	public static void initialize() throws ClassNotFoundException, SQLException {
			// The Data Source Name (DSN) is "electronics.accdb"
		url = "jdbc:odbc:MS Access Database;DBQ=.\\electronics.accdb";
			// load the jdbc - odbc bridge driver for Windows
		Class.forName ("net.ucanaccess.jdbc.UcanaccessDriver");
			// create connection instance
		aConnection = DriverManager.getConnection ("jdbc:ucanaccess://electronics.accdb");
			// create statement object instance for this connection
		aStatement = aConnection.createStatement();
	}
	
	/**
	 * A static method to close the database connection.
	 *
	 * @throws SQLException the SQL exception
	 */
	public static void terminate() throws SQLException {
			// close db connection
		aStatement.close();
		aConnection.close();
	}
	
	/**
	 * Get all the objects (data) from the database.<br /><br />
	 * 
	 * This method is used only once - when the program is started - to populate the DataStorage ArrayList.<br />
	 *
	 * @return an ArrayList of Person objects.
	 * @throws SQLException the SQL exception
	 */
	public static ArrayList<Electronics> getAll () throws SQLException {
		ArrayList<Electronics> electronics = new ArrayList<Electronics>();

			// ******* define the SQL query statement to get all Smartphones
		String sqlQuery = "SELECT E.electronicID, E.brandName, E.Price, E.Stock, S.modelType, S.YearOfRelease, S.OperatingSystem FROM ELECTRONIC E INNER JOIN SMARTPHONE S ON E.electronicID = S.electronicID";
			// execute the SQL query statement
		ResultSet rs = aStatement.executeQuery (sqlQuery);
		boolean moreData = rs.next();
				// next() sets cursor & returns true if there is data
		while (moreData) { // get the data records, if any
				// extract the data
			String ModelNumber = rs.getString(1);
			String BrandName = rs.getString (2);
			String modelName = rs.getString(5);
			String YearOfRelease = rs.getString(6);
			String OperatingSystem = rs.getString(7);
			int price = rs.getShort(3);
			int stock = rs.getShort(4);
			
				// create Employee instance & add it to the ArrayList
			anElectronic = new Smartphone (ModelNumber, BrandName, price, stock, modelName, YearOfRelease, OperatingSystem);
			electronics.add (anElectronic);
			moreData = rs.next();
		}
			// ******* define the SQL query statement to get all Televisions
		sqlQuery = "SELECT E.electronicID, BrandName, Price, Stock, ScreenType, ScreenSize, Resolution FROM ELECTRONIC E INNER JOIN TELEVISION T ON E.electronicID = T.electronicID ";
			// execute the SQL query statement
		rs = aStatement.executeQuery (sqlQuery);
		moreData = rs.next();
				// next() sets cursor & returns true if there is data
		while (moreData) {
				// extract the data
			String ModelNumber = rs.getString(1);
			String BrandName = rs.getString (2);
			double price = rs.getDouble(3);
			int stock = rs.getShort(4);
				//Variables for the television
			String ScreenType = rs.getString(5);
			String ScreenSize = rs.getString(6);
			String Resolution = rs.getString(7);
			// create a television and add it to electronics array list.
			anElectronic = new Television (ModelNumber, BrandName, (int) price, stock, ScreenType, ScreenSize, Resolution);
			electronics.add (anElectronic);
			moreData = rs.next();
		}
		rs.close();

		return electronics;
	}

	// Implement the four instance methods *************
	// addNew, delete, update - called from each specific PD class
	// find - used locally by addNew(), delete(), and update().
	/**
	 * An instance method to find a record in the database.<br /><br />
	 * 
	 * This is a private method and can only be used locally within objects instantiated from this class.<br />
	 * Used by addNew(), delete(), and update().
	 *
	 * @param objectType the object type
	 * @param key the key
	 * @return a Person object
	 * @throws NotFoundException the not found exception
	 * @throws SQLException the SQL exception
	 */
	private Electronics find (String objectType, String key) throws NotFoundException, SQLException {

		anElectronic = null;

		if (objectType.equalsIgnoreCase ("Smartphone")) {
				// define the SQL query statement using the phone number key
			String sqlQuery = "SELECT E.electronicID, E.brandName, E.Price, E.Stock, S.modelType, S.YearOfRelease, S.OperatingSystem FROM ELECTRONIC E INNER JOIN SMARTPHONE S ON E.electronicID = S.electronicID WHERE electronicID = '" + key +"'";

				// execute the SQL query statement
			ResultSet rs = aStatement.executeQuery (sqlQuery);

				// next method sets cursor & returns true if there is data
			boolean gotIt = rs.next();
			if (gotIt) {
				String ModelNumber = rs.getString(1);
				String BrandName = rs.getString (2);
				double price = rs.getDouble(3);
				int stock = rs.getShort(4);
				String modelName = rs.getString(5);
				String YearOfRelease = rs.getString(6);
				String OperatingSystem = rs.getString(7);
					// create Smartphone instance & add it to the ArrayList
				anElectronic = new Smartphone (ModelNumber, BrandName, (int) price, stock, modelName, YearOfRelease, OperatingSystem);

				rs.close();
			} else {
					// nothing was retrieved
				rs.close();
				throw (new NotFoundException ("not found "));
			}
		} else if (objectType.equalsIgnoreCase ("Television")) {
				// code for processing Student goes here
		}

		return anElectronic;
	}  // end find()
	
	/**
	 * Add a new Smartphone record to database.
	 *
	 * @param anElectronic the an electronic
	 * @throws DuplicateException the duplicate exception
	 * @throws SQLException the SQL exception
	 */
	public void addNew (Electronics anElectronic) throws DuplicateException, SQLException {
		if (anElectronic instanceof Electronics) {
			Smartphone aSmartphone= (Smartphone) anElectronic;
				// retrieve the customer attribute values
			
			String BrandName = aSmartphone.getbName();
			String price = Double.toString (aSmartphone.getpri());
			String stock = Integer.toString(aSmartphone.getstck());
			String modelName = aSmartphone.getModelName();
			String YearOfRelease = aSmartphone.getYearOfRelease();
			String OperatingSystem = aSmartphone.getOperatingSystem();
			String ModelNumber = aSmartphone.getmNumber();
			

				// create the SQL insert statement using attribute values
			String sqlInsert = 
					"INSERT INTO ELECTRONIC (electronicID, brandName, Price, Stock) VALUES ('" +
					ModelNumber	+ "', '" +
					BrandName + "', '" +
					price + "', '" +
					stock + "') "; 
					
					//smartphone insert
			String sqlInsert2 = "INSERT INTO SMARTPHONE (electronicID, modelType, YearOfRelease, OperatingSystem) VALUES ('" +
					ModelNumber + "', '" + 
					modelName + "', '"+
					YearOfRelease + "', '" +
					OperatingSystem + "')";
			JOptionPane.showMessageDialog(null, sqlInsert);
			JOptionPane.showMessageDialog(null, sqlInsert2);
			
				aStatement.executeUpdate (sqlInsert);
				aStatement.executeUpdate (sqlInsert2);
				
			
		} else if (anElectronic instanceof Television) {
				// code for processing Student goes here
		} //end if
	} // end addNew()
	
	/**
	 * Delete a record from the database.
	 *
	 * @param anElectronic the an electronic
	 * @throws NotFoundException the not found exception
	 * @throws SQLException the SQL exception
	 */
	public void delete (Electronics anElectronic) throws NotFoundException, SQLException {
		if (anElectronic instanceof Smartphone) {
			Smartphone aSmartphone = (Smartphone) anElectronic;
				// retrieve the employee number (key)
			String modelNum = aSmartphone.getmNumber();
				// create the SQL delete statement
			String sqlDelete = 
					"DELETE FROM ELECTRONIC WHERE electronicID = '" + modelNum + "'";
				// see if this record already exists in the database
				// NotFoundException & SQLException are thrown by find method
			JOptionPane.showMessageDialog(null, sqlDelete);
			find ("Electronic", modelNum);
				// if found, execute the SQL update statement
			aStatement.executeUpdate (sqlDelete);
		} else if (anElectronic instanceof Television) {
				// code for processing Supplier goes here
		} // end if
	} // end delete()
	
	/**
	 * Update a record in the database.
	 *
	 * @param anElectronic the an electronic
	 * @throws NotFoundException the not found exception
	 * @throws SQLException the SQL exception
	 */
	public void update (Electronics anElectronic) throws NotFoundException, SQLException {
		if (anElectronic instanceof Smartphone) {
			Smartphone aSmartphone = (Smartphone) anElectronic;
				// retrieve the customer attribute values
			
	
			String BrandName = aSmartphone.getbName();
			String price = Double.toString (aSmartphone.getpri());
			String stock = Integer.toString(aSmartphone.getstck());
			String modelName = aSmartphone.getModelName();
			String YearOfRelease = aSmartphone.getYearOfRelease();
			String OperatingSystem = aSmartphone.getOperatingSystem();
			String ModelNumber = aSmartphone.getmNumber();
			

			// define the SQL query statement using the electronic ID (modelNumber) key

			String sqlUpdate = "UPDATE SMARTPHONE " +
			"SET electronicID = '" + ModelNumber + "', "+
					"elecBrand = '" + BrandName + "', " +
					"Price = '" + price + "', " +
					"Stock = '" + stock +
					//Updating Smartphone related table
					"modelType = '" + modelName + "', "+
					"YearOfRelease = '" + YearOfRelease + "', " +
					"OperatingSystem = '" + OperatingSystem + "', " +
					"WHERE ELECTRONIC.electronicID = '" + ModelNumber + "' ";

			
				// see if this electronic already exists in the database
				// NotFoundException & SQLException are thrown by find method
			find ("Smartphone", ModelNumber);
					// if found, execute the SQL update statement
			aStatement.executeUpdate (sqlUpdate);
		} else if (anElectronic instanceof Television) {
				// code for processing Supplier goes here
		} // end if
	} // end update()

} // end class
