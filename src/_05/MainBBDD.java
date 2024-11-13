package _05;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import _02.Pais;

public class MainBBDD {

	public static void main(String[] args) throws SQLSyntaxErrorException, SQLException {
		
		BaseDatosDAO dao = new BaseDatosDAO();

		List<Pais> lista = new ArrayList<Pais>();
		// preguntar a manu como accedemos a los datos de la lista de paises del XML
		lista.add(new Pais("Argentina", "Alberto Fernandez", 450000L, 40.0));
		lista.add(new Pais("Brasil", "Lula da Silva", 2200000L, 50.0));
		
		dao.crearTablaPaises();
		
		for (Pais pais : lista) {
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
		
	}

}
