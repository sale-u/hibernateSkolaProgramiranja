package controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Smer;

public class TransPerzDetach {

	public static void main(String[] args) {

		// proba rada sa stanjima objekta u Hibernetu
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		
		// Stanje objekta u Hibernate: Transient, Persistent, Detached
		
		// Transient:
		Smer smer = new Smer();
		smer.setNazivSmera("Tandaramandara");
		System.out.println("Transient objekat - naziv = " + smer.getNazivSmera());
		
		// Persistent
		Session sesija = factory.openSession();
		Transaction tx = sesija.beginTransaction();
		int idd=0;
		try {
			idd = (int) sesija.save(smer);	// objekat smer smo asocirali sa sesijom
			System.out.println("Smer " + smer.getNazivSmera() + " sacuvan pod id = " + idd);
			// nakon sto je objekat smer asociran sa bazom, menjamo ime naziva
			smer.setNazivSmera("JavaScript"); 
			// ova izmena se reflektuje i na bazu tako sto Hibernate pusta SQL UPDATE naredbu
			System.out.println("Izmena naziva smera = " + smer.getNazivSmera());
			// Svaka izmena na perzistentnom objektu se azurira i u bazi
			tx.commit();
			System.out.println("Commit...");
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			tx.rollback();
		} finally {
			sesija.close();
			System.out.println("Close session...\n");
		}
		
		// Detached
		System.out.println("Sada je smer objekat " + smer.getNazivSmera() + " detachovan");
		// Sada bilo koja izmena na njemu se NECE odraziti na bazu
		smer.setNazivSmera("xyz");
		System.out.println("Izmenili smo detachovan smer objekat " + smer.getNazivSmera());
		System.out.println("Ali u bazi NIJE izvrsena ova izmena, sve dok ponovo ne otvorimo novu sesiju");
    	sesija = factory.openSession();	// otvaramo novu sesiju
		tx = sesija.beginTransaction();
		try {
			sesija.update(smer);
			System.out.println("Smer " + smer.getNazivSmera() + " je u novoj sesiji updateovan i u bazi");
			tx.commit();
			System.out.println("Commit...");
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			tx.rollback();
		} finally {
			sesija.close();
			System.out.println("Close session...\n");
		}
		
		// izvlacimo objekat iz baze pod id = idd
		// ponovo asociramo objekat sa sesijom pa je on perzistentan
		sesija = factory.openSession();
		tx = sesija.beginTransaction();
		try {
			smer = sesija.get(Smer.class, idd);
			System.out.println("Izvucen objekat iz baze idSmer=" + smer.getIdSmer() + " , nazivSmera=" + smer.getNazivSmera());
			smer.setNazivSmera("Zlatibor");
			System.out.println("Izmenio sam naziv perzistentnog objekta u " + smer.getNazivSmera());
			System.out.println("i automatski je azuriran sadrzaj u bazi");
			tx.commit();
			System.out.println("Commit...");
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			tx.rollback();
		} finally {
			sesija.close();
			System.out.println("Close session...\n");
		}
		
		
		// brisanje objekata iz baze/tabele
		sesija = factory.openSession();
		tx = sesija.beginTransaction();
		try {
			smer = sesija.get(Smer.class, idd);
			System.out.println("Izvucen objekat iz baze idSmer=" + smer.getIdSmer() + " , nazivSmera=" + smer.getNazivSmera());
			sesija.delete(smer);				// brisemo objekat iz baze
			System.out.println("Brisemo objekat naziv=" + smer.getNazivSmera());
			// pa je sada objekat prebacen iz perzistentnog u transietno stanje
			smer.setNazivSmera("Divcibare");	// menjamo naziv u transietnom objektu
			System.out.println("Izmenio sam naziv transietnog objekta u " + smer.getNazivSmera());
			tx.commit();
			System.out.println("Commit...");
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			tx.rollback();
		} finally {
			sesija.close();
			System.out.println("Close session...\n");
		}

	}

}
