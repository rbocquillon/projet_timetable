package timeTableModel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import timeTableController.TimeTableController;
/**
 * Cette classe permet de tester les fonctions du contr√¥leur d'emplois du temps.
 * Elle cr√©e une base de donn√©es de 3 salles et de 4 r√©servations d'emplois du temps et les sauvegarde dans le fichier "timeTableDB.xml". 
 * 
 * @author Jose Mennesson (Mettre √† jour)
 * @version 04/2016 (Mettre √† jour)
 * 
 */

//TODO Classe pouvant √™tre modifi√©e suivant vos besoins
import java.util.Hashtable;
import timeTableModel.EmploiDuTemps;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import org.jdom2.filter.*;

import java.util.Arrays;
import java.util.Enumeration;

public class Test {
	/**
	 * Fonction principale 
	 * 
	 * @param args
	 * 			Les arguments donn√©s en entr√©e du programme.
	 * 
	 */
	private SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
	
	public static void main(String[] args) {
		
		final String file="timeTableDB.xml";
		TimeTableDB TTDB = new TimeTableDB("1");
		
		//String a = "23/06/16 15:25:00";
		
		//System.out.println("20"+a.substring(6,8));
		//System.out.println(a.substring(3,5));
		//System.out.println(a.substring(0,2));
		
		/*
		Set keySet =TTDB.getRooms().keySet(); 
		Iterator it= keySet.iterator(); 

		while (it.hasNext()){Object key =it.next(); 

			System.out.println("RoomID : "+(String)key+" - CapacitÈ : "+ TTDB.getRooms().get(key).getCapacity());
		}
		
		Set keySet2 =TTDB.getEmploiDuTemps().keySet(); 
		Iterator it2= keySet2.iterator(); 

		while (it2.hasNext()){Object key2 =it2.next(); 
			Set keySet3 = TTDB.getEmploiDuTemps().get(key2).getReservations().keySet(); 
			Iterator it3 = keySet3.iterator();
			while (it3.hasNext()){Object key3 =it3.next(); 
				System.out.println("edtID : "+(String)key2+" - RÈservation : "+ TTDB.getEmploiDuTemps().get(key2).getReservations().get(key3).getNumeroDeReservation() +"- Login : "+ TTDB.getEmploiDuTemps().get(key2).getReservations().get(key3).getLoginProf()+ "- DÈbut : "+TTDB.getEmploiDuTemps().get(key2).getReservations().get(key3).getStartDate()+ "- Fin : "+TTDB.getEmploiDuTemps().get(key2).getReservations().get(key3).getEndDate()+ "- Salle : "+  TTDB.getEmploiDuTemps().get(key2).getReservations().get(key3).getRoom().getRoomID());
			}
		}
		
		*/
		TTDB.AddRoom("4", 120);
		TTDB.AddRoom("5",92);
		
		/*
		Set keySet6 =TTDB.getRooms().keySet(); 
		Iterator it6= keySet6.iterator(); 
		while (it6.hasNext()){Object key6 =it6.next(); 

		System.out.println("RoomID : "+(String)key6+" - CapacitÈ : "+ TTDB.getRooms().get(key6).getCapacity());
		}
		*/
		
		
		/*
		TTDB.RemoveRoom(TTDB.getRooms().get("1"));
		
		Set keySet5 =TTDB.getRooms().keySet(); 
		Iterator it5= keySet5.iterator(); 

		while (it5.hasNext()){Object key5 =it5.next(); 

		System.out.println("RoomID : "+(String)key5+" - CapacitÈ : "+ TTDB.getRooms().get(key5).getCapacity());
		}
		
		
		TTDB.RemoveEDT(TTDB.getEmploiDuTemps().get("1"));
		
		Set keySet4 =TTDB.getEmploiDuTemps().keySet(); 
		Iterator it4= keySet4.iterator(); 

		while (it4.hasNext()){Object key4 =it4.next(); 
			Set keySet3 = TTDB.getEmploiDuTemps().get(key4).getReservations().keySet(); 
			Iterator it3 = keySet3.iterator();
			while (it3.hasNext()){Object key3 =it3.next(); 
				System.out.println("edtID : "+(String)key4+" - RÈservation : "+ TTDB.getEmploiDuTemps().get(key4).getReservations().get(key3).getNumeroDeReservation() +"- Login : "+ TTDB.getEmploiDuTemps().get(key4).getReservations().get(key3).getLoginProf()+ "- DÈbut : "+TTDB.getEmploiDuTemps().get(key4).getReservations().get(key3).getStartDate()+ "- Fin : "+TTDB.getEmploiDuTemps().get(key4).getReservations().get(key3).getEndDate()+ "- Salle : "+  TTDB.getEmploiDuTemps().get(key4).getReservations().get(key3).getRoom().getRoomID());
			}
		}
		*/
		TTDB.AddEDT("2");
		TTDB.getEmploiDuTemps().get("2").addReservation(1, "HG", new GregorianCalendar(2018,3,7,8,15), new GregorianCalendar(2018,3,7,10,45), TTDB.getRooms().get("1"));
		TTDB.getEmploiDuTemps().get("2").addReservation(2, "HG", new GregorianCalendar(2018,3,8,13,10), new GregorianCalendar(2018,3,8,16,45), TTDB.getRooms().get("4"));
		
		//TTDB.RemoveEDT(TTDB.getEmploiDuTemps().get("2"));
		//TTDB.RemoveRoom(TTDB.getRooms().get("4"));
		
		TTDB.saveDB();
		TimeTableController control = new TimeTableController("tt");
		System.out.println(java.util.Arrays.toString(control.roomsIdToString()));
		//TTDB.getEmploiDuTemps().get("2").tester();
		System.out.println(java.util.Arrays.toString(control.roomsToString()));
		System.out.println(java.util.Arrays.toString(control.timeTablesIDToString()));
		System.out.println(java.util.Arrays.toString(control.booksIdToString(1)));
		System.out.println(control.getBookingsMaxId(1));
		Hashtable<Integer, Date> TestDebut = new Hashtable<Integer, Date>();
		Hashtable<Integer, Date> TestFin = new Hashtable<Integer, Date>();
		control.getBookingsDate(1, TestDebut, TestFin);
		Enumeration e1=TestDebut.elements();
		Enumeration e2=TestFin.elements();
		while(e1.hasMoreElements())
			System.out.println(e1.nextElement());
		while(e2.hasMoreElements())
			System.out.println(e2.nextElement());
	}
}
