import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LieferungDB {

	public static int anlegen(Connection cn,LieferungDTO L)throws Exception {
		String sql="INSERT INTO Lieferung(LNr, ANr, Preis)"+ " VALUES (?, ?, ?)";
		try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
			pstmt.setInt(1, L.getLnr());
	      	pstmt.setInt(2, L.getAnr());
	      	pstmt.setBigDecimal(3, L.getPreis());
	      	return pstmt.executeUpdate();
		}
	}
	public static int aktualisieren(Connection cn,LieferungDTO L)throws Exception {
		String sql = "UPDATE Lieferung SET Preis=? WHERE LNr=? AND ANR=?";
		try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
		pstmt.setBigDecimal(1, L.getPreis());
		pstmt.setInt(2, L.getLnr());
		pstmt.setInt(3, L.getAnr());
		return pstmt.executeUpdate();
		}
		
	}
	public static int loeschen(Connection cn,int lNr,int aNr)throws Exception {
		String sql="Delete  from Lieferung Where LNr=? AND ANr=?";
		try(PreparedStatement pstmt=cn.prepareStatement(sql)){
			pstmt.setInt(1, lNr);
			pstmt.setInt(2, aNr);
			
		return pstmt.executeUpdate();
	}
}
	public static int loeschenMitAnr(Connection cn,int aNr) throws SQLException {//Hilfsmethode zum Loeschen eines bestimmten Lieferenten oder Datensatzes
		String sql="Delete  from Lieferung Where ANr=? ";
		try(PreparedStatement pstmt=cn.prepareStatement(sql)){
			pstmt.setInt(1, aNr);
			
		return pstmt.executeUpdate();
	}
	}
	public static int loeschenMitLnr(Connection cn,int lNr) throws SQLException {//Hilfsmethode zum Loeschen eines bestimmten Lieferenten oder Datensatzes
		String sql="Delete  from Lieferung Where LNr=? ";
		try(PreparedStatement pstmt=cn.prepareStatement(sql)){
			pstmt.setInt(1, lNr);
			
			
		return pstmt.executeUpdate();
	}
	}
	public static List<LieferungDTO> findMitLnr(Connection cn,int lNr)throws Exception {
		String sql="select * from Lieferung Where LNr=?";
		try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
			pstmt.setInt(1, lNr);
			try (ResultSet rs = pstmt.executeQuery()) {
				List<LieferungDTO> li= new ArrayList<>();
			while(rs.next()) {
              li.add(new LieferungDTO(rs));
		
			}
			return li;
			
			}
			}
	
		}
	public static List<LieferungDTO> findMitAnr(Connection cn,int aNr)throws Exception {
		String sql="select * from Lieferung Where ANr=?";
		List<LieferungDTO > list=new ArrayList<>();
		try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
			pstmt.setInt(1, aNr);
			try (ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				list.add(new LieferungDTO(rs));
			}
			return list;
			}
			}
		}
}
