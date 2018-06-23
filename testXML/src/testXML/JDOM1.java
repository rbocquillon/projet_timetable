package testXML;

import java.io.*;
import org.jdom2.*;
import org.jdom2.output.*;

public class JDOM1 {

	//Nous allons commencer notre arborescence en cr�ant la racine XML
	   //qui sera ici "personnes".
	   static Element racine = new Element("TimeTableDB");

	   //On cr�e un nouveau Document JDOM bas� sur la racine que l'on vient de cr�er
	   static org.jdom2.Document document = new Document(racine);

	   public static void main(String[] args)
	   { 
			
			Element nouvellesalle = new Element("Room");
			racine.addContent(nouvellesalle);
			
			Element id = new Element("RoomID");
			id.setText("BO1N");
			
			Element capacity = new Element("RoomCapacity");
			capacity.setText("9000");
			
			nouvellesalle.addContent(id);
			nouvellesalle.addContent(capacity);

	      affiche();
	      enregistre("Exercice1.xml");
	   }
	   
	   static void affiche()
	   {
	      try
	      {
	         //On utilise ici un affichage classique avec getPrettyFormat()
	         XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	         sortie.output(document, System.out);
	      }
	      catch (java.io.IOException e){}
	   }

	   static void enregistre(String fichier)
	   {
	      try
	      {
	         //On utilise ici un affichage classique avec getPrettyFormat()
	         XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	         //Remarquez qu'il suffit simplement de cr�er une instance de FileOutputStream
	         //avec en argument le nom du fichier pour effectuer la s�rialisation.
	         sortie.output(document, new FileOutputStream(fichier));
	      }
	      catch (java.io.IOException e){}
	   }
	   
}
