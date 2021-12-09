package model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("Redovni")
public class RedovniPredavac extends Predavac {
	
	private int brojNaucnihRadova;

	// =============================================================================
	
	public int getBrojNaucnihRadova() {
		return brojNaucnihRadova;
	}

	public void setBrojNaucnihRadova(int brojNaucnihRadova) {
		this.brojNaucnihRadova = brojNaucnihRadova;
	}

}
