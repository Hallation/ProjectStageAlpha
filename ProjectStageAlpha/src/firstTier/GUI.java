package firstTier;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import exceptions.DuplicateException;
import exceptions.NotFoundException;
import secondTier.*;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

import exceptions.*;
// TODO: Auto-generated Javadoc
/**
 * The Class GUI.
 */
public class GUI {

	/** The frame. */
	private JFrame frame;
	
	/** The finished. */
	boolean finished = false;
	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize.
	 * Within initialize. Everything happens, from creating the GUI to the case switch
	 * for selection of what to do. However the delete and update do not work. As for why It is unknown.
	 */
	private void initialize() {
		//create Electronics arraylist
		final DataStorage Electronics = new DataStorage();
		 //assuming file names
		try {
			ElectronicWorker.initialize();
			Electronics.getAll(); // called only once - to populate the ArrayList from the data in the DB
			JOptionPane.showMessageDialog (null, "\n    **    Database successfully opened    **\n", "Success",  JOptionPane.PLAIN_MESSAGE);
		}
		catch (SQLException se) {
			JOptionPane.showMessageDialog (null, "\n**** ERROR: Problem opening database ****\n" + se.getMessage(), "ERROR",  JOptionPane.ERROR_MESSAGE);
			finished = true; // prevents while loop from processing - ERROR stop
		}
		catch (ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog (null, "\n** ERROR: Cannot find database **\n", "ERROR",  JOptionPane.ERROR_MESSAGE);
			finished = true; // prevents while loop from processing - ERROR stop
		}
		if (finished) {
			JOptionPane.showMessageDialog (null, "\n*** Fatal ERROR - Program Ended ***\n" +
					"\n*** Please contact you computer services centre ***\n", "ERROR",  JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				Electronics.sort(); // sort the ArrayList to allow use of binarySearch() to find data
			}
			catch (ClassCastException cce) {
				JOptionPane.showMessageDialog (null, "Data not sorted properly", "Sorting Error",  JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		
		//creating the GUI and adding its elements
		
		
		frame = new JFrame();
		if (finished){
			System.exit(0);
		}
		frame.setBounds(100, 100, 295, 175);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//combobox used for selections
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(40, 36, 183, 27);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("Add new Smartphone");
		comboBox.addItem("Add new Television");
		comboBox.addItem("Find an individual Smartphone");
		comboBox.addItem("Find an individual Television");
		comboBox.addItem("Delete a Smartphone");
		comboBox.addItem("Delete a Television");
		comboBox.addItem("Update a Smartphone");
		comboBox.addItem("Update a Television");
		comboBox.addItem("Display all Details");
	
		
		
	
		
		
		//event listening for close to be pressed for program to close
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		closeButton.setBounds(137, 95, 117, 29);
		frame.getContentPane().add(closeButton);
		//labels not named since not actually needed.
		JLabel headerLabel = new JLabel("Please make a selection and press select");
		headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		headerLabel.setBounds(10, 11, 200, 14);
		frame.getContentPane().add(headerLabel);
		


		
		//select button
		JButton selectButton = new JButton("Select");
		//event listener waiting for the select button to be selected
		selectButton.addActionListener(new ActionListener() {
			
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				
				int selection = comboBox.getSelectedIndex();
				switch (selection) {
				case 0: // add a new Smartphone
							try {
								Electronics.add (addSmartphone ());
								Electronics.sort();	// sort the data again to make sure it's in order for a binarySearch
							}
							catch (DuplicateException de) {
								JOptionPane.showMessageDialog (null, "ERROR: Cannot add Smartphone - key already exists in database!", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
							catch (SQLException se) {
								JOptionPane.showMessageDialog (null, "ERROR: Problem with database - cannot add record!", "ERROR", JOptionPane.ERROR_MESSAGE);
								JOptionPane.showMessageDialog(null, se);
							}
							break;
				case 1: // add a new Television
							try {
								Electronics.add (addTelevision ());
								Electronics.sort(); // sort the data again to make sure it's in order for a binarySearch
							}
							catch (DuplicateException de) {
								JOptionPane.showMessageDialog (null, "ERROR: Cannot add Television - key already exists in database!", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
							catch (SQLException se) {
								JOptionPane.showMessageDialog (null, "ERROR: Problem with database - cannot add record!", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
							break;
				case 2: // find single Smartphone's details
							String ModelNumber = JOptionPane.showInputDialog (null, "What is the Smartphone's Number?", "Smartphone Enquiry", JOptionPane.INFORMATION_MESSAGE);
							int index = Electronics.find (new Smartphone (ModelNumber.trim())); // object created with ModelNumber only
							JOptionPane.showMessageDialog(null, ModelNumber);
							if (index < 0) { // not found
								JOptionPane.showMessageDialog (null, "Smartphone not found - please check Smartphone Number", "Not Found", JOptionPane.INFORMATION_MESSAGE);
							} else { // found
								JOptionPane.showMessageDialog (null, "The Smartphone's details are:\n\n" + Electronics.get(index) + "\n", "Person Details", JOptionPane.PLAIN_MESSAGE);
							}
							break;
				case 3: // find single Television's details
							ModelNumber = JOptionPane.showInputDialog (null, "What is the Television's model Number?", "Television Enquiry", JOptionPane.INFORMATION_MESSAGE);
							index = Electronics.find (new Television (ModelNumber.trim())); // object created with TelevisionNumber only
							if (index < 0) { // not found
								JOptionPane.showMessageDialog (null, "Television not found - please check Television Number", "Not Found", JOptionPane.INFORMATION_MESSAGE);
							} else { // found
								JOptionPane.showMessageDialog (null, "The Television's details are:\n\n" + Electronics.get(index) + "\n", "Person Details", JOptionPane.PLAIN_MESSAGE);
							}
							break;
				case 4: // delete an Smartphone
							ModelNumber = JOptionPane.showInputDialog (null, "What is the Smartphone's Number?", "Smartphone Enquiry", JOptionPane.INFORMATION_MESSAGE);
							JOptionPane.showMessageDialog(null, ModelNumber.trim());
							try {
								Smartphone sp = (Smartphone) Electronics.delete (new Smartphone (ModelNumber.trim()));
								int response = JOptionPane.showConfirmDialog (null, e + "\nAre you sure you wish to delete this record?\n", "Confirm Delete?", JOptionPane.YES_NO_OPTION);
								if (response == JOptionPane.YES_OPTION) {
									try {
										Electronics.deleteConfirmed (Electronics.find (sp));
										Electronics.sort(); // removing shouldn't alter the order, but sort it just in case!!
									}
									catch (NotFoundException nfe) {
										JOptionPane.showMessageDialog (null, "Smartphone not found - please check Smartphone Number", "Not Found", JOptionPane.INFORMATION_MESSAGE);
									}
									catch (SQLException se) {
										JOptionPane.showMessageDialog (null, "ERROR in databaser", "DATABASE ERROR", JOptionPane.ERROR_MESSAGE);
									}
								} else {
									// Smartphone record not deleted
									JOptionPane.showMessageDialog (null, "The Smartphone Record has NOT been Deleted", "Not Deleted", JOptionPane.INFORMATION_MESSAGE);
								}
							}
							catch (NotFoundException nfe){
								JOptionPane.showMessageDialog (null, "Smartphone not found - please check Smartphone Number", "Not Found", JOptionPane.INFORMATION_MESSAGE);
							}
							break;
				case 5: // delete a Television
							ModelNumber = JOptionPane.showInputDialog (null, "What is the Television's Number?", "Television Enquiry", JOptionPane.INFORMATION_MESSAGE);
							try {
								Television t = (Television) Electronics.delete (new Television (ModelNumber.trim()));
								int response = JOptionPane.showConfirmDialog (null, t + "\nAre you sure you wish to delete this record?\n", "Confirm Delete?", JOptionPane.YES_NO_OPTION);
								if (response == JOptionPane.YES_OPTION) {
									try {
										Electronics.deleteConfirmed (Electronics.find (t));
										Electronics.sort(); // removing shouldn't alter the order, but sort it just in case!!
									}
									catch (NotFoundException nfe) {
										JOptionPane.showMessageDialog (null, "Television not found - please check Television Number", "Not Found", JOptionPane.INFORMATION_MESSAGE);
									}
									catch (SQLException se) {
										JOptionPane.showMessageDialog (null, "ERROR in databaser", "DATABASE ERROR", JOptionPane.ERROR_MESSAGE);
									}
								} else {
									// Television record not deleted
									JOptionPane.showMessageDialog (null, "The Television Record has NOT been Deleted", "Not Deleted", JOptionPane.INFORMATION_MESSAGE);
								}
							}
							catch (NotFoundException nfe){
								JOptionPane.showMessageDialog (null, "Television not found - please check Television Number", "Not Found", JOptionPane.INFORMATION_MESSAGE);
							}
							break;
				case 6:	JOptionPane.showMessageDialog (null, "This option is not yet available");
							break;
				case 7:	JOptionPane.showMessageDialog (null, "This option is not yet available");
							break;
				case 8: // display details of all Smartphones & Televisions
							JOptionPane.showMessageDialog(null, Electronics.size());
							JOptionPane.showMessageDialog (null, "Displaying the electronic information ...", "Electronic List", JOptionPane.PLAIN_MESSAGE);
							for (int i = 0; i < Electronics.size(); i++) {
								// When displaying the contents of an object using print() or println()
								// Java automatically looks for a toString() method, and, if available
								// uses it to display the object contents.
								// Polymorphism ensures that the correct data is displayed for each
								// Smartphone or Television.
								JOptionPane.showMessageDialog (null, Electronics.get(i), "Electronic Details", JOptionPane.PLAIN_MESSAGE);
							}
							break;
				case 9: // Exit Program
							finished = true; // stops the while loop - normal
							try {
								ElectronicWorker.terminate(); // close the database
								JOptionPane.showMessageDialog (null, "   ** Database successfully closed **", "All OK", JOptionPane.INFORMATION_MESSAGE);
							}
							catch (SQLException se) {
								JOptionPane.showMessageDialog (null, "ERROR: Database not closed correctly", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
							JOptionPane.showMessageDialog (null, "    *** Program Ended ***\n        Have a nice day!");
							break;
				default: // invalid selection made
							JOptionPane.showMessageDialog (null, "\n** Invalid Selection **\n", "ERROR", JOptionPane.ERROR_MESSAGE);
			} // end switch
		}//end of action performed
	}//end of action listener
		);//god knows what this is but it works.

		selectButton.setBounds(25, 95, 102, 30);
		frame.getContentPane().add(selectButton);
	}
	

	
	
	
	/**
	 * Adds the smartphone with validation
	 * @return the smartphone
	 */
	public Smartphone addSmartphone () {
		Smartphone sp = new Smartphone ();
		
		
		String bName = (JOptionPane.showInputDialog(null, "What is the Smartphone brand name? ")).trim();
		while (bName.equals("")){
			JOptionPane.showMessageDialog(null, "Cannot accept blank entries! \nPlease try again.");
			bName = JOptionPane.showInputDialog("What is its model name ");
		}
		sp.setBrandName(bName);
		
		String mNumber = (JOptionPane.showInputDialog(null, "What is it's model number ")).trim();
		while (mNumber.equals("")){
			JOptionPane.showMessageDialog(null, "Cannot accept blank entries! \nPlease try again.");
			mNumber = JOptionPane.showInputDialog("What is its model name ");
		}
		sp.setModelNumber(mNumber);
		
	
		try 
		{
		String tprice = JOptionPane.showInputDialog(null, "How much does it cost? ").trim();
		while (tprice.equals("")){
			JOptionPane.showMessageDialog(null, "Cannot accept blank entries! \nPlease try again.");
			tprice = JOptionPane.showInputDialog("How much does it cost? ");
		}
		double price = Double.parseDouble(tprice);
		sp.setprice(price);
		}
		catch (NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "\n*** ERROR NUMBER NOT ENTERED*** \n \nPlease reenter \n");
			double price = Double.parseDouble((JOptionPane.showInputDialog(null, "How much does it cost? ")).trim());
			sp.setprice(price);
		}
		
		try 
		{				
		String tStock = JOptionPane.showInputDialog(null, "How much stock is left ").trim();
		while (tStock.equals("")){
			JOptionPane.showMessageDialog(null, "Cannot accept blank entries! \nPlease try again.");
			tStock = JOptionPane.showInputDialog("How much does it cost? ");
		}
		int stock = Integer.parseInt(tStock);
		sp.setstock(stock);
		}
		catch (NumberFormatException nfe) 
		{
			JOptionPane.showMessageDialog(null, "\n*** ERROR NUMBER NOT ENTERED*** \n \nPlease reenter \n");
			int stock = Integer.parseInt((JOptionPane.showInputDialog(null, "How much stock is left ")).trim());
			sp.setstock(stock);
		}
					
		String mNa = (JOptionPane.showInputDialog(null, "What is its model name ")).trim();
		while (mNa.equals("")){
			JOptionPane.showMessageDialog(null, "Cannot accept blank entries! \nPlease try again.");
			mNa = JOptionPane.showInputDialog("What is its model name ");
		}
		sp.setModelName(mNa);
		
		String OS = (JOptionPane.showInputDialog(null, "Which operating system is it running ")).trim();
		while (OS.equals("")){
			JOptionPane.showMessageDialog(null, "Cannot accept blank entries! \nPlease try again.");
			OS = JOptionPane.showInputDialog("Which operating system is it running ");
		}
		sp.setOperatingSystem(OS);
		
		String YoR = (JOptionPane.showInputDialog(null, "What year was the smartphone released ")).trim();
		while (YoR.equals("")){
			JOptionPane.showMessageDialog(null, "Cannot accept blank entries! \nPlease try again.");
			YoR = JOptionPane.showInputDialog("What year was the smartphone released ");
		}
		sp.setYearOfRelease(YoR);

		return sp;
	}//end addSmartphone()
	
	/**
	 * Adds the television.
	 *
	 * @return the television
	 */
	public Television addTelevision ()
	{
		Television t = new Television();
		
		String bName = (JOptionPane.showInputDialog(null, "What is the Television brand name? ")).trim();
		while (bName.equals("")){
			JOptionPane.showMessageDialog(null, "Cannot accept blank entries!");
			bName = JOptionPane.showInputDialog("What is its model name ");
		}
		t.setBrandName(bName);
		try{
		String mNumber = (JOptionPane.showInputDialog(null, "What is the Television brand name? ")).trim();
		while (mNumber.equals("")){
			JOptionPane.showMessageDialog(null, "Cannot accept blank entries!");
			mNumber = JOptionPane.showInputDialog("What is its model name ");
		}
		t.setModelNumber(mNumber);
		
		
		//price related
		String tPrice = JOptionPane.showInputDialog(null, "Price ").trim();
		while (tPrice.equals("")){
			JOptionPane.showMessageDialog(null, "Cannot accept balnk entries!");
		}
		JOptionPane.showMessageDialog(null, tPrice);
		double price = Double.parseDouble(tPrice);
		t.setprice(price);
		}catch (NumberFormatException nfe) 
			{
				JOptionPane.showMessageDialog(null, "\n*** ERROR NUMBER NOT ENTERED*** \n \nPlease reenter \n");
				int stock = Integer.parseInt((JOptionPane.showInputDialog(null, "How much stock is left ")).trim());
				t.setstock(stock);
			}
		//stock related
		try {
		String tStock = JOptionPane.showInputDialog(null, "How much stock is left ").trim();//existence validation
		while (tStock.equals("")){
			JOptionPane.showMessageDialog(null, "Cannot accept blank entries!");
			tStock = JOptionPane.showInputDialog("How much stock is left ");
		}
		int stock = Integer.parseInt(tStock);
		t.setstock(stock);
		}
		catch (NumberFormatException nfe) //data type validation
			{
				JOptionPane.showMessageDialog(null, "\n*** ERROR NUMBER NOT ENTERED*** \n \nPlease reenter \n");
				int stock = Integer.parseInt((JOptionPane.showInputDialog(null, "How much stock is left ")).trim());
				t.setstock(stock);
			}

		String sS = (JOptionPane.showInputDialog(null, "What is the TV's Screen size")).trim();
		while (sS.equals("")){
			JOptionPane.showMessageDialog(null, "Cannot accept blank entries!");
			sS = JOptionPane.showInputDialog("What is its model name ");
		}
		t.setScreenSize(sS);

		String Res = null;
			do { 
				Res = (JOptionPane.showInputDialog(null, "What is the screen's Resolution? ")).trim();
				while (Res.equals("")){
					JOptionPane.showMessageDialog(null, "Cannot accept blank entries!");
					Res = JOptionPane.showInputDialog("What is it's resolution ");
				}
				if (!Res.equals ("1080p") && !Res.equals("720p") && !Res.equals("4K")){
					JOptionPane.showMessageDialog (frame, "Error, Screen resolution must either be 720p, 1080p or 4K");
				}
			} while (!Res.equals ("1080p") && !Res.equals("720p") && !Res.equals("4K"));
			t.setResolution(Res);
		String sType = null;
			do { System.out.print ("What is the screen type?");
				sType = (JOptionPane.showInputDialog(null, "What is it's screen type? ")).trim();
				while (Res.equals("")){
					JOptionPane.showMessageDialog(null, "Cannot accept blank entries!");
					Res = JOptionPane.showInputDialog("What is it's screen type? ");
				}
				if (!sType.equals("LCD") && !sType.equals("LED") && !sType.equals ("Plasma")) { 
					System.out.println ("Error, Screen type must either be LCD, LED or Plasma");
				}
			}while (!sType.equals("LCD") && !sType.equals("LED") && !sType.equals ("Plasma"));
			t.setScreenType(sType);
		return t;
	} // end addTelevision()
}
