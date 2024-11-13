package _02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

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

	public void imprimirListaPaises() {
		System.out.println("ListaPaises:");
		for (Pais pais : paises) {
			System.out.println(pais);
		}
	}

	/* el tipo de coleccion que vamos a añadir (la clase propietaria, el nombre del
	 * atributo List<Pais>, y la clase del tipo de objeto que guarda la lista)
	 * 
	 * Realmente el metodo hace lo mismo que la clase LecturaXMLconXStream, pero al crear este metodo
	 * en la clase ListaPaises, podemos reutilizarlo para la insercion de estos datos en la BBDD
	 */
	public ListaPaises leerXMLconXStream(String rutaArchivo) throws FileNotFoundException {
		FileInputStream archivoXML = new FileInputStream(rutaArchivo);
		XStream xs = new XStream();
		xs.addPermission(NoTypePermission.NONE);
		xs.addPermission(NullPermission.NULL);
		xs.addPermission(PrimitiveTypePermission.PRIMITIVES);
		xs.allowTypes(new Class[] { ListaPaises.class, Pais.class });
		xs.alias("Paises", ListaPaises.class);
		xs.alias("Pais", Pais.class);
		xs.addImplicitCollection(ListaPaises.class, "paises", Pais.class);

		return (ListaPaises) xs.fromXML(archivoXML);
	}

}
