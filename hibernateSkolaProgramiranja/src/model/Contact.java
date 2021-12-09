package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Contact {

	@Column (name = "E_MAIL")
	private String email;
	@Column (name = "MOB_TELEFON")
	private String mobilniTelefon;
	@Column (name = "FIX_TELEFON")
	private String fiksniTelefon;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobilniTelefon() {
		return mobilniTelefon;
	}
	public void setMobilniTelefon(String mobilniTelefon) {
		this.mobilniTelefon = mobilniTelefon;
	}
	public String getFiksniTelefon() {
		return fiksniTelefon;
	}
	public void setFiksniTelefon(String fiksniTelefon) {
		this.fiksniTelefon = fiksniTelefon;
	}
	
	
	
}
