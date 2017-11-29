package dao;

import java.util.List;

import javax.ejb.Local;

import model.Kisi;

@Local
public interface KisiDao {
	
	public void kisiEkle(Kisi kisiE);
	public void kisiSil(int id);
	public void kisiDuzenle(Kisi kisiD,int sira);
	public List<Kisi> kisiListe();
	public Kisi kisiBul(int id);
	public boolean kayitKontrol(String ad,String soyad,String mail);
}
