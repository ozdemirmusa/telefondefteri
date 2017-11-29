package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="viev",eager = true)
@SessionScoped
public class ViewBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ViewBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int id;

	public int getId() {
		System.out.println(id);
		return id;
	}
	public void setId(int id) {
		this.id = id;
		System.out.println("viewBean Id değeri:"+id);
	}
	
	

	
	
	 
	

}
