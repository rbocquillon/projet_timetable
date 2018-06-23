/*******************************************************************************
 * 2018, All rights reserved.
 *******************************************************************************/
package timeTableModel;

import java.util.Hashtable;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Room.
 * 
 * @author Hugo
 */
public class Room {
	/**
	 * Description of the property roomID.
	 */
	private String roomID;
	
	/**
	 * Description of the property capacity.
	 */
	private int capacity;
	
	// Start of user code (user defined attributes for Room)
	
	// End of user code
	
	/**
	 * The constructor.
	 */
	public Room(String RoomID, int Capacity) {
		// Start of user code constructor for Room)
		this.roomID = RoomID;
		this.capacity = Capacity;
		// End of user code
	}
	
	// Start of user code (user defined methods for Room)
	
	// End of user code
	/**
	 * Returns roomID.
	 * @return roomID 
	 */
	public String getRoomID() {
		return this.roomID;
	}
	
	/**
	 * Sets a value to attribute roomID. 
	 * @param newRoomID 
	 */
	public void setRoomID(String newRoomID) {
	this.roomID = newRoomID;
	}

	/**
	 * Returns capacity.
	 * @return capacity 
	 */
	public int getCapacity() {
		return this.capacity;
	}
	
	/**
	 * Sets a value to attribute capacity. 
	 * @param newCapacity 
	 */
	public void setCapacity(int newCapacity) {
		this.capacity = newCapacity;
	}



}
