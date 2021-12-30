import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LieferantDTO implements Serializable{
    
	private int lnr;
	private String name;
	private String plz;
	private List<LieferungDTO> list=new ArrayList<>();
	public LieferantDTO() {
		
	}
public LieferantDTO(int lnr, String name, String plz) {
		
		this.lnr = lnr;
		this.name = name;
		this.plz = plz;
	}
public LieferantDTO(ResultSet rs) throws SQLException {
	this.setLnr(rs.getInt("LNr"));
	this.setName(rs.getString("Name"));
	this.setPlz(rs.getString("PLZ"));
}
	public int getLnr() {
		return lnr;
	}
	public String getName() {
		return name;
	}
	public String getPlz() {
		return plz;
	}
	public List<LieferungDTO> getList() {
		return list;
	}
	public void setLnr(int lnr) {
		this.lnr = lnr;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public void setList(List<LieferungDTO> list) {
		this.list = list;
	}
	
	
	public String toString() {
		return( "LieferantDTO [Lnr=" + lnr + "; name=" + name + ";Plz=" + plz + "]");



	}

}
