package model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("Vanredni")
public class VanredniPredavac extends Predavac {
	
	private int brojPoslova;	// vanredni moze da predaje na vise fakulteta
	
	// =============================================================================

	public int getBrojPoslova() {
		return brojPoslova;
	}

	public void setBrojPoslova(int brojPoslova) {
		this.brojPoslova = brojPoslova;
	}

}
