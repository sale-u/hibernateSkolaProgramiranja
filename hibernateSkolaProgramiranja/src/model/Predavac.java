package model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name = "tip_predavaca", discriminatorType = DiscriminatorType.STRING)
public class Predavac {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int idPredavac;
	private String imePredavaca;
	private String prezimePredavaca;
	
	// =======================================================
	
	public int getIdPredavac() {
		return idPredavac;
	}
	public void setIdPredavac(int idPredavac) {
		this.idPredavac = idPredavac;
	}
	public String getImePredavaca() {
		return imePredavaca;
	}
	public void setImePredavaca(String imePredavaca) {
		this.imePredavaca = imePredavaca;
	}
	public String getPrezimePredavaca() {
		return prezimePredavaca;
	}
	public void setPrezimePredavaca(String prezimePredavaca) {
		this.prezimePredavaca = prezimePredavaca;
	}


	
	
	
}
