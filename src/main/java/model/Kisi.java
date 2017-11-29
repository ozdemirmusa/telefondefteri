package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Kisi")
public class Kisi  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String ad;
	private String soyad;
	private String mail;
	private String adres;
	@OneToMany(mappedBy="kisi",cascade=CascadeType.ALL)
	private List<Telefon> telefons;
	public Kisi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Kisi(String ad, String soyad, String mail, String adres) {
		super();
		this.ad = ad;
		this.soyad = soyad;
		this.mail = mail;
		this.adres = adres;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSoyad() {
		return soyad;
	}
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public List<Telefon> getTelefons() {
		return telefons;
	}
	public void setTelefons(List<Telefon> telefons) {
		this.telefons = telefons;
	}
	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public Telefon addTelefon(Telefon telefon) {
		getTelefons().add(telefon);
		telefon.setKisi(this);

		return telefon;
	}

	public Telefon removeTelefon(Telefon telefon) {
		getTelefons().remove(telefon);
		telefon.setKisi(this);

		return telefon;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Kisi)) {
			return false;
		}
		Kisi other = (Kisi) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
}
