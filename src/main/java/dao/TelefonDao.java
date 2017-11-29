package dao;

import java.util.List;

import javax.ejb.Local;

import model.Telefon;

@Local
public interface TelefonDao {
	public void telefonEkle(Telefon  telefonE);
	public void telefonSil(int id);
	public void telefonDuzenle(Telefon telefonD);
	public Telefon telefonBul(int id);
	public List<Telefon> telefonListe();
	public boolean kayitKontrol(String numara,int k_id);

}
