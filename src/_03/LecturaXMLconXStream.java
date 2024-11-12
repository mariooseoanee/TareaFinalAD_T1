package _03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import _02.ListaPaises;
import _02.Pais;

public class LecturaXMLconXStream {
	public static void main(String[] args) throws FileNotFoundException {
		FileInputStream archivoXML = new FileInputStream("paises.xml");

		XStream xs = new XStream();
		xs.addPermission(NoTypePermission.NONE);
		xs.addPermission(NullPermission.NULL);
		xs.addPermission(PrimitiveTypePermission.PRIMITIVES);
		
		// especificamos las clases permitidas
		Class[] clasesPermitidas = { ListaPaises.class, Pais.class };
		xs.allowTypes(clasesPermitidas);

		xs.alias("Paises", ListaPaises.class);
		xs.alias("Pais", Pais.class);

		// el tipo de coleccion que vamos a añadir (la clase propietaria, el nombre del
		// atributo List<Pais>, y la clase del tipo de objeto que guarda la lista)
		xs.addImplicitCollection(ListaPaises.class, "paises", Pais.class);

		ListaPaises lista = new ListaPaises();
		lista = (ListaPaises) xs.fromXML(archivoXML);

		lista.imprimirListaPaises();
	}
}
