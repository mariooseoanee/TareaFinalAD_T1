package _04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import _02.ListaPaises;
import _02.Pais;

public class FicheroBinario {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ListaPaises listaPaises = new ListaPaises().leerXMLconXStream("paises.xml");
		
		FileOutputStream escritor = new FileOutputStream("Paises.dat");
		ObjectOutputStream escritorObjetos = new ObjectOutputStream(escritor);
		
		FileInputStream lector = new FileInputStream("Paises.dat");
		ObjectInputStream lectorObjetos = new ObjectInputStream(lector);
		
	
		for (Pais pais : listaPaises.getPaises()) {
			escritorObjetos.writeObject(pais);
		}
		escritorObjetos.close();	
		
		for (Pais pais : listaPaises.getPaises()) {
			System.out.println(lectorObjetos.readObject());
		}
	}
}
