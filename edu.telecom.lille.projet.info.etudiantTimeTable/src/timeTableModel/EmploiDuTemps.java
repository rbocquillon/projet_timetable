/*******************************************************************************
 * 2018, All rights reserved.
 *******************************************************************************/
package timeTableModel;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Map.Entry;

import timeTableModel.Reservation;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of EmploiDuTemps.
 * 
 * @author Hugo
 */
public class EmploiDuTemps {
	/**
	 * Description of the property edtID.
	 */
	private String edtID ;
	
	/**
	 * Description of the property reservations.
	 */
	private Hashtable<Integer ,Reservation> reservations = new Hashtable<Integer ,Reservation> () ;
	
	// Start of user code (user defined attributes for EmploiDuTemps)
	
	// End of user code
	
	/**
	 * The constructor.
	 */
	public EmploiDuTemps(String edtID) {
		// Start of user code constructor for EmploiDuTemps)
		this.edtID = edtID;
		// End of user code
	}
	
	// Start of user code (user defined methods for EmploiDuTemps)
	public void addReservation(int numBook, String Login, GregorianCalendar StartDate, GregorianCalendar EndDate, Room room) {
		reservations.put(numBook, new Reservation(numBook, Login, StartDate, EndDate, room )); 
	}
	
	public void RemoveReservation(int NumRes) {
		reservations.remove(NumRes);
	}
	
	// End of user code
	/**
	 * Returns edtID.
	 * @return edtID 
	 */
	public String getEdtID() {
		return this.edtID;
	}
	
	/**
	 * Sets a value to attribute edtID. 
	 * @param newEdtID 
	 */
	public void setEdtID(String newEdtID) {
		this.edtID = newEdtID;
	}

	/**
	 * Returns reservations.
	 * @return reservations 
	 */
	public Hashtable<Integer, Reservation> getReservations() {
		return this.reservations;
	}

	// juste pour test de roomsIdToString du controller (comment marche le entry ? )
	/**public int tester() {
		for(Entry<Integer, Reservation> entry:this.reservations.entrySet())
			{System.out.println(entry.getKey());
			System.out.println(entry.getValue());}
		return 0;
	} */
}
