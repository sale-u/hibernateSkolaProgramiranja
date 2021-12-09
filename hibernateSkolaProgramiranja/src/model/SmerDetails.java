package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SmerDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSmerDetails;
	private String opisSmera;
	private double minimalanBrojPoenaZaUpis;
	private double cenaZaSamofinansirajuce;
	
	public int getIdSmerDetails() {
		return idSmerDetails;
	}
	public void setIdSmerDetails(int idSmerDetails) {
		this.idSmerDetails = idSmerDetails;
	}
	public String getOpisSmera() {
		return opisSmera;
	}
	public void setOpisSmera(String opisSmera) {
		this.opisSmera = opisSmera;
	}
	public double getMinimalanBrojPoenaZaUpis() {
		return minimalanBrojPoenaZaUpis;
	}
	public void setMinimalanBrojPoenaZaUpis(double minimalanBrojPoenaZaUpis) {
		this.minimalanBrojPoenaZaUpis = minimalanBrojPoenaZaUpis;
	}
	public double getCenaZaSamofinansirajuce() {
		return cenaZaSamofinansirajuce;
	}
	public void setCenaZaSamofinansirajuce(double cenaZaSamofinansirajuce) {
		this.cenaZaSamofinansirajuce = cenaZaSamofinansirajuce;
	}
	
	

}
