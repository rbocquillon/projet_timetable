package timeTableController;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import timeTableModel.EmploiDuTemps;
import timeTableModel.Reservation;
import timeTableModel.Room;
import timeTableModel.TimeTableDB;
/**
 * Cette classe est le controleur d'emplois du temps que vous devez implementer. 
 * Elle contient un attribut correspondant a� la base de donnees d'emplois du temps que vous allez créer.
 * Elle contient toutes les fonctions de l'interface ITimeTableController que vous devez implémenter.
 * 
 * @author Jose Mennesson (Mettre àjour)
 * @version 04/2016 (Mettre � jour)
 * 
 */

//TODO Classe à modifier

public class TimeTableController implements ITimeTableController{

	/**
	 * Contient une instance de base de données d'emplois du temps
	 * 
	 */
	TimeTableDB tTDB;
	/**
	 *
	 * Constructeur de controleur d'emplois du temps créant la base de données d'emplois du temps
	 * 
	 * @param tTfile
	 * 		Fichier XML contenant la base de données d'emplois du temps
	 */
	public TimeTableController(String tTfile) {
		TimeTableDB tTDB=new TimeTableDB(tTfile);
		this.tTDB=tTDB;
	}

	@Override
	public String getTeacherLogin(int timeTableId, int bookId) {
		return tTDB.getEmploiDuTemps().get(""+timeTableId).getReservations().get(bookId).getLoginProf();
	}

	@Override
	public String[] roomsIdToString() {
		ArrayList<String> liste = new ArrayList<String>();
		for(Entry<String, Room>entry:tTDB.getRooms().entrySet()) {
			liste.add(entry.getKey());
		}
			int taille = liste.size();
			String[] rep = new String[taille];
			int i;
			for(i=0;i<taille;i++) {
				rep[i]=liste.get(i);
			}
		return rep;
	}

	@Override
	public String[] roomsToString() {
		// renvoyer une liste de rooms, chaque room sous forme d'un String comprenant Id et capacity
		ArrayList<String> liste = new ArrayList<String>();
		for(Entry<String, Room>entry:tTDB.getRooms().entrySet()) {
			liste.add("Id : " +entry.getKey()+" - Capacity : "+""+entry.getValue().getCapacity());
		}
			int taille = liste.size();
			String[] rep = new String[taille];
			int i;
			for(i=0;i<taille;i++) {
				rep[i]=liste.get(i);
			}
		return rep;
	}

	@Override
	public String[] timeTablesIDToString() {
		ArrayList<String> timeTableIDs = new ArrayList<String>();
		for(Entry<String, EmploiDuTemps> entry:tTDB.getEmploiDuTemps().entrySet()) {
			timeTableIDs.add(entry.getKey());
		}
			int taille = timeTableIDs.size();
			String[] rep = new String[taille];
			int i;
			for(i=0;i<taille;i++) {
				rep[i]=timeTableIDs.get(i);
			}
		return rep;
	}

	@Override
	public String[] booksIdToString(int timeTableId) {
		ArrayList<Integer> bookIDs = new ArrayList<Integer>();
		for(Entry<Integer, Reservation> entry:tTDB.getEmploiDuTemps().get(""+timeTableId).getReservations().entrySet()) {
			bookIDs.add(entry.getKey());
		}
			int taille = bookIDs.size();
			String[] rep = new String[taille];
			int i;
			for(i=0;i<taille;i++) {
				rep[i]=""+bookIDs.get(i);
			}
		return rep;
	}

	@Override
	public boolean addRoom(int roomId, int capacity) {
		boolean ans = false;
		if(!tTDB.getRooms().containsKey(""+roomId)) {
			tTDB.AddRoom(""+roomId, capacity);
			ans = true;
			tTDB.saveDB();
		}
		return ans;
	}

	@Override
	public boolean removeRoom(int roomId) {
		boolean ans = false;
		if(tTDB.getRooms().containsKey(""+roomId)) {
			tTDB.RemoveRoom(""+roomId);
			ans = true;
		}
		return ans;
	}

	@Override
	public int getRoom(int timeTableId, int bookId) {
		return Integer.parseInt(tTDB.getEmploiDuTemps().get(""+timeTableId).getReservations().get(bookId).getRoom().getRoomID());
	}

	@Override
	public boolean addTimeTable(int timeTableId) {
		boolean ans = false;
		if(!tTDB.getEmploiDuTemps().containsKey(""+timeTableId)) {
			tTDB.AddEDT(""+timeTableId);
			ans = true;
		}
		return ans;
	}

	@Override
	public boolean removeTimeTable(int timeTableId) {
		boolean ans = false;
		if(tTDB.getEmploiDuTemps().containsKey(""+timeTableId)) {
			tTDB.RemoveEDT(""+timeTableId);
			ans = true;
		}
		return ans;
	}

	@Override
	public boolean addBooking(int timeTableId, int bookingId, String login, Date dateBegin, Date dateEnd, int roomId) {
		boolean ans = false ;
		if(!tTDB.getEmploiDuTemps().get(""+timeTableId).getReservations().containsKey(bookingId)) {
			tTDB.getEmploiDuTemps().get(""+timeTableId).addReservation(bookingId, login, tTDB.DatetoGregoCalendar(dateBegin), tTDB.DatetoGregoCalendar(dateEnd), tTDB.getRooms().get(""+roomId));
			ans = true;
		}
		return ans;
	}

	@Override
	public void getBookingsDate(int timeTableId, Hashtable<Integer, Date> dateBegin, Hashtable<Integer, Date> dateEnd) {
		// remplir les hashtables begin et end � partir de l'EDT dont l'ID est timeTableId
		//dateBegin.put(arg0, arg1);
		for(Entry<Integer, Reservation> entry:tTDB.getEmploiDuTemps().get(""+timeTableId).getReservations().entrySet()) {
			dateBegin.put(entry.getKey(),entry.getValue().getStartDate().getTime());
			dateEnd.put(entry.getKey(),entry.getValue().getEndDate().getTime());
		}
	}

	@Override
	public boolean removeBook(int timeTableId, int bookId) {
		boolean ans = false ;
		if(tTDB.getEmploiDuTemps().get(""+timeTableId).getReservations().containsKey(bookId)) {
			tTDB.getEmploiDuTemps().get(""+timeTableId).RemoveReservation(bookId);
			ans = true;
		}
		return ans;
	}

	@Override
	public int getBookingsMaxId(int timeTableId) {
		ArrayList<Integer> bookingIDs = new ArrayList<Integer>();
		for(Entry<Integer, Reservation> entry:tTDB.getEmploiDuTemps().get(""+timeTableId).getReservations().entrySet())
			bookingIDs.add(entry.getKey());
		return Collections.max(bookingIDs);
	}

	@Override
	public boolean saveDB() {
		tTDB.saveDB();
		return true;
	}

	@Override
	public boolean loadDB() {
		tTDB.loadDB();
		return true;
	}
	
	
}
