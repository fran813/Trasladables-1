package org.iesalandalus.programacion.trasladable.modelo.dominio;

import java.io.Serializable;

public interface Trasladable extends Serializable {

	public void trasladar(double x, double y);
	
	public int hashCode();
	
	public boolean equals(Object obj);
	
	public String toString();
}
