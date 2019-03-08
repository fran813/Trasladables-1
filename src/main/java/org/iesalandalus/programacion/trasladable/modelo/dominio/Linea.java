package org.iesalandalus.programacion.trasladable.modelo.dominio;

import java.util.Objects;

public class Linea implements Trasladable {
	
	private Punto p1;
	private Punto p2;
	
	public Linea(Punto p1, Punto p2) {
		setP1(p1);
		setP2(p2);
	}
	
	public Linea(Linea linea) {
		if (linea == null) {
			throw new NullPointerException("No puedo copiar una línea nula.");
		}
		setP1(linea.p1);
		setP2(linea.p2);
	}
	
	private void setP1(Punto p1) {
		this.p1 = new Punto(p1);
	}
	
	private void setP2(Punto p2) {
		this.p2 = new Punto(p2);
	}
	
	public Punto getP1() {
		return new Punto(p1);
	}
	
	public Punto getP2() {
		return new Punto(p2);
	}

	@Override
	public void trasladar(double dx, double dy) {
		p1.trasladar(dx, dy);
		p2.trasladar(dx, dy);
	}

	@Override
	public int hashCode() {
		return Objects.hash(p1, p2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Linea)) {
			return false;
		}
		Linea other = (Linea) obj;
		return Objects.equals(p1, other.p1) && Objects.equals(p2, other.p2);
	}

	@Override
	public String toString() {
		return String.format("Linea [p1=%s, p2=%s]", p1, p2);
	}

}
