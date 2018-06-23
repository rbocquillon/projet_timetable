/*******************************************************************************
 * 2018, All rights reserved.
 *******************************************************************************/
package timeTableModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import timeTableModel.Room;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Reservation.
 * 
 * @author Hugo & Robin
 */
public class Reservation {
	
	private SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
	/**
	 * Description of the property room.
	 */
	private Room room;
	
	/**
	 * Description of the property StartDate.
	 */
	private GregorianCalendar StartDate;
	
	/**
	 * Description of the property NumeroDeReservation.
	 */
	private int NumeroDeReservation;
	
	/**
	 * Description of the property EndDate.
	 */
	private  GregorianCalendar EndDate;
	
	/**
	 * Description of the property Login.
	 */
	private String LoginProf;
	
	// Start of user code (user defined attributes for Reservation)
	
	// End of user code
	
	/**
	 * The constructor.
	 */
	public Reservation(int NumRes, String Login,  GregorianCalendar StartDate,  GregorianCalendar EndDate, Room room ) {
		// Start of user code constructor for Reservation)
		this.NumeroDeReservation = NumRes;
		this.LoginProf = Login;
		this.StartDate = StartDate;
		this.EndDate = EndDate;
		this.room = room;
		
		// End of user code
	}
	
	// Start of user code (user defined methods for Reservation)
	
	// End of user code

	/**
	 * Returns room.
	 * @return room 
	 */
	public Room getRoom() {
		return this.room;
	}
	
	/**
	 * Sets a value to attribute room. 
	 * @param newRoom 
	 */
	public void setRoom(Room newRoom) {
		this.room = newRoom;
	}

	/**
	 * Returns StartDate.
	 * @return StartDate 
	 */
	public GregorianCalendar getStartDate() {
		return this.StartDate;
	}
	
	/**
	 * Sets a value to attribute StartDate. 
	 * @param newStartDate 
	 */
	public void setStartDate(GregorianCalendar newStartDate) {
		this.StartDate = newStartDate;
	}

	/**
	 * Returns NumeroDeReservation.
	 * @return NumeroDeReservation 
	 */
	public int getNumeroDeReservation() {
		return this.NumeroDeReservation;
	}
	
	/**
	 * Sets a value to attribute NumeroDeReservation. 
	 * @param newNumeroDeReservation 
	 */
	public void setNumeroDeReservation(int newNumeroDeReservation) {
		this.NumeroDeReservation = newNumeroDeReservation;
	}

	/**
	 * Returns EndDate.
	 * @return EndDate 
	 */
	public GregorianCalendar getEndDate() {
		return this.EndDate;
	}
	
	/**
	 * Sets a value to attribute EndDate. 
	 * @param newEndDate 
	 */
	public void setEndDate(GregorianCalendar newEndDate) {
		this.EndDate = newEndDate;
	}
	
	/**
	 * Returns Login.
	 * @return Login 
	 */
	public String getLoginProf() {
		return this.LoginProf;
	}
	
	/**
	 * Sets a value to attribute Login. 
	 * @param newLogin 
	 */
	public void setLoginProf(String newLoginProf) {
		this.LoginProf = newLoginProf;
	}



}
