package slozeniModeli;

import model.Finansije;

public class StudentSmerDetailsHQL {
	
	private String imeStudenta;
	private String prezimeStudenta;
	private String brojIndexa;
	private String drzava;
	private String finansije;	// ovo u varijanti HQL mora biti String kako bi radilo OK (u Native SQL varijanti, ovde je integer)
	// vidi objasnjenje predavaca od 16.12.21
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
	public String getFinansije() {
		return finansije;
	}
	public void setFinansije(String finansije) {
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
