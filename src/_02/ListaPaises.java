package _02;

import java.util.ArrayList;
import java.util.List;

public class ListaPaises {
	private List<Pais> paises;

	public ListaPaises() {
		this.paises = new ArrayList<>();
	}

	public void addPais(Pais pais) {
		paises.add(pais);
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	@Override
	public String toString() {
		System.out.println("ListaPaises:");
		for (Pais pais : paises) {
			System.out.println(pais);
		}
	    return "";
	}


}
