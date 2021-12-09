package controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dao.CrudDao;
import dao.HqlCrud;
import model.Adresa;
import model.Finansije;
import model.Smer;
import model.Student;
import java.util.ArrayList;
import java.util.List;
import model.Contact;

public class HibernateController {

	public static void main(String[] args) {

//		CrudDao dao = new CrudDao();

//		dao.insertSmer("Python");

//		Smer smerIzBaze = dao.vratiSmerPoId(3);
//		if (smerIzBaze == null) {
//			System.out.println("Ne postoji taj id!");
//		} else {
//			System.out.println("id: " + smerIzBaze.getIdSmer());
//			System.out.println("Naziv: " + smerIzBaze.getNazivSmera());
//		}
//		smerIzBaze.setNazivSmera("C#");
//		System.out.println("Novi naziv smera = " + smerIzBaze.getNazivSmera());

//		boolean uradiUpdateNazivSmera = dao.updateNazivSmera("Java", 1);
//		if (uradiUpdateNazivSmera) {
//			System.out.println("Uradjen update...");
//		} else {
//			System.out.println("Nije uspeo update!");
//		}

//		if (dao.deleteSmer(2)) {
//			System.out.println("Uradjen delete...");
//		} else {
//			System.out.println("Nije uspeo delete!");
//		}
//		

		// ******************** 16.11.2021 *************************

		// insert Student
//		dao.insertStudent("Pera", "Peric", "AB123", "Srbija", "Beograd", "Kneza Milosa 21", "11000", Finansije.BUDZET);
//		dao.insertStudent("Nenad", "Nenadovic", "NM123", "Srbija", "Beograd", "Kneza Mihajla", "11000", Finansije.SAMOFINANSIRANJE);

//		Student student = dao.vratiStudentaPoId(2);
//		System.out.println("Ime: " + student.getIme());
//		Adresa adresa = student.getAdresa();
//		System.out.println("Drzava = " + adresa.getDrzava());
//		System.out.println("Grad = " + adresa.getGrad());
//		System.out.println("Ulica = " + adresa.getUlica());
//		System.out.println("PB = " + adresa.getPostanskiBroj());
//		System.out.println("Finansije = " + student.getFinansije());

		// **************************** 18.11.2021 ************************

//		Student s = dao.vratiStudentaPoId(1);
//		System.out.println("Ulica = " + s.getAdresa().getUlica()); // kroz adresu vadimo ulicu

//		Adresa adresa = new Adresa();
//			adresa.setDrzava("Srbija");
//			adresa.setGrad("Novi Sad");
//			adresa.setUlica("Zmaj Jovina 3");
//			adresa.setPostanskiBroj("21000");
//		
//		Contact kontakt1 = new Contact();
//			kontakt1.setEmail("prviMail@gmail.com");
//			kontakt1.setFiksniTelefon("021333444");
//			kontakt1.setMobilniTelefon("064111222");
//
//		Contact kontakt2 = new Contact();
//			kontakt2.setEmail("drugiMail@gmail.com");
//			kontakt2.setFiksniTelefon("021111444");
//			kontakt2.setMobilniTelefon("061333222");	
//		List<Contact> kontakti = new ArrayList<Contact>();
//			kontakti.add(kontakt1);
//			kontakti.add(kontakt2);
//			
//		String imeStudenta = "Lala";
//		String prezimeStudenta = "Lalic";
//		String brojIndexa = "987ER";
//		Finansije finansije = Finansije.POLAPOLA;
//		
//		dao.insertStudent(imeStudenta, prezimeStudenta, brojIndexa, adresa, finansije, kontakti);

//		Student student = dao.vratiStudentaPoId(5);
//		System.out.println("Ime: " + student.getIme());
//		System.out.println("Grad: " + student.getAdresa().getGrad());
//		System.out.println("Status: " + student.getFinansije());
//		
//		for (Contact c : student.getContact()) {
//			System.out.print("E-mail = " + c.getEmail() + "\t");
//			System.out.print("Mobilni telefon = " + c.getMobilniTelefon() + "\t");
//			System.out.println("Fiksni telefon = " + c.getFiksniTelefon());
//		}

//		dao.insertSmerDetails(3, "Ovo je smer za Python", 93.4, 140000);

//		dao.spojiStudentaISmer(1, 4);
//		dao.spojiStudentaISmer(5, 1);

//		dao.insertPredmet("Baze podataka", 5);
//		dao.insertPredmet("Osnovi programiranja", 10);
//		dao.spojiPredmetISmer(1, 3);

//		dao.insertSmerDetails(4, "C# smer", 90, 75000);

//		dao.insertPredavac("Pera", "Peric", 10, 2);
//		dao.insertPredavac("Nenad", "Nenadovic", 5, 3);
//		dao.insertPredavac("Milan", "Milanovic", 21, 6);
//		dao.insertPredavac("Ana", "Anic", 5, 2);

		// ********************* 07.12.2021 *******************

		HqlCrud crud = new HqlCrud();

//		List<Student> listaStudenata = crud.vratiStudentaPoBrojuIndexa("234NM");
//
//		if (listaStudenata.isEmpty()) {
//			System.out.println("Ne postoji broj indexa");
//		} else {
//			for (Student s : listaStudenata) {
//				System.out.println("Id: " + s.getIdStudent());
//				System.out.println("Ime: " + s.getIme());
//				System.out.println("Prezime: " + s.getPrezime());
//				System.out.println("Grad: " + s.getAdresa().getGrad());
//				System.out.println("Finansiranje: " + s.getFinansije());
//				System.out.println("Smer: " + s.getSmer().getNazivSmera());
//				System.out.println("==================================");
//			}
//		}
		
		List<Double> cene = crud.vratiCeneNaOsnovuPoena(80.0);
		System.out.println("Cene u ponudi:");
		for (Double c : cene) {
			System.out.println(c);
		}
		
		
		

	}

}
