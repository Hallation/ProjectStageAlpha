
package secondTier;

import java.sql.*;
import thirdTier.*;


// TODO: Auto-generated Javadoc
/**
 * The Class ElectronicWorker.
 */
public class ElectronicWorker {

	/** The Constant serialVersionUID. */
	static final long serialVersionUID = 100L;

	/**
	 * Terminate.
	 *
	 * @throws SQLException the SQL exception
	 */
	public static void terminate() throws SQLException {
		DBAccess.terminate();
	}
	
	/**
	 * Initialize.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 */
	public static void initialize() throws ClassNotFoundException, SQLException {
		DBAccess.initialize();
	}
	 }


