package slozeniModeli;

import model.Finansije;

public class StudentSmerDetailsHQL {
	
	private String imeStudenta;
	private String prezimeStudenta;
	private String brojIndexa;
	private String drzava;
	private Finansije finansije;	// ovo u varijanti HQL mora biti klase Finansije (a ne int kao kod Native SQLa)
	private String smer;
	private double poeni;
	
	
	public String getImeStudenta() {
		return imeStudenta;
	}
	public void setImeStudenta(String imeStudenta) {
		this.imeStudenta = imeStudenta;
	}
	public String getPrezimeStudenta() {
		return prezimeStudenta;
	}
	public void setPrezimeStudenta(String prezimeStudenta) {
		this.prezimeStudenta = prezimeStudenta;
	}
	public String getBrojIndexa() {
		return brojIndexa;
	}
	public void setBrojIndexa(String brojIndexa) {
		this.brojIndexa = brojIndexa;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	public Finansije getFinansije() {
		return finansije;
	}
	public void setFinansije(Finansije finansije) {
		this.finansije = finansije;
	}
	public String getSmer() {
		return smer;
	}
	public void setSmer(String smer) {
		this.smer = smer;
	}
	public double getPoeni() {
		return poeni;
	}
	public void setPoeni(double poeni) {
		this.poeni = poeni;
	}
	
	

}
