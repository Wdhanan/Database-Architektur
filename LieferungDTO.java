import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LieferungDTO implements Serializable{

   private int lnr;
	private int anr;
	private BigDecimal preis;
	
	public LieferungDTO() {
		
	}
	
	public LieferungDTO(ResultSet rs) throws SQLException {
		this.setLnr(rs.getInt("Lnr"));
		this.setAnr(rs.getInt("Anr"));
		this.setPreis(rs.getBigDecimal("Preis"));

	}
public LieferungDTO(int lnr, int anr, BigDecimal preis) {
		
		this.lnr = lnr;
		this.anr = anr;
		this.preis = preis;
	}
	public void setLnr(int lnr) {
		this.lnr = lnr;
	}
	public void setAnr(int anr) {
		this.anr = anr;
	}
	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}
	public int getLnr() {
		return lnr;
	}
	public int getAnr() {
		return anr;
	}
	public BigDecimal getPreis() {
		return preis;
	}

	
	public String toString() {
		return( "LieferungDTO [Lnr=" + lnr + "; Anr=" + anr + ";Preis=" + preis + "]");
	}

}
