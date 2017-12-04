package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import dao.KisiDao;
import dao.TelefonDao;
import model.Kisi;
import model.Telefon;

@ManagedBean(name = "Bean", eager = true)
@RequestScoped
public class AnaBean {
	@ManagedProperty(value = "#{viev}")
	private ViewBean viev;
	@EJB
	KisiDao kisiDaoImpl;
	@EJB
	TelefonDao telefonDaoImpl;
	private Kisi kisi = new Kisi();
	private Telefon telefon = new Telefon();
	private List<Kisi> kisiListe;
	private boolean deger;
	private String gorunurluk="hidden";
	private String gorunurlukk="visible";

	public AnaBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Kisi getKisi() {
		return kisi;
	}

	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}

	public Telefon getTelefon() {
		return telefon;
	}

	public void setTelefon(Telefon telefon) {
		this.telefon = telefon;
	}

	public List<Kisi> getKisiListe() {
		return kisiListe;
	}

	public void setKisiListe(List<Kisi> kisiListe) {
		this.kisiListe = kisiListe;
	}

	public boolean isDeger() {
		return deger;
	}

	public void setDeger(boolean deger) {
		this.deger = deger;
	}

	public ViewBean getViev() {
		return viev;
	}

	public void setViev(ViewBean viev) {
		this.viev = viev;
	}
	
	

	public String getGorunurluk() {
		return gorunurluk;
	}

	public void setGorunurluk(String gorunurluk) {
		this.gorunurluk = gorunurluk;
	}
	
	

	public String getGorunurlukk() {
		return gorunurlukk;
	}

	public void setGorunurlukk(String gorunurlukk) {
		this.gorunurlukk = gorunurlukk;
	}

	@PostConstruct
	public void liste() {
		kisiListe = kisiDaoImpl.kisiListe();
	}

	public void kisiEkle() {
		if (!kayitKontrol()) {
			System.out.println("Kayıt");
			kisiDaoImpl.kisiEkle(kisi);
			liste();
			RequestContext.getCurrentInstance().update("kisi");
			RequestContext.getCurrentInstance().update("form");
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bu Kayıt Daha Önce Yapıldı", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void kisiSil(int id) {
		kisiDaoImpl.kisiSil(id);
		liste();
		RequestContext.getCurrentInstance().update("form");
	}

	public String kisiDuzenle() {
		kisiDaoImpl.kisiDuzenle(kisi, 2);
		liste();
		RequestContext.getCurrentInstance().update("kisi");
		RequestContext.getCurrentInstance().update("form");
		return null;
	}

	/*
	 * public void kisiBul() { kisiDaoImpl.kisiBul(kisi.getId()); }
	 */

	public boolean kayitKontrol() {
		return kisiDaoImpl.kayitKontrol(kisi.getAd(), kisi.getSoyad(), kisi.getMail());
	}

	public void telefonEkle() {
		deger = telefonDaoImpl.kayitKontrol(telefon.getNumara(), viev.getId());
		System.out.println(deger);

		if (deger) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bu Kayıt Daha Önce Yapıldı", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			System.out.println(viev.getId());
			kisi = kisiDaoImpl.kisiBul(viev.getId());
			kisi.addTelefon(telefon);
			kisiDaoImpl.kisiDuzenle(kisi, 1);
			liste();
			System.out.println(kisi.getTelefons().size());
			RequestContext.getCurrentInstance().update("telefon");
			RequestContext.getCurrentInstance().update("form");
		}
	}

	public void telefonSil(int kisiId, int telefonId) {
		kisi = kisiDaoImpl.kisiBul(kisiId);
		telefon = telefonDaoImpl.telefonBul(telefonId);
		kisi.removeTelefon(telefon);
		kisiDaoImpl.kisiDuzenle(kisi, 1);
		telefonDaoImpl.telefonSil(telefonId);
		liste();
		RequestContext.getCurrentInstance().update("form");
	}

	public void telefonDuzenle() {
		kisi = kisiDaoImpl.kisiBul(viev.getId());
		kisi.removeTelefon(telefon);
		kisi.addTelefon(telefon);
		kisiDaoImpl.kisiDuzenle(kisi, 1);
		telefon.setKisi(kisi);
		telefonDaoImpl.telefonDuzenle(telefon);
		liste();
		RequestContext.getCurrentInstance().update("telefon");
		RequestContext.getCurrentInstance().update("form");
	}

	public void buttonAction(ActionEvent actionEvent) {
		viev.setId((int) actionEvent.getComponent().getAttributes().get("Id"));
	gorunurluk="visibile";
	gorunurlukk="hidden";
	}
	public void degisim(ActionEvent action) {
		gorunurluk="visibile";
		gorunurlukk="hidden";
	}

}
