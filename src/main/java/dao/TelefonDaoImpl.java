package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Telefon;

/**
 * Session Bean implementation class TelefonDaoImpl
 */
@Stateless
@LocalBean
public class TelefonDaoImpl implements TelefonDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public TelefonDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void telefonEkle(Telefon telefonE) {
		// TODO Auto-generated method stub
		entityManager.persist(telefonE);

	}

	@Override
	public void telefonSil(int id) {
		// TODO Auto-generated method stub
		entityManager.remove(telefonBul(id));

	}

	@Override
	public void telefonDuzenle(Telefon telefonD) {
		// TODO Auto-generated method stub
		entityManager.merge(telefonD);
	}

	@Override
	public Telefon telefonBul(int id) {
		// TODO Auto-generated method stub
		TypedQuery<Telefon> query = entityManager.createQuery("select t from Telefon t where t.id=:id", Telefon.class)
				.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public List<Telefon> telefonListe() {
		// TODO Auto-generated method stub
		TypedQuery<Telefon> query = entityManager.createQuery("select t from Telefon t", Telefon.class);
		return query.getResultList();
	}

	@Override
	public boolean kayitKontrol(String numara,int k_id) {
		// TODO Auto-generated method stub
		Query query = entityManager
				.createQuery("select count(k) from Kisi k  join k.telefons t where t.numara=:numara and k.id=:id")
				.setParameter("numara", numara).setParameter("id", k_id);
		if ((Long) query.getSingleResult() > 0)
			return true;
		else
			return false;
	}

}
