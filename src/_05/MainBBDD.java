package _05;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;


import _02.ListaPaises;
import _02.Pais;
public class MainBBDD {

	public static void main(String[] args) throws SQLSyntaxErrorException, SQLException, FileNotFoundException {
		
		BaseDatosDAO dao = new BaseDatosDAO();
		
		// cojemos los datos del XML 
		ListaPaises listaPaises = new ListaPaises().leerXMLconXStream("paises.xml");
		
		dao.crearTablaPaises();
		
		
		for (Pais pais : listaPaises.getPaises()) {
			dao.insertarPais(pais);
		}
		
		dao.mostrarTablaPaises();
		
		
		// ejemplo de busqueda (que no existe)
		Pais paisBusqueda = new Pais("China", "Xi Jinping",  16418615L, 46.7);
		System.out.println("Existe el país introducido en la BBDD: " + dao.existePais(paisBusqueda));
		
		
		// ejemplo de insertar otro pais
		Pais paisEjemplo = new Pais("Argelia", "Abdelmadjid Tebboune",  221957L,  27.6);
		dao.insertarPais(paisEjemplo);
		
		dao.mostrarTablaPaises();
				
		// ejemplo de borrar pais
		dao.eliminarPais(paisEjemplo);
		
		dao.mostrarTablaPaises();
		
		// todos los paises incrementan su PIB
		dao.incrementoPIB();
		
		dao.mostrarTablaPaises();
		
		// solo los paises Mexico, Honduras y El Salvador disminuyen su coeficiente de gini (recorremos todos los paises de la bbdd)
		for (Pais pais : listaPaises.getPaises()) {
			dao.reduccionCoGini(pais);
		}
		
		
		dao.mostrarTablaPaises();
		
	}

}
