
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArtikelDB {
    public static int anlegen(Connection cn,ArtikelDTO A)throws Exception {
	String sql="INSERT INTO Artikel (ANr, Bezeichnung, Preis,Angelegt)"+ " VALUES (?, ?, ? , ?)";
	try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
		pstmt.setInt(1, A.getAnr());
      	pstmt.setString(2,A.getBezeichnung() );
      	pstmt.setBigDecimal(3, A.getPreis());
      	pstmt.setDate(4, A.getAngelegt());
      	return pstmt.executeUpdate();
	}
    }
	public static int aktualisieren(Connection cn , ArtikelDTO ar ) throws Exception {
		String sql = "UPDATE Artikel SET Bezeichnung=?, Preis=?, Angelegt=? WHERE ANr=?";
		try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
			pstmt.setString(1,ar.getBezeichnung() );
			pstmt.setBigDecimal(2,ar.getPreis() );
			pstmt.setDate(3,ar.getAngelegt() );
			pstmt.setInt(4, ar.getAnr());
			return pstmt.executeUpdate();
			}
	}
	public static int loeschen(Connection cn,int aNr) throws SQLException {
		String sql="Delete  from Artikel Where ANr=?";
		try(PreparedStatement pstmt=cn.prepareStatement(sql)){
			pstmt.setInt(1,aNr);
			LieferungDB.loeschenMitAnr(cn, aNr);//wenn ein Artikel geloescht ist, wird er auch in der entsprechenden Lieferung geloescht
		return pstmt.executeUpdate();
			
		}
	}
	public static ArtikelDTO findMitAnr(Connection cn ,int aNr) throws Exception {
		String sql="select * from Artikel Where ANr=?";
		
		try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
			pstmt.setInt(1, aNr);
			try (ResultSet rs = pstmt.executeQuery()) {
			if(rs.next()) {
				ArtikelDTO A=new ArtikelDTO(rs);
				A.setList(LieferungDB.findMitAnr(cn, A.getAnr()));//Artikel und Lieferung
				return A;
			}
		
			
			}
			
	}
		return null;

}
	public static List<ArtikelDTO> findArt(Connection cn ,BigDecimal preis)throws Exception{
		String sql="select * from Artikel Where Preis >?" ;
		
		List<ArtikelDTO > list=new ArrayList<>();
		try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
		 pstmt.setBigDecimal(1, preis);
			try (ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
			ArtikelDTO A=new ArtikelDTO(rs);
			A.setList(LieferungDB.findMitAnr(cn, A.getAnr()));//Artikel und Lieferung
				list.add(A);
			}
			return list;
			}
			}
	}
	public static List<ArtikelDTO> aktuList(Connection cn,List<ArtikelDTO> li){
	Collections.sort(li);
		
		return li;
	}
	
}

