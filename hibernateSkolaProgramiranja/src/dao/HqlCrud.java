package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;

import model.Student;
import model.TipUsera;
import model.User;

// ********************** 07.12.21 *************************

public class HqlCrud {

	SessionFactory factory = new Configuration().configure().buildSessionFactory();

	public List<Student> vratiStudentaPoBrojuIndexa(String brIndexa) {

		List<Student> listaStudenata = new ArrayList<>();

		Session session = factory.openSession();
		session.beginTransaction();

		try {

			// 1. napisi upit
			String hql = "from Student where brojIndexa = :bridx";
			// 2. napravi Query objekat
			Query query = session.createQuery(hql);
			// 3. setovanje parametara
			query.setParameter("bridx", brIndexa);
			// 4. vrati rezultate iz baze
			listaStudenata = (List<Student>) query.getResultList(); // a moze i da se castuje u List<Student>

			System.out.println("Sve ok, vracam studente..");
			session.getTransaction().commit();
			return listaStudenata;

		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Puklo je u metodi vratiStudentaPoBrojuIndexa");
			return null;
		} finally {
			session.close();
		}
	}

	public List<Double> vratiCeneNaOsnovuPoena(Double brPoena) {

		List<Double> listaCena = new ArrayList<>();

		Session session = factory.openSession();
		session.beginTransaction();

		try {

			// 1. napisi upit
			String hql = "SELECT cenaZaSamofinansirajuce FROM SmerDetails "
					+ "WHERE minimalanBrojPoenaZaUpis <= :poeni";
			// 2. napravi Query objekat
			Query query = session.createQuery(hql);
			// 3. setovanje parametara
			query.setParameter("poeni", brPoena);
			// 4. vrati rezultate iz baze
			listaCena = query.getResultList(); // moze i da se castuje (List<Double>)

			System.out.println("Sve ok, vracam cene..");
			session.getTransaction().commit();
			return listaCena;

		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Puklo je u metodi vratiCeneNaOsnovuPoena");
			return null;
		} finally {
			session.close();
		}
	}

	// ********************** 09.12.21 *************************

	// Domaci za 09.12.21
	public List<Student> vratiStudenteIzOdredjenogGrada(String grad) {

		// Vraca listu studenata kojima je u adresi polje grad = zadatom imenu grada

		List<Student> listaStudenata = new ArrayList<>();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "FROM Student WHERE adresa.grad = :imeGrada"; // interesantno da adresa.grad prolazi!

		try {
			Query query = session.createQuery(hql);
			query.setParameter("imeGrada", grad);
			listaStudenata = query.getResultList();
			System.out.println("Sve je u redu, vracam studente");
			tx.commit();
			return listaStudenata;
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Nesto je puklo u metodi vratiStudenteIzOdredjenogGrada");
			System.out.println("Exception greska : " + e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public void ubaciUsera(String username, String password, TipUsera tip) {

		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		user.setTip(tip);

		Session session = factory.openSession();
		session.beginTransaction();
		try {
			session.save(user);
			System.out.println("Ubacen user " + user.getUserName());
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Nesto je puklo u ubaciUsera");
		} finally {
			session.close();
		}
	}

	public User vratiUsera(String userName, String password) {

		User user = new User();

		Session session = factory.openSession();
		session.beginTransaction();
		try {
			String hql = "FROM User WHERE userName = :un AND password = :pass";
			Query query = session.createQuery(hql);
			query.setParameter("un", userName);
			query.setParameter("pass", password);

			user = (User) query.getSingleResult();

			System.out.println("Sve je ok sa userom...");
			session.getTransaction().commit();
			return user;
		} catch (NoResultException e) {
			System.out.println("Nema rezultata jer nisam uspeo da pronadjem usera / " + e.getMessage());
			session.getTransaction().rollback();
			return user;
		} catch (NonUniqueResultException e) {
			System.out.println("Ne mogu vratiti jedinstveni rezultat. Ima ih vise... / " + e.getMessage());
			session.getTransaction().rollback();
			return null;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Nesto je puklo u vratiUsera /" + e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<String> vratiSveUserNameove() {

		List<String> userNames = new ArrayList<>();

		Session session = factory.openSession();
		session.beginTransaction();

		try {

			// 1. napisi upit
			String sql = "SELECT user_name FROM users"; // ovde navodimo tabele i kolone
			// 2. napravi Query objekat
			Query query = session.createNativeQuery(sql);
			// 3. setovanje parametara
//			query.setParameter("poeni", brPoena);
			// 4. vrati rezultate iz baze
			userNames = query.getResultList();

			System.out.println("Sve ok, vracam usernames..");
			session.getTransaction().commit();
			return userNames;

		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Puklo je u metodi vratiSveUserNameove");
			return null;
		} finally {
			session.close();
		}
	}

	// ******* DOMACI za 14.12.21 **********************

	public List<Integer> vratiSveTipoveUsera_SQL() {

		List<Integer> listaTipova = new ArrayList<>();

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "SELECT tip_usera FROM users"; // obracamo se tabeli i njenim kolonama

		try {
			Query query = session.createNativeQuery(sql);
			listaTipova = query.getResultList();
			System.out.println("Sve je OK, vracam listu Integer tipova usera...");
			tx.commit();
			return listaTipova;
		} catch (Exception e) {
			System.out.println("Nesto je puklo u vratiSveTipoveUsera_SQL / " + e.getMessage());
			tx.rollback();
			return null;
		} finally {
			session.close();
		}
	}

	public List<TipUsera> vratiSveTipoveUsera_HQL() {

		List<TipUsera> listaTipova = new ArrayList<>();

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "SELECT tip FROM User"; // obracamo se entitetu/klasi User i njenim poljima

		try {
			Query query = session.createQuery(hql);
			listaTipova = query.getResultList();
			System.out.println("Sve je OK, vracam listu Enum tipova usera...");
			tx.commit();
			return listaTipova;
		} catch (Exception e) {
			System.out.println("Nesto je puklo u vratiSveTipoveUsera_HQL / " + e.getMessage());
			tx.rollback();
			return null;
		} finally {
			session.close();
		}
	}
	
	
	

}
