import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LieferantDB {

public static int anlegen(Connection cn,LieferantDTO L)throws Exception {
	String sql="INSERT INTO Lieferant(LNr, Name, PLZ)"+ " VALUES (?, ?, ?)";
	try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
		pstmt.setInt(1, L.getLnr());
      	pstmt.setString(2, L.getName());
      	pstmt.setString(3, L.getPlz());
      	return pstmt.executeUpdate();
	}
	
}
public static int aktualisieren(Connection cn,LieferantDTO L)throws Exception {
	String sql = "UPDATE Lieferant SET Name=?, PLZ=? WHERE LNr=?";
	try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
	pstmt.setString(1, L.getName());
	pstmt.setString(2, L.getPlz());
	pstmt.setInt(3, L.getLnr());
	return pstmt.executeUpdate();
	}
}
public static int loeschen(Connection cn,int lNr)throws Exception {
	String sql="Delete  from Lieferant Where LNr=?";
	try(PreparedStatement pstmt=cn.prepareStatement(sql)){
		pstmt.setInt(1, lNr);
		LieferungDB.loeschenMitLnr(cn, lNr);//wenn ein Lieferant geloescht wird,wird er auch in der entsprechenden Lieferung geloescht
	return pstmt.executeUpdate();
		
	}
}
public static List<LieferantDTO> findAll(Connection cn)throws Exception{
	String sql="SELECT * from Lieferant";
	List<LieferantDTO>list=new ArrayList<>();
	try(Statement stmt=cn.createStatement(); 
			ResultSet rs=stmt.executeQuery(sql)){
		while(rs.next()) {
			LieferantDTO L=new LieferantDTO(rs);
			L.setList(LieferungDB.findMitLnr(cn, L.getLnr()));//Lieferant und Lieferung
			list.add(L);
			
		}
		return list;
	}
}
public static LieferantDTO findMitLnr(Connection cn,int lNr)throws Exception {
	String sql="select * from Lieferant Where LNr=?";
	
	try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
		pstmt.setInt(1, lNr);
		try (ResultSet rs = pstmt.executeQuery()) {
		if(rs.next()) {
			LieferantDTO L=new LieferantDTO(rs);
			L.setList(LieferungDB.findMitLnr(cn, L.getLnr()));//Lieferant und Lieferung
			return L;
			
		}
		}
		}
	return null;
}
public static List<LieferantDTO>findBetween(Connection cn,int lNr1,int lNr2)throws Exception{
	 String sql="select * from Lieferant where LNr between?and?";
	 List<LieferantDTO>list=new ArrayList<LieferantDTO>();
	
		try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
			pstmt.setInt(1, lNr1);
			pstmt.setInt(2, lNr2);
			try (ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				LieferantDTO L=new LieferantDTO(rs);
				L.setList(LieferungDB.findMitLnr(cn, L.getLnr()));//Lieferant und Lieferung
				list.add(L);
			}
			return list;
			
			}
			}
}
}
