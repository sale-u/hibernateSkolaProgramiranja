package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idStudent;
	private String ime;
	private String prezime;
	private String brojIndexa;
	
	@Embedded
	private Adresa adresa;	// Klasa Student ima referencu prema Adresa objektu.
							// Ali to ne znaci da ce Hibernate napraviti posebnu tabelu Adresa (Adresa nije entitet niti ima smisla sama za sebe)
							// jer anotacijom @Embedded je receno da ce polja klase Adresa
							// biti dodatne kolone tabele Student.
	
	@Enumerated
	private Finansije finansije;	// enum podatak
	
	@ElementCollection(fetch = FetchType.EAGER)	// po defaultu FetchType je LAZY, a moze da se stavi i EAGER
	@JoinTable( name = "KONTAKTI", 
	joinColumns = @JoinColumn(name = "STUDENT_ID"))
	private List<Contact> contact = new ArrayList<>();	// List podataka prema posebnoj tabeli koja NIJE entitet

	@ManyToOne			// dogovor je da moze biti vise studenata na jednom smeru
	private Smer smer;	// ali da student moze biti samo na jednom smeru -> zato je @ManyToOne

	// ===============================================================================
	
	public Smer getSmer() {
		return smer;
	}
	public void setSmer(Smer smer) {
		this.smer = smer;
	}
	public List<Contact> getContact() {
		return contact;
	}
	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}
	public int getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getBrojIndexa() {
		return brojIndexa;
	}
	public void setBrojIndexa(String brojIndexa) {
		this.brojIndexa = brojIndexa;
	}
	public Adresa getAdresa() {
		return adresa;
	}
	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}
	public Finansije getFinansije() {
		return finansije;
	}
	public void setFinansije(Finansije finansije) {
		this.finansije = finansije;
	}

	
}
