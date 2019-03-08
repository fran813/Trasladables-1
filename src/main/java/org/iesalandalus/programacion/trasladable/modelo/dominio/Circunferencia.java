package org.iesalandalus.programacion.trasladable.modelo.dominio;

import java.util.Objects;

public class Circunferencia implements Trasladable {
	
	private Punto centro;
	private double radio;
	
	public Circunferencia(Punto centro, double radio) {
		setCentro(centro);
		setRadio(radio);
	}
	
	public Circunferencia(Circunferencia circunferencia) {
		if (circunferencia == null) {
			throw new NullPointerException("No puedo copiar una circunferencia nula.");
		}
		setCentro(circunferencia.centro);
		setRadio(circunferencia.radio);
	}
	
	private void setCentro(Punto centro) {
		this.centro = new Punto(centro);
	}
	
	private void setRadio(double radio) {
		this.radio = radio;
	}
	
	public Punto getCentro() {
		return new Punto(centro);
	}
	
	public double getRadio() {
		return radio;
	}

	@Override
	public void trasladar(double dx, double dy) {
		centro.trasladar(dx, dy);
	}

	@Override
	public int hashCode() {
		return Objects.hash(centro, radio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Circunferencia)) {
			return false;
		}
		Circunferencia other = (Circunferencia) obj;
		return Objects.equals(centro, other.centro)
				&& Double.doubleToLongBits(radio) == Double.doubleToLongBits(other.radio);
	}

	@Override
	public String toString() {
		return String.format("Circunferencia [centro=%s, radio=%s]", centro, radio);
	}

}
