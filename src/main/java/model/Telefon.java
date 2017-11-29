package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Telefon")
public class Telefon implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String tur;
	private String numara;
	@ManyToOne
	@JoinColumn(name="K_ID")
	private Kisi kisi;
	public Telefon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Telefon(String tur, String numara) {
		super();
		this.tur = tur;
		this.numara = numara;
	}
	public String getTur() {
		return tur;
	}
	public void setTur(String tur) {
		this.tur = tur;
	}
	public String getNumara() {
		return numara;
	}
	public void setNumara(String numara) {
		this.numara = numara;
	}
	public Kisi getKisi() {
		return kisi;
	}
	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
		if (!(obj instanceof Telefon)) {
			return false;
		}
		Telefon other = (Telefon) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
	

}
