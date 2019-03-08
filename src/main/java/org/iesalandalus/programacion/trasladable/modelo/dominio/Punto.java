package org.iesalandalus.programacion.trasladable.modelo.dominio;

import java.util.Objects;

public class Punto implements Trasladable {

	private double x;
	private double y;
	
	public Punto(double x, double y) {
		setX(x);
		setY(y);
	}
	
	public Punto(Punto punto) {
		if (punto == null) {
			throw new NullPointerException("No puedo copiar un punto nulo.");
		}
		setX(punto.x);
		setY(punto.y);
	}
	
	private void setX(double x) {
		this.x = x;
	}
	
	private void setY(double y) {
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	@Override
	public void trasladar(double dx, double dy) {
		x = x + dx;
		y = y + dy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Punto)) {
			return false;
		}
		Punto other = (Punto) obj;
		return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
	}

	@Override
	public String toString() {
		return String.format("Punto [x=%s, y=%s]", x, y);
	}
	
}
