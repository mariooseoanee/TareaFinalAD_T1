package _02;

import java.io.Serializable;

public class Pais implements Serializable{
	
	private String nombre;
	private String presidente;
	private Long pib;
	private Double coeficienteDeGini;
	
    public Pais(String nombre, String presidente, Long pib, Double coeficienteDeGini) {
        this.nombre = nombre;
        this.presidente = presidente;
        this.pib = pib;
        this.coeficienteDeGini = coeficienteDeGini;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresidente() {
        return presidente;
    }
    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public Long getPib() {
        return pib;
    }
    public void setPib(Long pib) {
        this.pib = pib;
    }

    public Double getCoeficienteDeGini() {
        return coeficienteDeGini;
    }
    public void setCoeficienteDeGini(Double coeficienteDeGini) {
        this.coeficienteDeGini = coeficienteDeGini;
    }

    @Override
    public String toString() {
        return "Pais [" +
                "nombre ='" + nombre +
                ", presidente ='" + presidente +
                ", pib =" + pib +
                ", coeficienteDeGini =" + coeficienteDeGini +"]";
    }
}