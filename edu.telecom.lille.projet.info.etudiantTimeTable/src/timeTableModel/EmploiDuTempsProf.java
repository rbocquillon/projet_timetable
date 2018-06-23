/*******************************************************************************
 * 2018, All rights reserved.
 *******************************************************************************/
package timeTableModel;

import java.util.Hashtable;
import timeTableModel.EmploiDuTemps;
import timeTableModel.Reservation;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of EmploiDuTempsProf.
 * 
 * @author Hugo
 */
public class EmploiDuTempsProf extends EmploiDuTemps {
	/**
	 * Description of the property profID.
	 */
	private String profID;
	
	/**
	 * Description of the property reservationsProf.
	 */
	private Hashtable<Integer, Reservation> reservationsProf = new Hashtable<Integer, Reservation>();
	
	// Start of user code (user defined attributes for EmploiDuTempsProf)
	
	// End of user code
	
	/**
	 * The constructor.
	 */
	public EmploiDuTempsProf(String ProfID,String edtID) {
		// Start of user code constructor for EmploiDuTempsProf)
		super(edtID);
		this.profID = ProfID;
		// End of user code
	}
	
	// Start of user code (user defined methods for EmploiDuTempsProf)
	
	// End of user code
	/**
	 * Returns profID.
	 * @return profID 
	 */
	public String getProfID() {
		return this.profID;
	}
	
	/**
	 * Sets a value to attribute profID. 
	 * @param newProfID 
	 */
	public void setProfID(String newProfID) {
		this.profID = newProfID;
	}

	/**
	 * Returns reservationsProf.
	 * @return reservationsProf 
	 */
	public Hashtable<Integer, Reservation> getReservationsProf() {
		return this.reservationsProf;
	}



}
