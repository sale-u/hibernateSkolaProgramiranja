package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
//@Table(name = "tabela_smer")
public class Smer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSmer;
//	@Column(name = "naziv_smera")
	private String nazivSmera;
	
	@OneToOne(cascade = CascadeType.ALL) // pa nam nece trebati poseban sesija.save(smerDetails)
	private SmerDetails details;			// jer ce na svaki saveOrUpdate(smer) uraditi i kaskadno cuvanje pridruzenog objekta smerdetails

	// mappedBy = "smer", 				// ako se doda mappedBy onda nece praviti visak veznu tabelu smer_student
	@OneToMany(mappedBy = "smer", fetch = FetchType.LAZY)	// dogovor je da moze biti vise studenata na jednom smeru
	private List<Student> listaStudenata = new ArrayList<>();  // ali da student moze biti samo na jednom smeru
	// zato na Smeru imamo listu Studenata i relacija Smer->Student je @OneToMany
	
	@ManyToMany(mappedBy = "listaSmerova", fetch = FetchType.LAZY) 						// 
	private List<Predmet> listaPredmetaNaSmeru = new ArrayList<>();
	
	// =====================================================================================
	
	public List<Student> getListaStudenata() {
		return listaStudenata;
	}
	public void setListaStudenata(List<Student> listaStudenata) {
		this.listaStudenata = listaStudenata;
	}
	public SmerDetails getDetails() {
		return details;
	}
	public void setDetails(SmerDetails details) {
		this.details = details;
	}
	public int getIdSmer() {
		return idSmer;
	}
	public void setIdSmer(int idSmer) {
		this.idSmer = idSmer;
	}
	public String getNazivSmera() {
		return nazivSmera;
	}
	public void setNazivSmera(String nazivSmera) {
		this.nazivSmera = nazivSmera;
	}
	public List<Predmet> getListaPredmetaNaSmeru() {
		return listaPredmetaNaSmeru;
	}
	public void setListaPredmetaNaSmeru(List<Predmet> listaPredmetaNaSmeru) {
		this.listaPredmetaNaSmeru = listaPredmetaNaSmeru;
	}
	
}
