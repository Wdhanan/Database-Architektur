import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


public class TestView {
 static Scanner ein =new Scanner(System.in);
	public static void main(String[] args) throws Exception {
		
		try (Connection cn= new ControllerKlasse().connect();) {
			ausWahl(cn);
		      }
		
	}
	//Der Anwender wird gefragt ,welche Klasse er testen will
	public static void ausWahl(Connection cn) throws Exception {
		System.out.println("===============Die mögliche Klassen sind:=========================");
		System.out.println("ArtikelDB\n"+"LieferantDB\n"+"LieferungDB");
		String auswahl;
		System.out.println("=======Waehlen sie bitte eine Klasse Zwischen die folgende Klassen:==============");
		
		auswahl=ein.next();
		switch(auswahl) {
		case "ArtikelDB":
			ControllerKlasse.ArtikelDB(cn);
			ein.close();
			break;
		case "LieferantDB":
			ControllerKlasse.LieferantDB(cn);
			ein.close();
			break;
		case "LieferungDB":
			ControllerKlasse.LieferungDB(cn);
			ein.close();
		    break;
		default:
			System.out.println("==============Falsche eingabe===============");
			ein.close();
		
			
		}
	}
//Ausgabe Methoden von Lieferung
	public static void findMitLnrLieferungDB(Connection cn, List<LieferungDTO> li) throws SQLException {
		for(LieferungDTO L:li) {
		System.out.println(L.toString());
	}        
			        
		
		
	}

	public static void anlegenLieferung(Connection cn, int b) {
		if(b==1) {
			System.out.println("====Neuer Datensatz angelegt======");
		}
		else{
			System.out.println("=====kein neuer Datensatz wurde angelegt=======");
		}
		
	}

	public static void aktualisierenLieferung(Connection cn, int erg) {
		if(erg==1) {
			System.out.println("==== Datensatz wurde aktualisiert======");
		}
		else{
			System.out.println("=====keine Aktualisierung wurde Durchgeführt=======");
		}
	}

	public static void loeschenLieferung(Connection cn, int a) {
		
		if(a==1) {
			System.out.println("==== Die entsprechende Lieferung wurde geloescht======");
		}
		else{
			System.out.println("=====keine Loescherung Durchgeführt=======");
		}
	}

	public static void findMitAnrLieferung(Connection cn, List<LieferungDTO> li1) {
		
		for(LieferungDTO L:li1) {
			System.out.println(L.toString());
		}        
	}
//Ausgabe-Methoden von Lieferant
	public static void anlegenLieferant(Connection cn, int a) {
		if(a==1) {
			System.out.println("============Neuer Lieferant angelegt============");
		}
		else {
			System.out.println("=====================kein neuer Lieferant angelegt=============");
		}
		
	}

	public static void aktualisierenLieferant(Connection cn, int b) {
		if(b==1) {
			System.out.println("============Lieferant wurde aktualisiert============");
		}
		else {
			System.out.println("=====================Lieferant wurde nicht aktualisiert=============");
		}
		
	}

	public static void loeschenLieferant(Connection cn, int c) {
		
		if(c==1) {
			System.out.println("============Lieferant wurde geloescht============");
		}
		else {
			System.out.println("=====================Lieferant wurde nicht geloescht=============");
		}
	}

	public static void findAllLieferant(Connection cn, List<LieferantDTO> li) {
		for(LieferantDTO L:li) {
      System.out.println( L.toString());
      for(int i=0;i<L.getList().size();i++){
			
    	 System.out.println( L.getList().get(i).toString());
		}
			System.out.println("===========================================================================");
			
		}
		
	}

	public static void findMitLnrLieferant(Connection cn, LieferantDTO l3) {
		System.out.println(l3.toString());;
		for(int i=0;i<l3.getList().size();i++){
			
	    	 System.out.println( l3.getList().get(i).toString());
			}
		System.out.println("===========================================================================");
		
	}

	public static void findBetweenLieferant(Connection cn, List<LieferantDTO> l4) {
		for(LieferantDTO L:l4) {
			 System.out.println( L.toString());
		      for(int i=0;i<L.getList().size();i++){
					
		    	 System.out.println( L.getList().get(i).toString());
				}
		      System.out.println("===========================================================================");
					
				}
		
	}
//Ausgabe-Methoden von Artikel
	public static void anlegenArtikel(Connection cn, int a) {
		if(a==1)
			System.out.println("neuer Artikel angelegt");
		else
			System.out.println("kein neuer Artikel angelegt");
	}

	public static void aktualisierenArtikel(Connection cn, int b) {
		if(b==1)
			System.out.println(" Artikel wurde aktualisiert");
		else
			System.out.println("kein Aktualisierung wurde Durchgefuhrt");
		
	}

	public static void loeschenArtikel(Connection cn, int c) {
		if(c==1)
			System.out.println(" Artikel wurde geloescht");
		else
			System.out.println("Artikel wurde nicht geloescht");
	}

	public static void findArtArtikel(Connection cn, List<ArtikelDTO> list) {
	for(ArtikelDTO A:list) {
		System.out.println(A.toString());
		 
		 for(int i=0;i<A.getList().size();i++) {
			System.out.println( A.getList().get(i).toString());
		  }
		 System.out.println("===========================================================================");
	}
		
	}

	public static void findMitAnrArtikel(Connection cn, ArtikelDTO a3) {
		System.out.println(a3.toString());
		 for(int i=0;i<a3.getList().size();i++) {
				System.out.println( a3.getList().get(i).toString());
			  }
		 System.out.println("===========================================================================");
	}

	public static void aktuListArtikel(Connection cn, List<ArtikelDTO> ergebnis) {
		for(ArtikelDTO A:ergebnis) {
			System.out.println(A.toString());
			 
			 for(int i=0;i<A.getList().size();i++) {
				System.out.println( A.getList().get(i).toString());
			  }
			 System.out.println("===========================================================================");
		}
		
	}

	
		
	

	

}
