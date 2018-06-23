/*******************************************************************************
 * 2018, All rights reserved.
 *******************************************************************************/
package timeTableModel;

import java.util.GregorianCalendar;
import java.util.Hashtable;
import timeTableModel.EmploiDuTemps;
import java.io.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;
import java.util.List;
import java.util.Map.Entry;
import java.util.Iterator;
import org.jdom2.filter.*;



// Start of user code (user defined imports)

// End of user code

/**
 * Description of TimeTableDB.
 * 
 * @author Hugo
 */
public class TimeTableDB {
	/**
	 * Description of the property file.
	 */
	private String TTDB_ID ;
	
	/**
	 * Description of the property emploiDuTemps.
	 */
	private Hashtable<String, EmploiDuTemps> emploiDuTemps = new Hashtable<String, EmploiDuTemps>();
	
	private Hashtable<String, Room> Rooms = new Hashtable<String, Room>();
	// Start of user code (user defined attributes for TimeTableDB)
	private SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
	
	   //On crée un nouveau Document JDOM basé sur la racine que l'on vient de créer
	static org.jdom2.Document document;
	// End of user code
	 
	/**
	 * The constructor.
	 */
	public TimeTableDB(String id) {
		// Start of user code constructor for TimeTableDB)
		this.TTDB_ID = id;
		
		SAXBuilder sxb = new SAXBuilder();
		try{
		document = sxb.build(new File("timeTableDB.xml"));
		}catch(Exception e){}
		
		//System.out.println(getRooms()); //On vérifie que la Hashtable est vide
		
		loadDB();  // Lecture du fichier XML
		// End of user code
	}
	
	/**
	 * Description of the method saveDB.
	 */
	public void saveDB() {
		
		org.jdom2.Document NEWdocument = new Document(new Element("TimeTablesDB"));
		Element Salles = new Element("Rooms");
		Element EDTs = new Element("TimeTables");
		NEWdocument.getRootElement().addContent(Salles);
		NEWdocument.getRootElement().addContent(EDTs);
		
		Element SALLES = NEWdocument.getRootElement().getChildren().get(0);
		int i = 0;
		
		for(Entry<String, Room> entry:Rooms.entrySet()) {
			SALLES.addContent(new Element("Room"));
			SALLES.getChildren().get(i).addContent(new Element("RoomID"));
			SALLES.getChildren().get(i).getChildren().get(0).setText(entry.getValue().getRoomID());
			
			SALLES.getChildren().get(i).addContent(new Element("Capacity"));
			SALLES.getChildren().get(i).getChildren().get(1).setText(""+entry.getValue().getCapacity());
			
			i=i+1;
			}

		Element EDTS = NEWdocument.getRootElement().getChildren().get(1);
		int j = 0;
		
		for(Entry<String, EmploiDuTemps> entry:emploiDuTemps.entrySet()) {
			EDTS.addContent(new Element("TimeTable"));
			EDTS.getChildren().get(j).addContent(new Element("GroupID"));
			EDTS.getChildren().get(j).getChildren().get(0).setText(entry.getValue().getEdtID());
			
			EDTS.getChildren().get(j).addContent(new Element("Books"));
			Element Reservations = EDTS.getChildren().get(j).getChildren().get(1);
			int k = 0;
			
			for(Entry<Integer, Reservation> entry2:emploiDuTemps.get(entry.getKey()).getReservations().entrySet()) {
				Reservations.addContent(new Element("Book"));
				
				Reservations.getChildren().get(k).addContent(new Element("BookingId"));
				Reservations.getChildren().get(k).getChildren().get(0).setText(""+entry2.getValue().getNumeroDeReservation());
				
				Reservations.getChildren().get(k).addContent(new Element("Login"));
				Reservations.getChildren().get(k).getChildren().get(1).setText(""+entry2.getValue().getLoginProf());
				
				Reservations.getChildren().get(k).addContent(new Element("DateBegin"));
				GregorianCalendar SD = entry2.getValue().getStartDate();
				Reservations.getChildren().get(k).getChildren().get(2).setText(FormatGrego(SD.get(GregorianCalendar.DAY_OF_MONTH))+"/" + FormatGrego(SD.get(GregorianCalendar.MONTH)) + "/" + FormatGrego(SD.get(GregorianCalendar.YEAR))+" "+FormatGrego(SD.get(GregorianCalendar.HOUR_OF_DAY))+":"+FormatGrego(SD.get(GregorianCalendar.MINUTE))+":"+FormatGrego(SD.get(GregorianCalendar.SECOND)));
				
				Reservations.getChildren().get(k).addContent(new Element("DateEnd"));
				GregorianCalendar ED = entry2.getValue().getEndDate();
				Reservations.getChildren().get(k).getChildren().get(3).setText(FormatGrego(ED.get(GregorianCalendar.DAY_OF_MONTH))+"/" + FormatGrego(ED.get(GregorianCalendar.MONTH)) + "/" + FormatGrego(ED.get(GregorianCalendar.YEAR))+" "+FormatGrego(ED.get(GregorianCalendar.HOUR_OF_DAY))+":"+FormatGrego(ED.get(GregorianCalendar.MINUTE))+":"+FormatGrego(ED.get(GregorianCalendar.SECOND)));
				
				Reservations.getChildren().get(k).addContent(new Element("RoomId"));
				Reservations.getChildren().get(k).getChildren().get(4).setText(""+entry2.getValue().getRoom().getRoomID());
				
				k=k+1;
				}
			
			j=j+1;
			}
		
		try
		   {
		      //On utilise ici un affichage classique avec getPrettyFormat()
		      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		      //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
		      //avec en argument le nom du fichier pour effectuer la sérialisation.
		      sortie.output(NEWdocument, new FileOutputStream("timeTableDB.xml"));
		   }
		   catch (java.io.IOException e){}
	}
	
	private String FormatGrego(int Grego) {
		String BonFormat;
		if (Grego<10)
			BonFormat="0"+Grego;
		else
			BonFormat=""+Grego;
		return BonFormat;
	}
	 
	/**
	 * Description of the method loadDB.
	 */
	public void loadDB() {
		// Start of user code for method loadDB
		if(document!=null){
			
			Element racine = document.getRootElement();        //Récupération de la racine TimeTableDB
			//System.out.println(racine);
			List<Element> Sommaire = racine.getChildren();    //Récupération des deux sous racines Rooms et TimeTables
			//System.out.println(Sommaire);
			List<Element> Salles = Sommaire.get(0).getChildren(); // Récupération des différentes salles
			//System.out.println(Salles);
			Iterator<Element> itRoom = Salles.iterator(); // Création d'un parcoureur de liste
			
			while(itRoom.hasNext()){ // Si il y a encore un élément à parcourir
				
				Element uneSalle = (Element)itRoom.next();  //incrémentation du parcoureur (Je ne sais pas pourquoi mais il y a pas de problème d'indice... le next doit se faire a posteriori
				Rooms.put(uneSalle.getChildren().get(0).getText(), new Room (uneSalle.getChildren().get(0).getText(),Integer.valueOf(uneSalle.getChildren().get(1).getText())));
			// la ligne du dessus ajoute à l'hashtable les informations de la Room récupérées dans le fichier XML
			}
			
			List<Element> EDTs = Sommaire.get(1).getChildren();
			//System.out.println(EDTs);
			Iterator<Element> itEDT = EDTs.iterator();
			
			while(itEDT.hasNext()){ // Si il y a encore un élément à parcourir
				
				Element unEDT = (Element)itEDT.next();  //incrémentation du parcoureur (Je ne sais pas pourquoi mais il y a pas de problème d'indice... le next doit se faire a posteriori
				//System.out.println(unEDT.getChildren());
				emploiDuTemps.put(unEDT.getChildren().get(0).getText(), new EmploiDuTemps(unEDT.getChildren().get(0).getText()));
				Iterator<Element> itBook = unEDT.getChildren().get(1).getChildren().iterator();
				
				while(itBook.hasNext()){ // Si il y a encore un élément à parcourir
					Element unBook = (Element)itBook.next();
					//System.out.println(unBook.getChildren());
					try {
						String SD = unBook.getChildren().get(2).getText();
						String ED = unBook.getChildren().get(3).getText();
						
						//System.out.println(SD);
						//System.out.println(ED);
						emploiDuTemps.get(unEDT.getChildren().get(0).getText()).addReservation(Integer.parseInt(unBook.getChildren().get(0).getValue()), unBook.getChildren().get(1).getText(), new GregorianCalendar(Integer.parseInt(SD.substring(6, 10)),Integer.parseInt(SD.substring(3, 5)),Integer.parseInt(SD.substring(0, 2)),Integer.parseInt(SD.substring(11, 13)),Integer.parseInt(SD.substring(14, 16))), new GregorianCalendar(Integer.parseInt(ED.substring(6, 10)),Integer.parseInt(ED.substring(3, 5)),Integer.parseInt(ED.substring(0, 2)),Integer.parseInt(ED.substring(11, 13)),Integer.parseInt(ED.substring(14, 16))), Rooms.get(unBook.getChildren().get(4).getText()));
					} catch (NumberFormatException e) { // si le format date n'est pas respecté (mesure de préc=vention imposée par le debugeur)
						e.printStackTrace();
					}
				}
			}
		}
			
		// End of user code
	}
	 
	// Start of user code (user defined methods for TimeTableDB)
	
	public void AddRoom(String idRoom, int capacity) {
		
		Rooms.put(idRoom, new Room(idRoom, capacity));
	}
	
	public void RemoveRoom(String roomID) {
		
		Rooms.remove(roomID);
		
	}
	
	public void AddEDT(String edtID) {
		
		emploiDuTemps.put(edtID, new EmploiDuTemps(edtID));
		

	}
	
	public void RemoveEDT(String EdtID) {
		
		emploiDuTemps.remove(EdtID);
		
	}
	
	// End of user code
	/**
	 * Returns file.
	 * @return file 
	 */
	public String getTTDB_ID() {
		return this.TTDB_ID;
	}
	
	/**
	 * Sets a value to attribute file. 
	 * @param newFile 
	 */
	public void setTTDB_ID(String newTTDB_ID) {
		this.TTDB_ID = newTTDB_ID;
	}

	/**
	 * Returns emploiDuTempss.
	 * @return emploiDuTempss 
	 */
	public Hashtable<String, EmploiDuTemps> getEmploiDuTemps() {
		return this.emploiDuTemps;
	}
	
	public Hashtable<String, Room> getRooms() {
		return this.Rooms;
	}

	public GregorianCalendar DatetoGregoCalendar(Date date) {
		GregorianCalendar gregcal = new GregorianCalendar();
		gregcal.setTime(date);
		return gregcal;
	}

}
