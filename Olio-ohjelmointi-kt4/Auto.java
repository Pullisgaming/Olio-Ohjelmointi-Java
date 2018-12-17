package kt4;

public class Auto{
	private String autonMerkki;
	private String autonMalli;
	private String autonMoottori;	
	
	public String toString() {
		return "Auto [autonMerkki=" +autonMerkki + ", autonMalli=" + autonMalli + ", autonMoottori=" + autonMoottori + "]";
	}
	public String getAutonMerkki() {
		return autonMerkki;
	}
	public void setAutonMerkki(String autonMerkki) {
		this.autonMerkki = autonMerkki;
	}
	public String getAutonMalli() {
		return autonMalli;
	}
	public void setAutonMalli(String autonMalli) {
		this.autonMalli = autonMalli;
	}
	public String getAutonMoottori() {
		return autonMoottori;
	}
	public void setAutonMoottori(String autonMoottori) {
		this.autonMoottori = autonMoottori;
	}	
	
	public Auto(String autonMerkki, String autonMalli, String autonMoottori) {
		this.autonMerkki = autonMerkki;
		this.autonMalli = autonMalli;
		this.autonMoottori = autonMoottori;
	}	
}