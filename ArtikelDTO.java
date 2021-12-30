import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtikelDTO implements Serializable ,Comparable<ArtikelDTO>{
	private int anr;
	private String bezeichnung;
	private BigDecimal preis;
	private Date  angelegt;
	private List<LieferungDTO> list=new ArrayList<>();
	public ArtikelDTO() {
		
	}
	public  ArtikelDTO(ResultSet rs) throws SQLException {
		this.setAnr(rs.getInt("ANr"));
		this.setBezeichnung(rs.getString("Bezeichnung"));
		this.setPreis(rs.getBigDecimal("Preis"));
		this.setAngelegt(rs.getDate("Angelegt"));
		
		
	}
public ArtikelDTO(int anr, String bezeichnung, BigDecimal preis,Date angelegt) {
		
		this.anr = anr;
		this.bezeichnung = bezeichnung;
		this.preis = preis;
		this.angelegt = angelegt;
	}
	public int getAnr() {
		return anr;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public BigDecimal getPreis() {
	return preis;
	}
	public Date  getAngelegt() {
		return  angelegt;
	}
	public List<LieferungDTO> getList() {
		return list;
	}
	public void setAnr(int anr) {
		this.anr = anr;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}
	public void setAngelegt(Date  date) {
		this.angelegt = date;
	}
	public void setList(List<LieferungDTO> list) {
		this.list = list;
	}

	public String toString() {
  return ("ArtikelDTO [Anr=" + anr + "; Bezeichnung=" + bezeichnung + "; Preis=" + preis + "; Angelegt=" + angelegt+ "]");


	}
	@Override
	public int compareTo(ArtikelDTO other) {
		if(this.getAnr() > other.getAnr())
			return 1;
		else if(this.getAnr() < other.getAnr())
			return -1;
		else
        return 0;
	}
	

}
