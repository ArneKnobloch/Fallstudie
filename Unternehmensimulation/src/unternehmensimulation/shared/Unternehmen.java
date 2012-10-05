package unternehmensimulation.shared;

import com.google.gwt.user.client.Window;

public class Unternehmen {
	
	private String NameUN;
	private int personal, kapital, qualitaet, kapazitaet;

	public Unternehmen(String NameUN, int personal, int kapital, int qualitaet, int kapazitaet) {
		NameUN=this.getNameUN();
		personal=this.getPersonal();
		kapital=this.getKapital();
		qualitaet=this.getQualitaet();
		kapazitaet=this.getKapazitaet();
		Window.alert("Hallo das sind die Werte" + personal + kapital);
		
	}
	public String getNameUN() {
		return NameUN;
	}

	public void setNameUN(String nameUN) {
		NameUN = nameUN;
	}
	

	public int getPersonal() {
		return personal;
	}

	public void setPersonal(int personal) {
		this.personal = personal;
	}

	public int getKapital() {
		return kapital;
	}

	public void setKapital(int kapital) {
		this.kapital = kapital;
	}
	public int getQualitaet() {
		return qualitaet;
	}
	public void setQualitaet(int qualitaet) {
		this.qualitaet = qualitaet;
	}
	public int getKapazitaet() {
		return kapazitaet;
	}
	public void setKapazitaet(int kapazitaet) {
		this.kapazitaet = kapazitaet;
	}
	public int einstellen(int anzahl)
	{
		personal=personal+anzahl;
		return personal;
	}
	

}
