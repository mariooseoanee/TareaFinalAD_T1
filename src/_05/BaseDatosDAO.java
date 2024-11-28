package _05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

import _02.Pais;

// hemos decidido utilizar HSQL
public class BaseDatosDAO {

	public void crearTablaPaises() throws SQLException, SQLSyntaxErrorException {
		String url = "jdbc:hsqldb:file:./BDPaisesHSQL";
		Connection conexion = DriverManager.getConnection(url);
		PreparedStatement sentencia;

		sentencia = conexion.prepareStatement("CREATE TABLE IF NOT EXISTS PAISES (NOMBRE VARCHAR(30) PRIMARY KEY, "
				+ "PRESIDENTE VARCHAR(50), PIB BIGINT, COEFICIENTEGINI DOUBLE)");
		sentencia.execute();
		conexion.close();
		System.out.println("BBDD creada correctamente");
	}

	public boolean existePais(Pais pais) throws SQLException {
		String url = "jdbc:hsqldb:file:./BDPaisesHSQL";
		Connection conexion = DriverManager.getConnection(url);
		PreparedStatement sentencia;
		ResultSet resultado;

		sentencia = conexion.prepareStatement("SELECT * FROM PAISES WHERE NOMBRE = ?");
		sentencia.setString(1, pais.getNombre());
		resultado = sentencia.executeQuery();
		if (resultado.next()) {
			conexion.close();
			return true;
		} else {
			conexion.close();
			return false;
		}
	}

	public void insertarPais(Pais pais) throws SQLException {
		String url = "jdbc:hsqldb:file:./BDPaisesHSQL";
		Connection conexion = DriverManager.getConnection(url);
		PreparedStatement sentencia;

		if (!existePais(pais)) {
			sentencia = conexion.prepareStatement("INSERT INTO PAISES VALUES (?, ?, ? ,?)");
			sentencia.setString(1, pais.getNombre());
			sentencia.setString(2, pais.getPresidente());
			sentencia.setFloat(3, pais.getPib());
			sentencia.setDouble(4, pais.getCoeficienteDeGini());

			sentencia.execute();
			System.out.println("País insertado correctamente: " + pais.getNombre());
		} else {
			System.out.println("No se ha podido insertar el pais " + pais.getNombre() + " (Primary Key REPETIDA)");
		}
		conexion.close();
	}

	public void eliminarPais(Pais pais) throws SQLException {
		String url = "jdbc:hsqldb:file:./BDPaisesHSQL";
		Connection conexion = DriverManager.getConnection(url);
		PreparedStatement sentencia;

		if (existePais(pais)) {
			sentencia = conexion.prepareStatement("DELETE FROM PAISES WHERE NOMBRE = ?");
			sentencia.setString(1, pais.getNombre());

			sentencia.execute();
			System.out.println("El país ha sido eliminado correctamente: " + pais.getNombre());
		} else {
			System.out.println("El país que intentas eliminar no existe en la BBDD");
		}
		conexion.close();
	}

	public void mostrarTablaPaises() throws SQLException {
		String url = "jdbc:hsqldb:file:./BDPaisesHSQL";
		Connection conexion = DriverManager.getConnection(url);
		PreparedStatement sentencia;
		ResultSet resultado;

		sentencia = conexion.prepareStatement("SELECT * FROM PAISES");
		System.out.println("BBDD:");
		resultado = sentencia.executeQuery();

		boolean hayDatos = false;
		while (resultado.next()) {
			System.out.println("NOMBRE: " + resultado.getString("NOMBRE") + "  - PRESIDENTE: "
					+ resultado.getString("PRESIDENTE") + "  - PIB: " + resultado.getLong("PIB")
					+ "  - COEFICIENTE DE GINI: " + resultado.getDouble("COEFICIENTEGINI"));
			hayDatos = true;
		}

		if (!hayDatos) {
			System.out.println("La tabla PAISES está vacía.");
		}
		System.out.println("-------------------------------------------------------------------------------");

		conexion.close();
	}

	public void incrementoPIB() throws SQLException {
		String url = "jdbc:hsqldb:file:./BDPaisesHSQL";
		Connection conexion = DriverManager.getConnection(url);
		PreparedStatement sentencia;

		sentencia = conexion.prepareStatement("UPDATE PAISES SET PIB = PIB + 10000000");
		sentencia.executeUpdate();
		
		System.out.println("Todos los países han incrementado su PIB en 10 millones");

		conexion.close();

	}

	public void reduccionCoGini(Pais pais) throws SQLException {
		String url = "jdbc:hsqldb:file:./BDPaisesHSQL";
		Connection conexion = DriverManager.getConnection(url);
		PreparedStatement sentencia;
		
		String[] paisesReducen = {"México", "Honduras", "El Salvador"};
		
		for (int i = 0; i < paisesReducen.length; i++) {
	
			if (pais.getNombre().equals(paisesReducen[i])) {
				
				sentencia = conexion.prepareStatement("UPDATE PAISES SET COEFICIENTEGINI = COEFICIENTEGINI * 2/3 "
						+ "WHERE NOMBRE = ?");
				sentencia.setString(1, pais.getNombre());
				sentencia.executeUpdate();
				System.out.println("El pais " + pais.getNombre() + " ha reducido su Coeficiente de Gini en 1/3");
			}
			
		}
		conexion.close();


		
	}

}
