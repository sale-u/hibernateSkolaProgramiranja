package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Predmet {
	
//	Jedan Predmet moze biti na vise Smerova, ali i jedan Smer moze imati vise predmeta.
//	Tako da nam je Smer -> Predmet relacija @OneToMany
//	ali nam je i Predmet -> Smer relacija takodje @OneToMany
//	sto se svodi da je relacija @ManyToMany izmedju entiteta Smer i Predmet.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPredmet;			// PK AI
	private String nazivPredmeta;
	private int brojPoena;
	@ManyToMany (fetch = FetchType.LAZY)				// (mappedBy = "listaPredmetaNaSmeru")
	private List<Smer> listaSmerova = new ArrayList<>();
	
	// =================================================================
	
	public int getIdPredmet() {
		return idPredmet;
	}
	public void setIdPredmet(int idPredmet) {
		this.idPredmet = idPredmet;
	}
	public String getNazivPredmeta() {
		return nazivPredmeta;
	}
	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}
	public int getBrojPoena() {
		return brojPoena;
	}
	public void setBrojPoena(int brojPoena) {
		this.brojPoena = brojPoena;
	}
	public List<Smer> getListaSmerova() {
		return listaSmerova;
	}
	public void setListaSmerova(List<Smer> listaSmerova) {
		this.listaSmerova = listaSmerova;
	}
	

	
	

}
