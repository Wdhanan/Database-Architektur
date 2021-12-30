import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;



public class ControllerKlasse {
	
static Scanner ein=new Scanner(System.in);
	
	public Connection connect() throws SQLException{
		final  String propfile = "/praktikum3/db.properties";
        System.out.println("Connect ENTER");
        Connection cn=null;
        try {
        	final java.io.InputStream propFile=getClass().getResourceAsStream(propfile);
        	final Properties props= new Properties(System.getProperties());
        	props.load(propFile);
        	
        	final String url=props.getProperty("url");
        	cn=DriverManager.getConnection(url, props);
        	cn.setAutoCommit(false);
        	cn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        	if(cn!=null)
        		System.out.println("Verbindung erfolgreich aufgestellt");
        	
        }catch(IOException e) {
        	System.out.println("Connect-Error-Propfile:"+e.toString());
        }catch(SQLException e) {
        	System.out.println("Connect-Error:"+e.toString());
        	throw e;
        	
        }
        
     return cn;   
}
	public static void main(String[] args) {
	     
		try(Connection cn= new ControllerKlasse().connect()){
	    	 System.out.println("Erfolgreich verbunden");
	     } catch (SQLException e) {
	      System.out.println("Error:"+e.toString());
			
		}
}
	public static void LieferungDB(Connection cn) throws Exception {
		String auswahl;
		
		System.out.println("=======Die möglichen aufrufbare Methoden von Lieferung sind: ================");
		System.out.println("anlegen,aktualisieren,loeschen,findMitLnr,findMitAnr");
		System.out.println("Wählen Sie bitte eine zu aufzurende Methode aus:");
		auswahl=ein.next();
	
		switch(auswahl) {
		case "anlegen":
			LieferungDTO L= new LieferungDTO(4,0,new BigDecimal(60.99));
			int b=LieferungDB.anlegen(cn, L);
			TestView.anlegenLieferung(cn,b);//fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			String eingabe;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe=ein.next();
			if(eingabe.equals("j"))
				ControllerKlasse.LieferungDB(cn);
			else if(eingabe.equals("n")|| !eingabe.equals("j"))
				System.out.println("======ende=====");
			ein.close();
			break;
			
			
		case "aktualisieren":
			LieferungDTO L1= new LieferungDTO(4,5,new BigDecimal(29.99));
			int erg=LieferungDB.aktualisieren(cn, L1);
			TestView.aktualisierenLieferung(cn,erg);//fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			
			String eingabe2;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe2=ein.next();
			if(eingabe2.equals("j"))
				ControllerKlasse.LieferungDB(cn);
			else if(eingabe2.equals("n")|| !eingabe2.equals("j"))
				System.out.println("======ende=====");
			ein.close();
			
			break;
		case "loeschen":
			int a=LieferungDB.loeschen(cn, 4, 0);
			TestView.loeschenLieferung(cn,a);//fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			
			String eingabe3;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe3=ein.next();
			if(eingabe3.equals("j"))
				ControllerKlasse.LieferungDB(cn);
			else if(eingabe3.equals("n")|| !eingabe3.equals("j"))
				System.out.println("======ende=====");
			
			break;
		case "findMitLnr":
				List<LieferungDTO> li=LieferungDB.findMitLnr(cn, 1 );//Alle Lieferungen Wobei der LNr 1 ist ,Sollen ausgegeben werden
			
			    TestView.findMitLnrLieferungDB(cn, li);//fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			    
			    String eingabe4;
				System.out.println("=====Weiter Testen?(j/n)?=====");
				eingabe4=ein.next();
				if(eingabe4.equals("j"))
					ControllerKlasse.LieferungDB(cn);
				else if(eingabe4.equals("n")|| !eingabe4.equals("j"))
					System.out.println("============ende=================");
				ein.close();
				break;
				
		case "findMitAnr":
			List<LieferungDTO>li1=LieferungDB.findMitAnr(cn, 3);//Alle Lieferungen Wobei der ANr 3 ist ,Sollen ausgegeben werden
			TestView.findMitAnrLieferung(cn,li1);////fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			String eingabe5;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe5=ein.next();
			if(eingabe5.equals("j"))
				ControllerKlasse.LieferungDB(cn);
			else if(eingabe5.equals("n")|| !eingabe5.equals("j"))
				System.out.println("==================ende====================");
			ein.close();
			break;
			
		default:
			System.out.println("Die entsprechende Methode existiert bei uns nicht ");
			System.out.println("==================ende====================");
			ein.close();

		}
		
		
	
		
		
	}
	public static void LieferantDB(Connection cn) throws Exception {
    String auswahl;
		System.out.println("=======Die möglichen aufrufbare Methoden von Lieferant sind: ================");
		System.out.println("anlegen,aktualisieren,loeschen,findAll,findMitLnr,findBetween");
		System.out.println("Wählen Sie bitte eine zu aufzurende Methode aus:");
		auswahl=ein.next();
		switch(auswahl) {
		case "anlegen":
			LieferantDTO L=new LieferantDTO();
			L.setName("junior");
			L.setLnr(2);
			L.setPlz("65070");
			int a=LieferantDB.anlegen(cn, L);
			TestView.anlegenLieferant(cn,a);//fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			String eingabe;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe=ein.next();
			if(eingabe.equals("j"))
				ControllerKlasse.LieferantDB(cn);
			else if(eingabe.equals("n")|| !eingabe.equals("j"))
				System.out.println("===========ende===========");
			ein.close();
			break;
		
			
		case "aktualisieren":
			LieferantDTO L1=new LieferantDTO();
			L1.setName("Wilfried");
			L1.setPlz("60897");
			L1.setLnr(4);
			int b=LieferantDB.aktualisieren(cn, L1);
			TestView.aktualisierenLieferant(cn,b);//fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			String eingabe1;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe1=ein.next();
			if(eingabe1.equals("j"))
				ControllerKlasse.LieferantDB(cn);
			else if(eingabe1.equals("n")|| !eingabe1.equals("j"))
				System.out.println("===========ende===========");
			ein.close();
			break;
		
		case "loeschen":
		
			int c= LieferantDB.loeschen(cn, 2);//nicht nur der Lieferant wird geloescht auch die Lieferung,bei der er auftritt wird geloescht
			TestView.loeschenLieferant(cn,c);////fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			String eingabe2;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe2=ein.next();
			if(eingabe2.equals("j"))
				ControllerKlasse.LieferantDB(cn);
			else if(eingabe2.equals("n")|| !eingabe2.equals("j"))
				System.out.println("===========ende===========");
			ein.close();
			break;
		
		case "findAll":
			List<LieferantDTO>li=LieferantDB.findAll(cn);
			TestView.findAllLieferant(cn,li);////fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			
			String eingabe3;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe3=ein.next();
			if(eingabe3.equals("j"))
				ControllerKlasse.LieferantDB(cn);
			else if(eingabe3.equals("n")|| !eingabe3.equals("j"))
				System.out.println("===========ende===========");
			ein.close();
			break;
		
		case "findMitLnr":
			LieferantDTO L3=LieferantDB.findMitLnr(cn, 1);
			TestView.findMitLnrLieferant(cn, L3);//fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			String eingabe4;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe4=ein.next();
			if(eingabe4.equals("j"))
				ControllerKlasse.LieferantDB(cn);
			else if(eingabe4.equals("n")|| !eingabe4.equals("j"))
				System.out.println("===========ende===========");
			ein.close();
			break;
			
		case "findBetween":
			List<LieferantDTO>L4=LieferantDB.findBetween(cn, 3, 6);
			TestView.findBetweenLieferant(cn,L4);//fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			String eingabe5;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe5=ein.next();
			if(eingabe5.equals("j"))
				ControllerKlasse.LieferantDB(cn);
			else if(eingabe5.equals("n")|| !eingabe5.equals("j"))
				System.out.println("===========ende===========");
			ein.close();
			break;
			
		default:
			System.out.println("Die entsprechende Methode existiert bei uns nicht ");
			System.out.println("==================ende====================");
			ein.close();
		}
		
	}
	public static void ArtikelDB(Connection cn) throws Exception {
		String auswahl;
		System.out.println("=======Die möglichen aufrufbare Methoden von Artikel sind: ================");
		System.out.println("anlegen,aktualisieren,loeschen,findArt,findMitAnr,aktuList");
		System.out.println("Wählen Sie bitte eine zu aufzurende Methode aus:");
		auswahl=ein.next();
		switch(auswahl) {
		case "anlegen":
			ArtikelDTO A=new ArtikelDTO();
			A.setAnr(8);
			A.setBezeichnung("Socken");
			A.setPreis(new BigDecimal(7.99));
			long millis=System.currentTimeMillis();
			java.sql.Date date= new java.sql.Date(millis);
			A.setAngelegt( date);
			int a=ArtikelDB.anlegen(cn, A);
			TestView.anlegenArtikel(cn,a);//fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			String eingabe;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe=ein.next();
			if(eingabe.equals("j"))
				ControllerKlasse.ArtikelDB(cn);
			else if(eingabe.equals("n")|| !eingabe.equals("j"))
				System.out.println("===========ende===========");
			ein.close();
			break;
			
		case "aktualisieren":
			ArtikelDTO A1=new ArtikelDTO();
			A1.setAnr(0);
			A1.setBezeichnung("Ball");
			A1.setPreis(new BigDecimal(10.99));
			long millis1=System.currentTimeMillis();
			java.sql.Date date1= new java.sql.Date(millis1);
			A1.setAngelegt(date1);
			int b=ArtikelDB.aktualisieren(cn, A1);
			TestView.aktualisierenArtikel(cn,b);//fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			String eingabe1;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe1=ein.next();
			if(eingabe1.equals("j"))
				ControllerKlasse.ArtikelDB(cn);
			else if(eingabe1.equals("n")|| !eingabe1.equals("j"))
				System.out.println("===========ende===========");
			ein.close();
			break;
			
		case "loeschen":


			int c=ArtikelDB.loeschen(cn, 8);
			TestView.loeschenArtikel(cn,c);//fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			String eingabe2;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe2=ein.next();
			if(eingabe2.equals("j"))
				ControllerKlasse.ArtikelDB(cn);
			else if(eingabe2.equals("n")|| !eingabe2.equals("j"))
				System.out.println("===========ende===========");
			ein.close();
			break;
			
		case "findArt":
			List<ArtikelDTO>list=ArtikelDB.findArt(cn, new BigDecimal(100.99));
			TestView.findArtArtikel(cn,list);//fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			String eingabe3;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe3=ein.next();
			if(eingabe3.equals("j"))
				ControllerKlasse.ArtikelDB(cn);
			else if(eingabe3.equals("n")|| !eingabe3.equals("j"))
				System.out.println("===========ende===========");
			ein.close();
			break;
			
		case "findMitAnr":
			ArtikelDTO A3= ArtikelDB.findMitAnr(cn, 3);
			TestView.findMitAnrArtikel(cn,A3);//fur die Ausgabe wird immer die Entsprechende Klasse in TestView Aufgerufen
			String eingabe4;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe4=ein.next();
			if(eingabe4.equals("j"))
				ControllerKlasse.ArtikelDB(cn);
			else if(eingabe4.equals("n")|| !eingabe4.equals("j"))
				System.out.println("===========ende===========");
			ein.close();


			break;
		
		case "aktuList":
			List<ArtikelDTO>list2=new ArrayList<>();
			
			ArtikelDTO L1=new ArtikelDTO();
			L1.setAnr(7);
			L1.setBezeichnung("Socken");
			L1.setPreis(new BigDecimal(7.99));
			long millis3=System.currentTimeMillis();
			java.sql.Date date5= new java.sql.Date(millis3);
			L1.setAngelegt( date5);
			ArtikelDTO L2=new ArtikelDTO();
			L2.setAnr(8);
			L2.setBezeichnung("Tasche");
			L2.setPreis(new BigDecimal(77.39));
			long millis4=System.currentTimeMillis();
			java.sql.Date date6= new java.sql.Date(millis4);
			L2.setAngelegt( date6);
			ArtikelDTO L3=new ArtikelDTO();
			L3.setAnr(9);
			L3.setBezeichnung("Auto");
			L3.setPreis(new BigDecimal(70000.99));
			long millis5=System.currentTimeMillis();
			java.sql.Date date7= new java.sql.Date(millis5);
			L3.setAngelegt( date7);
			ArtikelDTO L4=new ArtikelDTO();
			L4.setAnr(10);
			L4.setBezeichnung("Bleistift");
			L4.setPreis(new BigDecimal(7.99));
			long millis6=System.currentTimeMillis();
			java.sql.Date date8= new java.sql.Date(millis6);
			L4.setAngelegt( date8);
			list2.add(L3);//in beliebiger Reihenfolge in der Liste einfugen
			list2.add(L1);
			list2.add(L2);
			list2.add(L4);
			List<ArtikelDTO> ergebnis=ArtikelDB.aktuList(cn, list2);
			TestView.aktuListArtikel(cn,ergebnis);
			String eingabe5;
			System.out.println("=====Weiter Testen?(j/n)?=====");
			eingabe5=ein.next();
			if(eingabe5.equals("j"))
				ControllerKlasse.ArtikelDB(cn);
			else if(eingabe5.equals("n")|| !eingabe5.equals("j"))
				System.out.println("===========ende===========");
			ein.close();
			break;
			
		default:
			System.out.println("Die entsprechende Methode existiert bei uns nicht ");
			System.out.println("==================ende====================");
			ein.close();
			
		}
	}
}

