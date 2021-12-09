package dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Adresa;
import model.Finansije;
import model.Predavac;
import model.Predmet;
import model.RedovniPredavac;
import model.Smer;
import model.SmerDetails;
import model.Student;
import model.VanredniPredavac;

import java.util.ArrayList;
import java.util.List;
import model.Contact;

public class CrudDao {

	// CRUD - Create/Insert Read/Select Update Delete operacije
	SessionFactory factory = new Configuration().configure().buildSessionFactory();

	public void insertSmer(String nazivSmera) {

		Smer smer = new Smer(); // za insertovanje jednog objekta u bazi prvo napravimo instancu klase Smer
		smer.setNazivSmera(nazivSmera);

		// otvaranje sesije
		Session sesija = factory.openSession();
		// zapocinjemo transakciju koja moze da se zavrsi uspesno (try blok-commit) ili
		// neuspesno (catch blok-rollback)
		sesija.beginTransaction();

		try {
			// radimo insert
			int idd = (int) sesija.save(smer);
			// save() vraca novo generisanu vrednost id tj, Serializable objekat koji se
			// moze castovati u int
//			System.out.println("Vracen ID = " + idd);
			System.out.println("Upisan smer " + smer.getNazivSmera() + " u bazu pod id = " + idd);

			// zatvaram transakciju USPESNO
			sesija.getTransaction().commit();

		} catch (Exception e) {
			// zatvaram transakciju NEUSPESNO
			sesija.getTransaction().rollback();

		} finally {
			// zatvaranje sesije
			sesija.close();
		}
	}

	public Smer vratiSmerPoId(int id) {

		Smer smer = null;

		Session sesija = factory.openSession();
		sesija.beginTransaction();

		try {
			// select * from smer where idSmer = id
			smer = sesija.get(Smer.class, id);
			sesija.getTransaction().commit();
			return smer;
		} catch (Exception e) {
			System.out.println("Bacen je exception");
			sesija.getTransaction().rollback();
			return null;
		} finally {
			sesija.close();
		}

	}

	public boolean updateNazivSmera(String noviNazivSmera, int idSmer) {

		Session sesija = factory.openSession();
		sesija.beginTransaction();

		try {
			// select * from smer where idSmer = id
			Smer smer = sesija.get(Smer.class, idSmer);
			if (smer != null) {
				// na izvucenom objektu iz baze, izmenimo zeljeni atribut nazivSmera
				smer.setNazivSmera(noviNazivSmera);
				sesija.update(smer); // azurirana instanca objekta ide nazad u bazu
				sesija.getTransaction().commit();
				return true;
			} else {
				sesija.getTransaction().rollback();
				return false;
			}
		} catch (Exception e) {
			System.out.println("Update je izleteo u Exception :" + e.getMessage());
			sesija.getTransaction().rollback();
			return false;
		} finally {
			sesija.close();
		}

	}

	public boolean deleteSmer(int idSmer) {

		Session sesija = factory.openSession();
		sesija.beginTransaction();

		try {
			// select * from smer where idSmer = id
			Smer smer = sesija.get(Smer.class, idSmer);
			// delete
			// Ako ga nije nasao (vracen null) ovaj delete() ce izleteti u Exception
			sesija.delete(smer); // brise iz baze
			sesija.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Delete je izleteo u Exception :" + e.getMessage());
			sesija.getTransaction().rollback();
			return false;
		} finally {
			sesija.close();
		}

	}

	// ******************** 16.11.2021 ***********************

	public void insertStudent(String imeStudenta, String prezimeStudenta, String brojIndexa, Adresa adresa,
			Finansije finansije, List<Contact> kontakti) {

		Student student = new Student();
		student.setIme(imeStudenta);
		student.setPrezime(prezimeStudenta);
		student.setBrojIndexa(brojIndexa);

//		Adresa adresa = new Adresa();
//		adresa.setDrzava(drzava);
//		adresa.setGrad(grad);
//		adresa.setUlica(ulica);
//		adresa.setPostanskiBroj(postanskiBroj);

		student.setAdresa(adresa);
		student.setContact(kontakti);
		student.setFinansije(finansije);

		// otvaranje sesije
		Session sesija = factory.openSession();
		// zapocinjemo transakciju koja moze da se zavrsi uspesno (try blok-commit) ili
		// neuspesno (catch blok-rollback)
		sesija.beginTransaction();

		try {
			// radimo insert
			int idd = (int) sesija.save(student);
			// save() vraca novo generisanu vrednost id tj, Serializable objekat koji se
			// moze castovati u int
//			System.out.println("Vracen ID = " + idd);
			System.out.println("Upisan student " + student.getBrojIndexa());

			// zatvaram transakciju USPESNO
			sesija.getTransaction().commit();

		} catch (Exception e) {
			// zatvaram transakciju NEUSPESNO
			sesija.getTransaction().rollback();

		} finally {
			// zatvaranje sesije
			sesija.close();
		}
	}

	public Student vratiStudentaPoId(int id) {

		Student student = null;

		Session sesija = factory.openSession();
		sesija.beginTransaction();

		try {
			// select * from smer where idSmer = id
			student = sesija.get(Student.class, id);
//			Hibernate.initialize(student.getContact());  // rucno forsiramo ucitavanje ove liste
			// ili da kod polja Student.contact stavimo fetch = FetchType.EAGER
			sesija.getTransaction().commit();
			return student;
		} catch (Exception e) {
			System.out.println("Bacen je exception");
			sesija.getTransaction().rollback();
			return null;
		} finally {
			sesija.close();
		}

	}

	// ******************* 23.11.2021 ********************************

	public void insertSmerDetails(int idSmer, String opis, double minBrojPoena, double cena) {

		SmerDetails details = new SmerDetails();
		details.setOpisSmera(opis);
		details.setMinimalanBrojPoenaZaUpis(minBrojPoena);
		details.setCenaZaSamofinansirajuce(cena);

		Session sesija = factory.openSession();
		sesija.beginTransaction();

		try {
			Smer smer = sesija.get(Smer.class, idSmer); // vadimo konkretan smer iz baze kako bi mu dodeli smerDetails
			smer.setDetails(details); // i sada goredefinisani objekat details ubacujemo u smer objekat

			// sledecu liniju izbacujemo jer smo stavili cascade = CascadeType.ALL ili MERGE
			// sesija.saveOrUpdate(details); // prvo moramo details objekat da ucinimo
			// perzistentnim

			sesija.saveOrUpdate(smer); // ovo generalno nije potrebno ali stavljamo zbog pune kontrole
			sesija.getTransaction().commit();
			System.out.println("Uspesno azuriran smer : " + smer.getNazivSmera());
		} catch (Exception e) {
			sesija.getTransaction().rollback();
			System.out.println("Neuspesno azuriran smer");
		} finally {
			sesija.close();
		}
	}

	public void spojiStudentaISmer(int idStudent, int idSmer) {

		// povezujemo idStudenta i idSmera kroz posebnu veznu tabelu smer_student
		// koju ce Hibernate automatski da napravi

		Session sesija = factory.openSession();
		sesija.beginTransaction();

		try {
			Student student = sesija.get(Student.class, idStudent); // dovlacimo Studenta po id iz baze
			Smer smer = sesija.get(Smer.class, idSmer); // dovlacimo Smer po id iz baze

			student.setSmer(smer); // studentu dodeljujemo odgovarajuci smer
			Hibernate.initialize(smer.getListaStudenata()); // na smeru imamo listu studenata ciji je dflt fetch = LAZY
			// pa se mora dodati Hibernate.Initialize...kako bi sledeca linija radila bez
			// bacanja exceptiona
			smer.getListaStudenata().add(student); // na smeru, u listi studenata dodajemo jos jednog studenta

			sesija.getTransaction().commit();
			System.out.println("Povezani su student i smer...");
		} catch (Exception e) {
			System.out.println("Bacen je exception: " + e.getMessage());
			sesija.getTransaction().rollback();
		} finally {
			sesija.close();
		}
	}
	
	// ********************** 25.11.2021 **************************

	public void insertPredmet(String nazivPredmeta, int brojPoena) {

		Predmet predmet = new Predmet();
		predmet.setNazivPredmeta(nazivPredmeta);
		predmet.setBrojPoena(brojPoena);

		Session sesija = factory.openSession();
		sesija.beginTransaction();

		try {
			// radimo insert
			sesija.persist(predmet);
			System.out.println("Upisan predmet " + predmet.getNazivPredmeta());
			sesija.getTransaction().commit();

		} catch (Exception e) {
			// zatvaram transakciju NEUSPESNO
			System.out.println("Puklo je");
			sesija.getTransaction().rollback();

		} finally {
			// zatvaranje sesije
			sesija.close();
		}
	}

	public void spojiPredmetISmer(int idPredmet, int idSmer) {

		Session sesija = factory.openSession();
		sesija.beginTransaction();

		try {
			Predmet predmet = sesija.get(Predmet.class, idPredmet);
			Smer smer = sesija.get(Smer.class, idSmer);
			Hibernate.initialize(predmet.getListaSmerova());

			predmet.getListaSmerova().add(smer);

			sesija.saveOrUpdate(predmet);

			System.out.println("Povezani su predmet i smer...");
			sesija.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Bacen je exception: " + e.getMessage());
			sesija.getTransaction().rollback();
		} finally {
			sesija.close();
		}
	}

	public void insertPredavac(String imePredavaca, String prezimePredavaca,
			int brojNaucnihRadova, int brojPoslova)  {

		Predavac predavac = new Predavac();
		predavac.setImePredavaca(imePredavaca);
		predavac.setPrezimePredavaca(prezimePredavaca);
		
		RedovniPredavac redovni = new RedovniPredavac();
		redovni.setImePredavaca("Redovni " + imePredavaca);
		redovni.setPrezimePredavaca("Redovni " + prezimePredavaca);
		redovni.setBrojNaucnihRadova(brojNaucnihRadova);
		
		VanredniPredavac vanredni = new VanredniPredavac();
		vanredni.setImePredavaca("Vanredni " + imePredavaca);
		vanredni.setPrezimePredavaca("Vanredni " + prezimePredavaca);
		vanredni.setBrojPoslova(brojPoslova);

		Session sesija = factory.openSession();
		sesija.beginTransaction();

		try {
			// radimo insert
			sesija.save(predavac);
			System.out.println("Upisan predavac " + predavac.getImePredavaca());
			sesija.save(redovni);
			System.out.println("Upisan redovni predavac " + redovni.getImePredavaca());
			sesija.save(vanredni);
			System.out.println("Upisan vanredni predavac " + vanredni.getImePredavaca());
			sesija.getTransaction().commit();

		} catch (Exception e) {
			// zatvaram transakciju NEUSPESNO
			sesija.getTransaction().rollback();
			System.out.println("Puklo je : " + e.getMessage());
		} finally {
			// zatvaranje sesije
			sesija.close();
		}
	}

}
