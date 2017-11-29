package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Kisi;

/**
 * Session Bean implementation class KisiDao
 */
@Stateless
@LocalBean
public class KisiDaoImpl implements KisiDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public KisiDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void kisiEkle(Kisi kisiE) {
		// TODO Auto-generated method stub
		entityManager.persist(kisiE);
	}

	@Override
	public void kisiSil(int id) {
		// TODO Auto-generated method stub
		entityManager.remove(kisiBul(id));

	}

	@Override
	public void kisiDuzenle(Kisi kisiD,int sira) {
		// TODO Auto-generated method stub
		// 
		if(sira==1)
			entityManager.merge(kisiD);
		else {
			Query query = entityManager
					.createQuery("Update Kisi SET ad=:ad,soyad=:soyad,mail=:mail,adres=:adres where id=" + kisiD.getId());
			int update = query.setParameter("ad", kisiD.getAd()).setParameter("soyad", kisiD.getSoyad()).setParameter("mail", kisiD.getMail()).setParameter("adres", kisiD.getAdres()).executeUpdate();
			
		}
		
	}

	@Override
	public List<Kisi> kisiListe() {
		// TODO Auto-generated method stub
		TypedQuery<Kisi> query = entityManager.createQuery("select k from Kisi k", Kisi.class);
		return query.getResultList();
	}

	@Override
	public Kisi kisiBul(int id) {
		// TODO Auto-generated method stub
		// TypedQuery<Kisi> query=entityManager.createQuery("select k from Kisi k where
		// k.id=:id",Kisi.class).setParameter("id", id);
		// return query.getSingleResult();
		return entityManager.find(Kisi.class, id);
	}

	@Override
	public boolean kayitKontrol(String ad, String soyad, String mail) {
		// TODO Auto-generated method stub
		Query query = entityManager
				.createQuery("select count(k) from Kisi k where k.ad=:ad and k.soyad=:soyad and k.mail=:mail")
				.setParameter("ad", ad).setParameter("soyad", soyad).setParameter("mail", mail);
		if ((Long) query.getSingleResult() > 0)
			return true;
		else
			return false;
	}

}
