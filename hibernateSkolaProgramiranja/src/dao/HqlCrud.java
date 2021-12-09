package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Student;

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
			String hql = "select cenaZaSamofinansirajuce from SmerDetails "
					+ "where minimalanBrojPoenaZaUpis <= :poeni";
			// 2. napravi Query objekat
			Query query = session.createQuery(hql);
			// 3. setovanje parametara
			query.setParameter("poeni", brPoena);
			// 4. vrati rezultate iz baze
			listaCena = query.getResultList();  // moze i da se castuje (List<Double>)

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
	
	
	

}
